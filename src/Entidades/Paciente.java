package src.Entidades;

import src.Enums.TipoUsuario;
import src.Models.Usuario;

import java.util.Date;

public class Paciente extends Usuario {
    public Paciente(String nome, TipoUsuario tipoUsuario) {
        super(nome, tipoUsuario);
    }

    public void marcarExameRealizado(Date data, Exame exame) {
        Date examemais30 = exame.getDataRealizada();
        examemais30.setTime(examemais30.getTime() + 30L * 24 * 60 * 60 * 1000);
        if(exame.getPaciente().getId() != this.getId()){System.out.print("O Exame solicitado não é deste paciente");}
        else if(exame.getDataRealizada().before(exame.getDataCadastro()) ){
            System.out.println("Data de exame inválida pois é menor que a data de sua autorização!");
        }else if(exame.getDataRealizada().after(examemais30)){ System.out.println("A data de exame é posterior à 30 dias desde sua autorização, por isso ele não pode ser realizado!");}
        else{exame.setRealizado(true); exame.setDataRealizada(data);}



    }
}
