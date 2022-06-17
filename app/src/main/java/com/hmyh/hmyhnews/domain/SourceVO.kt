package com.hmyh.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceVO(

    var id: String?=null,

    var name: String?=null

):Parcelable