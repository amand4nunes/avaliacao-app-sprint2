package com.example.vamos_lucrar.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.prova2.conexao.RetrofitClient

import com.example.prova2.conexao.User
import com.example.prova2.conexao.UserService
import com.example.vamos_lucrar.R
import com.example.vamos_lucrar.views.DetalhesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdapter(var contexto: Context, var resouces: Int, var itens: List<User>) :
    ArrayAdapter<User>(contexto, resouces, itens) {

    val remote = RetrofitClient.createService(UserService::class.java)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(contexto)
        val view: View = layoutInflater.inflate(resouces, null)

        val imageView: ImageView = view.findViewById(R.id.image)
        val titleTextView: TextView = view.findViewById(R.id.textView)
        val descriptionTextView: TextView = view.findViewById(R.id.textView2)
        val deletar: ImageView = view.findViewById(R.id.deletar)
        val mais: ImageView = view.findViewById(R.id.mais)

        var mItem: User = itens[position]

        imageView.setImageDrawable(mItem.img?.let { contexto.resources.getDrawable(it) })
        titleTextView.text = mItem.nome
        descriptionTextView.text = mItem.email

        deletar.setOnClickListener(View.OnClickListener {
            remove(itens[position])
        })
        mais.setOnClickListener(View.OnClickListener {
            mItem.id?.let { it1 -> detalhesItem(it1) }
        })

        return view
    }

    fun remove(contato: User) {

        val deletarContato = contato.id?.let { remote.deleteContato(it) }
        if (deletarContato != null) {
            deletarContato.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(
                        contexto, "Deletado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(
                        contexto, "Erro ao deletar",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }

        super.remove(contato)
    }

    fun detalhesItem(position: Int) {
        SharedPreferences(contexto).storeId("id", position)
       contexto.startActivity(Intent(contexto, DetalhesActivity::class.java))

    }

}






