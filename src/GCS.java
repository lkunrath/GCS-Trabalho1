import Entidades.*;
import Enums.TipoExame;
import Enums.TipoUsuario;
import Models.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GCS {

    Scanner sc;
    ArrayList<Medico> medicos;
    ArrayList<Paciente> pacientes;
    ArrayList<Administrador> administradores;
    Date date;

    Autorizacao autorizacoes;

    public GCS() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        administradores = new ArrayList<>();
        date = new Date();
        //adicionar autorizacao aq
    }

    public void executa() {
        Usuario usuarioAtual = selecionaUsuarioAtual();

        mostrarMenu(usuarioAtual);

        if(usuarioAtual instanceof Administrador){System.out.println("Logado como Administrado");} //SOP apenas para ver se funciona, pode trocar por metodo
        if(usuarioAtual instanceof Paciente){System.out.println("Logado como Paciente");} //SOP apenas para ver se funciona, pode trocar por metodo

    }

    public void preCadastro( ) {
        Paciente p1 = new Paciente( "Joao", TipoUsuario.PACIENTE );
        Paciente p2 = new Paciente( "Lara", TipoUsuario.PACIENTE );
        Paciente p3 = new Paciente( "Luiz", TipoUsuario.PACIENTE );
        Paciente p4 = new Paciente( "Alberto", TipoUsuario.PACIENTE );
        Paciente p5 = new Paciente( "Lucas", TipoUsuario.PACIENTE );

        pacientes.add( p1 );
        pacientes.add( p2 );
        pacientes.add( p3 );
        pacientes.add( p4 );
        pacientes.add( p5 );
    }
    private Usuario selecionaUsuarioAtual( ) {
        sc = new Scanner( System.in );
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
                res = Integer.parseInt( sc.nextLine( ) );

                switch ( res ) {
                    case 1 -> {
                        System.out.print( "Nome do medico: " );
                        nome = sc.nextLine( );
                        usuario = new Medico( nome, TipoUsuario.MEDICO );
                        medicos.add( (Medico) usuario);
                    }
                    case 2 -> {
                        System.out.print( "Nome do paciente: " );
                        nome = sc.nextLine( );
                        usuario = new Paciente( nome, TipoUsuario.PACIENTE );
                        pacientes.add( (Paciente) usuario);
                    }
                    case 3 -> {
                        System.out.print( "Nome do administrador: " );
                        nome = sc.nextLine( );
                        usuario = new Administrador( nome, TipoUsuario.ADMINISTRADOR );
                        administradores.add( (Administrador) usuario);
                    }
                    default -> throw new NumberFormatException( );
                }
            } catch ( NumberFormatException e ) {
                System.out.println( "\nValor invalido\n" );
                res = -1;
            }
        }
        return usuario;
    }
    private void mostrarMenu( Usuario u ) {
        sc = new Scanner( System.in );
        String pattern = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        // Menu que só será exibido para o médico
        if( u instanceof Medico ){
            int res = -1;

            while ( res == -1 ) {
                System.out.println("""
                    
                    ------------------
                    LOGADO COMO MEDICO
                    ------------------
                    
                    Selecione uma opcao:
                    
                    [1] Adicionar nova autorizacao
                    
                    """);

                try {
                    res = Integer.parseInt( sc.nextLine( ) );

                    switch ( res ) {
                        case 1: // Adicionar nova autorização
                            Paciente p = null;
                            TipoExame tipoExame = null;
                            Exame exame;

                            System.out.println("""
                                    
                                    --------------------------
                                    ADICIONAR NOVA AUTORIZACAO
                                    --------------------------
                                    
                                    Pacientes cadastrados:
                                    """);

                            // Imprime o ID e nome de todos os pacientes cadastrados
                            for ( Paciente paciente : pacientes ) {
                                System.out.printf( "[%d] %s\n", paciente.getId( ), paciente.getNome( ) );
                            }

                            // Recebe o id
                            System.out.print(" \nSelecione o paciente: " );
                            int numeroPaciente = Integer.parseInt( sc.nextLine( ) );

                            // Atribui esse id a uma referência de paciente
                            for ( Paciente pac : pacientes ) {
                                if ( pac.getId( ) == numeroPaciente ) {
                                    p = pac;
                                    break;
                                }
                            }

                            // Verifica se o ID está correto
                            if ( p == null ) throw new NumberFormatException( );


                            // Imprime todos ID e nome de todos os tipos de exames disponíveis
                            System.out.print(" \nSelecione o tipo de exame: \n" );
                            for ( TipoExame t : TipoExame.values( ) ) {
                                System.out.printf( "[%d] %s\n", t.getId( ), t.name( ) );
                            }

                            // Recebe o ID do tipo de exame
                            int inputTipoExame = Integer.parseInt( sc.nextLine( ) );

                            // Atribui esse id a uma referência de tipo de exame
                            for( TipoExame e : TipoExame.values( ) ) {
                                if( e.getId() == inputTipoExame ) tipoExame = e;
                            }

                            // Verifica se o ID está correto
                            if (tipoExame == null) throw new NumberFormatException();

                            // Instancia um novo objeto Exame
                            exame = new Exame(date, (Medico) u, p, tipoExame);
                            autorizacoes.adicionaExame(exame);

                            // Imprime uma confirmação
                            System.out.printf("""
                                    
                                    ----------------------------------
                                    AUTORIZACAO ADICIONADA COM SUCESSO
                                    ----------------------------------
                                    
                                    Data: %s
                                    Nome do medico: %s
                                    Nome do paciente: %s
                                    Exame autorizado: %s
                                    
                                    """, simpleDateFormat.format(date), u.getNome(), p.getNome(), tipoExame.name());

                            break;
//                        case 2: {
//                            break;
//                        }
                        default:
                            System.out.println("\nValor invalido");
                            res = -1;
                    }


                } catch ( NumberFormatException e ) {
                    System.out.println( "\nValor invalido\n" );
                    res = -1;
                }
            }
        }
    }
}
