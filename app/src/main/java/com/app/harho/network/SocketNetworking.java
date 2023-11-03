package com.app.harho.network;


import android.app.Application;

import com.app.harho.BuildConfig;

import org.json.JSONException;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketNetworking extends Application {

    private static Socket socket;

    public static Socket getSocket() throws JSONException {
        if (socket == null) {
            try {
                socket = IO.socket(BuildConfig.BASE_URL);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        socket.connect();
        return socket;
    }
}
