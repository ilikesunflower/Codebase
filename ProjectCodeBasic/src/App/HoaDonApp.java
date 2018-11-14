/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author Trần Thị Thu Hiền
 */
import CheckData.CheckData;
import CheckData.DocSo;
import CheckData.Utils;
import Object.ChiSoDien;
import Object.HoaDon;
import Object.KhachHang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HoaDonApp {

    CheckData check = new CheckData();
    ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choose;
        do {
            System.out.println("+-----------------------------------------+");
            System.out.println("+      [1]. Xem danh sách hóa đơn         +");
            System.out.println("+-----------------------------------------+");
            System.out.println("+      [2]. Tạo hóa đơn theo tháng        +");
            System.out.println("+-----------------------------------------+");
            System.out.println("+      [3]. Xem chi tiết một hóa đơn      +");
            System.out.println("+-----------------------------------------+");
            System.out.println("+      [4]. Thoát                         +");
            System.out.println("Mời bạn chọn chức năng: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    xemDanhSachHoaDon();
                    break;
                case 2:
                    taoHoaDonTheoThang();
                    break;
                case 3:
                    inHoaDonChiTiet();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Bạn chọn sai chức năng! ");
                    break;
            }

        } while (choose != 4);

    }

    public void taoFileHoaDonTheoThang(String time) {
        String arr[] = time.split("_");
        if (kiemTraTonTaiFileCSD(time)) {
            File file = new File("data/HD/" + arr[1] + "/" + time + ".txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    System.out.println("Tạo mới file HD " + time + " thành công! ");
                    themDuLieuHoaDonTheoThang(time);
                } catch (IOException ex) {
                    Logger.getLogger(HoaDonApp.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                themDuLieuHoaDonTheoThang(time);
            }
        } else {
            System.err.println("Hiện tại chưa có dữ liệu về chỉ số điện tháng " + time + " !");
        }
    }

    public String nhapThoiGian() {
        Scanner sc = new Scanner(System.in);
        String time;
        do {
            System.out.println("Mời bạn nhập vào tháng năm muốn xem hóa đơn(VD: 9_2018): ");
            time = sc.nextLine();
            if (check.kiemTraDinhDangThangNam(time) == false) {
                System.err.println("Định dạng ngày tháng sai, nhập lại theo dạng chuẩn ");

            }
        } while (check.kiemTraDinhDangThangNam(time) == false);
        return time;
    }

    public void xemDanhSachHoaDon() {
        String time = nhapThoiGian();
        String arr[] = time.split("_");
        String thang=arr[0];
        File file = new File("data/HD/" + arr[1] + "/" + time + ".txt");
        if (file.exists()) {
            docFileHoaDon(time);
            System.out.println("+-----------------------------------------------------------------------------------------+");
            System.out.println("+                DANH SÁCH HÓA ĐƠN TIỀN ĐIỆN THEO THÁNG" + time + "                       +");
            System.out.println("+-----------------------------------------------------------------------------------------+");
            System.out.println("+ STT +        Mã HĐ             +  Họ tên KH       +  Tổng số điện  +   Thành tiền       +");
            System.out.println("+-----+--------------------------+------------------+----------------+--------------------+");
            int stt = 1;
            KhachHangApp1 khApp = new KhachHangApp1();
            khApp.docFileKH();
            DecimalFormat df = new DecimalFormat("#,###");
            for (int i = 0; i < listHoaDon.size(); i++) {
                HoaDon hd = listHoaDon.get(i);
                System.out.printf("|%5d|", stt++);
                System.out.printf("%26s|%18s|%16s|%20s|\n", hd.getMaHD(), khApp.timKiemMotKhachHang(hd.getMaKH()).getHoTen(),df.format(hd.getTongSoDien()),df.format(hd.getThanhTien()));

            }
            System.out.println("+-----+--------------------------+------------------+----------------+--------------------+");
        } else {
            System.err.println("Hiện tại chưa có dữ liệu hóa đơn tiền điện tháng: " + time);
        }
    }

    public void taoHoaDonTheoThang() {
        Scanner sc = new Scanner(System.in);
        String time;
        do {
            System.out.println("Mời bạn nhập vào tháng năm muốn xem hóa đơn(VD: 9_2018): ");
            time = sc.nextLine();
            if (check.kiemTraDinhDangThangNam(time) == false) {
                System.err.println("Định dạng ngày tháng sai, nhập lại theo dạng chuẩn ");

            }
        } while (check.kiemTraDinhDangThangNam(time) == false);
        taoFolfer(time);
        taoFileHoaDonTheoThang(time);
    }

    public void taoFolfer(String time) {
        String arr[] = time.split("_");
        {
            File folderYear = new File("data/HD/" + arr[1]);
            if (!folderYear.exists()) {
                folderYear.mkdir();
            }
        }
    }

    public boolean kiemTraTonTaiFileCSD(String time) {
        String arr[] = time.split("_");
        File file = new File("data/CD/" + arr[1] + "/" + time + ".txt");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public void themDuLieuHoaDonTheoThang(String time) {
        Scanner sc = new Scanner(System.in);
        String arr[] = time.split("_");
        ChiSoDienApp csdApp = new ChiSoDienApp();
        Date date = new Date();
        docFileHoaDon(time);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_hh_mm_ss");
        if (kiemTraTonTaiFileCSD(time)) {
            csdApp.docChiSoDienTheoThanghienTai(arr[0], arr[1]);
            for (int i = 0; i < csdApp.listCSDThangHienTai.size(); i++) {
                if (csdApp.listCSDThangHienTai.get(i).getTrangThai() == 0) {
                    ChiSoDien csd = csdApp.listCSDThangHienTai.get(i);
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(csd.getMaKH());
                    hd.setMaKH(csd.getMaKH());
                    hd.setThang(csd.getThang());
                    hd.setNam(csd.getNam());
                    hd.setTongSoDien(Integer.parseInt(csd.getChiSoDienMoi())-Integer.parseInt(csd.getChiSoDienCu()) );
                    int vat=(tinhTienTheoBac(hd.getTongSoDien())*10)/100;
                    hd.setThanhTien((tinhTienTheoBac(hd.getTongSoDien()) + vat ));
                    listHoaDon.add(hd);
                    capNhapTrangThaiHienTaiCSDDaCoHoaDon(hd.getMaKH(), time);

                }
            }
            ghiFileHoaDon(time);
            System.out.println("Đã khởi tạo các hóa đơn thành công! ");
        }
    }

    public int tinhTienTheoBac(int tongSoDien) {
        Utils util = new Utils();
        int tongTien = 0;
        if (tongSoDien > 400) {
            tongTien = ((tongSoDien - 400) * util.DON_GIA_BAC_6) + (util.DON_GIA_BAC_1 * util.MAX_BAC_1) + (util.DON_GIA_BAC_2 * util.MAX_BAC_2) + (util.DON_GIA_BAC_3 * util.MAX_BAC_3) + (util.DON_GIA_BAC_4 * util.MAX_BAC_4) + (util.DON_GIA_BAC_5 * util.MAX_BAC_5);
        } else if (tongSoDien > 300) {
            tongTien = ((tongSoDien - 300) * util.DON_GIA_BAC_5) + (util.DON_GIA_BAC_1 * util.MAX_BAC_1) + (util.DON_GIA_BAC_2 * util.MAX_BAC_2) + (util.DON_GIA_BAC_3 * util.MAX_BAC_3) + (util.DON_GIA_BAC_4 * util.MAX_BAC_4);
        } else if (tongSoDien > 200) {
            tongTien = ((tongSoDien - 200) * util.DON_GIA_BAC_4) + (util.DON_GIA_BAC_1 * util.MAX_BAC_1) + (util.DON_GIA_BAC_2 * util.MAX_BAC_2) + (util.DON_GIA_BAC_3 * util.MAX_BAC_3);
        } else if (tongSoDien > 100) {
            tongTien = ((tongSoDien - 100) * util.DON_GIA_BAC_3) + (util.DON_GIA_BAC_1 * util.MAX_BAC_1) + (util.DON_GIA_BAC_2 * util.MAX_BAC_2);
        } else if (tongSoDien > 50) {
            tongTien = ((tongSoDien - 50) * util.DON_GIA_BAC_2) + (util.DON_GIA_BAC_1 * util.MAX_BAC_1);
        } else if (tongSoDien > 0) {
            tongTien = ((tongSoDien - 50) * util.DON_GIA_BAC_1);
        }
        return tongTien;
    }

    public void ghiFileHoaDon(String time) {
        try {
            String arr[] = time.split("_");
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            for (int i = 0; i < listHoaDon.size(); i++) {
                String row = "";
                row += listHoaDon.get(i).getMaHD() + "\t";
                row += listHoaDon.get(i).getMaKH() + "\t";
                row += listHoaDon.get(i).getNam() + "\t";
                row += listHoaDon.get(i).getThang() + "\t";
                row += listHoaDon.get(i).getTongSoDien() + "\t";
                row += listHoaDon.get(i).getThanhTien() + "\n";
                data += row;

            }
         fw = new FileWriter("data/HD/" + arr[1] + "/" + time + ".txt");
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(HoaDonApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void docFileHoaDon(String time) {
        String arrTime[] = time.split("_");
        try {
            BufferedReader br = null;
            FileReader fr = null;
            listHoaDon = new ArrayList<>();

            fr = new FileReader("data/HD/" + arrTime[1] + "/" + time + ".txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String arr[] = s.split("\t");
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(arr[0]);
                    hd.setMaKH(arr[1]);
                    hd.setNam(arr[2]);
                    hd.setThang(arr[3]);
                    hd.setTongSoDien(Integer.parseInt(arr[4]));
                    hd.setThanhTien(Integer.parseInt(arr[5]));
                    listHoaDon.add(hd);

                }
            } catch (IOException ex) {
                Logger.getLogger(HoaDonApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HoaDonApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inHoaDonChiTiet() {
        Scanner sc = new Scanner(System.in);
        String time = nhapThoiGian();
        String arr[] = time.split("_");
        System.out.println("Bạn đang muốn xem chi tiết hóa đơn ở trong tháng: " + time);
        File file = new File("data/HD/" + arr[1] + "/" + time + ".txt");
        if (!file.exists()) {
            System.out.println("Dữ liệu hóa đơn của tháng " + time + " chưa cập nhật...!");
        } else {
            docFileHoaDon(time);
            System.out.println("Mời bạn nhập mã khách hàng cần xem: ");
            String maKH = sc.nextLine();
            for (int i = 0; i < listHoaDon.size(); i++) {
                HoaDon hd = listHoaDon.get(i);
                if (maKH.equals(hd.getMaKH())) {
                    KhachHangApp1 khApp = new KhachHangApp1();
                    khApp.docFileKH();
                    KhachHang kh = khApp.timKiemMotKhachHang(maKH);
                    ChiSoDienApp csdApp = new ChiSoDienApp();
                    ChiSoDien csd = csdApp.layChiSoDien(maKH, arr[0], arr[1]);
                    Utils util = new Utils();
                    DecimalFormat df = new DecimalFormat("#,###");
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    System.out.println("+                             BIÊN LAI HÓA ĐƠN TIỀN ĐIỆN                               +");
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    System.out.println("| Công ty Điện lực Zent Group                                                          |");
                    System.out.println("| Địa chỉ tầng 5-số 2A- Trại Cá-Trương Định-Hà Nội                                     |");
                    System.out.println("| SĐT:012545666335  Email: abc@gmail.com                                               |");
                    System.out.printf("| Mã HĐ: %-11s                                                      |n", hd.getMaHD());
                    System.out.printf("| Mã KH: %-11s                                                                  |\n", kh.getMaKh());
                    System.out.printf("| Tên khách hàng: %-24s", kh.getHoTen());
                    System.out.printf("| SĐT: %-13s ", kh.getSoDienThoai());
                    System.out.printf("|Địa chỉ: %-15s|\n", kh.getDiaChi());
                    System.out.println("+-------------------------------------------------------------------------------------+");
                    System.out.println("| Chỉ số cũ      |  Chỉ số mới    | Điện năng TT   |   Đơn giá        |  Thành tiền   |");
                    System.out.println("+---------------+----------------+----------------+------------------+---------------+");
                    System.out.printf("|%15s|", csd.getChiSoDienCu());
                    System.out.printf("%17s|", csd.getChiSoDienMoi());
                    System.out.printf("%-16d|", hd.getTongSoDien());
                    System.out.printf("%19s|", "");
                    System.out.printf("%15s|\n", "");

                    if (hd.getTongSoDien()>400) {
                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_1);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_1));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_1 * util.DON_GIA_BAC_1));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_2);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_2));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_2 * util.DON_GIA_BAC_2));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_3);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_3));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_3 * util.DON_GIA_BAC_3));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_4);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_4));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_4 * util.DON_GIA_BAC_4));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_5);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_5));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_5 * util.DON_GIA_BAC_5));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17d|", "");
                        System.out.printf("%16d|", hd.getTongSoDien() - 400);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_6));
                        System.out.printf("%15s|\n", df.format((hd.getTongSoDien() - 400) * util.DON_GIA_BAC_6));

                    } else if (hd.getTongSoDien() > 300) {
                        System.out.printf("|%15s|", "");
                        System.out.printf("%17d|", "");
                        System.out.printf("%16d|", util.MAX_BAC_1);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_1));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_1 * util.DON_GIA_BAC_1));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_2);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_2));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_2 * util.DON_GIA_BAC_2));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_3);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_3));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_3 * util.DON_GIA_BAC_3));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_4);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_4));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_4 * util.DON_GIA_BAC_4));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", hd.getTongSoDien() - 300);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_5));
                        System.out.printf("%15s|\n", df.format((hd.getTongSoDien() - 300) * util.DON_GIA_BAC_5));

                    } else if (hd.getTongSoDien() > 200) {
                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_1);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_1));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_1 * util.DON_GIA_BAC_1));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_2);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_2));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_2 * util.DON_GIA_BAC_2));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_3);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_3));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_3 * util.DON_GIA_BAC_3));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", hd.getTongSoDien() - 200);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_4));
                        System.out.printf("%15s|\n", df.format((hd.getTongSoDien() - 200) * util.DON_GIA_BAC_4));

                    } else if (hd.getTongSoDien() > 100) {
                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_1);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_1));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_1 * util.DON_GIA_BAC_1));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_2);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_2));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_2 * util.DON_GIA_BAC_2));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", hd.getTongSoDien() - 100);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_3));
                        System.out.printf("%15s|\n", df.format((hd.getTongSoDien() - 100) * util.DON_GIA_BAC_3));

                    } else if (hd.getTongSoDien() > 50) {
                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", util.MAX_BAC_1);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_1));
                        System.out.printf("%15s|\n", df.format(util.MAX_BAC_1 * util.DON_GIA_BAC_1));

                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", hd.getTongSoDien() - 50);
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_2));
                        System.out.printf("%15s|\n", df.format((hd.getTongSoDien() - 50) * util.DON_GIA_BAC_2));

                    } else if (hd.getTongSoDien() > 0) {
                        System.out.printf("|%15s|", "");
                        System.out.printf("%17s|", "");
                        System.out.printf("%16d|", hd.getTongSoDien());
                        System.out.printf("%19s|", df.format(util.DON_GIA_BAC_1));
                        System.out.printf("%15s|\n", df.format(hd.getTongSoDien() * util.DON_GIA_BAC_1));
                    }
                    System.out.println("+---------------+----------------+----------------+------------------+----------------+");
                    System.out.printf("|                                                    TỔNG:     %20s |\n ", df.format(tinhTienTheoBac(hd.getTongSoDien())));
                    int vat = (tinhTienTheoBac(hd.getTongSoDien() * 10)) / 100;
                    int tongTien = tinhTienTheoBac(hd.getTongSoDien() + vat);
                    System.out.printf("|                                                   Thuế VAT: %21s |\n", df.format(vat));
                    System.out.printf("|                               Tổng số tiền cần thanh toán: %23s |\n ", df.format(tinhTienTheoBac(hd.getTongSoDien())));
                    System.out.println("+-------------------------------------------------------------------------------------+");
                   
//                    util.sendmail(kh.getEmail(), String.valueOf(hd.getTongSoDien()), tongTien, time, kh.getHoTen());
                }

            }
        }

    }

    public void capNhapTrangThaiHienTaiCSDDaCoHoaDon(String maKH, String time) {
        String arr[] = time.split("_");
        ChiSoDienApp csdApp = new ChiSoDienApp();
        csdApp.docChiSoDienTheoThanghienTai(arr[0], arr[1]);
        for (int i = 0; i < csdApp.listCSD1.size(); i++) {
            ChiSoDien csd = csdApp.listCSD1.get(i);
            if (maKH.equals(csdApp.listCSD1.get(i).getMaKH())) {
                csd.setTrangThai(i);
                csdApp.listCSD1.set(i, csd);
                csdApp.ghiDuLieuVaoFile(arr[0], arr[1]);
                break;
            }
        }
    }

}
