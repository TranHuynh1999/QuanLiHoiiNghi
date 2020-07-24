/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.*;
import POJOs.*;
import org.hibernate.*;
/**
 *
 * @author Tran Huynh
 */
public class HoiNghiDAO {
    //private final SessionFactory sf=HibernateUtil.getSessionFactory();
    
    public static List<Hoinghi> findAll()
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Hoinghi> hn = null;
        
        try{
            String hql = "select hn from Hoinghi hn";
            Query q = s.createQuery(hql);
            hn = q.list();
            
        }catch(HibernateException e)
        {
            System.err.println(e);
        }
        finally{
            s.close();
        }
        return hn;
    }
    public static boolean themHoiNghi(Hoinghi hn)
    {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(hn);
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            System.err.println(e);
            
        }finally{
            session.close();
    }
    return true;
    }
    public static boolean updateConference(Hoinghi hn) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                if (HoiNghiDAO.findInforHoinghi(hn.getIdHoiNghi())==null)
                {
                    return false;
                }
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();
                    session.update(hn);
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
     public static Hoinghi findInforHoinghi(int idHoiNghi){
            Hoinghi hn=null;
            Session session=HibernateUtil.getSessionFactory().openSession();
            try {
            hn=(Hoinghi) session.get(Hoinghi.class, idHoiNghi);
        } catch (HibernateException e) {
                System.err.println(e);
        }finally{
                session.close();
            }
            return hn;
    }
      public static boolean addMemberConference(Hoinghi hn){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        boolean kq=true;
        try {
            transaction=session.beginTransaction();
            session.saveOrUpdate(hn);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
            kq=false;
        }
        finally{session.close();
        }
        return kq;
    }
           public static Hoinghi findHoinghi(String tenHoiNghi){
            Hoinghi hn=null;
            Session session=HibernateUtil.getSessionFactory().openSession();
            try {
            hn=(Hoinghi) session.get(Hoinghi.class,tenHoiNghi);
        } catch (HibernateException e) {
                System.err.println(e);
        }finally{
                session.close();
            }
            return hn;
    }
}