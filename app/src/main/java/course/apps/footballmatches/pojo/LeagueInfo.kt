package course.apps.footballmatches.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class LeagueInfo
(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("country")
    @Expose
    val country: String? = null,

    @SerializedName("logo")
    @Expose
    val logoImg: String? = null,

    @SerializedName("flag")
    @Expose
    val flagImg: String? = null,

    @SerializedName("season")
    @Expose
    val season: Int? = null,

    @SerializedName("round")
    @Expose
    val round: String? = null
)
