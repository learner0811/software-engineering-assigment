/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Mon;
import Model.ThucDon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author ns_red
 */
public class MenuCtr {
    private static Connection con;
    
    public MenuCtr(){
        //singleton partern to make 1 instance
        if (con == null){
            String dbUrl = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.jdbc.Driver";
            try{
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "Huy811");
                System.out.println("Conected");
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public ArrayList<Mon> getDish(String key){
        //varible
        ArrayList<Mon> res = new ArrayList<>();
        Mon dish = null;
        
        //query
        String query = "SELECT * FROM tblmon WHERE ten LIKE ?";
        //getDish that match with the key
        try {
            PreparedStatement ps = con.prepareStatement(query);     
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                //encap the object
                dish = new Mon();
                dish.setId(rs.getInt("idtblMon"));
                dish.setTen(rs.getString("ten"));
                dish.setGia(rs.getDouble("gia"));
                //add to list
                res.add(dish);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //test
        /*for (Mon a : res){
            System.out.println(a.getId() + " " + a.getTen() + " " + a.getGia());
        }*/
        return res;
    }
    
    public boolean SaveMenu(ThucDon menu){
        //varible
        String name;
        String query;
        
        //check if the name is exist
        name = menu.getTen();
        query = "SELECT * FROM tblthucdon WHERE ten = ?";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst())
                return false;
            //has the same name
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        //get id of the max row
        int LastID = 0;
        query = "SELECT MAX(idtblThucDon) as ID FROM tblthucdon";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                LastID = rs.getInt("ID");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        //reset auto increment to the latest recored's id for tblthucdon
        query = "ALTER TABLE tblthucdon AUTO_INCREMENT = ?";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, LastID);
            ps.execute();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        //save to tblThucDon
        query = "INSERT INTO tblthucdon (ten, tongTien) VALUE(?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, menu.getTen());
            ps.setDouble(2, menu.getTongTien());
            ps.execute();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        //save to tblMonTrongThucDon      
        int thucDonID = LastID + 1;
        menu.setId(thucDonID);
        ArrayList<Mon> target = menu.getDanhSachMon();
                
        for (int i = 0; i < target.size(); i++){
            query = "INSERT INTO tblmontrongthucdon (tblThucDonID, tblMonID) VALUE(?,?)";
            int monID = target.get(i).getId();            
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, thucDonID);
                ps.setInt(2, monID);
                ps.execute();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }        
        return true;
    }
}