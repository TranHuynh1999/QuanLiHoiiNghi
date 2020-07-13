/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DAO.*;
import POJOs.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Tran Huynh
 */
public class test {
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //List<Hoinghi> hn = HoiNghiDAO.findAll();
        
//        for (int i = 0; i < hn.size(); i++) {
//            Hoinghi temp = hn.get(i);
//            System.out.println("id: " + temp.getIdHoiNghi());
//                System.out.println("ngan"+temp.getMoTaNgangon());
//            System.out.println("songuoi"+temp.getSoNguoiThamDu());
//    }


//            Diadiemtochuc dd=DiaDiemToChucDAO.layDiaDiem(2);
//            if(dd!=null)
//            {
//                System.out.println("id "+dd.getIdDiaDiemToChuc());
//                System.out.println("ten"+dd.getTen());
//            }else{
//                System.out.println("Không tồn tại");
//            }


    
//               List<Diadiemtochuc> dd=DiaDiemToChucDAO.findAll();
//               for (int i = 0; i < dd.size(); i++) {
//                  Diadiemtochuc temp=dd.get(i);
//                   System.out.println("id"+temp.getIdDiaDiemToChuc());
//               }
//                   
//            
//        }
                


//            Hoinghi hn=new Hoinghi();
//            hn.setTen("Huynh Ttran33");
//            Calendar calendar=Calendar.getInstance();
//            calendar.set(2015, 1, 1, 1, 1, 1);
//            Date date=calendar.getTime();
//            hn.setThoiGian(date);
            
//            Diadiemtochuc dd = DiaDiemToChucDAO.layDiaDiem(1);
//            
//            hn.setDiadiemtochuc(dd);
//            hn.setSoNguoiThamDu(1002);
//            hn.setHinhAnh("null2");
//            hn.setMoTaNgangon("Hội nghị tại trường KHTN2");
//            hn.setMoTaChitiet("Tham quan công ty tại trường2..");
//            
//            boolean kq = HoiNghiDAO.themHoiNghi(hn);
//            if(kq) System.out.println("Thanh công");
//            else System.out.println("fail");
//            
//            
////            
//           Member mb=new Member();
//            mb.setTen("Tran Huynh");            
//            mb.setEmail("tranhuynh@gmail.com");
//            mb.setUserName("huynh11");
//            mb.setVaiTro(1);
//            mb.setPassword("huynh012");
//            
//            boolean kq=MemberDao.themMember(mb);
//            if(kq) System.out.println("thanh cong");
//            else System.out.println("That bai");
//
//               List<Member> listmb=MemberDao.findAll();
//              for (int i = 0; i<listmb.size(); i++) {
//                  Member temp=listmb.get(i);
//                  System.out.println("id"+temp.getIdMember());
//                  System.out.println("ten"+temp.getTen());
//        }
//            
//        String password = "stackjava.com";
//String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
//System.out.println("BCrypt hash: " + hash);
//    }
//    String user="huynh012";
//    String pasword="huynh012";
//    boolean a=MemberDao.checkSignin(user, pasword);
//    if(a) System.out.println("thành công");
//    else System.out.println("K thành coong");
// Member member=MemberDao.findInforMember(18);
//        System.out.println("id"+member.getIdMember());
//        System.out.println("name"+member.getTen());
//        System.out.println("usename"+member.getUserName());
//        System.out.println("pass"+member.getPassword());
//    }
//        int id=18;
//       Member mb= MemberDao.findInforMember(id);
//       mb.setEmail("sdasd");
//       
//       boolean kq= MemberDao.updateProfile(mb);
//        if(kq) System.out.println("true");
//        else System.out.println("false");
//    }
       
//        Member mb=MemberDao.findInforMember(18);
//        List<Hoinghi> hoinghis=(List<Hoinghi>) mb.getHoinghis();
//        Collections.sort(hoinghis, new Comparator<Hoinghi>() {
//            @Override
//            public int compare(Hoinghi o1, Hoinghi o2) {
//               return o1.getTen().compareTo(o2.getTen());
//            }
//        
//      
//            });
//        for (int i = 0; i< hoinghis.size(); i++) {
//            Hoinghi temp=hoinghis.get(i);
//            System.out.println(temp.getTen());
//            
//        }
//        
    
        
             

// Member mb=MemberDao.findInforMember(27);
//            Hoinghi hn=HoiNghiDAO.findInforHoinghi(100);
//            hn.getMembers().add(mb);
//            boolean kq=HoiNghiDAO.addMemberConference(hn);
//           if(kq) System.out.println("true");
//           else System.out.println("false");
            
            

//        Iterator<Hoinghi> hoinghis=mb.getHoinghis().iterator();
//        List<String> list=new ArrayList<>();
//        while(hoinghis.hasNext()){
//            Hoinghi temp=hoinghis.next();
//            
//            list.add(temp.getTen());
//            
//           
//        }
//         Collections.sort(list);
//         System.out.println(list);
                Member mb=MemberDao.findInforMember(35);
                Iterator<Hoinghi> hoinghis= mb.getHoinghis().iterator(); 
                List<Hoinghi> kq=new ArrayList<>();
                while (hoinghis.hasNext()) {
                    kq.add(hoinghis.next());
                        
            
        }
               
            
        }
    }
    

    
    
    
    

