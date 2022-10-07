package Entidades;

import Enums.TipoUsuario;
import Models.Usuario;

public class Medico extends Usuario {
    public Medico(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }
}
