package com.app.harho.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.CalendarContract.CalendarAlerts
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.lang.Exception
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Utility {
    companion object {
        fun dateYesterdayToday(date: String): Date? {
            val dateTimeSplit = date.split("")
            var calendar: Calendar? = null
            var calendar2: Calendar? = null
            try {
                val chatDate = dateTimeSplit[0]
                val chatTime = dateTimeSplit[1]
                calendar = Calendar.getInstance()
                val parseToDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(chatDate)
                val parseToTime = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(chatTime)
                calendar.timeInMillis = parseToDate.time
                calendar2 = Calendar.getInstance()
                calendar2.time = calendar.time
                calendar2.set(
                    2023,
                    calendar.time.month,
                    calendar.time.date,
                    parseToDate.hours,
                    parseToTime.minutes
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return calendar2?.time
        }

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            return cm?.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }

        fun getFormattedAmount(amount: String): String? {
            var convertedAmount = 0
            try {
                convertedAmount = amount.toInt()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return NumberFormat.getNumberInstance(Locale.US).format(convertedAmount)
        }

        fun openMap(context: Context, latitude: Double, longitude: Double) {
            val mapUri = Uri.parse("geo:0,0?q=$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)

        }

//        fun Context.setImage(ImageUrl: String, imageView: ImageView) {
//            Glide.with(this).load(BuildConfig.IMAGE_URL + ImageUrl).error(R.mipmap.person_black)
//                .into(imageView)
//        }

    }
}