/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ns_red
 */
public class LoginCtr {
    private static Connection con;
    
    public LoginCtr(){
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
    
    public boolean checkLogin(String userName, String PassWord){
        //userName is id, passWord is matkhau
        //check if userName is an Integer
        if (!userName.matches("\\d+"))
            return false;
        int id = 0;
        try{
            id = Integer.parseInt(userName);
        } catch (NumberFormatException ex){
            return false;
        }
        
        //check if password and user name exist in table
        String query = "SELECT * FROM tblnguoidung WHERE idtblNguoiDung = ? AND matKhau = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, PassWord);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                return false;            
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }
}
