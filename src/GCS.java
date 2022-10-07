package src;

import src.Entidades.*;
import src.Enums.TipoExame;
import src.Enums.TipoUsuario;
import src.Models.Usuario;

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
        autorizacoes = new Autorizacao();
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

        Medico m1 = new Medico("Rodrigo", TipoUsuario.MEDICO);
        Medico m2 = new Medico("Gustavo", TipoUsuario.MEDICO);
        Medico m3 = new Medico("Joana", TipoUsuario.MEDICO);
        Medico m4 = new Medico("Leonardo", TipoUsuario.MEDICO);
        Medico m5 = new Medico("Maria", TipoUsuario.MEDICO);

        medicos.add(m1);
        medicos.add(m2);
        medicos.add(m3);
        medicos.add(m4);
        medicos.add(m5);

        Exame ex1 = new Exame(date, m1 , p1, TipoExame.RADIOGRAFIA);
        Exame ex2 = new Exame(date, m5 , p5, TipoExame.HEMOGRAMA);
        Exame ex3 = new Exame(date, m2 , p2, TipoExame.HEMOGRAMA);
        Exame ex4 = new Exame(date, m3 , p2, TipoExame.GLICEMIA);
        Exame ex5 = new Exame(date, m1 , p1, TipoExame.ECOCARDIOGRAMA);

        autorizacoes.adicionaExame(ex1);
        autorizacoes.adicionaExame(ex2);
        autorizacoes.adicionaExame(ex3);
        autorizacoes.adicionaExame(ex4);
        autorizacoes.adicionaExame(ex5);
    }
    private Usuario selecionaUsuarioAtual( ) {
        sc = new Scanner( System.in );
        Usuario usuario = null;
        int res = -1;
        String nome;

        while ( res == -1 ) {
            System.out.println("""
                    Selecione o tipo de usuário:
                    
                    [1]: Médico
                    [2]: Paciente
                    [3]: Administrador
                    """);
            try {
                res = Integer.parseInt( sc.nextLine( ) );

                switch ( res ) {
                    case 1 -> {
                        System.out.print( "\nNome do médico: " );
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
                System.out.println( "\nValor inválido\n" );
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
                    LOGADO COMO MÉDICO
                    ------------------
                    
                    Selecione uma opção:
                    
                    [1] Adicionar nova autorização
                    [2] Listar autorizações 
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
                                    ADICIONAR NOVA AUTORIZAÇÃO
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
                                    AUTORIZAÇÃO ADICIONADA COM SUCESSO
                                    ----------------------------------
                                    
                                    Data: %s
                                    Nome do médico: %s
                                    Nome do paciente: %s
                                    Exame autorizado: %s
                                    
                                    """, simpleDateFormat.format(date), u.getNome(), p.getNome(), tipoExame.name());

                            break;

                         case 2: // lista as autorizações
                            Paciente pacienteTestep = null;
                            System.out.println("""
                                        
                                --------------------------
                                   LISTAR AUTORIZAÇÕES
                                --------------------------
                                
                                Filtros:

                                [1] Por paciente
                                [2] Por exame
                                """);
                             
                            int op = Integer.parseInt(sc.nextLine());
                            Paciente pac = null;
                            TipoExame tpEx = null;

                            switch (op){
                                case 1:
                                // Imprime o ID e nome de todos os pacientes cadastrados
                                for (Paciente paciente : pacientes) {
                                    System.out.printf("[%d] %s\n", paciente.getId( ), paciente.getNome( ));
                                }

                                // Recebe o id
                                System.out.print(" \nSelecione o paciente: ");
                                int numPaciente = Integer.parseInt(sc.nextLine( ));

                                // Atribui esse id a uma referência de paciente
                                for (Paciente pct : pacientes) {
                                     if (pct.getId( ) == numPaciente) {
                                        p = pct;
                                        break;
                                    }
                                }
                                
                                // Verifica se o ID está correto
                                if (pac == null ) throw new NumberFormatException( );


                                //pesquisaAutorizacao.filtroPaciente(pac);
                                
                                
                                System.out.println("\nEsses são os exames do paciente " + pac.getNome() +":");
                                
                                case 2 :
                                    // Imprime todos ID e nome de todos os tipos de exames disponíveis
                                    System.out.print(" \nSelecione o tipo de exame: \n");
                                    
                                    for (TipoExame t : TipoExame.values( )) {
                                        System.out.printf("[%d] %s\n", t.getId( ), t.name( ));
                                    }

                                    int inputExame = Integer.parseInt(sc.nextLine( ));

                                    for (TipoExame e : TipoExame.values()) {
                                        if (e.getId() == inputExame) tpEx = e;
                                    }

                                    if (tpEx == null) throw new NumberFormatException();

                                    ArrayList<Exame> examesFiltrados = autorizacoes.filtroExames(tpEx);

                                    System.out.println(tpEx);

                                    for (Exame e : examesFiltrados) {
                                        System.out.println("----------------------");
                                        System.out.println("Médico: " + e.getMedico().getNome());
                                        System.out.println("Paciente: " + e.getPaciente().getNome());
                                        System.out.println("Já realizado: " + (e.getRealizado() == true ? "Sim" : "Não"));
                                        System.out.println("Data Cadastro: " + e.getDataCadastro());
                                        System.out.println("Data Realizada: " +( e.getDataRealizada() == null ? "-" : e.getDataRealizada()) );
                                        System.out.println("----------------------");
                                    }

                                    // for (Exame ex : autorizacoes.getExames()) {
                                    //     if (ex.getTipoExame() == tpEx) {
                                    //         System.out.println("----------------------");
                                    //         System.out.println("Médico: " + ex.getMedico().getNome());
                                    //         System.out.println("Paciente: " + ex.getPaciente().getNome());
                                    //         System.out.println("Já realizado: " + (ex.getRealizado() == true ? "Sim" : "Não"));
                                    //         System.out.println("Data Cadastro: " + ex.getDataCadastro());
                                    //         System.out.println("Data Realizada: " +( ex.getDataRealizada() == null ? "-" : ex.getDataRealizada()) );
                                    //         System.out.println("----------------------");
                                    //     }
                                    // }
                                    break;
                         
                        default:
                            System.out.println("\nValor inválido");
                            res = -1;
                        }
                    }

                } catch ( NumberFormatException e ) {
                    System.out.println( "\nValor inválido\n" );
                    res = -1;
                
                }
            }
        }
    }
}
