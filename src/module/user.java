/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {
    public static void tambah(String username,String tipe, String password) {
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "insert into user values (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,tipe);
            pst.setString(3, password);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
    }
    
    public static void update(String username,String tipe, String password){
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update user set username = ?,tipe = ?, password = ? where username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2, tipe);
            pst.setString(3, password);
            pst.setString(4, username);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static void hapus(String username){
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "delete from user where usernmae = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static ResultSet data(){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from user";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static ResultSet cari(String username){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from user where username like '%"+username+"%'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static void main(String[] args) {
        tambah("admin", "bendahara", "admin");
    }
}
