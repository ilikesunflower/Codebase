/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import CheckData.CheckData;
import CheckData.Utils;
import Object.KhachHang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trần Thị Thu Hiền
 */
public class KhachHangApp1 {

    ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
    private String filename = "data/KhachHang.txt";
    CheckData check = new CheckData();
    Utils util = new Utils();

    public void docFileKH() {
        try {
            listKH = new ArrayList<KhachHang>();
            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            while ((s = br.readLine()) != null) {
                String arr[] = s.split("\t");
                KhachHang kh = new KhachHang();
                kh.setMaKh(arr[0]);
                kh.setHoTen(arr[1]);
                kh.setSoDienThoai(arr[2]);
                kh.setEmail(arr[3]);
                kh.setDiaChi(arr[4]);
                kh.setSoCongTo(arr[5]);
                listKH.add(kh);
            }
        } catch (IOException ex) {
            Logger.getLogger(KhachHangApp1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ghiFileKH() {
       
        FileWriter fr = null;
        
        try {
            String data = "";

            for (int i = 0; i < listKH.size(); i++) {
                String row = "";
                row += listKH.get(i).getMaKh() + "\t";
                row += listKH.get(i).getHoTen() + "\t";
                row += listKH.get(i).getSoDienThoai() + "\t";
                row += listKH.get(i).getEmail() + "\t";
                row += listKH.get(i).getDiaChi() + "\t";
                row += listKH.get(i).getSoCongTo() + "\n";
                data += row;
            }
            fr = new FileWriter(this.filename);
           
            BufferedWriter br = new BufferedWriter(fr);
            
            br.write(data);
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHangApp1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(KhachHangApp1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void menu() {
        docFileKH();
        int a;
        do{
            Scanner sc = new Scanner(System.in);
        System.out.println("+-------------------QUẢN LÝ KHÁCH HÀNG-----------------+");
        System.out.println("|                   1.XEM DAMH SÁCH                    |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("|              2.THÊM MỚI MỘT KHÁCH HÀNG               |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("|             3.SỬA THÔNG TIN KHÁCH HÀNG               |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("|             4.XÓA THÔNG TIN KHÁCH HÀNG               |");
        System.out.println("+------------------------------------------------------|");
        System.out.println("|              5.QUAY TRỞ LẠI MENU CHÍNH               |");
        System.out.println("+------------------------------------------------------|");
        System.out.println("Chọn nhiệm vụ:");
         a = sc.nextInt();

        switch (a) {
            case 1:
                System.out.println("1.XEM DAMH SÁCH  ");
                docFileKH();
                new KhachHangApp1().xemDanhSach(listKH);
                break;
            case 2:
                System.out.println("2.THÊM MỚI MỘT KHÁCH HÀNG");
                new KhachHangApp1().themKH();
                break;
            case 3:
                System.out.println("3.SỬA THÔNG TIN KHÁCH HÀNG ");
                new KhachHangApp1().suaKH();
                break;
            case 4:
                System.out.println("4.XÓA THÔNG TIN KHÁCH HÀNG ");
                new  KhachHangApp1().xoaKH();
                break;
            case 5: 
                System.out.println(" 5.QUAY TRỞ LẠI MENU CHÍNH");
                break;
            default:
                System.out.println("Nhập sai.Hãy nhập lại");
        }
        }while(a!=5);
    }

    public void xemDanhSach(ArrayList<KhachHang> list) {
        if (list.isEmpty()) {
            System.err.println("Chưa có khách hàng nào trong danh sách");
        } else {
            System.out.println("+------------------------------------------------------------------------------------------------+");
            System.out.println("|                             DANH SÁCH KHÁCH HÀNG                                               |");
            System.out.println("+------------------------------------------------------------------------------------------------+");
            System.out.println("|  Ma KH  |     Họ Tên          |   SDT       |        EMAIL             |ĐỊA CHỈ   |SỐ CÔNG TƠ  |");
            System.out.println("+------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).inTT();
            }
            System.out.println("+------------------------------------------------------------------------------------------------+");
        }

    }

    public boolean kiemTraMaKH(String maKH) {
        boolean check = false;
        KhachHang kh = new KhachHang();
        for (int i = 0; i < listKH.size(); i++) {
            kh = listKH.get(i);
            if (maKH.equalsIgnoreCase(kh.getMaKh())) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void themKH() {
        KhachHang kh = new KhachHang();
        Scanner sc = new Scanner(System.in);
        System.out.println("Mời Nhập Thông TIn Để Tạo Mới Khách Hàng");
        do {
            System.out.println("Nhập Mã Khách Hàng:");
            kh.setMaKh(sc.nextLine());
            if ("".equals(kh.getMaKh()) == true) {
                System.out.println("Mã Khách Hàng Không được rỗng");
            }
            if (kiemTraMaKH(kh.getMaKh()) == true) {
                System.out.println("MÃ Khách Hàng Đã Tồn Tại|Thêm Không Thành Công");
            }
        } while ("".equals(kh.getMaKh()) || kiemTraMaKH(kh.getMaKh()));
        
        do {
            System.out.println("Nhập Họ Tên Khach Hàng:");
            kh.setHoTen(sc.nextLine());
            if (check.kiemTraTen(kh.getHoTen()) == false) {
                System.err.println("Tên không được có số hoặc rỗng");
            }
        } while (!check.kiemTraTen(kh.getHoTen()));
        kh.setHoTen(util.chuanhoa(kh.getHoTen()));
        
        do {
            System.out.println("Nhập Số Diện Thoại Khách Hàng:");
            kh.setSoDienThoai(sc.nextLine());

        } while (!check.kiemTraSoDienTHoai(kh.getSoDienThoai()));
        
        do {
            System.out.println("Nhâp Email Khách Hàng:");
            kh.setEmail(sc.nextLine());
        } while (!check.kiemTRaEmail(kh.getEmail()));
        
        do {
            System.out.println("Nhập Địa CHỉ Khách Hàng:");
            kh.setDiaChi(sc.nextLine());
            if ("".equals(kh.getDiaChi())) {
                System.out.println("Địa chỉ không được bỏ trống");
            }
        } while ("".equals(kh.getDiaChi()));
        
        do {
            System.out.println("Nhập Số Công Tơ Khách Hàng:");
            kh.setSoCongTo(sc.nextLine());
            if ("".equals(kh.getSoCongTo())) {
                System.out.println("Số công tơ không được bỏ trống");
            }
//            if(check.kiemTraSoCongTo(kh.getSoCongTo())==false){
//                System.err.println("Số công tơ phải là số");
//            }
        } while ("".equals(kh.getSoCongTo()));
        docFileKH();
        listKH.add(kh);
        ghiFileKH();
        System.out.println("Thêm Mới Khách Hàng Thanhf Công!");

    }

    public void suaKH() {
        Scanner sc = new Scanner(System.in);
        KhachHang kh;
        do {
            System.out.println("Mời Bạn Nhập Mã KH Cần Sửa:");
            String MaKH = sc.nextLine();
            kh = timKiemMotKhachHang(MaKH);
            if (kh == null) {
                System.out.println("Mã không tồn tại.");
                System.out.println("Vui lòng nhập lại.");
            }
        } while (kh == null);
        if (kh != null) {
            String hotencu = kh.getHoTen();
            String emailcu = kh.getEmail();
            String soDienThoaiCu = kh.getSoDienThoai();
            String diaChiCu = kh.getDiaChi();
            String soCongToCu = kh.getSoCongTo();
            System.out.println("Mời Bạn Cập NHật Thông Tin KHách Hàng.");
            System.out.println("Lưu ý: Nhấn Enter là bỏ qua không cập nhật thông tin ấy");
            System.out.println("Mã khách hàng:" + kh.getMaKh());
            System.out.println("HỌ tên cũ:" + hotencu);
            do {
                System.out.println("Nhâp tên khách hàng mới:");
                kh.setHoTen(sc.nextLine());
                if (kh.getHoTen().equalsIgnoreCase("")) {
                    kh.setHoTen(hotencu);
                    break;
                }
                if (check.kiemTraSuaTen(kh.getHoTen()) == false) {
                    System.err.println("Tên không được có số ");
                }
            } while (!check.kiemTraSuaTen(kh.getHoTen()));
            
            kh.setHoTen(util.chuanhoa(kh.getHoTen()));
            System.out.println("Số điện thoại cũ:" + soDienThoaiCu);
            do {
                System.out.println("Nhập Số Diện Thoại mới:");
                kh.setSoDienThoai(sc.nextLine());
                if (kh.getSoDienThoai().equalsIgnoreCase("")) {
                    kh.setSoDienThoai(soDienThoaiCu);
                    break;
                }
            } while (!check.kiemTraSoDienTHoai(kh.getSoDienThoai()));
            
            System.out.println("Email cũ:" + emailcu);
            do {
                System.out.println("Nhâp Email Khách Hàng:");
                kh.setEmail(sc.nextLine());
                if (kh.getEmail().equalsIgnoreCase("")) {
                    kh.setEmail(emailcu);
                    break;
                }
            } while (!check.kiemTRaEmail(kh.getEmail()));
            
            System.out.println("Địa chỉ cũ:" + diaChiCu);
            
                System.out.println("Nhập Địa CHỉ Khách Hàng:");
                kh.setDiaChi(sc.nextLine());
                if ("".equals(kh.getDiaChi())) {
                    kh.setDiaChi(diaChiCu);
                }
            
            System.out.println("Số công tơ cũ:" + soCongToCu);
//            do {
                System.out.println("Nhập Số Công Tơ Khách Hàng:");
                kh.setSoCongTo(sc.nextLine());
                if ("".equals(kh.getSoCongTo())) {
                    kh.setSoCongTo(soCongToCu);
//                    break;
                }
//                if(check.kiemTraSoCongTo(kh.getSoCongTo())==false){
//                    System.err.println("Số công tơ phải là số");
//                }
//            } while (check.kiemTraSoCongTo(kh.getSoCongTo())==false);
            listKH.set(chiso, kh);
            ghiFileKH();
            System.out.println("Cập nhật thông tin của Khách Hàng" + kh.getMaKh() + " Thanhf Công!");

        }

    }

    public void xoaKH() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Mời Bạn Nhập Mã Cần Xóa:");
        String MaKH = sc.nextLine();
        KhachHang kh = timKiemMotKhachHang(MaKH);
        if (kh == null) {
            System.out.println("KHách hàng không tồn tại trong hệ thống");
        } else {
            listKH.remove(kh);
            System.out.println("Xóa thành công mã " + kh.getMaKh());
            ghiFileKH();
        }
    
    }

    public ArrayList<KhachHang> timKiemTheoTuKhoa() {
        ArrayList<KhachHang> listTimKiemKH = new ArrayList<KhachHang>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tư khóa cần tìm kiếm:");
        String input = sc.nextLine();
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).getDiaChi().contains(input) || listKH.get(i).getEmail().contains(input) || listKH.get(i).getHoTen().contains(input) || listKH.get(i).getMaKh().contains(input) || listKH.get(i).getSoCongTo().contains(input) || listKH.get(i).getSoDienThoai().contains(input)) {
                listTimKiemKH.add(listKH.get(i));
            }
        }
        return listTimKiemKH;
    }
int chiso;
    public KhachHang timKiemMotKhachHang(String MaKh) {
        
        docFileKH();
        for (int i = 0; i < listKH.size(); i++) {
            if (MaKh.equals(listKH.get(i).getMaKh())) {
               chiso=i;
                return listKH.get(i);
            }
        }
        return null;
    }
}
