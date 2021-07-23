package course.apps.footballmatches.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Team(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("logo")
    @Expose
    val logo: String? = null,

    @SerializedName("winner")
    @Expose
    val winner: Boolean? = null
)
