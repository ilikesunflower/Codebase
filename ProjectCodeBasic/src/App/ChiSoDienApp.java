/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import CheckData.CheckData;
import Object.ChiSoDien;
import Object.KhachHang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
public class ChiSoDienApp {

    ArrayList<ChiSoDien> listCSD = new ArrayList<ChiSoDien>();
    ArrayList<ChiSoDien> listCSD1 = new ArrayList<ChiSoDien>();
    CheckData check = new CheckData();

    public void nhapThoiGian() {
        Scanner sc = new Scanner(System.in);
        String time;
        do {
            System.out.println("Mời bạn nhập tháng năm tạo chỉ số điện(VD:9_2018):");
            time = sc.nextLine();
            if (check.kiemTraDinhDangThangNam(time) == false) {
                System.err.println("Định dạnh ngày sai.Hãy nhập lại theo cú pháp 9_2018");
            }
        } while (check.kiemTraDinhDangThangNam(time) == false);
        taoFolderTheoNam(time);
        taoFileMoi(time);
         String check;
        do {
            nhapMaKH(time);
            System.out.println("Nhaạp 0 nếu bạn muốn thoát khỏi chương trình này hoặc Enter để tiếp tục!");
            check = sc.nextLine();
            if (check.equals(0)) {
                
            }
        } while (check.equals(""));
    }

    public void taoFolderTheoNam(String time) {
        String arr[] = time.split("_");
        File folderYear = new File("data/CD/" + arr[1]);
        if (!folderYear.exists()) {
            folderYear.mkdir();
        }
    }

    public void taoFileMoi(String time) {
        String arr[] = time.split("_");
        File file = new File("data/CD/" + arr[1] + "/" + time + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Tạo mới file" + time + "thành công!");
            } catch (IOException ex) {
                Logger.getLogger(ChiSoDienApp.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Đã có file " + time + ".Hãy nhập dữ liệu vào.");
        }
    }

    public void nhapMaKH(String time) {
        String arrTime[] = time.split("_");
        String thangHienTai = arrTime[0];
        String namHienTai = arrTime[1];
        Scanner sc = new Scanner(System.in);
        KhachHangApp1 khApp = new KhachHangApp1();
        String MaKH;
        do {
            System.out.println("Mời bạn nhập mã khách hàng:");
            MaKH = sc.nextLine();

            khApp.docFileKH();
            if (khApp.kiemTraMaKH(MaKH)) {
                ArrayList<KhachHang> list = new ArrayList<KhachHang>();
                list.add(khApp.timKiemMotKhachHang(MaKH));
                khApp.xemDanhSach(list);
                nhapCSDMoi(MaKH, thangHienTai, namHienTai);
            } else {
                System.out.println("Mã KH bạn vừa nhập không có trên hệ thống. Vui lòng nhập lại");
            }
        } while (!khApp.kiemTraMaKH(MaKH));

    }
    public void nhapCSDMoi(String maKH, String thang, String nam) {
        KhachHangApp1 khApp = new KhachHangApp1();
        khApp.docFileKH();
        Scanner sc = new Scanner(System.in);
        docChiSoDienTheoThanghienTai(thang, nam);
        int dem = 0;
        for (int i = 0; i < listCSD1.size(); i++) {
            if (maKH.equals(listCSD1.get(i).getMaKH())) {
                System.out.println("Chỉ số điện tháng " + thang + "/" + nam + " của KH " + khApp.timKiemMotKhachHang(maKH).getHoTen());
                dem++;
                break;
            }
        }
        int CSDMoiThangTruoc;
        if (dem == 0) {
            int CSDMoi;
            if (thang.equals(1)) {
                nam = String.valueOf(Integer.parseInt(nam) - 1);
                CSDMoiThangTruoc = layChiSoDienThangTruoc(maKH, "12", nam);
            } else {
                CSDMoiThangTruoc = layChiSoDienThangTruoc(maKH, String.valueOf(Integer.parseInt(thang) - 1), nam);
            }
            do {
                System.out.println("Mời bạn nhập chỉ số điện mới của KH: ");
                CSDMoi = sc.nextInt();
                if (CSDMoi < CSDMoiThangTruoc) {
                    System.out.println("Chỉ số điện mới phải lớn hơn hoặc bằng chỉ số điện cũ.");
                    System.out.println("Chỉ số điện đến tháng " + String.valueOf(Integer.parseInt(thang) - 1) + " năm " + nam + " ");
                }
            } while (CSDMoi < layChiSoDienThangTruoc(maKH, String.valueOf(Integer.parseInt(thang) - 1), nam));
            docChiSoDienTheoThanghienTai(thang, nam);
            ChiSoDien csd = new ChiSoDien();
            csd.setMaKH(maKH);
            csd.setChiSoDienCu(String.valueOf(CSDMoiThangTruoc));
            csd.setChiSoDienMoi(String.valueOf(CSDMoi));
            csd.setThang(thang);
            csd.setNam(nam);
            csd.setTrangThai(0);
            listCSD1.add(csd);
            ghiDuLieuVaoFile(thang, nam);
        }else{
            System.out.println("Bạn có muốn cập nhật lại chỉ số điện. Vui lòng chọn 1-Có, 2-Không!");
            int choose= sc.nextInt();
            switch (choose) {
                case 1:
                    chinhSuaChiSoDienMoi(maKH, thang, nam);
                    break;
                case 2:
                    nhapMaKH(thang+ "_"+ nam);
                    break;    
            }
        }
    }
    
    public void ghiDuLieuVaoFile(String thang, String nam) {
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            for (int i = 0; i < listCSD1.size(); i++) {
                String row = "";
                row += listCSD1.get(i).getMaKH() + "\t";
                row += listCSD1.get(i).getChiSoDienCu() + "\t";
                row += listCSD1.get(i).getChiSoDienMoi() + "\t";
                row += listCSD1.get(i).getThang() + "\t";
                row += listCSD1.get(i).getNam() + "\t";
                row += listCSD1.get(i).getTrangThai() + "\n";
                data += row;
            }
            fw = new FileWriter("data/CD/" + nam + "/" + thang + "_"+ nam +".txt");
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ChiSoDienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int layChiSoDienThangTruoc(String maKH, String thang, String nam) {
        docChiSoDienTheoThang(thang, nam);
        for (int i = 0; i < listCSD.size(); i++) {
            if (maKH.equals(listCSD.get(i).getMaKH())) {
                return ((Integer.decode(listCSD.get(i).getChiSoDienMoi())));
            }
        }
        return 0;
    }
    
    public void docChiSoDienTheoThang(String thang, String nam) {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            listCSD = new ArrayList<>();
            fr = new FileReader("data/CD/" + nam + "/" + thang + "_" + nam + ".txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    ChiSoDien csd = new ChiSoDien();
                    csd.setMaKH(arr[0]);
                    csd.setChiSoDienCu(arr[1]);
                    csd.setChiSoDienMoi(arr[2]);
                    csd.setThang(arr[3]);
                    csd.setNam(arr[4]);
                    csd.setTrangThai(Integer.parseInt(arr[5]));
                    listCSD.add(csd);
                }
            } catch (IOException ex) {
                Logger.getLogger(ChiSoDienApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChiSoDienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       ArrayList<ChiSoDien> listCSDThangHienTai = new ArrayList<ChiSoDien>();
    public void docChiSoDienTheoThanghienTai(String thang, String nam) {
        try {
            BufferedReader br = null;
            FileReader fr = null;
         
            fr = new FileReader("data/CD/" + nam + "/" + thang + "_" + nam + ".txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    ChiSoDien csd = new ChiSoDien();
                    csd.setMaKH(arr[0]);
                    csd.setChiSoDienCu(arr[1]);
                    csd.setChiSoDienMoi(arr[2]);
                    csd.setThang(arr[3]);
                    csd.setNam(arr[4]);
                    csd.setTrangThai(Integer.parseInt(arr[5]));
                    listCSDThangHienTai.add(csd);
                }
            } catch (IOException ex) {
                Logger.getLogger(ChiSoDienApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChiSoDienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ChiSoDien layChiSoDien(String maKh, String thang, String nam) {
        docChiSoDienTheoThanghienTai(thang, nam);
        for (int i = 0; i < listCSDThangHienTai.size(); i++) {
            if (maKh.equals(listCSDThangHienTai.get(i).getMaKH())) {
                return listCSDThangHienTai.get(i);
            }
        }
        return null;
    }
    public void chinhSuaChiSoDienMoi(String maKH, String thang, String nam){
        Scanner sc= new Scanner(System.in);
        int check= 0;
        docChiSoDienTheoThanghienTai(thang, nam);
        for (int i = 0; i < listCSD1.size(); i++) {
            if (maKH.equals(listCSD1.get(i).getMaKH())) {
                if (listCSD1.get(i).getTrangThai()==0) {
                    ChiSoDien csd= listCSD1.get(i);
                    System.out.println("Mời bạn nhập chỉ số điện mới: ");
                    csd.setChiSoDienMoi(sc.nextLine());
                    System.out.println("Cập nhật chỉ số điện mới thành công!");
                    listCSD1.set(i, csd);
                    ghiDuLieuVaoFile(thang, nam);
                    check=1;
                    break;
                }
            }
        }
        if (check !=1) {
            System.out.println("Bạn không thể sửa chỉ số điện của KH "+maKH+" .Do chỉ số điện tháng này đã được tạo hóa đơn!");
        }
    }
}
    
    
    

    
    