package com.phonezoo.network

import BasicAuth
import android.content.Context
import com.phonezoo.R
import com.phonezoo.model.Device
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import android.util.Log


interface ApiService {
    @GET("devices.json")
    suspend fun getDevices(): List<Device>

    companion object {
        var apiService: ApiService? = null
        fun getInstance(context: Context): ApiService {
            val username: String = context.getString(R.string.username)
            val password: String = context.getString(R.string.password)
            Log.d("CREDS", username)
            Log.d("CREDS", password)
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
                .addInterceptor(
                    BasicAuth
                        (username, password)
                )
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api-cloud.browserstack.com/app-automate/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}