package pl.Marta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotorcycleRepository {

    Connection con = null;

    //connectionToDtabase

    public MotorcycleRepository() {
        String url = "jdbc:mysql://localhost:3306/motorcycleRest";
        String username = "root";
        String password = "coderslab";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //getMoto


    public Motorcycle getMotorcycle(int id) {

        String sql = "select * from motorcycle where id=" + id;
        Motorcycle moto = new Motorcycle();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                moto.setId(rs.getInt(1));
                moto.setBrand(rs.getString(2));
                moto.setModel(rs.getString(3));
                moto.setHorsePower(rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moto;

    }



    //created

    public void create(Motorcycle moto) {
        String sql = "insert into motorcycle values(?,?,?,?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, moto.getId());
            st.setString(2, moto.getBrand());
            st.setString(3, moto.getModel());
            st.setInt(4, moto.getHorsePower());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update



    public void update(Motorcycle moto) {
        String sql = "update motorcycle set model=?, brand=? horsePower=? where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(4, moto.getId());
            st.setString(1, moto.getBrand());
            st.setString(2, moto.getModel());
            st.setInt(3, moto.getHorsePower());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//delete

    public void deleteMotorcyle(int id) {
        String sql = "delete from motorcycle where id=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //list
    public List<Motorcycle> getMotorcycles() {

        List<Motorcycle> motorcycles = new ArrayList<Motorcycle>();
        String sql = "select * from motorcycle";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Motorcycle moto = new Motorcycle();
                moto.setId(rs.getInt(1));
                moto.setBrand(rs.getString(2));
                moto.setModel(rs.getString(3));
                moto.setHorsePower(rs.getInt(4));
                motorcycles.add(moto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motorcycles;
    }

}
