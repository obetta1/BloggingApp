package com.example.bloggingapp.model.network

import com.example.bloggingapp.utilities.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JsonClient {

    fun getJsonEndPoint(): JsonEndPoint{

        // HttpLogging for network inspection
        var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        //returns the retrofit builder which implement the jsonEndPoint
       return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(logging).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonEndPoint::class.java)
    }
}