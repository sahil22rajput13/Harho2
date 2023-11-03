package com.app.harho.base

import com.app.harho.utils.SharedPreference

object GetObjects {
    val preference = SharedPreference.getInstance(MyApplication.mContext)
}