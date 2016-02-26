
package foorumi;


public class Ketju {
    
    private int id;
    private int alueId;
    private String otsikko;
    
    public Ketju(int id, int alueId, String otsikko){
        this.id = id;
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
    
    
    
}
