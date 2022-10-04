package Entidades;

import java.util.ArrayList;
import java.util.Date;

public class Autorizacao {
    int id;
    Date data;
    Medico medico;
    Paciente paciente;
    Exame exame;
    private ArrayList<Exame> exames;

    //lista com todas as autorizacoes para verificar se o codigo identificador é repetido
    private static ArrayList<Autorizacao> todasAutorizacoes;


    //construtor com os atributos pedidos pelo topico 2
    public Autorizacao(int id, Date data, Medico medico, Paciente paciente, Exame exame) {
        this.id = id;
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
        this.exame = exame;
    }

    //getters e setters dos atrbutos
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

    public static ArrayList<Autorizacao> getTodasAutorizacoes() {
        return todasAutorizacoes;
    }

    public static void adicionaAutorizacao(Autorizacao autorizacao){

        todasAutorizacoes.add(autorizacao);
    }

    public boolean adicionaExame(Exame e) {
        for (Exame exame : exames) {
            if (exame.getTipoExame().equals(e.getTipoExame())) return false;
        }
        return exames.add(e);
    }

    public ArrayList<Exame> getExames() {
        return new ArrayList<>(exames);
    }
}
