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
public class ChiSoDien {
    private String maKH;
    private String chiSoDienCu;
    private String chiSoDienMoi;
    private String thang;
    private String nam;
    private int trangThai;
    public ChiSoDien(String maKH, String chiSoDienCu,String chiSoDienMoi,String thang,String nam, int trangThai){
        this.chiSoDienCu= chiSoDienCu;
        this.chiSoDienMoi=chiSoDienMoi;
        this.maKH=maKH;
        this.nam= nam;
        this.thang= thang;
        this.trangThai=trangThai;
    }

    public ChiSoDien() {
        
       
    }

    
    public void setMaKH(String maKH){
        this.maKH= maKH;
    }
    public String getMaKH(){
     return maKH;   
    }

    public String getChiSoDienCu() {
        return chiSoDienCu;
    }

    public void setChiSoDienCu(String chiSoDienCu) {
        this.chiSoDienCu = chiSoDienCu;
    }

    public String getChiSoDienMoi() {
        return chiSoDienMoi;
    }

    public void setChiSoDienMoi(String chiSoDienMoi) {
        this.chiSoDienMoi = chiSoDienMoi;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
