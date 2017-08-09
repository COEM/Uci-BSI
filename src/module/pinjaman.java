package module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class pinjaman {
    
    private static String selectedPinjaman;
    
    public static void tambah(String no,String no_anggota,String username,Integer lama, Integer jumlah, String tanggal) {
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "insert into pinjaman values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no);
            pst.setString(2,no_anggota);
            pst.setString(3,username);
            pst.setInt(4, lama);
            pst.setInt(5, jumlah);
            pst.setString(6,"belum_lunas");
            pst.setString(7, tanggal);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static void update (String no,String no_anggota,Integer lama, Integer jumlah, String tanggal){
         try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update pinjaman set no_anggota = ?,lama = ?, jumlah = ?, tanggal=? where no=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no_anggota);
            pst.setInt(2, lama);
            pst.setInt(3,jumlah);
            pst.setString(4,tanggal);
            pst.setString(5,no);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static void hapus (String no){
         try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "delete from pinjaman where no=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static ResultSet cari (String text){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from pinjaman where no like '"+text+"%' or no_anggota like '"+text+"%'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static String namaPeminjam (String no_pinjam){
        String nama = null;
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select anggota.nama as nama from pinjaman,anggota where pinjaman.no_anggota = anggota.no and pinjaman.no = '"+no_pinjam+"'";
            data = query.executeQuery(sql);
            while (data.next()) {                  
                nama = data.getString("nama");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return nama;
    }
    
    public static ResultSet data (){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from pinjaman";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static void updateLunas(String no_pinjam){
        
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update pinjaman set status = 'lunas' where no_pinjam = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no_pinjam);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static Integer totalKembali(String no_pinjam){
        String total = null;
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select ((jumlah * 1/100)*lama_pinjaman)+jumlah as total from pinjaman where no = '"+no_pinjam+"'";
            data = query.executeQuery(sql);
            while (data.next()) {                  
                total = data.getString("total");
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        Double x = Double.parseDouble(total);
        return x.intValue();
    }
    
    public static String buat_kode(){
        return umum.buat_kode("PJ", "no", "pinjaman");
    }
    
    public static void setNo(String no){
        selectedPinjaman = no;
    }
    
    public static String getNo(){
        return selectedPinjaman;
    }
    
    public static void main(String[] args) {
        System.out.println(totalKembali("PJ00001"));;
    }    
}
