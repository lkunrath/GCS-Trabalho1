package src.Entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
public class Autorizacao {

    private ArrayList<Exame> exames;
    private List<Paciente> filtroNome;

    public Autorizacao() {
        exames = new ArrayList<>();
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
   
    public Paciente filtroPaciente(Paciente paciente){
        for (int i = 0; i < exames.size(); i++) {
            filtroNome = new ArrayList<>();
            filtroNome.add(exames.get(i).getPaciente());
        }
        System.out.println(filtroNome.toString()); 
        return paciente;
    }
}
