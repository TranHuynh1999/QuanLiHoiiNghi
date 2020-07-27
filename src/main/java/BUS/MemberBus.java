/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import POJOs.Member;
import java.util.List;
import DAO.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author Tran Huynh
 */
public class MemberBus {
    public static List<Member> sortIdMembers()
    {
        List<Member> kList=MemberDao.findAll();
        Comparator<Member> comparator=new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                int id1=o1.getIdMember();
                int id2=o2.getIdMember();
                if(id1>id2)
                    return 1;
                else if(id1<id2)
                    return 2;
                else return 0;
            }
        };
        Collections.sort(kList, comparator);
        return kList;                
    }
    public static List<Member> sortNameMembers()
    {
        List<Member> lists=MemberDao.findAll();
        Comparator<Member> comparator=new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                String name1=o1.getTen();
                String name2=o2.getTen();
                return name1.compareTo(name2);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        Collections.sort(lists, comparator);
        return lists;
    }
    
    public static List<Member> filterActiveMembers()
    {
        List<Member> members=MemberDao.findAll();
        List<Member>kq=new ArrayList<>();
        for(int i=0;i<members.size();i++)
        {
          Member temp=members.get(i);
          if(temp.getActive()==1)
          {
              kq.add(temp);
          }
        }
       return kq;
    }
    public static List<Member> filterInactiveMembers(){
        List<Member> members=MemberDao.findAll();
        List<Member> kq= new ArrayList<>();
        for(int i =0;i<members.size();i++)
        {
            Member temp=members.get(i);
            if(temp.getActive()==0)
            {
                kq.add(temp);
            }
        }
        return kq;
    }
    public static List<Member> filterNameMembers(String nameFilter)
    {
        List<Member> members=MemberDao.findAll();
        List<Member> kq=new ArrayList<>();
        for(int i=0;i<members.size();i++)
        {
          Member temp=members.get(i);
          if(temp.getTen().equals(nameFilter))
          {
              kq.add(temp);
          }
        }
        return kq;
    }
    
}
