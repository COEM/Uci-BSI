/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class anggota {
    private static String selectedAnggota;
    public static void tambah(String id,String nama, String nrp, String pangkat) {
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "insert into anggota values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,null);
            pst.setString(2,id);
            pst.setString(3, nama);
            pst.setString(4, nrp);
            pst.setString(5, pangkat);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static void update(String id,String nama, String nrp, String pangkat){
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update anggota set nama = ?,nrp = ?, pangkat = ? where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,nama);
            pst.setString(2, nrp);
            pst.setString(3, pangkat);
            pst.setString(4, id);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static void hapus(String id){
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "delete from anggota where id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,id);
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
            String sql = "select * from anggota";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static ResultSet getAnggota(String id){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from anggota where id = '"+id+"'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static ResultSet cari(String id){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from anggota where id like '%"+id+"%' or nama like '%"+id+"%'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static String buat_kode(){
        return umum.buat_kode("AG", "no", "anggota");
    }
    
    public static void setId(String id){
        selectedAnggota = id;
    }
    
    public static String getId(){
        return selectedAnggota;
    }
}
