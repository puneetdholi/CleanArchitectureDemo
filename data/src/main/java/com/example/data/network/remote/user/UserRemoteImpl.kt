package com.example.data.network.remote.user


import android.content.Context
import android.util.Log
import com.example.data.entity.UserEntity
import com.example.data.network.service.UserService
import io.reactivex.Observable
import java.io.IOException
import java.net.InetAddress
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteImpl @Inject constructor(
    val context: Context,
    val userService: UserService
) : UserRemote {

    override fun getAllUsers(): Observable<List<UserEntity>> {
        return userService.getAllUsers().map {
            if (it.isSuccessful) {
                return@map it.body()
            } else {
                throw Exception("Something went wrong: error ${it.code()}")
            }
        }.map {
            return@map it
        }
    }

    /*
        ====
        BELOW MENTIONED METHOD(S) IS FOR CHECKING THE
        INTERNET CONNECTION. API CALLS PROCEED ONLY IF
        THESE METHODS RESPOND TRUE.
        ====
        */
    fun isInternetAvailable(): Boolean {
        try {
            val ipAddr = InetAddress.getByName("google.com")
            //You can replace it with your name
            Log.e("InterNet", "Working smoothly")
            return !ipAddr.equals("")
        } catch (e: Exception) {
            Log.e("InterNet", "DEAD!!!")
            e.printStackTrace()
            return false
        }
    }

    // ==== Pings a server and checks if internet is connected ====
    fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 www.google.com")
            val exitValue = ipProcess.waitFor()
            Log.e("NETWORK", "CHECK BEGINS")
            Log.e("Connection", (exitValue == 0).toString())
            return exitValue == 0
        } catch (e: IOException) {
            Log.e("InterNet", "DEAD!!!")
            e.printStackTrace()
        } catch (e: InterruptedException) {
            Log.e("InterNet", "DEAD!!!")
            e.printStackTrace()
        }
        return false
    }
}

