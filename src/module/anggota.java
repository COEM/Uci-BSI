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
    public static void tambah(String no,String nama, String nrp, String pangkat) {
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "insert into anggota values (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1,null);
            pst.setString(1,no);
            pst.setString(2, nama);
            pst.setString(3, nrp);
            pst.setString(4, pangkat);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static void update(String no,String nama, String nrp, String pangkat){
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update anggota set nama = ?,nrp = ?, pangkat = ? where no = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,nama);
            pst.setString(2, nrp);
            pst.setString(3, pangkat);
            pst.setString(4, no);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static void hapus(String no){
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "delete from anggota where no = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no);
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
    
    public static ResultSet getAnggota(String no){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from anggota where no = '"+no+"'";
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
            String sql = "select * from anggota where no like '%"+id+"%' or nama like '%"+id+"%'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static String buat_kode(){
        return umum.buat_kode("AG", "no", "anggota");
    }
    
    public static void setId(String no){
        selectedAnggota = no;
    }
    
    public static String getId(){
        return selectedAnggota;
    }
}
