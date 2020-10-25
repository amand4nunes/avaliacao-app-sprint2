package com.example.vamos_lucrar.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.prova2.conexao.RetrofitClient
import com.example.prova2.conexao.User
import com.example.prova2.conexao.UserService
import com.example.vamos_lucrar.R
import com.example.vamos_lucrar.utils.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaContatosActivity : AppCompatActivity() {
    val remote = RetrofitClient.createService(UserService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_contatos)


        val listaContatos = remote.getContatos()
        listaContatos.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                val listView = findViewById<ListView>(R.id.listView)
                val list = mutableListOf<User>()

                response.body()?.forEach {
                    list.add(
                        User(
                            it.id,
                            it.nome,
                            it.email,
                            it.senha,
                            it.telefone,
                            R.drawable.contato
                        )
                    )
                    listView.adapter = MyAdapter(this@ListaContatosActivity, R.layout.row, list)

                }
            }


            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(applicationContext, "erro ao listar contatos", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }
}
