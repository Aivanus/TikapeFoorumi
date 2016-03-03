package foorumi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KetjuDao implements Dao<Ketju, Integer> {

    private Database database;

    public KetjuDao(Database database) throws Exception {
        this.database = database;
    }

    private List<Ketju> collect(ResultSet rs) throws Exception {
        ArrayList<Ketju> ketjut = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int lkm = this.viestienLukumaara(id);
//            String pvm = this.viimeisinViesti(id);
            String pvm = rs.getString("max_pvm");
            if(pvm == null){
                pvm = "ei viestejä";
            }
            ketjut.add(new Ketju(id, rs.getInt("alue_id"), rs.getString("otsikko"), lkm, pvm));
        }
        return ketjut;
    }

    @Override
    public Ketju findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ketju WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        Ketju k = new Ketju(rs.getInt("id"), rs.getInt("alue_id"), rs.getString("otsikko"));

        rs.close();
        stmt.close();
        connection.close();
        return k;
    }

    public List<Ketju> findAllIn(int alueId, int sivu) throws SQLException {
        Connection connection = database.getConnection();
        //PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ketju WHERE alue_id = ? LIMIT 10 OFFSET ?;");
        PreparedStatement stmt = connection.prepareStatement("SELECT Ketju.*, max_pvm FROM Ketju LEFT JOIN " +
            "(SELECT Viesti.ketju_id, MAX(pvm) AS max_pvm FROM viesti GROUP BY Viesti.ketju_id)" +
            "viesti ON Ketju.id = viesti.ketju_id " +
            "WHERE ketju.alue_id = ?" +
            "ORDER BY max_pvm DESC LIMIT 10 OFFSET ?;");
        stmt.setInt(1, alueId);
        stmt.setInt(2, (sivu - 1) * 10);
        ResultSet rs = stmt.executeQuery();
        List<Ketju> lista = null;
        try {

            lista = collect(rs);
        } catch (Exception ex) {
        }

        rs.close();
        stmt.close();
        connection.close();

        return lista;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM ketju WHERE id=?;");
        stmt.setObject(1, key);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public void add(Ketju k) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO ketju(alue_id, otsikko) VALUES(?, ?);");
        stmt.setInt(1, k.getAlueId());
        stmt.setString(2, k.getOtsikko());
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public List<Ketju> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int viestienLukumaara(int ketjuId) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS lkm FROM viesti WHERE ketju_id=?;");
        stmt.setObject(1, ketjuId);
        ResultSet rs = stmt.executeQuery();

        int tulos = rs.getInt("lkm");

        rs.close();
        stmt.close();
        connection.close();

        return tulos;
    }

//    public String viimeisinViesti(int ketjuId) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT pvm FROM viesti WHERE ketju_id=? ORDER BY pvm DESC LIMIT 1;");
//        stmt.setObject(1, ketjuId);
//        ResultSet rs = stmt.executeQuery();
//
//        String tulos;
//
//        try {
//            tulos = rs.getString("pvm");
//        } catch (Exception e) {
//            tulos = "ei viestejä";
//        }
//
//        rs.close();
//        stmt.close();
//        connection.close();
//
//        return tulos;
//    }
    
    public int countAllIn(int alueId) throws SQLException{
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM ketju WHERE alue_id = ?");
        stmt.setInt(1, alueId);
        ResultSet rs = stmt.executeQuery();
        int tulos = rs.getInt("COUNT(*)");
        
        rs.close();
        stmt.close();
        connection.close();

        return tulos;
    }

}
