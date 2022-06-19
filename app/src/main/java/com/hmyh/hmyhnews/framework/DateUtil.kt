package com.hmyh.hmyhnews.framework

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_YYY_MM_DD="yyyy-MM-dd'T'HH:mm:ss"
const val FORMAT_DD_MMMM_YYY="dd MMMM, yyyy"

//yyyy-MM-dd'T'HH:mm:ss

fun getApiDateTime(inputDate: String?,format: String =FORMAT_DD_MMMM_YYY): String{

    if (inputDate == null){
        return ""
    }
    else{
        try {
            val simpleDateFormat = SimpleDateFormat(FORMAT_YYY_MM_DD, Locale.US)
            val date = simpleDateFormat.parse(inputDate)

            val formatShowData = SimpleDateFormat(format, Locale.US)
            return formatShowData.format(date)
        }catch (e: ParseException){
            return ""
        }
    }
}