package br.edu.ifpb.pdm.memoryprof

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.children

class MainActivity : AppCompatActivity() {
    private lateinit var tabuleiro:GridLayout;
    private lateinit var barrinha:ProgressBar;
    private lateinit var pontuacao:TextView;
    private lateinit var tentativas:TextView;
    private lateinit var botao: Button;

    private var jogo = Jogo();
    private var cartasViradas = mutableListOf<ImageView>();

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tabuleiro = findViewById(R.id.tabuleiro)
        this.barrinha = findViewById(R.id.barrinha)
        this.pontuacao = findViewById(R.id.pontuacao)
        this.tentativas = findViewById(R.id.tentativas)
        this.botao = findViewById(R.id.botao)


        this.atualizarTentativas()
        this.atualizarPontuacao()
        preencherTabuleiro()


        object : CountDownTimer(7000, 70) {
            override fun onTick(millisUntilFinished: Long) {
                barrinha.progress += 1
            }
            override fun onFinish() {
                virarTabuleiro()
                botao.setOnClickListener({reiniciarTabuleiro()})
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        reiniciarTabuleiro()
    }

    fun preencherTabuleiro(){
        for (professor in this.jogo.getProfessores()){
            val cartinha = ImageView(this);
            this.tabuleiro.addView(cartinha);
            cartinha.setImageResource(professor);
            cartinha.layoutParams.height = 240;
            cartinha.layoutParams.width = 240;
            cartinha.tag = professor;
        }
    }

    fun virarTabuleiro(){
        for (carta in this.tabuleiro.children){
            var cartinha = carta as ImageView
            cartinha.setImageResource(R.drawable.versocarta)
            cartinha.setOnClickListener({ virarCartinha(cartinha) })
        }
    }

    fun virarCartinha(cartinha: ImageView){
        cartinha.setImageResource(cartinha.tag as Int)
        cartinha.setOnClickListener({})
        this.cartasViradas.add(cartinha)
        if(this.cartasViradas.size==2){
            if(!this.jogo.jogada(this.cartasViradas)){
                val cartinhas = this.cartasViradas.toMutableList()
                object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }
                    override fun onFinish() {
                        desvirarCartinhas(cartinhas)
                    }
                }.start()
            }
            this.atualizarPontuacao()
            this.atualizarTentativas()
            this.cartasViradas.clear()
            this.isFimDeJogo()
        }
    }



    fun desvirarCartinhas(cartinhas:MutableList<ImageView>){
        for (cartinha in cartinhas){
            cartinha.setImageResource(R.drawable.versocarta)
            cartinha.setOnClickListener({ virarCartinha(cartinha) })
        }
    }

    fun atualizarPontuacao(){
        this.pontuacao.setText("Pontuação: ${this.jogo.getPontuacao()}")
    }

    fun atualizarTentativas(){
        this.tentativas.setText("Tentativas: ${this.jogo.getTentativas()}")
    }

    fun isFimDeJogo(){
        if(this.jogo.getStatus() == "VENCEU"){
            val intent = Intent(this,WinActivity::class.java)
            startActivity(intent)
        }
        else if (this.jogo.getStatus() == "PERDEU"){
            val intent = Intent(this,LoseActivity::class.java)
            startActivity(intent)
        }
    }

    fun reiniciarTabuleiro(){
        this.jogo.reiniciar()
        this.atualizarTentativas()
        this.atualizarPontuacao()
        this.barrinha.progress = 0
        this.tabuleiro.removeAllViews()

        preencherTabuleiro()
        this.botao.setOnClickListener({})

        object : CountDownTimer(7000, 70) {
            override fun onTick(millisUntilFinished: Long) {
                barrinha.progress += 1
            }
            override fun onFinish() {
                virarTabuleiro()
                botao.setOnClickListener({reiniciarTabuleiro()})
            }
        }.start()
    }
}