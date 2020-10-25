package com.example.vamos_lucrar.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.prova2.conexao.RetrofitClient
import com.example.prova2.conexao.User
import com.example.prova2.conexao.UserService
import com.example.vamos_lucrar.R
import com.example.vamos_lucrar.utils.SharedPreferences
import kotlinx.android.synthetic.main.activity_detalhes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesActivity : AppCompatActivity() {
    val remote = RetrofitClient.createService(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val id = SharedPreferences(this).getId("id")

        val detalhesContato = remote.getContato(id)


        detalhesContato.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val contato = response.body()
                valueNome.text = contato?.nome
                valueEmail.text = contato?.email
                valueSenha.text = contato?.senha
                valueTelefone.text = contato?.telefone

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(
                    this@DetalhesActivity,
                    "Erro ao carregar informações do contato",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
    fun voltar(v: View){
     startActivity(Intent(this, ListaContatosActivity::class.java))
    }
}