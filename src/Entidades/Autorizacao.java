package Entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Autorizacao implements Comparable<Autorizacao> {
    int id;
    Date data;
    Medico medico;
    Paciente paciente;
    Exame exame;
    private ArrayList<Exame> exames;

    //lista com todas as autorizacoes para verificar se o codigo identificador é repetido
    private static ArrayList<Autorizacao> todasAutorizacoes = new ArrayList<>();


    //construtor com os atributos pedidos pelo topico 2
    public Autorizacao(int id, Date data, Medico medico, Paciente paciente, Exame exame) {
        this.id = id;
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
        this.exame = exame;
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
    public static ArrayList<Autorizacao> getTodasAutorizacoes() {
        return (ArrayList<Autorizacao>) todasAutorizacoes.clone();
    }

    //adiociona uma autorização (utilizar o metodo em Paciente para cadastrar, pois ele verifica se o id é repetido. Se não é, chama esse método)
    public static void adicionaAutorizacao(Autorizacao autorizacao) {

        todasAutorizacoes.add(autorizacao);
    }

    //indica como deve ser ordenanda uma lista de autorizações
    @Override
    public int compareTo(Autorizacao autorizacao) {
        return getData().compareTo(autorizacao.getData());
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
