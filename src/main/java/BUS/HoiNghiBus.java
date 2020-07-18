/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MemberDao;
import POJOs.Diadiemtochuc;
import POJOs.Hoinghi;
import POJOs.Member;
import POJOs.MemberList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Huynh
 */
public class HoiNghiBus {
    public  static List<Hoinghi> sortNameListConferenceMember(int key){
                            Member mb=MemberDao.findInforMember(key);
                            Iterator<MemberList> memberlists= mb.getMemberLists().iterator(); 
                            List<Hoinghi> kq=new ArrayList<>();
                            while (memberlists.hasNext()) {
                                    Hoinghi hn=memberlists.next().getHoinghi();
                                    kq.add(hn);
                                        }
                              Comparator<Hoinghi> comparator=new Comparator<Hoinghi>() {
                                @Override
                                public int compare(Hoinghi o1, Hoinghi o2) {
                                    String name1=o1.getTen();
                                    String name2=o2.getTen();
                                    return name1.compareTo(name2);
                                }
                            };
                              Collections.sort(kq,comparator);
                              return kq;
                            
    }
        public static List<Hoinghi> sortTimeListConferenceMember(int key){
                            Member mb=MemberDao.findInforMember(key);
                            Iterator<MemberList> memberlists= mb.getMemberLists().iterator(); 
                            List<Hoinghi> kq=new ArrayList<>();
                            while (memberlists.hasNext()) {
                                    Hoinghi hn=memberlists.next().getHoinghi();
                                    kq.add(hn);
                                        }
                              Comparator<Hoinghi> comparator=new Comparator<Hoinghi>() {
                                @Override
                                public int compare(Hoinghi o1, Hoinghi o2) {
                                    Date date1=o1.getThoiGian();
                                    Date date2=o2.getThoiGian();
                                    return date1.compareTo(date2);
                                    
                                }
                            };
                              Collections.sort(kq,comparator);
                              return kq;
    }
     public static List<Hoinghi> sortAttendeesListConferenceMember(int key){
                            Member mb=MemberDao.findInforMember(key);
                            Iterator<MemberList> memberlists= mb.getMemberLists().iterator(); 
                            List<Hoinghi> kq=new ArrayList<>();
                            while (memberlists.hasNext()) {
                                    Hoinghi hn=memberlists.next().getHoinghi();
                                    kq.add(hn);
                                        }
                              Comparator<Hoinghi> comparator=new Comparator<Hoinghi>() {
                                @Override
                                public int compare(Hoinghi o1, Hoinghi o2) {
                                    int att1=o1.getSoNguoiThamDu();
                                    int att2=o2.getSoNguoiThamDu();
                                    if(att1>att2) return 1;
                                    else if(att1<att2)return -1;
                                    else return 0;
                                }
                            };
                              Collections.sort(kq,comparator);                                    
                             return kq;
    }
    
}
