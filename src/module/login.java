/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author user
 */
public class login {
    private static String username,type;
    public static boolean login(String username, String password){
        boolean status = false;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from user where username ='"+username+"' and password ='"+password+"'";
            ResultSet data  = query.executeQuery(sql);
                while (data.next()) {                  
                   status = true;
                   login.setUserName(data.getString("username"));
                   login.setType(data.getString("tipe"));
               }
            } catch (Exception e) {
                
            }
        return status;
    }
    
    public static void setUserName(String user){
        username = user;
    }
    
    public static String getUserName(){
        return username;
    }
    
    public static void setType(String tipe){
        type = tipe;
    }
    
    public static String getType(){
        return type;
    }
}
