
package foorumi;

import java.sql.Timestamp;

public class Viesti {
    private int id;
    private int ketjuId;
    private String viesti;
    private Timestamp pvm;
    private String nimim;

    public Viesti(int id, int ketjuId, String viesti, Timestamp pvm, String nimim) {
        this.id = id;
        this.ketjuId = ketjuId;
        this.viesti = viesti;
        this.pvm = pvm;
        this.nimim = nimim;
    }

    public int getId() {
        return id;
    }

    public int getKetjuId() {
        return ketjuId;
    }

    public String getViesti() {
        return viesti;
    }

    public Timestamp getPvm() {
        return pvm;
    }

    public String getNimim() {
        return nimim;
    }
    
}
