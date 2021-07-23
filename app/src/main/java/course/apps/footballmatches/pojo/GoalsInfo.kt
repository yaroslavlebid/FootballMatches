package course.apps.footballmatches.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class GoalsInfo(
    @SerializedName("home")
    @Expose
    val home: Int? = null,

    @SerializedName("away")
    @Expose
    val away: Int? = null
)
