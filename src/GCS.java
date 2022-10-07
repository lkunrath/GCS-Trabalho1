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
    ArrayList<Autorizacao> autorizacoes;
    ArrayList<Exame> exames;

    public GCS() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        administradores = new ArrayList<>();
        autorizacoes = new ArrayList<>();
        exames = new ArrayList<>();
        date = new Date();
    }

    public void executa() {
        Usuario usuarioAtual = selecionaUsuarioAtual();
        sc = new Scanner(System.in);
        mostrarMenu(usuarioAtual);
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

        Autorizacao a1 = new Autorizacao(date, m1, p1, TipoExame.RADIOGRAFIA);
        Autorizacao a2 = new Autorizacao(date, m2, p2, TipoExame.CREATINA);
        Autorizacao a3 = new Autorizacao(date, m3, p3, TipoExame.HEMOGRAMA);
        Autorizacao a4 = new Autorizacao(date, m4, p4, TipoExame.GLICEMIA);
        Autorizacao a5 = new Autorizacao(date, m5, p5, TipoExame.ECOCARDIOGRAMA);

        autorizacoes.add(a1);
        autorizacoes.add(a2);
        autorizacoes.add(a3);
        autorizacoes.add(a4);
        autorizacoes.add(a5);

        Exame ex1 = new Exame(date, a1);
        Exame ex2 = new Exame(date, a2);
        Exame ex3 = new Exame(date, a3);
        Exame ex4 = new Exame(date, a4);
        Exame ex5 = new Exame(date, a5);

        exames.add(ex1);
        exames.add(ex2);
        exames.add(ex3);
        exames.add(ex4);
        exames.add(ex5);
    }
    
    private Usuario selecionaUsuarioAtual( ) {
        sc = new Scanner( System.in );
        Usuario usuario = null;
        int res = -1;
        String nome;

        while ( res == -1 ) {
            System.out.println("\n");
            System.out.println("""
                                          
                      --------------------
                          MENU INICIAL
                      --------------------
                                            
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

    private void menuAdmnistrador(){
        int res = -1;
        String pattern = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        while (res == -1) {
            System.out.println("""
                                        
                    -------------------------
                    LOGADO COMO ADMINISTRADOR
                    -------------------------
                                        
                    Selecione uma opção:
                                        
                    [1] Adicionar novo usuário
                    [2] Procurar autorizações por nome de Usuario
                    [3] Estatisticas Gerais
                    [4] Voltar ao Menu Inicial
                    """);
            try {
                res = Integer.parseInt(sc.nextLine());

                switch (res) {
                    case 1:
                        System.out.println("Digite o nome do novo Usuário: ");
                        String nome = sc.next();
                        System.out.println("Insira o numero equivalente ao tipo de Usuário: \n [1] Médico \n [2] Paciente \n [3] Administrador ");
                        int escolha;
                        escolha = sc.nextInt();
                        switch (escolha) {
                            case 1:
                                Medico medico = new Medico(nome, TipoUsuario.MEDICO);
                                medicos.add(medico);
                                System.out.println("Médico Cadastrado");
                                break;

                            case 2:
                                Paciente paciente = new Paciente(nome, TipoUsuario.PACIENTE);
                                pacientes.add(paciente);
                                System.out.println("Paciente Cadastrado");
                                break;

                            case 3:
                                Administrador administrador = new Administrador(nome, TipoUsuario.ADMINISTRADOR);
                                administradores.add(administrador);
                                System.out.println("Administrador Cadastrado");
                                break;

                        } sc.nextLine(); res = -1 ;break;

                    case 2:
                        System.out.println("Digite o nome do Usuario:");
                        String nom = sc.next();
                        int count = 0;
                        for ( Autorizacao autorizacao : autorizacoes ) {
                            if (autorizacao.getPaciente().getNome().equalsIgnoreCase(nom)){
                                count++;
                                System.out.println("\n" + count + "# Autorizacao:\nID: " + autorizacao.getId() + "\nTipo de Exame: " + autorizacao.getExame() + "\nMedico: " + autorizacao.getMedico().getNome() + "\nData da requizição: " +  simpleDateFormat.format(autorizacao.getData()));
                            }
                        }
                        if (count == 0)
                            System.out.println("Nenhuma autorização foi encontrada!");

                        sc.nextLine(); res = -1 ;break;

                    case 3:
                        System.out.println("\nNúmero de Pacientes: "+ pacientes.size() + "\nNúmero de Médicos: " + medicos.size() + "\nNúmero de Administradores: " + administradores.size());
                        res = -1 ;break;

                    case 4:
                        executa();
                }
            } 
            catch (NumberFormatException e) {
                System.out.println("\nValor inválido\n");
                res = -1;
            }
        }
    }

    private void menuMedico(Medico u){
        int res = -1;
        String pattern = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        while ( res == -1 ) {
            System.out.println("""
                
                ------------------
                LOGADO COMO MÉDICO
                ------------------
                
                Selecione uma opção:
                
                [1] Adicionar nova autorização
                [2] Listar autorizações 
                [3] Voltar ao Menu Inicial
                """);

            try {
                res = Integer.parseInt( sc.nextLine( ) );

                switch ( res ) {
                    case 1: // Adicionar nova autorização
                        Paciente p = null;
                        TipoExame tipoExame = null;
                        Autorizacao autorizacao;

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

                        // Instancia um novo objeto Autorizacao
                        autorizacao = new Autorizacao(date, u, p, tipoExame);
                        autorizacoes.add(autorizacao);

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

                        res = -1 ;break;

                    case 2: // lista as autorizações
                        System.out.println("""
                                
                        --------------------------
                            LISTAR AUTORIZAÇÕES
                        --------------------------
                        
                        Filtros:

                        [1] Por paciente
                        [2] Por exame
                    
                        """);
                        
                        int op = sc.nextInt();

                        switch (op) {
                            case 1:
                                // Imprime o ID e nome de todos os pacientes cadastrados
                                for ( Paciente paciente : pacientes ) {
                                    System.out.printf( "[%d] %s\n", paciente.getId( ), paciente.getNome( ) );
                                }

                                // Recebe o id
                                System.out.print(" \nSelecione o paciente: " );
                                int numPaciente = Integer.parseInt( sc.next( ));

                                int count = 0;
                                for (Autorizacao autoriza: autorizacoes){
                                    if (autoriza.getPaciente().getId() == numPaciente){
                                        count++;
                                        System.out.println("\n" + count + "# Autorizacao:\nID: " + autoriza.getId() + "\nTipo de Exame: " + autoriza.getExame() + "\nMedico: " + autoriza.getMedico().getNome() + "\nData da requizição: " + simpleDateFormat.format(autoriza.getData())); 
                                    }
                                }
                                
                                if (count == 0)
                                    System.out.println("Nenhuma autorização encontrada");
                                
                                sc.nextLine();
                                res = -1;
                                break;

                            case 2 :
                                System.out.println("[1] HEMOGRAMA\n[2] GLICEMIA\n[3] COLESTEROL\n[4] CREATINA\n[5] ELETROCARDIOGRAMA\n[6] TESTE_ERGOMETRICO\n[7] ECOCARDIOGRAMA\n[8] RAIO_X\n[9] RADIOGRAFIA\n[10] TOMOGRAFIA");

                                // Imprime todos ID e nome de todos os tipos de exames disponíveis
                                System.out.print(" \nSelecione o tipo de exame: \n");

                                int exameId = Integer.parseInt( sc.next( ));

                                int contagem = 0;
                                for (Autorizacao t : autorizacoes) {
                                    if (t.getExame().getId() == exameId){
                                        contagem++;
                                        System.out.println("\n" + contagem + "# Autorizacao:\nID: " + t.getId() + "\nTipo de Exame: " + t.getExame() + "\nMedico: " + t.getMedico().getNome() + "\nData da requizição: " + simpleDateFormat.format(t.getData())); 
                                    }
                                }
                                
                                if (contagem == 0)
                                    System.out.println("Nenhuma autorização encontrada");
                                
                                sc.nextLine();
                                res = -1;
                                break;

                            default:
                                System.out.println("\nValor inválido");
                                res = -1;
                                break;
                        }
                    case 3:
                        executa();
                }


            } catch ( NumberFormatException e ) {
                System.out.println( "\nValor inválido\n" );
                res = -1;
            
            }
        }
    }

    private void mostrarMenu( Usuario u ) {
        sc = new Scanner( System.in );


        // Menu exclusivo do Administrador
        if(u instanceof Administrador)
            menuAdmnistrador();

        // Menu que só será exibido para o médico
        if( u instanceof Medico )
            menuMedico((Medico) u);
    }
}
