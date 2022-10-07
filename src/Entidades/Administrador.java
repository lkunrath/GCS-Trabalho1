
package Entidades;
import Enums.TipoUsuario;
import Models.Usuario;

public class Administrador extends Usuario {

    public Administrador(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }
    
}