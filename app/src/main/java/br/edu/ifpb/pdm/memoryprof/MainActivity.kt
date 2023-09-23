package br.edu.ifpb.pdm.memoryprof

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var tabuleiro:GridLayout;
    private var jogo = Jogo();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tabuleiro = findViewById(R.id.tabuleiro)
        this.preencherTabuleiro()

    }

    fun preencherTabuleiro(){
        for (professor in this.jogo.getProfessores()){
            val cartinha = ImageView(this);
            this.tabuleiro.addView(cartinha);
            cartinha.setImageResource(professor);
            cartinha.layoutParams.height = 240;
            cartinha.layoutParams.width = 240;
        }
    }
}