package src.Entidades;

import src.Enums.TipoUsuario;
import src.Models.Usuario;

public class Medico extends Usuario {

    public Medico(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }
}
