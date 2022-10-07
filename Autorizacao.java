package src.Entidades;

import java.util.ArrayList;
import java.util.Collections;

import src.Enums.TipoExame;

import java.util.*;
public class Autorizacao {

    private ArrayList<Exame> exames;
    private List<Exame> filtroNome;
    private List<Exame> filtroTipoExame;

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
   
    static class SortByDate implements Comparator<Exame> {
        @Override
        public int compare(Exame a, Exame b) {
            return a.getDataCadastro().compareTo(b.getDataCadastro());
        }
    }
    
    public List<Exame> filtroPaciente(Paciente paciente){
        filtroNome = new ArrayList<>();
        for (int i = 0; i < exames.size(); i++) {
            if(exames.get(i).getPaciente().equals(paciente))
            filtroNome.add(exames.get(i));
        }
        Collections.sort(filtroNome, new SortByDate());
        return filtroNome;
    }

    public List<Exame> filtroTipoExames(TipoExame tipoExame){
        filtroTipoExame = new ArrayList<>();
        for (int i = 0; i < exames.size(); i++) {
            if(exames.get(i).getTipoExame().equals(tipoExame))
            filtroTipoExame.add(exames.get(i));
        }
        Collections.sort(filtroTipoExame, new SortByDate());
        return filtroTipoExame;
    }
}

