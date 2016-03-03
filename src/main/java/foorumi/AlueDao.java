package foorumi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlueDao implements Dao<Alue, Integer> {

    private Database database;

    public AlueDao(Database database) throws Exception {
        this.database = database;
    }

    private List<Alue> collect(ResultSet rs) throws Exception {
        ArrayList<Alue> alueet = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int lkm = this.viestienLukumaara(id);
            String pvm = this.viimeisinViesti(id);
         
            alueet.add(new Alue(rs.getString("nimi"), id, lkm, pvm));

        }
        return alueet;
    }

    @Override
    public void add(Alue a) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO alue(nimi) VALUES(?);");
        stmt.setString(1, a.getNimi());
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public Alue findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM alue WHERE id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        Alue a = new Alue(rs.getString("nimi"), rs.getInt("id"));
        
        rs.close();
        stmt.close();
        connection.close();
        return a;
    }

    @Override
    public List findAll() throws SQLException {
        Connection connection = database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM alue ORDER BY nimi ASC");
        List<Alue> lista = null;
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
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM alue WHERE id=?;");
        stmt.setObject(1, key);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    public int viestienLukumaara(int AlueId) throws SQLException {
        Connection connection1 = database.getConnection();
        PreparedStatement stmt1 = connection1.prepareStatement("SELECT COUNT(*) AS lkm FROM viesti, ketju WHERE alue_id=?"
                + " AND ketju_id = ketju.id;");
        stmt1.setObject(1, AlueId);
        ResultSet rs1 = stmt1.executeQuery();

        int tulos = rs1.getInt("lkm");

        rs1.close();
        stmt1.close();
        connection1.close();

        return tulos;
    }

    public String viimeisinViesti(int AlueId) throws SQLException {
        Connection connection2 = database.getConnection();
        PreparedStatement stmt2 = connection2.prepareStatement("SELECT pvm FROM viesti, ketju WHERE alue_id=? "
                + "AND ketju_id = ketju.id ORDER BY pvm DESC LIMIT 1;");
        stmt2.setObject(1, AlueId);
        ResultSet rs2 = stmt2.executeQuery();
        String tulos;
        
        try {
            tulos = rs2.getString("pvm");        
        } catch (Exception e) {
            tulos = "ei viestejä";
        }

        rs2.close();
        stmt2.close();
        connection2.close();

        return tulos;
    }

}
