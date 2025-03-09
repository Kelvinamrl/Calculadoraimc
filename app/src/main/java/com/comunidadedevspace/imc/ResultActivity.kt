package com.comunidadedevspace.imc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_IMC = "ResultActivity.KEY_IMC"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        // Ajusta os paddings para respeitar as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Retorno correto para evitar erro de tipo
        }

        // Recupera o resultado enviado pela Intent
        val result = intent.getFloatExtra(KEY_RESULT_IMC, 0.0f)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)
        tvResult.text = result.toString()

        var classificacao: String? = null

       if(result <= 18.5f){
            classificacao = "MAGREZA"
        } else if(result > 18.5f && result <= 24.9f){
            classificacao = "NORMAL"
        } else if(result > 25f && result <= 29.9f){
            classificacao = "SOBREPESO"
        } else if(result > 30f && result <= 34.9f){
            classificacao = "OBESIDADE"
        } else if(result > 35f && result <= 39.9f) {
            classificacao = "OBESIDADE II"
        } else {
            classificacao = "OBESIDADE MÓRBIDA"
        }

        tvClassificacao.text = classificacao

        }
    }