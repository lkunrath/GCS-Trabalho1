package src.Entidades;

import Enums.TipoUsuario;
import Models.Usuario;

import java.util.ArrayList;

public class Medico extends Usuario {

    private ArrayList<Exame> autorizacoes;

    public Medico(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
        autorizacoes = new ArrayList<>();
    }

    public boolean adicionarAutorizacao(Exame e) {
        return autorizacoes.add(e);
    }

    public ArrayList<Exame> getAutorizacoes() {
        return new ArrayList<>(autorizacoes);
    }
}
