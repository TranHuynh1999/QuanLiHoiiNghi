package DAO;
import POJOs.*;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tran Huynh
 */
public class DiaDiemToChucDAO {
    public static List<Diadiemtochuc> findAll(){
        List<Diadiemtochuc> dd=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select dd from Diadiemtochuc dd";
            Query query=session.createQuery(hql);
            dd=query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        }finally{
            session.close();
        }
        return dd;
    }
    public static Diadiemtochuc layDiaDiem(int idDiaDiem)
    {
        Diadiemtochuc dd=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            dd=(Diadiemtochuc) session.get(Diadiemtochuc.class,idDiaDiem);
            
        } catch (HibernateException e) {
            System.err.println(e);
            
        }finally{
            session.close();
    }
    return dd;
    }
     public static Diadiemtochuc findDiadiem(String tenDiadiem){
            Diadiemtochuc dd=null;
            List<Diadiemtochuc> diadiemtochucs=DiaDiemToChucDAO.findAll();
            for(int i=0;i<diadiemtochucs.size();i++)
            {
                Diadiemtochuc temp=diadiemtochucs.get(i);
                if(temp.getTen().equals(tenDiadiem))
                {
                    dd=temp;
                }
            }
            return dd;
    }
}