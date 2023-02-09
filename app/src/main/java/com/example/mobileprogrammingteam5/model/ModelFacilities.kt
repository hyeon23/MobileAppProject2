package com.example.mobileprogrammingteam5.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

//필요시 주석제거해서 사용하세요.
data class ModelFacilities(
    var buildingName: String? = "",
    val Facilities: Map<String, ModelFacility>? = null
//    val buildingNum : Int? = 0,
)

data class ModelFacility(
    val buildingName: String? = "",
    var facilityName: String? = "",
    var like: Boolean? = false,
//    val location: String? = "",
//    val phoneNum: String? = "",
//    val useTime: String? = "",
//    val comments: Map<String, Comment>? = null
) : Serializable

//data class Comment(
//    val info: String? ="",
//    val starNum: Double? = 0.0
//)


data class ModelBookmark (
    val buildingName : String?,
    val facilityInfo : List<ModelFacility>
) : Serializable





