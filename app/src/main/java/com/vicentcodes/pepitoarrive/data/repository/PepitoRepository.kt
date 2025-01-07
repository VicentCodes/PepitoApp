package com.vicentcodes.pepitoarrive.data.repository

import com.vicentcodes.pepitoarrive.data.model.PepitoEvent


import android.util.Log
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class PepitoRepository(
    private val okHttpClient: OkHttpClient
) {

    private val _pepitoEvents = MutableSharedFlow<PepitoEvent>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val pepitoEvents: SharedFlow<PepitoEvent> = _pepitoEvents.asSharedFlow()

    private var eventSource: EventSource? = null

    fun startSSE() {
        val request = Request.Builder()
            .url("https://api.thecatdoor.com/sse/v1/events")
            .build()

        val factory = EventSources.createFactory(okHttpClient)
        eventSource = factory.newEventSource(request, object : EventSourceListener() {
            override fun onOpen(eventSource: EventSource, response: Response) {
                Log.d("PepitoRepository", "SSE Connection Opened")
            }

            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String
            ) {
                Log.d("PepitoRepository", "SSE onEvent: $data")
                try {
                    val json = JSONObject(data)
                    val event = json.optString("event")

                    if (event == "pepito") {
                        val pepitoEvent = PepitoEvent(
                            event = event,
                            type = json.optString("type"),
                            time = json.optLong("time"),
                            img = json.optString("img")
                        )
                        _pepitoEvents.tryEmit(pepitoEvent)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onClosed(eventSource: EventSource) {
                Log.d("PepitoRepository", "SSE Connection Closed")
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                Log.e("PepitoRepository", "SSE Connection Failed", t)
            }
        })
    }

    fun stopSSE() {
        eventSource?.cancel()
        eventSource = null
    }
}
