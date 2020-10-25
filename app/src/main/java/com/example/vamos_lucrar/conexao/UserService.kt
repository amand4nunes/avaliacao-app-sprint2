package com.example.prova2.conexao

import retrofit2.Call
import retrofit2.http.*

interface UserService {


    @GET("user")
    fun getContatos(): Call<List<User>>
    @GET("user/{id}")
    fun getContato(@Path("id") id:Int): Call<User>

    @POST("user")
    fun postContato(@Body novoFilme:User): Call<Void>

    @DELETE("user/{id}")
    fun deleteContato(@Path("id") id: Int): Call<Void>
}