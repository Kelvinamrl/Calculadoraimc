package com.comunidadedevspace.imc

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.ContextCompat

const val KEY_RESULT_IMC = "ResultActivity.KEY_IMC"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val result = intent.getFloatExtra(KEY_RESULT_IMC, 0.0f)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvMensagem = findViewById<TextView>(R.id.tv_mensagem)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)
        tvResult.text = result.toString()

        var classificacao: String? = null
        var mensagem: String? = null

        if (result <= 18.5f) {
            classificacao = "ABAIXO DO PESO"
            mensagem = "Seu IMC indica abaixo do peso. Que tal consultar um profissional para avaliar sua saúde?"
        } else if (result > 18.6f && result <= 24.9f) {
            classificacao = "NORMAL"
            mensagem = "Parabéns! Seu IMC está normal, continue cuidando da sua saúde!"
        } else if (result > 25f && result <= 29.9f) {
            classificacao = "SOBREPESO"
            mensagem = "Seu IMC indica sobrepeso. Uma rotina equilibrada pode ajudar a melhorar!"
        } else if (result > 30f && result <= 39.9f) {
            classificacao = "OBESIDADE I"
            mensagem = "Seu IMC indica obesidade grau I. Considere buscar orientação médica para um plano saudável."
        } else {
            classificacao = "OBESIDADE II"
            mensagem = "Seu IMC indica obesidade grau II. É importante buscar apoio médico para cuidar de você."
        }

        tvClassificacao.text = classificacao
        tvMensagem.text = mensagem

        val cor = when (classificacao) {
            "ABAIXO DO PESO" -> R.color.blue
            "NORMAL" -> R.color.green
            "SOBREPESO" -> R.color.yellow
            "OBESIDADE I" -> R.color.orange
            else -> R.color.red
        }
        tvClassificacao.setTextColor(ContextCompat.getColor(this, cor))

        val imagem = when (classificacao) {
            "ABAIXO DO PESO" -> R.drawable.abaixo
            "NORMAL" -> R.drawable.normal
            "SOBREPESO" -> R.drawable.acima
            "OBESIDADE I" -> R.drawable.obesidade
            else -> R.drawable.obesidade_2
        }

        val imagemView = findViewById<ImageView>(R.id.resultado_imagem)
        imagemView.setImageResource(imagem)


    }
}