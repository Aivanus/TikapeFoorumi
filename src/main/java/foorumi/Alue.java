package foorumi;

public class Alue {

    private String nimi;
    private int id;
    private int viestien_lkm;
    private String pvm;

    public Alue(String nimi, int id, int viestien_lkm, String pvm) {
        this(nimi, id);
        this.viestien_lkm = viestien_lkm;
        this.pvm = pvm;
    }

    Alue(String nimi, int id) {
        this.nimi = nimi;
        this.id = id;
    }

    Alue(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public int getId() {
        return id;
    }

    public int getViestien_lkm() {
        return viestien_lkm;
    }

    public void setViestien_lkm(int viestien_lkm) {
        this.viestien_lkm = viestien_lkm;
    }

    public String getPvm() {
        return pvm;
    }

    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

}
