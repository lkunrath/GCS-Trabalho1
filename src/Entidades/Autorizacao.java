package src.Entidades;

import java.util.ArrayList;

public class Autorizacao {

    private ArrayList<Exame> exames;

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
}
