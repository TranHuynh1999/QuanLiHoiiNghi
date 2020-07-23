/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import DAO.*;
import POJOs.*;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Tran Huynh
 */
public class MemberListsDAO {
    public static List<MemberList> findAll(){
        Session s=HibernateUtil.getSessionFactory().openSession();
        List<MemberList> mbl = null;
        
        try{
            String hql = "select mbl from MemberList mbl";
            Query q = s.createQuery(hql);
            mbl = q.list();
            
        }catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally{
            s.close();
        }
        return mbl;
    }
    public static boolean addMemberList(MemberList mbl)
    {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(mbl);
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            System.err.println(e);
            
        }finally{
            session.close();
    }
    return true;
    }
    public static boolean findMemberList(MemberList mbl)
    {
        List<MemberList> memberLists=MemberListsDAO.findAll();
        for(int i=0;i<memberLists.size();i++)
        {
            if(mbl.getHoinghi().getIdHoiNghi()==memberLists.get(i).getId().getIdHoinghi()&&mbl.getMember().getIdMember()==memberLists.get(i).getMember().getIdMember())
                    {
                        return false;
                    }
        }
        return true;
    }

     public static MemberList findMemberListtoID(int idMember,int idCon)
    {
        MemberList temp=null;
        List<MemberList> memberLists=MemberListsDAO.findAll();
        for(int i=0;i<memberLists.size();i++)
        {
            if(memberLists.get(i).getHoinghi().getIdHoiNghi()==idCon && memberLists.get(i).getMember().getIdMember()==idMember)
            {
                temp=memberLists.get(i);
            }
        }
        return temp;
    }
     public static boolean xoaMemberList(MemberList mbl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(mbl==null){
            return false;
            }
        Transaction transaction = null;
        try {
        transaction = session.beginTransaction();
        session.delete(mbl);
        transaction.commit();
        } catch (HibernateException ex) {
        //Log the exception
        transaction.rollback();
        System.err.println(ex);
        } finally {
        session.close();
        }
        return true;
        }
    
}
