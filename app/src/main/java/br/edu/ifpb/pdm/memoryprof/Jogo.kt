package br.edu.ifpb.pdm.memoryprof

import android.widget.ImageView

val TENTATIVAS = 10;

class Jogo {
    private var professores  = arrayOf(
        R.drawable.alex,R.drawable.alex,
        R.drawable.alexandre,R.drawable.alexandre,
        R.drawable.damires,R.drawable.damires,
        R.drawable.fausto,R.drawable.fausto,
        R.drawable.fred,R.drawable.fred,
        R.drawable.gustavo,R.drawable.gustavo,
        R.drawable.maxwell,R.drawable.maxwell,
        R.drawable.valeria,R.drawable.valeria);
    private var tentativas = TENTATIVAS;
    private var pontos = 0;
    private var status = "NOVO JOGO";

    constructor(){
        this.professores.shuffle();
        this.status = "JOGANDO";
    }

    fun jogada (palpites:MutableList<ImageView>): Boolean{
        val palpite1 = palpites[0].tag as Int
        val palpite2 = palpites[1].tag as Int
        if (this.status == "JOGANDO"){
            if(palpite1 == palpite2){
                this.pontos+=1000;
                if (this.pontos == 8000){
                    this.status = "VENCEU";
                }
                return true
            }else{
                this.tentativas-=1;
                if(this.tentativas<=0){
                    this.status = "PERDEU";
                }
                return false
            }
        }
        return false
    }

    fun reiniciar(){
        this.tentativas = TENTATIVAS;
        this.professores.shuffle();
        this.pontos = 0;
        this.status = "JOGANDO";
    }

    fun getProfessores():Array<Int>{
        return this.professores.clone();
    }

    fun getPontuacao():Int{
        return this.pontos
    }

    fun getTentativas():Int{
        return this.tentativas
    }

    fun getStatus():String{
        return this.status
    }

}