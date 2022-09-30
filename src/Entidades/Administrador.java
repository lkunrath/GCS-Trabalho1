package src.Entidades;

import src.Enums.TipoUsuario;
import src.Models.Usuario;

public class Administrador extends Usuario {

    public Administrador(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }

public Paciente buscaPaciente(String nome, TipoUsuario tipoUsuario) {
    
    if(TipoUsuario != Medico) {
        return null;
    }
    else {
       for(int i=0; i<=ListaPac.size(); i++) {
        if (nomespac.get(i).getNomePac().equalsIgnoreCase(nome)) {
            listapac.get(i).getNomePac();
        }
       }
       return ListaPac;
    }

}

public Medico buscaPaciente(String nome, TipoUsuario tipoUsuario) {
    if(TipoUsuario != Medico) {
        return null;
    }
    else {
       for(int i=0; i<=ListaMed.size(); i++) {
        if (Listamed.get(i).getNomeMed().equalsIgnoreCase(nome)) {
            ListaMed.get(i).getNomeMed();
        }
        return ListaMed;
       }
    }
}

}
