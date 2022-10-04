package Entidades;

import Enums.TipoUsuario;
import Models.Usuario;

import java.util.ArrayList;

public class Paciente extends Usuario {

    ArrayList<Autorizacao> autorizacoesPaciente =new ArrayList<>();
    public Paciente(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }

    public boolean cadastraAutorizacao(Autorizacao autorizacao){
        for(Autorizacao autorizacaoAux:Autorizacao.getTodasAutorizacoes()){
            if(autorizacaoAux.getId()==autorizacao.getId())return false;
        }

        Autorizacao.adicionaAutorizacao(autorizacao);
        return autorizacoesPaciente.add(autorizacao);
    }


    public ArrayList<Autorizacao> listaAutorizacoesOrdenada(){
        Autorizacao aux = null;
        boolean troca = false;
        do{
            troca = false;
            for(int i = 0; i<autorizacoesPaciente.size()-1;i++)
            {
                if(autorizacoesPaciente.get(i).getData().after(autorizacoesPaciente.get(i+1).getData()))
                {
                    aux = autorizacoesPaciente.get(i);
                    autorizacoesPaciente.add(i,autorizacoesPaciente.get(i+1));
                    autorizacoesPaciente.add(i+1,aux);
                    troca = true;
                }
            }
        }while(troca);
        return (ArrayList<Autorizacao>)autorizacoesPaciente.clone();

    }
}
