package course.apps.footballmatches.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class MatchesInfoRAWData (
    @SerializedName("response")
    @Expose
    val matches: List<JsonObject>? = null
)