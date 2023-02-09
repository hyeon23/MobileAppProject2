package com.example.mobileprogrammingteam5

data class Comment(val starNum: Float, val info: String){
    constructor():this(0.0.toFloat(), "noinfo")
}
