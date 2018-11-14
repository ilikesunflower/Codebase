/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import App.ChiSoDienApp;
import App.HoaDonApp;
import App.KhachHangApp1;
import java.util.Scanner;

/**
 *
 * @author Trần Thị Thu Hiền
 */
public class Login {

    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String pass, name;
        do {
            System.out.println("+-------------------ĐĂNG NHẬP----------------+");
            System.out.println("Mời bạn nhập tài khoản:");
            name = sc.nextLine();
            System.out.println("Mời bạn nhập mật khẩu:");
            pass = sc.nextLine();
            if (name.equals("ngocanh") && pass.equals("123456")) {
                System.out.println("Đăng Nhập THành Công!");
                count++;

            } else {
                System.out.println("Mật Khẩu hoặc tài khoản không đúng.");
            }
        } while (count == 0);
        new Login().menu();
    }

    public void menu() {
        int chon;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("+-------------QUẢN LÝ THANH TOÁN TIỀN ĐIỆN-------------+");
            System.out.println("|             1.QUẢN LÝ KHÁCH HÀNG                     |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|             2.QUẢN LÝ CHỈ SỐ ĐIỆN KHÁCH HÀNG         |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|             3.QUẢN LÝ HÓA ĐƠN TIỀN ĐIỆN HÀNG THÁNG   |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|             4.THOÁT                                  |");
            System.out.println("+------------------------------------------------------|");
            System.out.println("Chọn nhiệm vụ:");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    System.out.println("1.QUẢN LÝ KHÁCH HÀNG ");
                    new KhachHangApp1().menu();
                    break;
                case 2:
                    System.out.println("2.QUẢN LÝ CHỈ SỐ ĐIỆN KHÁCH HÀNG ");
                     ChiSoDienApp csd = new ChiSoDienApp();
                     csd.nhapThoiGian();
                    break;
                case 3:
                    System.out.println("3.QUẢN LÝ HÓA ĐƠN TIỀN ĐIỆN HÀNG THÁNG");
                    HoaDonApp hd =new HoaDonApp();
                    hd.menu();
                    break;
                case 4:
                    System.out.println("4.THOÁT  ");
                    System.out.println("Lỗi.Hãy nhập lại");
                       break;
            }
        } while (chon != 4);
    }
}
