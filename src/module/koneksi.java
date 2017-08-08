/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.sql.Connection;
//import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class koneksi {
    private static Connection koneksi;
    public static Connection GetConnection(){
        try {
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/primer_koperasi_kartika","root","");
            
        } catch (Exception e) {
        }
        return koneksi;
    }
    
    public Object getConnection(){
        throw new UnsupportedOperationException("not support");
    }  
    
    public static void main(String[] args) {
       
    }
}
