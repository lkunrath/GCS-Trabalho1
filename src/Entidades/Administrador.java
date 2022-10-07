
package src.Entidades;
import src.Enums.TipoUsuario;
import src.Models.Usuario;

public class Administrador extends Usuario {

    public String[] listaPac;
    public String[] listaMed;
    private int tamanho;
    
    public Administrador(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
        
        this.tamanho=0;
    }
    
    public boolean buscaPaciente(String nome) {
        for (int i=0; i<this.tamanho;i++) {
            if(this.listaPac[i].equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean buscaMedico(String nome) {
        for (int i=0; i<this.tamanho;i++) {
            if(this.listaMed[i].equals(nome)) {
                return true;
            }
        }
        return false;
    }
    
    



}
