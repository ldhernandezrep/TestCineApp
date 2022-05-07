package com.example.testcineapplication.core

import android.util.Log
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

object ConnectionInternet {

    suspend fun isInternetAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAdrres = InetSocketAddress("8.8.8.8", 53)
            //sock.connect(socketAdrres, 3000)
            //sock.close()
            true
        } catch (e: IOException) {
            Log.d("Error", e.message.toString())
            false
        }
    }
}