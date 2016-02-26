package foorumi;

import java.sql.Connection;
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
        ArrayList<Ketju> alueet = new ArrayList<>();
        while (rs.next()) {
            alueet.add(new Ketju(rs.getInt("id"), rs.getInt("alue_id"), rs.getString("otsikko")));
        }
        return alueet;
    }

    @Override
    public Ketju findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ketju> findAll() throws SQLException {
        Connection connection = database.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ketju");
        List<Alue> lista = null;
        try {

            lista = collect(rs);
        } catch (Exception ex) {
        }

        return lista;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Ketju type) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
