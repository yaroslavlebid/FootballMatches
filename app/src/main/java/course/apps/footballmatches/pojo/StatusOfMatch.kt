package course.apps.footballmatches.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class StatusOfMatch(
    @SerializedName("long")
    @Expose
    val _long: String? = null,

    @SerializedName("short")
    @Expose
    val _short: String? = null,

    @SerializedName("elapsed")
    @Expose
    val elapsed: Int? = null,
)
