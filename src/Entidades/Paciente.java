package Entidades;
import Enums.TipoUsuario;
import Models.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, 1);

        if (exame.getDataCadastro().after(cal.getTime())) {
            System.out.println("\nO exame possui mais de 30 dias.");
        } else {
            System.out.println("""
                
                    ----------------------------
                    EXAME MARCADO COMO REALIZADO
                    ---------------------------- 
                    """);
            exame.setRealizado(true);
            exame.setDataRealizada(data);
        }
    }
}
    
