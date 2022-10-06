package src;

import Enums.TipoExame;
import Enums.TipoUsuario;

import src.Entidades.*;
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
        sc = new Scanner(System.in);
        mostrarMenu(usuarioAtual);

bug-fix



        if(usuarioAtual instanceof Paciente){System.out.println("Logado como Paciente");} //SOP apenas para ver se funciona, pode trocar por metodo

 main
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
        Exame ex2 = new Exame(date, m5 , p5, TipoExame.CREATINA);
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
        int numId;

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
                        if (!exibirMedicosDisponiveis()) {
                            res = -1;
                            break;
                        }

                        System.out.print( "\nNumero do médico: " );
                        numId = Integer.parseInt( sc.nextLine( ) );

                        Medico m = getMedicoPorId( numId );
                        if ( m == null ) throw new NumberFormatException( );
                        usuario = m;
                    }
                    case 2 -> {
                        if (!exibirPacientesDisponiveis()) {
                            res = -1;
                            break;
                        }

                        System.out.print( "Numero do paciente: " );
                        numId = Integer.parseInt( sc.nextLine( ) );

                        Paciente p = getPacientePorId( numId );
                        if ( p == null ) throw new NumberFormatException( );
                        usuario = p;
                    }
                    case 3 -> {
                        if (!exibirAdministradoresDisponiveis()) {
                            res = -1;
                            break;
                        }

                        System.out.print( "Numero do administrador: " );
                        numId = Integer.parseInt( sc.nextLine( ) );

                        Administrador adm = getAdministradorPorId( numId );
                        if ( adm == null ) throw  new NumberFormatException();
                        usuario = adm;
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
 bug-fix
    private boolean exibirMedicosDisponiveis() {
        if (medicos.isEmpty()) {
            System.out.println("""
                    
                    ========================
                    NENHUM MEDICO DISPONIVEL
                    ========================
                    
                    """);
            return false;
        } else {
            System.out.printf("""
                    
                    =========================
                    %d MEDICO(S) ENCONTRADO(S)
                    =========================
                    
                    """, medicos.size());

            for (Medico medico : medicos) {
                System.out.printf("[%d] %s\n", medico.getId(), medico.getNome());
            }
        }
        return true;
    }
    private boolean exibirPacientesDisponiveis() {
        if (pacientes.isEmpty()) {
            System.out.println("""
                    
                    ==========================
                    NENHUM PACIENTE DISPONIVEL
                    ==========================
                    
                    """);
            return false;
        } else {
            System.out.printf("""
                    
                    ===========================
                    %d PACIENTE(S) ENCONTRADO(S)
                    ===========================
                    
                    """, pacientes.size());

            for (Paciente paciente : pacientes) {
                System.out.printf("[%d] %s\n", paciente.getId(), paciente.getNome());
            }
        }
        return true;
    }
    private boolean exibirAdministradoresDisponiveis() {
        if (administradores.isEmpty()) {
            System.out.println("""
                    
                    ===============================
                    NENHUM ADMINISTRADOR DISPONIVEL
                    ===============================
                    
                    """);
            return false;
        } else {
            System.out.printf("""
                    
                    ===================================
                    %d ADMINISTRADORES(S) ENCONTRADO(S)
                    ===================================
                    
                    """, administradores.size());

            for (Administrador adm : administradores) {
                System.out.printf("[%d] %s\n", adm.getId(), adm.getNome());
            }
        }
        return true;
    }


    private Medico getMedicoPorId(int id) {
        for (Medico m : medicos) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    private Paciente getPacientePorId(int id) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    private Administrador getAdministradorPorId(int id) {
        for (Administrador adm : administradores) {
            if (adm.getId() == id) {
                return adm;
            }
        }
        return null;
    }


    private void adicionarNovaAutorizacao(Usuario u) {


    private void menuAdmnistrador(){
            int res = -1;
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
                                    Usuario medico = new Medico(nome, TipoUsuario.MEDICO);
                                    medicos.add((Medico) medico);
                                    System.out.println("Médico Cadastrado");
                                    break;

                                case 2:
                                    Usuario paciente = new Paciente(nome, TipoUsuario.PACIENTE);
                                    pacientes.add((Paciente) paciente);
                                    System.out.println("Paciente Cadastrado");
                                    break;

                                case 3:
                                    Usuario administrador = new Administrador(nome, TipoUsuario.ADMINISTRADOR);
                                    administradores.add((Administrador) administrador);
                                    System.out.println("Administrador Cadastrado");
                                    break;
                            } sc.nextLine(); res = -1 ;break;

                        case 2:
                            System.out.println("Digite o nome do Usuario:");
                            String nom = sc.next();
                            for ( Paciente paciente : pacientes ) {
                                if (paciente.getNome().equalsIgnoreCase(nom))
                                    System.out.printf( "[%d] %s\n", paciente.getNome( ),"Autorização"  );
                            }
                            sc.nextLine(); res = -1 ;break;

                        case 3:
                            int pa = pacientes.size();
                            int me = medicos.size();
                            int ad = administradores.size();
                            System.out.println("Número de Pacientes: "+ pa+ "\nNúmero de Médicos: "+ me + "\nNúmero de Administradores: "+ad);
                            res = -1 ;break;

                        case 4: executa();



                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nValor inválido\n");
                    res = -1;
                }}
        }

    private void mostrarMenu( Usuario u ) {
        sc = new Scanner( System.in );
 main
        String pattern = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
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
        for (Paciente paciente : pacientes) {
            System.out.printf("[%d] %s\n", paciente.getId(), paciente.getNome());
        }

        // Recebe o id
        System.out.print(" \nSelecione o paciente: ");
        int numeroPaciente = Integer.parseInt(sc.nextLine());

        // Atribui esse id a uma referência de paciente
        for (Paciente pac : pacientes) {
            if (pac.getId() == numeroPaciente) {
                p = pac;
                break;
            }
        }

        // Verifica se o ID está correto
        if (p == null) throw new NumberFormatException();

        // Imprime todos ID e nome de todos os tipos de exames disponíveis
        System.out.print(" \nSelecione o tipo de exame: \n");
        for (TipoExame t : TipoExame.values()) {
            System.out.printf("[%d] %s\n", t.getId(), t.name());
        }

        // Recebe o ID do tipo de exame
        int inputTipoExame = Integer.parseInt(sc.nextLine());

        // Atribui esse id a uma referência de tipo de exame
        for (TipoExame e : TipoExame.values()) {
            if (e.getId() == inputTipoExame) tipoExame = e;
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
    }
    private void mostrarMenu( Usuario u ) {
        sc = new Scanner( System.in );

 bug-fix
        if( u instanceof Medico ) {


        // Menu exclusivo do Administrador
        if(u instanceof Administrador){menuAdmnistrador();}

        // Menu que só será exibido para o médico
        if( u instanceof Medico ){
 main
            int res = -1;

            while ( res == -1 ) {
                System.out.printf("""
                    
                    ------------------------
                    Bem-Vindo(a), Dr.(a) %s
                    ------------------------
                    
                    Selecione uma opção:
                    
                    [1] Adicionar nova autorização
 bug-fix
                    [2] Listar autorizações
                    
                    """, u.getNome());

                    [2] Listar autorizações 
                    [3] Voltar ao Menu Inicial
                    """);
 main

                try {
                    res = Integer.parseInt( sc.nextLine( ) );

                    switch (res) {
                        case 1 -> adicionarNovaAutorizacao(u);
                        case 2 -> { // lista as autorizações
                            Paciente pacienteTeste = null;
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
                                    for (Paciente paciente : pacientes) {
                                        System.out.printf("[%d] %s\n", paciente.getId(), paciente.getNome());
                                    }

                                    // Recebe o id
                                    System.out.print(" \nSelecione o paciente: ");
                                    int numPaciente = Integer.parseInt(sc.nextLine());

                                case 2:
                                    TipoExame tpExame = null;
                                    // Imprime todos ID e nome de todos os tipos de exames disponíveis
                                    System.out.print(" \nSelecione o tipo de exame: \n");

                                    for (TipoExame t : TipoExame.values()) {
                                        System.out.printf("[%d] %s\n", t.getId(), t.name());
                                    }

                                    break;

                                default:
                                    System.out.println("\nValor inválido");
                                    res = -1;
                            }
 bug-fix


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

                           res = -1 ;break;

                         case 2: // lista as autorizações
                            Paciente pacienteTeste = null;
                            System.out.println("""
                                        
                                --------------------------
                                   LISTAR AUTORIZAÇÕES
                                --------------------------
                                
                                Filtros:

                                [1] Por paciente
                                [2] Por exame
                           
                                """);
                             
                             int op = sc.nextInt();
                             switch (op){
                                case 1:
                                // Imprime o ID e nome de todos os pacientes cadastrados
                                for ( Paciente paciente : pacientes ) {
                                    System.out.printf( "[%d] %s\n", paciente.getId( ), paciente.getNome( ) );
                                }

                                // Recebe o id
                                System.out.print(" \nSelecione o paciente: " );
                                int numPaciente = Integer.parseInt( sc.next( ));

                                break;
                                case 2 :
                                TipoExame tpExame = null;
                                // Imprime todos ID e nome de todos os tipos de exames disponíveis
                                System.out.print(" \nSelecione o tipo de exame: \n");
                                    int exames = Integer.parseInt( sc.next( ));
                                for (TipoExame t : TipoExame.values( )) {
                                    System.out.printf("[%d] %s\n", t.getId( ), t.name( ));
                                break;
                                }}
                                    sc.nextLine(); res = -1 ;break;
                             case 3: executa();


                        default:
                            System.out.println("\nValor inválido");
                            res = -1;
 main
                        }


                } catch ( NumberFormatException e ) {
                    System.out.println( "\nValor inválido\n" );
                    res = -1;
                
                }
            }
        }
    }
}
