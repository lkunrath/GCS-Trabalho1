package src.Entidades;

import src.Enums.TipoExame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Exame {

    private static int globalId = 0;
    private final int id;
    private Date dataCadastro;
    private Medico medico;
    private Paciente paciente;
    private TipoExame tipoExame;
    private boolean realizado;
    private Date dataRealizada;

    public Exame(Date dataCadastro, Medico medico, Paciente paciente, TipoExame tipoExame) {
        id = globalId;
        ++globalId;
        this.dataCadastro = dataCadastro;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoExame = tipoExame;

        realizado = false;
        dataRealizada = null;
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

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
        if (realizado) {
            setDataRealizada(new Date());
        }
    }
    
    public boolean getRealizado() {
        return realizado;
    }
    
    public void setTipoExame(TipoExame tipoExame) {
        this.tipoExame = tipoExame;
    }
    
    public Date getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(Date dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public boolean isRealizado(){
        return realizado;
    }
}
