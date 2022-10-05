package Entidades;

import Enums.TipoUsuario;
import Models.Usuario;

import java.util.ArrayList;
import java.util.Collections;

public class Paciente extends Usuario {

    ArrayList<Autorizacao> autorizacoesPaciente =new ArrayList<>();
    public Paciente(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }

    public boolean cadastraAutorizacao(Autorizacao autorizacao){

        for (Autorizacao autorizacaoAux : Autorizacao.getTodasAutorizacoes()) {
            if (autorizacaoAux.getId() == autorizacao.getId()) return false;
        }

        Autorizacao.adicionaAutorizacao(autorizacao);
        return autorizacoesPaciente.add(autorizacao);
    }

    public ArrayList<Autorizacao> getAutorizacoesPaciente() {
        return (ArrayList<Autorizacao>)autorizacoesPaciente.clone();
    }

    //ordena um clone, da data mais antiga ate a mais recente
    public ArrayList<Autorizacao> ac() {
        ArrayList<Autorizacao> aux=getAutorizacoesPaciente();
        Collections.sort(aux);
        return aux;
    }
}
