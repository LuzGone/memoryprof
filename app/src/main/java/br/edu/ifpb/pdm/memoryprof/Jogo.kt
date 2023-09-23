package br.edu.ifpb.pdm.memoryprof

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

    fun jogada (palpite1:Int,palpite2:Int): Boolean{
        if (this.tentativas<0){
            return false
        }
        if(palpite1 == palpite2){
            this.pontos+=10;
            if (this.pontos == 80){
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

    fun reiniciar(){
        this.tentativas = TENTATIVAS;
        this.professores.shuffle();
        this.pontos = 0;
        this.status = "JOGANDO";
    }

    fun getProfessores():Array<Int>{
        return this.professores.clone();
    }

}