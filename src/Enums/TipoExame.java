package Enums;

public enum TipoExame {
    HEMOGRAMA(1),
    GLICEMIA(2),
    COLESTEROL(3),
    CREATINA(4),
    ELETROCARDIOGRAMA(5),
    TESTE_ERGOMETRICO(6),
    ECOCARDIOGRAMA(7),
    RAIO_X(8),
    RADIOGRAFIA(9),
    TOMOGRAFIA(10);

    private int id;

    TipoExame(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TipoExame getById( int id ) {
        for( TipoExame e : values( ) ) {
            if( e.id == id ) return e;
        }
        return null;
    }
}