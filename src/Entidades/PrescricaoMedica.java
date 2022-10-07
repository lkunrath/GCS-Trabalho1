package Entidades;

public class PrescricaoMedica {
    private final String nome;
    
    public PrescricaoMedica(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }
}
