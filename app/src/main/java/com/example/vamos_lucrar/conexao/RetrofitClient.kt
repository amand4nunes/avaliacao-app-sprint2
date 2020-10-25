package com.example.prova2.conexao

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient private constructor() {

    companion object {
        private lateinit var retrofit: Retrofit
        private val baseUrl ="https://5f923985eca67c0016409457.mockapi.io/"


        private fun getRetrofitInstance(): Retrofit {
            //gerencia as comunicações com chamadas http, é ele que faz a conexão com a internet
            val httpClient = OkHttpClient.Builder()
            //se não tiver inciada será iniciada
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())  //é quem gerencia as conexoes http
                    .addConverterFactory(GsonConverterFactory.create()) //converter dados json em dados kotlin
                    .build()
            }


            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S { //função generica para criar qualquer serviço
            return getRetrofitInstance().create(serviceClass)

        }

    }
}