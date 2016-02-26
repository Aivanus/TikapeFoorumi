
package foorumi;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws Exception{
        String address = "jdbc:sqlite:foorumi.db";
        Database database = new Database(address);
        AlueDao aDao = new AlueDao(database);
        aDao.add("hoi");
        ArrayList<Alue> alueet = (ArrayList) aDao.findAll();
        for (Alue a : alueet) {
            System.out.println(a.getId() + "\t" + a.getNimi());
        }
        aDao.delete(1);
        alueet = (ArrayList) aDao.findAll();
        for (Alue a : alueet) {
            System.out.println(a.getId() + "\t" + a.getNimi());
        }
        
    }

}
