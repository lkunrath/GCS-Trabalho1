package src;

import src.Entidades.*;
import src.Enums.*;
import src.Models.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class GCS {

    Scanner sc;

    ArrayList<Medico> medicos;
    ArrayList<Paciente> pacientes;
    ArrayList<Administrador> administradores;
    Date date;
    Calendar c;
    Autorizacao autorizacoes;

    public GCS() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        administradores = new ArrayList<>();
        date = new Date();
        c = Calendar.getInstance();
        autorizacoes = new Autorizacao();
    }

    public void executa() {
        Usuario usuarioAtual = selecionaUsuarioAtual();
        sc = new Scanner(System.in);
        mostrarMenu(usuarioAtual);
    }

    public void preCadastro( ) {
        c.setTime(date);
        Administrador Adm = new Administrador("Administrador", TipoUsuario.ADMINISTRADOR);

        administradores.add(Adm);

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
                    [0]: Sair do programa
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

                    case 0-> System.exit(0);
                    default -> throw new NumberFormatException( );
                }
            } catch ( NumberFormatException e ) {
                System.out.println( "\nValor inválido\n" );
                res = -1;
            }
        }
        return usuario;
    }

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
                    
                    [1] Adicionar nova autorização
                    [2] Listar autorizações
                    [3] Voltar ao Menu
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
    private Exame getExamePorId(int id) {
        for (Exame e : autorizacoes.getExames()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    private void imprimeAutorizacoesPorIdPaciente(int id) {
        Paciente p;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Atribui esse id a uma referência de paciente
        p = getPacientePorId(id);

        // Verifica se o ID está correto
        if (p == null) throw new NumberFormatException();

        ArrayList<Exame> examesFiltrados = new ArrayList<>(autorizacoes.filtroPaciente(p));

        if (examesFiltrados.isEmpty()) {
            System.out.println("""
                    
                                    ==============================
                                    NENHUMA AUTORIZACAO ENCONTRADA
                                    ==============================
                                    
                                    """);
        } else {
            System.out.printf("""
                                        
                    =================================
                    %d AUTORIZACAO(OES) ENCONTRADA(S)
                    =================================
                    """, examesFiltrados.size());

            for (Exame e : examesFiltrados) {
                System.out.println("\n----------------------");
                System.out.println("Código: " + e.getId());
                System.out.println("Médico: " + e.getMedico().getNome());
                System.out.println("Paciente: " + e.getPaciente().getNome());
                System.out.println("Tipo de Exame: " + e.getTipoExame());
                System.out.println("Já realizado: " + (e.getRealizado() ? "Sim" : "Não"));
                System.out.println("Data do cadastro: " + simpleDateFormat.format(e.getDataCadastro()));
                System.out.println("Data da realização: " + (e.getDataRealizada() == null ? "-" : simpleDateFormat.format(e.getDataRealizada())));
            }
        }
    }
    private void imprimeAutorizacoesPorPaciente(Paciente p) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Verifica se o ID está correto
        if (p == null) throw new NumberFormatException();

        ArrayList<Exame> examesFiltrados = new ArrayList<>(autorizacoes.filtroPaciente(p));

        if (examesFiltrados.isEmpty()) {
            System.out.println("""
                    
                                    ==============================
                                    NENHUMA AUTORIZACAO ENCONTRADA
                                    ==============================
                                    
                                    """);
        } else {
            System.out.printf("""
                                        
                    =================================
                    %d AUTORIZACAO(OES) ENCONTRADA(S)
                    =================================
                    """, examesFiltrados.size());

            for (Exame e : examesFiltrados) {
                System.out.println("\n----------------------");
                System.out.println("Código: " + e.getId());
                System.out.println("Módico: " + e.getMedico().getNome());
                System.out.println("Paciente: " + e.getPaciente().getNome());
                System.out.println("Tipo de exame: " + e.getTipoExame());
                System.out.println("Já realizado: " + (e.getRealizado() ? "Sim" : "Nao"));
                System.out.println("Data do cadastro: " + simpleDateFormat.format(e.getDataCadastro()));
                System.out.println("Data da realização: " + (e.getDataRealizada() == null ? "-" : simpleDateFormat.format(e.getDataRealizada())));
            }
        }
    }
    private void imprimeAutorizacoesPorTipo() {
        TipoExame tpEx = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Imprime todos ID e nome de todos os tipos de exames disponíveis
        System.out.println(" \nSelecione o tipo de exame: \n");

        for (TipoExame t : TipoExame.values())
            System.out.printf("[%d] %s\n", t.getId(), t.name());

        int inputExame = Integer.parseInt(sc.nextLine());

        for (TipoExame e : TipoExame.values()) {
            if (e.getId() == inputExame) {
                tpEx = e;
                break;
            }
        }

        if (tpEx == null) throw new NumberFormatException();

        ArrayList<Exame> examesFiltrados = (ArrayList<Exame>) autorizacoes.filtroExames(tpEx);

        if (examesFiltrados.isEmpty()) {
            System.out.println("""
                    
                                    ==============================
                                    NENHUMA AUTORIZACAO ENCONTRADA
                                    ==============================
                                    
                                    """);
        } else {
            System.out.printf("""
                    
                                    =================================
                                    %d AUTORIZACAO(OES) ENCONTRADA(S)
                                    =================================
                                    """, examesFiltrados.size());

            for (Exame e : examesFiltrados) {
                System.out.println("\n----------------------");
                System.out.println("Codigo: " + e.getId());
                System.out.println("Medico: " + e.getMedico().getNome());
                System.out.println("Paciente: " + e.getPaciente().getNome());
                System.out.println("Tipo de Exame: " + e.getTipoExame());
                System.out.println("Ja realizado: " + (e.getRealizado() ? "Sim" : "Nao"));
                System.out.println("Data Cadastro: " + simpleDateFormat.format(e.getDataCadastro()));
                System.out.println("Data Realizada: " + (e.getDataRealizada() == null ? "-" : simpleDateFormat.format(e.getDataRealizada())));
            }
        }
    }


    private void menuPaciente(Usuario u){
        int res = -1;
        while (res == -1) {
            System.out.printf("""
                                            
                        --------------------
                        OLA, PACIENTE %s
                        --------------------
                                            
                        Selecione uma opção:
                                            
                        [1] Marcar Exame Realizada
                        [2] Listar Autorizações
                        [3] Consultar Exames para realizar
                        [4] Voltar ao Menu Inicial

                        """, u.getNome().toUpperCase());
            try {
                res = Integer.parseInt(sc.nextLine());

                switch (res) {
                    case 1 -> {

                        ArrayList<Exame> examesPaciente = (ArrayList<Exame>) autorizacoes.filtroPaciente((Paciente) u);
                        if (examesPaciente.isEmpty()) {
                            System.out.println("""
                            -----------------------
                            NENHUM EXAME AUTORIZADO
                            -----------------------
                            """);
                            } else {
                                System.out.printf("""
                            -------------------------
                            %d EXAME(S) AUTORIZADO(S)
                            -------------------------
                            
                            """, examesPaciente.size());

                            imprimeAutorizacoesPorIdPaciente(u.getId());
                            System.out.print("\nSelecione o codigo do exame para confirmar: ");

                            int idEx = Integer.parseInt(sc.nextLine());

                            Exame exame = getExamePorId(idEx);
                            if (exame == null) throw new NumberFormatException();
                            if (exame.isRealizado()) {
                                System.out.println("\nEXAME JA REALIZADO");
                                break;
                            }

                            System.out.println("""
                            
                            -------------------------
                            EXAME MARCADO COM SUCESSO
                            -------------------------
                            """);
                            exame.setRealizado(true);
                        }

                        res = -1;
                    }
                    case 2 -> {
                        imprimeAutorizacoesPorPaciente((Paciente) u);
                        res = -1;
                    }
                    case 3 -> {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        ArrayList<Exame> examesPaciente = (ArrayList<Exame>) autorizacoes.filtroPaciente((Paciente) u);
                        examesPaciente.removeIf(Exame::isRealizado);

                        if (examesPaciente.isEmpty()) {
                            System.out.println("""
                            -----------------------
                            NENHUM EXAME AUTORIZADO
                            -----------------------
                            """);
                        } else {
                            System.out.printf("""
                            -------------------------
                            %d EXAME(S) AUTORIZADO(S)
                            -------------------------
                                                                
                            """, examesPaciente.size());
                            res = -1;

                            for (Exame e : examesPaciente) {
                                System.out.println("\n----------------------");
                                System.out.println("Codigo: " + e.getId());
                                System.out.println("Medico: " + e.getMedico().getNome());
                                System.out.println("Paciente: " + e.getPaciente().getNome());
                                System.out.println("Tipo de Exame: " + e.getTipoExame());
                                System.out.println("Ja realizado: " + (e.getRealizado() ? "Sim" : "Nao"));
                                System.out.println("Data Cadastro: " + simpleDateFormat.format(e.getDataCadastro()));
                                System.out.println("Data Realizada: " + (e.getDataRealizada() == null ? "-" : simpleDateFormat.format(e.getDataRealizada())));
                            }
                        }
                    }
                    case 4 -> executa();
                    default -> throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("\nValor inválido\n");
                res = -1;
            }}
    }
    private void menuMedico(Usuario u) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int res = -1;

        while ( res == -1 ) {
            System.out.printf("""
                    
                    -----------------------------
                    Bem-Vindo(a), Dr.(a) %s
                    -----------------------------
                    
                    Selecione uma opção:
                    
                    [1] Adicionar nova autorização
                    [2] Listar autorizações
                    [3] Voltar ao Menu
                    
                    """, u.getNome());

            try {
                res = Integer.parseInt( sc.nextLine( ) );

                switch (res) {
                    case 1 -> { // Adicionar nova autorização
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

                        res = -1;
                    }
                    case 2 -> { // lista as autorizações

                        System.out.println("""
                                        
                                --------------------------
                                   LISTAR AUTORIZAÇÕES
                                --------------------------
                                                                
                                Filtros:

                                [1] Por paciente
                                [2] Por exame
                                                                
                                """);

                        int op = Integer.parseInt(sc.nextLine());

                        switch (op) {
                            case 1 -> {
                                exibirPacientesDisponiveis();

                                // Recebe o id
                                System.out.print(" \nSelecione o paciente: ");
                                int numPaciente = Integer.parseInt(sc.nextLine());

                                imprimeAutorizacoesPorIdPaciente(numPaciente);
                                res = -1;
                            }
                            case 2 -> {
                                imprimeAutorizacoesPorTipo();
                                res = -1;
                            }
                            default -> throw new NumberFormatException();
                        }
                    }
                    case 3 -> executa();
                    default -> throw new NumberFormatException();
                }
            } catch ( NumberFormatException e ) {
                System.out.println( "\nValor inválido\n" );
                res = -1;}
        }
    }
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
                    case 1 -> {
                        System.out.println("Digite o nome do novo Usuário: ");
                        String nome = sc.next();

                        System.out.println("Insira o numero equivalente ao tipo de Usuário: \n [1] Médico \n [2] Paciente \n [3] Administrador ");
                        int escolha = sc.nextInt();

                        switch (escolha) {
                            case 1 -> {
                                Medico medico = new Medico(nome, TipoUsuario.MEDICO);
                                medicos.add(medico);
                                System.out.println("Médico Cadastrado");
                            }
                            case 2 -> {
                                Paciente paciente = new Paciente(nome, TipoUsuario.PACIENTE);
                                pacientes.add(paciente);
                                System.out.println("Paciente Cadastrado");
                            }
                            case 3 -> {
                                Administrador administrador = new Administrador(nome, TipoUsuario.ADMINISTRADOR);
                                administradores.add(administrador);
                                System.out.println("Administrador Cadastrado");
                            }
                        }
                        sc.nextLine();
                        res = -1;
                    }
                    case 2 -> {
                        System.out.println("Digite o nome do Usuario:");
                        String nom = sc.next();
                        for (Paciente paciente : pacientes) {
                            if (paciente.getNome().equalsIgnoreCase(nom))
                                System.out.println("Paciente: " + paciente.getNome() + "Autorizações:" + autorizacoes.getExame());
                        }
                        sc.nextLine();
                        res = -1;
                    }
                    case 3 -> {
                        int pa = pacientes.size();
                        int me = medicos.size();
                        int ad = administradores.size();
                        System.out.println("Número de Pacientes: " + pa + "\nNúmero de Médicos: " + me + "\nNúmero de Administradores: " + ad);
                        res = -1;
                    }
                    case 4 -> executa();
                    default -> throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("\nValor inválido\n");
                res = -1;
            }}}


    private void mostrarMenu(Usuario u) {

        //Menu Exclusivo de Pacientes
        if(u instanceof Paciente) {menuPaciente(u);}

        // Menu exclusivo do Administrador
        if(u instanceof Administrador) {menuAdmnistrador();}

        // Menu exclusivo do Médico
        if(u instanceof Medico) {menuMedico(u);}
    }
}
