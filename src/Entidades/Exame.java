package Entidades;

import Enums.TipoExame;

import java.util.Date;

public class Exame {

    private static int globalId = 0;
    private final int id;
    private Date dataCadastro;
    private Medico medico;
    private Paciente paciente;
    private TipoExame tipoExame;
    private boolean realizado;
    private Autorizacao autorizacao;

    private Date dataRealizada;

    public Exame(Date dataCadastro, Autorizacao autorizacao) {
        id = globalId;
        ++globalId;
        this.dataCadastro = dataCadastro;
        this.autorizacao = autorizacao;
        this.medico = autorizacao.getMedico();
        this.paciente = autorizacao.getPaciente();
        this.tipoExame = autorizacao.getExame();
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

    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao){
        this.autorizacao=autorizacao;
    }

    public TipoExame getTipoExame() {
        return tipoExame;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
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
        if(realizado){System.out.println("O exame foi realizado com sucesso!"); return true;}
        System.out.println("O exame não foi realizado");
        return false;
    }
}
