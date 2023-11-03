package com.app.harho.network

import android.util.Log
import android.widget.Toast
import com.app.harho.utils.SharedPreference
import com.app.harho.base.MyApplication
import com.app.harho.utils.Utility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ClientInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalBuilder: Request = chain.request()
        val response = originalBuilder.newBuilder()
        val token = SharedPreference.getInstance(MyApplication.getContext())
            .getString(SharedPreference.Key.TOKEN, "")
        Log.d(token, "intercept$token")
        if (Utility.isNetworkConnected(MyApplication.getContext())) {
            response.addHeader("Accept", "application/json")
            response.addHeader("Content-Type", "text/json; charset=utf-8")
            response.addHeader("Authorization", "Bearer $token").build()
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(MyApplication.getContext(), "No Internet", Toast.LENGTH_LONG).show()
                return@launch
            }
        }
        val request = response.build()
        return chain.proceed(request)

    }
}