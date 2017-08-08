/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static module.angsuran.cariDataLengkap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class umum {
//    public static  Connection con = koneksi.GetConnection();
//    public static Statement query = con.createStatement();
    
    public static String tanggal(Date tgl){
//        Calendar date = tgl.getInstance();
//        date.add(Calendar.DATE, 1);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//        return format.format(date.getTime());
            String x = new SimpleDateFormat("yyyy/MM/dd").format(tgl.getDate());
        return x;
    }
    
    public static String buat_kode(String kode, String kolom, String tabel){
        String x = "";
        try {
            Integer y = 0;
            Connection con = koneksi.GetConnection();
            Statement query = con.createStatement();
            String sql = "select count("+kolom+") as no from "+tabel+" order by no desc limit 1";
            ResultSet data  = query.executeQuery(sql);
            while (data.next()) {                  
                y = Integer.parseInt(data.getString("no"));
            }
            if (data.first()) {
                if (y <9) {
                    x = kode+"0000"+(y+1);
                } else if (y >= 9){
                    x = kode+"000"+ (y+1);
                }else if (y >= 99){
                    x = kode+"00"+ (y+1);
                } else if (y >= 999){
                    x = kode+"0"+ (y+1);
                }
            } else{
                x = kode+"00001";
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
        return x;
    }
    
    public static void showRS(ResultSet data){
        try {
            ResultSetMetaData rsmd = data.getMetaData();
            System.out.println("querying............");
            int columnsNumber = rsmd.getColumnCount();
            while (data.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = data.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                    System.out.println("");
                }
            } catch (Exception e) {
                
        }
    }
    
    public static void cetakLaporan(String laporan){
        JasperReport report;
        JasperPrint print;
        JasperDesign design;
        try {
            Connection con = koneksi.GetConnection();
            File file = new File("src/report/"+laporan+".jrxml");
            design = JRXmlLoader.load(file);
            report = JasperCompileManager.compileReport(design);
            print = JasperFillManager.fillReport(report, null, con);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
    }
    
    public static void cetakLaporanTanggal(String laporan,String tglAwal, String tglAkhir){
        JasperReport report;
        JasperPrint print;
        JasperDesign design;
        HashMap hash = new HashMap(2);
        hash.put("tgl_awal", tglAwal);
        hash.put("tgl_akhir", tglAkhir);
        try {
            Connection con = koneksi.GetConnection();
            File file = new File("src/report/"+laporan+".jrxml");
            design = JRXmlLoader.load(file);
            report = JasperCompileManager.compileReport(design);
            print = JasperFillManager.fillReport(report, hash, con);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
        }
    }
    
}
