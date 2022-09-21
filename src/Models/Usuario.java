package src.Models;

import src.Enums.TipoUsuario;

public abstract class Usuario {

    private static int id = 0;
    private String nome;
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, TipoUsuario tipoUsuario) {
        id++;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        System.out.println(id);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
