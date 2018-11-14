/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckData;

import java.text.DecimalFormat;

/**
 *
 * @author Trần Thị Thu Hiền
 */
public class Utils {


    public String chuanhoa(String ten){
        ten.trim();
        String arr[]=ten.split(" ");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i].substring(0, 1).toUpperCase()+arr[i].substring(1)+" ");
//        }
        String row="";
        for (int i = 0; i < arr.length; i++) {
            row+= arr[i].substring(0, 1).toUpperCase()+arr[i].substring(1)+" ";
        }
        return row;
    }
    public static final int DON_GIA_BAC_1 = 1484;
    public static final int DON_GIA_BAC_2 = 1533;
    public static final int DON_GIA_BAC_3 = 1786;
    public static final int DON_GIA_BAC_4 = 2242;
    public static final int DON_GIA_BAC_5 = 2503;
    public static final int DON_GIA_BAC_6 = 2587;
    public static final int MAX_BAC_1 = 50;
    public static final int MAX_BAC_2 = 50;
    public static final int MAX_BAC_3 = 100;
    public static final int MAX_BAC_4 = 100;
    public static final int MAX_BAC_5 = 100;
    static final String DATE_PATTERN ="([1-9]|1[012])_((19|20)\\d\\d)";

    public String chuanHoaKhoangTrang(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }
// public void sendmail(String emailAdress, String tongSoDien, int tongTien, String time, String tenKH){
//        try {
//        Email email= new SimpleEmail();
//        email.setHostName("smtp.googlemail.com");
//        email.setSmtPort(465);
//        email.setAuthenticator(new DefaultAuthenticator("abc@gmail.comm", "Acbd54545"));
//        email.setSSLOnConnect(true);
//        email.setFrom("abc@gamilcom","Công ty điện lực Zent Group");
//        email.setSubject("Thông báo tiền điện tháng: "+time);
//        DecimalFormat df= new DecimalFormat("#,###");
//        
//        email.setMsg("Công ty điện lực Zent Group trân trọng thông báo tiền điện tháng "+time+"\n tháng "+time+" quý khách là: ");
//        email.addTo(emailAdress);
//        email.send();
//        System.out.println("Gửi mail thành công, vui lòng chwck lại email");
//        System.out.println("\n");
//         } catch (Exception e) {
//            System.out.println("Gửi không thành công!");
//    }
//}
}

