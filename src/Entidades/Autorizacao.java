package src.Entidades;

import src.Enums.TipoExame;
import src.Models.Usuario;

import java.util.*;

public class Autorizacao implements Comparable<Autorizacao> {

    private int id;
    private Date data;
    private Medico medico;
    private Paciente paciente;
    private Exame exame;
    private ArrayList<Exame> exames = new ArrayList<>();

    //lista com todas as autorizacoes para verificar se o codigo identificador é repetido
    private ArrayList<Autorizacao> todasAutorizacoes = new ArrayList<>();

    //construtor com os atributos pedidos pelo topico 2
    public Autorizacao(int id, Date data, Medico medico, Paciente paciente, Exame exame) {
        this.id = id;
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
        this.exame = exame;
    }

    public Autorizacao() {
    }

    //getters e setters dos atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    //metodo que devolve um clone de todas autorizações
    public ArrayList<Autorizacao> getTodasAutorizacoes() {
        return (ArrayList<Autorizacao>) todasAutorizacoes.clone();
    }

    //adiociona uma autorização (utilizar o metodo em Paciente para cadastrar, pois ele verifica se o id é repetido. Se não é, chama esse método)
    public void adicionaAutorizacao(Autorizacao autorizacao) {
        todasAutorizacoes.add(autorizacao);
    }

    //indica como deve ser ordenanda uma lista de autorizações
    @Override
    public int compareTo(Autorizacao autorizacao) {
        return getData().compareTo(autorizacao.getData());
    }

    public boolean adicionaExame(Exame e) {
        return exames.add(e);
    }

    public ArrayList<Exame> getExames() {
        return (ArrayList<Exame>) exames.clone();
    }

    public List<Exame> filtroPaciente(Paciente p) {
        SortByDate sbd = new SortByDate();
        return sbd.filtroPaciente(p);
    }

    public List<Exame> filtroExames(TipoExame tipoExame) {
        SortByDate sbd = new SortByDate();
        return sbd.filtroExames(tipoExame);
    }


    public class SortByDate implements Comparator<Exame> {
        @Override
        public int compare(Exame a, Exame b) {
            if (a.getDataRealizada() != null && a.getDataRealizada() != null) {
                return a.getDataRealizada().compareTo(b.getDataRealizada());
            } else {
                return 0;
            }
        }

        public List<Exame> filtroPaciente(Paciente paciente) {
            ArrayList<Exame> filtroNome = new ArrayList<>();
            for (Exame value : exames) {
                if (value.getPaciente().equals(paciente))
                    filtroNome.add(value);
            }
            Collections.sort(filtroNome, new SortByDate());
            return filtroNome;
        }

        public ArrayList<Exame> filtroExames(TipoExame tipoExame){
            ArrayList<Exame> filtroExame = new ArrayList<>();
            for (Exame value : exames) {
                if (value.getTipoExame().equals(tipoExame))
                    filtroExame.add(value);
            }
            Collections.sort(filtroExame, new SortByDate());

            return filtroExame;
        }
    }
}
