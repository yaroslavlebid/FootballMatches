package course.apps.footballmatches.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class FixtureInfo(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("referee")
    @Expose
    val referee: String? = null,

    @SerializedName("timezone")
    @Expose
    val timezone: String? = null,

    @SerializedName("date")
    @Expose
    val date: String? = null,

    @SerializedName("timestamp")
    @Expose
    val timestamp: Int? = null,

    @SerializedName("status")
    @Expose
    val status: StatusOfMatch? = null
)
