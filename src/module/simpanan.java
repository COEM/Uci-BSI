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
import static module.umum.buat_kode;
/**
 *
 * @author UCI
 */
public class simpanan {
    
    public static void tambah(String no,String no_anggota,String username,Integer sukarela, Integer wajib, String tanggal) {
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "insert into simpanan values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no);
            pst.setString(2,no_anggota);
            pst.setString(3, username);
            pst.setInt(4, sukarela);
            pst.setInt(5, wajib);
            pst.setString(6, tanggal);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
    }
    
    public static void update(String no, String id_anggota,Integer sukarela, Integer wajib, String tanggal){
         try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update simpanan set id_anggota = ?,sukarela = ?, wajib = ?, tanggal=? where no = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,id_anggota);
            pst.setInt(2, sukarela);
            pst.setInt(3,wajib);
            pst.setString(4,tanggal);
            pst.setString(5,no);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString()); 
        }
    }
    public static void hapus(String no){
         try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "delete from simpanan where no=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());  
        }
    }
    
    public static ResultSet cari(String text){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from simpanan where no_anggota like '%"+text+"%' or no like '%"+text+"%'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return data;
    }
    public static ResultSet data(){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from simpanan";
            data = query.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return data;
    }
    
    public static String buat_kode(){
        return umum.buat_kode("SP", "no", "simpanan");
    }
}
