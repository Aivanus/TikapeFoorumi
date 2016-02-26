package foorumi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ketjut.add(new Ketju(rs.getInt("id"), rs.getInt("alue_id"), rs.getString("otsikko")));
        }
        return ketjut;
    }

    @Override
    public Ketju findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Ketju> findAllIn(int alue_id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ketju WHERE alue_id = ?");
        stmt.setInt(1, alue_id);
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

}
