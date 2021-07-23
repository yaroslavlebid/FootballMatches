package course.apps.footballmatches.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class TeamsInfo(
    @SerializedName("home")
    @Expose
    val home: Team? = null,

    @SerializedName("away")
    @Expose
    val away: Team? = null
)
