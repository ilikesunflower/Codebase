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
public class KhachHang {
    private String maKh;
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private String soCongTo;
    public KhachHang(){
        
    }
    public KhachHang(String maKh, String hoTen, String soDienThoai,String email,String diaChi,String SoCongTo){
        this.soCongTo= soCongTo;
        this.diaChi= diaChi;
        this.email=email;
        this.hoTen= hoTen;
        this.soDienThoai= soDienThoai;
        this.maKh= maKh;
    }
    public String getMaKh(){
        return maKh;
    }
    public void setMaKh(String maKh){
        this.maKh=maKh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoCongTo() {
        return soCongTo;
    }

    public void setSoCongTo(String soCongTo) {
        this.soCongTo = soCongTo;
    }
    public void inTT(){
        System.out.printf("|%9s|%21s|%13s|%26s|%10s|%12s|\n", maKh,hoTen,soDienThoai,email,diaChi,soCongTo);
    }
}
    