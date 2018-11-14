/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Trần Thị Thu Hiền
 */
public class HoaDon {
    private String maHD;
    private String maKH;
    private String thang;
    private String nam;
    private int tongSoDien;
    private int ThanhTien;
    public HoaDon(){
        
    }
    public HoaDon(String maHD, String maKH, String thang, String nam, int tongSoDiem, int THanhTien){
        this.ThanhTien= ThanhTien;
        this.maHD= maHD;
        this.maKH= maKH;
        this.nam= nam;
        this.tongSoDien= tongSoDien;
        this.thang= thang;
        
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public int getTongSoDien() {
        return tongSoDien;
    }

    public void setTongSoDien(int tongSoDien) {
        this.tongSoDien = tongSoDien;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

 
    
}
