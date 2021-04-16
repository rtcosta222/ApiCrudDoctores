/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Doctor;

/**
 *
 * @author lscar
 */
public class RepositoryDoctor {
    
    private Connection getConection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String cadena = "jdbc:sqlserver://sqlserverjavapgs.database.windows.net:1433;databaseName=SQLAZURE";
        Connection cn = DriverManager.getConnection(cadena, "adminsql", "Admin123");
        return cn;
    }
    
    public List<Doctor> getTablaDoctor() throws SQLException{
        Connection cn = this.getConection();
        String sql = "select * from doctor order by apellido";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Doctor> doctores = new ArrayList<Doctor>();
        while(rs.next()){
            Doctor doctor = new Doctor(rs.getInt("HOSPITAL_COD"),
                                        rs.getInt("DOCTOR_NO"),
                                        rs.getString("APELLIDO"),
                                        rs.getString("ESPECIALIDAD"),
                                        rs.getInt("SALARIO"));
            doctores.add(doctor);
        }
        rs.close();
        cn.close();
        return doctores;
    }
    
    public Doctor getDoctor(int iddoc) throws SQLException{
        Connection cn = this.getConection();
        String sql = "select * from doctor where doctor_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, iddoc);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            Doctor doctor = new Doctor(rs.getInt("HOSPITAL_COD"),
                                        rs.getInt("DOCTOR_NO"),
                                        rs.getString("APELLIDO"),
                                        rs.getString("ESPECIALIDAD"),
                                        rs.getInt("SALARIO"));
            rs.close();
            cn.close();
            return doctor;
        } else{
            rs.close();
            cn.close();
            return null;
        }
    }
    
    public void insertarDoctor(int hcod, int dcod, String ape, String espec, int sal) throws SQLException{
        Connection cn = this.getConection();
        String sql = "insert into doctor values (?,?,?,?,?)";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, hcod);
        pst.setInt(2, dcod);
        pst.setString(3, ape);
        pst.setString(4, espec);
        pst.setInt(5, sal);
        pst.executeUpdate();
        cn.close();
    }
    
    public void modificarDoctor(int hcod, int dcod, String ape, String espec, int sal) throws SQLException{
        Connection cn = this.getConection();
        String sql = "update doctor set hospital_cod=?, apellido=?, especialidad=?, salario=? where doctor_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, hcod);
        pst.setString(2, ape);
        pst.setString(3, espec);
        pst.setInt(4, sal);
        pst.setInt(5, dcod);
        pst.executeUpdate();
        cn.close();
    }
    
    public void eliminarDoctor(int iddoc) throws SQLException{
        Connection cn = this.getConection();
        String sql = "delete from doctor where doctor_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setInt(1, iddoc);
        pst.executeUpdate();
        cn.close();
    }
}
