package src;

import src.Entidades.Administrador;
import src.Entidades.Medico;
import src.Entidades.Paciente;
import src.Enums.TipoUsuario;
import src.Models.Usuario;

import java.util.Scanner;

public class GCS {

    Scanner sc;

    public void executa() {
        Usuario usuarioAtual = selecionaUsuarioAtual(sc);
        if(usuarioAtual instanceof Medico){System.out.println("Logado como Medico");} //SOP apenas para ver se funciona, pode trocar por metodo
        if(usuarioAtual instanceof Administrador){System.out.println("Logado como Administrado");} //SOP apenas para ver se funciona, pode trocar por metodo
        if(usuarioAtual instanceof Paciente){System.out.println("Logado como Paciente");} //SOP apenas para ver se funciona, pode trocar por metodo

    }

    private Usuario selecionaUsuarioAtual(Scanner sc) {
        sc = new Scanner(System.in);
        Usuario usuario = null;
        int res = -1;
        String nome;

        while ( res == -1 ) {
            System.out.println("""
                    Selecione o tipo de usuario:
                    
                    [1]: Medico
                    [2]: Paciente
                    [3]: Administrador
                    
                    """);
            try {
                res = Integer.parseInt(sc.nextLine());

                switch (res) {
                    case 1 -> {
                        System.out.print("Nome do medico: ");
                        nome = sc.nextLine();
                        usuario = new Medico(nome, TipoUsuario.MEDICO);
                    }
                    case 2 -> {
                        System.out.print("Nome do paciente: ");
                        nome = sc.nextLine();
                        usuario = new Paciente(nome, TipoUsuario.PACIENTE);
                    }
                    case 3 -> {
                        System.out.print("Nome do administrador: ");
                        nome = sc.nextLine();
                        usuario = new Administrador(nome, TipoUsuario.ADMINISTRADOR);
                        System.out.println("Você deseja buscar um Médico ou usuário?: ");
                        System.out.println("----- MENU ----- ");
                    }
                    default -> throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("\nValor invalido\n");
                res = -1;
            }
        }
        return usuario;
    }
}
