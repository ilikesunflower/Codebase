/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Trần Thị Thu Hiềun
 */
public class CheckData {
    private Pattern pattern;
    private  Matcher matcher;
    
    public boolean kiemTraTen(String ten){
        for (int i = 0; i < 10; i++) {
            if(ten.contains(i+"")||ten.equalsIgnoreCase("")){
                return false;
            }
        }
        return true;
    }
    public boolean kiemTraSuaTen(String ten){
        for (int i = 0; i < 10; i++) {
            if(ten.contains(i+"")){
                return false;
            }
        }
        return true;
    }
    public boolean kiemTraSoDienTHoai(String sdt){
     boolean check = true;
     if(!sdt.startsWith(0 + "")){
         System.out.println("Số điện thoại phải bắt đầu bằng số 0");
         check =false;
     }else if(sdt.length()<10){
         System.out.println("Số điện thoại không được ít hơn 10 số");
         check =false;
     }else if(sdt.length()>11){
         System.out.println("Số điện thoại không được nhiều hơn 11 số");   
         check =false;
     }
     return check;
    }
    public boolean kiemTRaEmail(String email){
        String dinhDangEmail="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[_A-Za-z]{2,})$";
        boolean ktEmail =email.matches(dinhDangEmail);
        if (ktEmail==false) {
            System.err.println("Email sai. Nhập lại theo dang abc@hhh.com");
            return false;
        }
        return true;
    }
    
   static final String DATE_PATTERN = "([1-9]|1[012])_((19|20)\\d\\d)";

    public boolean kiemTraDinhDangThangNam(String thangNam) {
        Utils util = new Utils();
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(thangNam);
       
        if (matcher.matches()) {
            return true;
        }
        return false;

    }
}
    