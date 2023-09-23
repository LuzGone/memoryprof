package br.edu.ifpb.pdm.memoryprof

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var tabuleiro:GridLayout;
    private var jogo = Jogo();
    private var cartasViradas = mutableListOf<ImageView>();
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
            cartinha.setImageResource(R.drawable.versocarta);
            cartinha.layoutParams.height = 240;
            cartinha.layoutParams.width = 240;
            cartinha.tag = professor;
            cartinha.setOnClickListener({ virarCartinha(cartinha) })
        }
    }

    fun virarCartinha(cartinha: ImageView){
        if(this.cartasViradas.size<1){
            cartinha.setImageResource(cartinha.tag as Int)
            this.cartasViradas.add(cartinha)
        }else{
            this.cartasViradas.add(cartinha)
            if (!this.jogo.jogada(this.cartasViradas[0].tag as Int,this.cartasViradas[1].tag as Int)){
                this.cartasViradas[0].setImageResource(R.drawable.versocarta)
                this.cartasViradas[1].setImageResource(R.drawable.versocarta)
            }
            this.cartasViradas.clear()
        }
    }
}