package Entidades;
import Enums.TipoUsuario;
import Models.Usuario;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Date;


public class Paciente extends Usuario {

    ArrayList<Autorizacao> autorizacoesPaciente;

    public Paciente(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
        autorizacoesPaciente = new ArrayList<>();
    }

    public boolean cadastraAutorizacao(Autorizacao autorizacao){

        for (Autorizacao autorizacaoAux : autorizacao.getTodasAutorizacoes()) {
            if (autorizacaoAux.getId() == autorizacao.getId()) return false;
        }

        autorizacao.adicionaAutorizacao(autorizacao);
        return autorizacoesPaciente.add(autorizacao);
    }

    public ArrayList<Autorizacao> getAutorizacoesPaciente() {
        return new ArrayList<>(autorizacoesPaciente);
    }

    //ordena um clone, da data mais antiga ate a mais recente
    public ArrayList<Autorizacao> ac() {
        ArrayList<Autorizacao> aux=getAutorizacoesPaciente();
        Collections.sort(aux);
        return aux;
    }

    public void marcarExameRealizado(Date data, Exame exame) {
        Date exameMais30 = exame.getDataCadastro();

        exameMais30.setTime(exameMais30.getTime() + 30L * 24 * 60 * 60 * 1000);

        if (exame.getPaciente().getId() != this.getId()) {
            System.out.print("O Exame solicitado não é deste paciente");
        }
        else if (exame.getDataRealizada().before(exame.getDataCadastro())) {
            System.out.println("Data de exame inválida pois é menor que a data de sua autorização!");
        }
        else if (exame.getDataRealizada().after(exameMais30)) {
            System.out.println("A data de exame é posterior à 30 dias desde sua autorização, por isso ele não pode ser realizado!");
        }
        else{exame.setRealizado(true); exame.setDataRealizada(data);}
    }
}
    

