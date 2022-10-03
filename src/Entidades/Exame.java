package src.Entidades;

import src.Enums.TipoExame;

import java.util.Date;

public class Exame {

    private static int globalId = 0;
    private final int id;
    private Date dataCadastro;
    private Medico medico;
    private Paciente paciente;
    private TipoExame tipoExame;

    public Exame(Date dataCadastro, Medico medico, Paciente paciente, TipoExame tipoExame) {
        id = globalId;
        ++globalId;
        this.dataCadastro = dataCadastro;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoExame = tipoExame;
    }

    public int getId() {
        return id;
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
