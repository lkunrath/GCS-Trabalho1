package src.Entidades;

import java.util.ArrayList;
import java.util.Collections;

import src.Enums.TipoExame;

import java.util.*;
public class Autorizacao {

    private ArrayList<Exame> exames;
 
    public Autorizacao() {
        exames = new ArrayList<>();
    }

    public boolean adicionaExame(Exame e) {
        return exames.add(e);
    }

    public ArrayList<Exame> getExames() {
        return new ArrayList<>(exames);
    }
   
    public class SortByDate implements Comparator<Exame> {
        @Override
        public int compare(Exame a, Exame b) {
            return a.getDataCadastro().compareTo(b.getDataCadastro());
        }
    } 
    
     public List<Exame> filtroPaciente(Paciente paciente){
        ArrayList<Exame> filtroNome = new ArrayList<>();
        for (int i = 0; i < exames.size(); i++) {
            if(exames.get(i).getPaciente().equals(paciente))
            filtroNome.add(exames.get(i));
        }
        Collections.sort(filtroNome, new SortByDate());
        return filtroNome;
    }

    public ArrayList<Exame> filtroExames(TipoExame tipoExame){
        ArrayList<Exame> filtroExame = new ArrayList<>();
        for (int i = 0; i < exames.size(); i++) {
            if(exames.get(i).getTipoExame().equals(tipoExame))
            filtroExame.add(exames.get(i));
        }
        Collections.sort(filtroExame, new SortByDate());
        
        return filtroExame;
    } 
}
