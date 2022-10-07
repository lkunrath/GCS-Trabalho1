package Entidades;

import java.util.Date;
import Enums.TipoExame;

public class Autorizacao implements Comparable<Autorizacao> {
    private static int globalId = 0;
    private final int id;
    private Date data;
    private Medico medico;
    private Paciente paciente;
    private TipoExame exame;

    //construtor com os atributos pedidos pelo topico 2
    public Autorizacao(Date data, Medico medico, Paciente paciente, TipoExame exame) {
        this.id = globalId;
        ++globalId;
        this.data = data;
        this.medico = medico;
        this.paciente = paciente;
        this.exame = exame;
    }

    //getters e setters dos atributos
    public int getId() {
        return id;
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

    public TipoExame getExame() {
        return exame;
    }

    public void setExame(TipoExame exame) {
        this.exame = exame;
    }

    //indica como deve ser ordenanda uma lista de autorizações
    @Override
    public int compareTo(Autorizacao autorizacao) {
        return getData().compareTo(autorizacao.getData());
    }
}
