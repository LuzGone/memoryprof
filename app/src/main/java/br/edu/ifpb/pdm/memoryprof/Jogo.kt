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

    constructor(){
        this.professores.shuffle();
    }

    fun jogar (palpite1:Int,palpite2:Int): Boolean{
        if (this.tentativas<0){
            return false
        }
        if(professores[palpite1] == professores[palpite2]){
            this.pontos+=1;
            return true
        }else{
            this.tentativas-=1;
            return false
        }
    }

}