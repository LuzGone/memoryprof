package br.edu.ifpb.pdm.memoryprof

val TENTATIVAS = 10;

class Jogo {
    private var professores  = arrayOf(
        "Valeria","Valeria",
        "Fausto","Fausto",
        "Alexandre","Alexandre",
        "Gustavo","Gustavo",
        "Maxwell","Maxwell",
        "Alex","Alex",
        "Fred","Fred",
        "Damires","Damires");
    private var tentativas = TENTATIVAS;
    private var pontos = 0;
    private var status = "NOVO JOGO";

    constructor(){
        this.professores.shuffle();
        this.status = "JOGANDO";
    }

    fun jogar (palpite1:Int,palpite2:Int): Boolean{
        if (this.tentativas<0){
            return false
        }
        if(professores[palpite1] == professores[palpite2]){
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

}