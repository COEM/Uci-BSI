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

/**
 *
 * @author user
 */
public class angsuran {
    public static void tambah(String no,String no_pinjam,String username,Integer ke, Integer jumlah, Integer jasa, String tanggal) {
        try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "insert into angsuran values (?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no);
            pst.setString(2,no_pinjam);
            pst.setString(3,username);
            pst.setInt(4, ke);
            pst.setInt(5, jumlah);
            pst.setInt(6, jasa);
            pst.setString(7, tanggal);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public static void update (String no_pinjam,Integer ke, Integer jumlah, Integer jasa, String tanggal){
         try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "update angsuran set no_pinjam = ?,jumlah = ?, jasa =?, tanggal = ? where no_pinjam=? and tanggal = ? and angsuran_ke = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no_pinjam);
            pst.setInt(2, jumlah);
            pst.setInt(3, jasa);
            pst.setString(4,tanggal);
            pst.setString(5,no_pinjam);
            pst.setString(6,tanggal);
            pst.setInt(6,ke);
            pst.execute(); 
        } catch (Exception e) {
            System.out.println(e.toString());  
        }
    }
    
    public static void hapus (String no_pinjam,Integer ke,String tanggal){
         try {
            Connection con = koneksi.GetConnection();
            Statement st = con.createStatement();
            String sql = "delete from simpanan where id_anggota=? and ke=? and tanggal=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,no_pinjam);
            pst.setInt(2, ke);
            pst.setString(3, tanggal);
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
            String sql = "select * from angsuran where no_pinjam like '%"+text+"'";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static ResultSet data (){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from angsuran";
            data = query.executeQuery(sql);
        } catch (Exception e) {
        }
        return data;
    }
    
    public static Integer ke(String no_pinjam){
        Integer x = 1;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select max(angsuran_ke) as ke from angsuran where no_pinjam ='"+no_pinjam+"'";
            ResultSet data  = query.executeQuery(sql);
            if (data.first()) {
                x = Integer.parseInt(data.getString("ke")) + 1;
            } else{
                x = 1;
            }
        } catch (Exception e) {
            
        }
        return x;
    }
    
    public static Integer perBulan(String no_pinjam){
        Double lama,jumlah;
        Double perBulan = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from pinjaman where no ='"+no_pinjam+"'";
            ResultSet data  = query.executeQuery(sql);
            if (data.first()) {
                lama = Double.parseDouble(data.getString("lama_pinjaman"));
                jumlah = Double.parseDouble(data.getString("jumlah"));
                perBulan = jumlah/lama;
            } else{
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return perBulan.intValue();
    }
    
    public static ResultSet detail(String no_pinjam){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select * from angsuran where no_pinjam = '"+no_pinjam+"' order by angsuran_ke asc";
            data = query.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return data;
    }
    
    public static ResultSet dataLengkap(){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select pinjaman.no as no, pinjaman.no_anggota as no, angsuran.angsuran_ke as ke, angsuran.jumlah_angsuran as jumlah, angsuran.jasa as jasa,angsuran.tanggal as tanggal from angsuran,anggota,pinjaman where angsuran.no_pinjam = pinjaman.no and pinjaman.no_anggota = anggota.no";
            data = query.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return data;
    }
    
    public static ResultSet cariDataLengkap(String text){
        ResultSet data = null;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select pinjaman.no as no, pinjaman.no_anggota as no, angsuran.angsuran_ke as ke, angsuran.jumlah_angsuran as jumlah, angsuran.jasa as jasa,angsuran.tanggal as tanggal from angsuran,anggota,pinjaman where angsuran.no_pinjam = pinjaman.no and pinjaman.no_anggota = anggota.no and (pinjaman.no_anggota like '"+text+"%' or pinjaman.no like '"+text+"%')";
            data = query.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return data;
    }
    
    
    public static String buatKode(){
        return umum.buat_kode("AGS", "no", "angsuran");
    }
    
    public static Integer jasa(String no_pinjam){
        Double x = 0.0;
        try {
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select jumlah * 1/100 as jasa from pinjaman where no = '"+no_pinjam+"'";
            ResultSet data  = query.executeQuery(sql);
            if (data.first()) {
                x = Double.parseDouble(data.getString("jasa"));
            } else{
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return x.intValue();
    }
    
    public static void main(String[] args) {
        Double x= 12.00;
        System.out.println(buatKode());
    }
}
