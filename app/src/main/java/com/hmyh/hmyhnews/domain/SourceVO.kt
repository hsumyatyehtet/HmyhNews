package com.hmyh.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceVO(

    @SerializedName("id")
    var id: String?=null,

    @SerializedName("name")
    var name: String?=null

):Parcelable