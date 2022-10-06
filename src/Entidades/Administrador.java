package src.Entidades;

import Enums.TipoUsuario;
import src.Models.Usuario;

public class Administrador extends Usuario {

    public Administrador(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }
}
