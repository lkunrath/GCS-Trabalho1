package src.Entidades;

import src.Enums.TipoUsuario;
import src.Models.Usuario;

public ArrayList<Paciente> ListaPac;

public class Paciente extends Usuario {
    public Paciente(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }
}
