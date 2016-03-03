
package foorumi;


public class Ketju {
    
    private int id;
    private int alueId;
    private String otsikko;
    private int viestien_lkm;
    private String pvm;
    
    public Ketju(int id, int alueId, String otsikko, int viestien_lkm, String pvm){
        this(id, alueId, otsikko);
        this.viestien_lkm = viestien_lkm;
        this.pvm = pvm;
    }
    
    public Ketju(int id, int alueId, String otsikko){
        this(alueId, otsikko);
        this.id = id;
    }
    
    public Ketju(int alueId, String otsikko){
        this.alueId = alueId;
        this.otsikko = otsikko;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlueId() {
        return alueId;
    }

    public void setAlueId(int alueId) {
        this.alueId = alueId;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public int getViestien_lkm() {
        return viestien_lkm;
    }

    public String getPvm() {
        return pvm;
    }
    
    
    
}
