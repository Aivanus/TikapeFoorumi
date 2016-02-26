package foorumi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;

    public ViestiDao(Database database) throws Exception {
        this.database = database;
    }

    private List<Viesti> collect(ResultSet rs) throws Exception {
        ArrayList<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            viestit.add(new Viesti(rs.getInt("id"), rs.getInt("ketju_id"), rs.getString("viesti"), rs.getTimestamp("pvm"), 
            rs.getString("nimim")));
        }
        return viestit;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Viesti> findAllIn(int ketju_id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM viesti WHERE ketju_id = ?");
        stmt.setInt(1, ketju_id);
        ResultSet rs = stmt.executeQuery();
        List<Viesti> lista = null;
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
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM viesti WHERE id=?;");
        stmt.setObject(1, key);
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public void add(Viesti v) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO viesti(ketju_id, viesti, pvm, nimim) VALUES(?, ?, datetime('now'), ?);");
        stmt.setInt(1, v.getKetjuId());
        stmt.setString(2, v.getViesti());
        stmt.setString(3, v.getNimim());
        stmt.executeUpdate();

        stmt.close();
        connection.close();
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
