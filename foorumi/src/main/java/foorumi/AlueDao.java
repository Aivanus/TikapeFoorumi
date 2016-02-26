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
            alueet.add(new Alue(rs.getString("nimi"), rs.getInt("id")));
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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM alue WHERE alue_id = ?");
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM alue");
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
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS lkm FROM viesti, ketju WHERE alue_id=?"
                + " AND ketju_id = ketju.id;");
        stmt.setObject(1, AlueId);
        ResultSet rs = stmt.executeQuery();

        int tulos = rs.getInt("lkm");

        rs.close();
        stmt.close();
        connection.close();

        return tulos;
    }

    public Timestamp viimeisinViesti(int AlueId) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT pvm FROM viesti, ketju WHERE alue_id=? "
                + "AND ketju_id = ketju.id ORDER BY pvm DESC LIMIT 1;");
        stmt.setObject(1, AlueId);
        ResultSet rs = stmt.executeQuery();

        Timestamp tulos = rs.getTimestamp("pvm");

        rs.close();
        stmt.close();
        connection.close();

        return tulos;
    }

}
