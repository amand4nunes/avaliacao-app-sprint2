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
import kotlinx.android.synthetic.main.activity_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {
    val remote = RetrofitClient.createService(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }


    fun novoContato( v: View ){

    val novoContato = User(
          null,
          nome_et.text.toString(),
          email_et.text.toString(),
          senha_et.text.toString(),
          telefone_et.text.toString(),
           null

      )



      val callNovoContato = remote.postContato(novoContato)

      callNovoContato.enqueue(object: Callback<Void> {
          override fun onFailure(call: Call<Void>, t: Throwable) {
              Toast.makeText(baseContext, "Erro: $t", Toast.LENGTH_SHORT).show()
          }

          override fun onResponse(call: Call<Void>, response: Response<Void>) {
              Toast.makeText(baseContext,
                  getString(R.string.sucesso_cad),
                  Toast.LENGTH_SHORT).show()
          }
      })}

    fun listaDeContato(v: View) {
        val contatos = Intent(applicationContext, ListaContatosActivity::class.java)
        startActivity(contatos)
    }

}