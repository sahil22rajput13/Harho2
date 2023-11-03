package com.app.harho.base

import android.annotation.SuppressLint
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.Window
import android.widget.ProgressBar
import com.app.harho.R
import com.app.harho.network.SocketNetworking
import io.socket.client.Socket
import java.util.Objects

class MyApplication : Application() {
    companion object {
        var mLoader: Dialog? = null
        var socket: Socket? = null

        var mLoadingView: ProgressBar? = null


        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }


        fun connectSocket() {
            socket = SocketNetworking.getSocket()
            socket?.connect()
            try {
                socket?.on(io.socket.client.Socket.EVENT_CONNECT) {
                    Log.d("applicationsocket", "socket connected ")
                }
                socket?.on(io.socket.client.Socket.EVENT_CONNECT_ERROR) {
                    Log.d("applicationsocket", "socket error ")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun showLoader(context: Context) {
            try {
                if (mLoader != null) {
                    mLoader!!.dismiss()
                    mLoader!!.cancel()
                }
                if (mLoadingView != null) {
                    //   mLoadingView!!.hide()
                }
                mLoader = Dialog(context)
                mLoader!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                mLoader!!.setContentView(R.layout.layout_progrzess_dialog)
                Objects.requireNonNull(mLoader!!.window)
                    ?.setBackgroundDrawable(
                        ColorDrawable(
                            Color.TRANSPARENT
                        )
                    )
                mLoader!!.setCancelable(false)
                mLoadingView = mLoader!!.findViewById(R.id.loadingView)
                //mLoadingView!!.show()
                mLoader?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun hideLoader() {
            if (mLoader != null) {
                mLoader!!.dismiss()
                mLoader!!.cancel()
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}