package com.example.login.retrofit

import com.example.login.consts.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface Endpoint {

    @POST("user/signup")
    suspend fun signUp(
        @Body userData: UserData
    ): Response<SignUpResponse>

    @POST("user/login")
    suspend fun login(
        @Body loginData: loginData
    ): Response<SignUpResponse>

    companion object {
        private const val BASE_URL = url
        private var endpoint: Endpoint? = null

        fun createEndpoint(): Endpoint {
            if (endpoint == null) {
                synchronized(this) {
                    val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    endpoint = retrofit.create(Endpoint::class.java)
                    println(endpoint) ;
                }
            }
            return endpoint!!
        }
    }
}


data class SignUpData(
    val userId: Int,
    val firstName : String,
    val lastName: String,
    val email: String,
    val NUmeroTel : String,
    val motDePasse : String,
    val PhotoUser : String
)

data class SignUpResponse(
    val status: Int,
    val message: String,
    val data: SignUpData
)

data class UserData(
    val userId: Int,
    val firstName : String,
    val lastName: String,
    val email: String,
    val NUmeroTel : String,
    val motDePasse : String,
    val PhotoUser : String
)

data class loginData(
    val email: String,
    val motDePasse : String
)

