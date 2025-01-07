package com.vicentcodes.pepitoarrive.data.model

import com.squareup.moshi.Json

/**
 * Json Event "pepito":
 * {"event":"pepito","type":"in","time":1725714575,"img":"https://..."}
 */
data class PepitoEvent(
    @Json(name = "event") val event: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "time") val time: Long?,
    @Json(name = "img") val img: String?
)