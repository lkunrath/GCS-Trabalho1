package src.Entidades;

import src.Enums.TipoExame;

import java.util.Date;

public class Exame {

    private static int id = 0;
    private String nomeExame;
    private Date dataCadastro;
    private Medico medico;
    private Paciente paciente;
    private TipoExame tipoExame;

    public Exame(String nomeExame, Date dataCadastro, Medico medico, Paciente paciente, TipoExame tipoExame) {
        id += 1;
        this.nomeExame = nomeExame;
        this.dataCadastro = dataCadastro;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoExame = tipoExame;
    }

    public int getId() {
        return id;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public TipoExame getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(TipoExame tipoExame) {
        this.tipoExame = tipoExame;
    }
}
