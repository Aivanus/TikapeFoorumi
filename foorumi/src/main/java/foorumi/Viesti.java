
package foorumi;

public class Viesti {
    private int id;
    private int ketjuId;
    private String viesti;
    private String pvm;
    private String nimim;

    public Viesti(int id, int ketjuId, String viesti, String pvm, String nimim) {
        this(ketjuId, viesti, nimim);
        this.id = id;
        this.pvm = pvm;
    }

    Viesti(int ketjuId, String viesti, String nimim) {
        this.ketjuId = ketjuId;
        this.viesti = viesti;
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

    public String getPvm() {
        return pvm;
    }

    public String getNimim() {
        return nimim;
    }
    
}
