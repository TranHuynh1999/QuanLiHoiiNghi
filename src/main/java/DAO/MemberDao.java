/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DAO.*;
import POJOs.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Tran Huynh
 */
public class MemberDao {
    public static List<Member> findAll(){
        List<Member> listmb=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select mb from Member mb";
            Query query=session.createQuery(hql);
            listmb=query.list();
                
        } catch (HibernateException e) {
            System.err.println(e);
        }finally{
            session.close();
        }
        return listmb;
    }
    public static boolean themMember(Member mb){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.save(mb);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
            
        }
        finally{session.close();
        }
        return true;
    }
   public static boolean findMemberUser(String User)
   {
       List<Member> listmb=MemberDao.findAll();
              for (int i = 0; i<listmb.size(); i++) {
                  Member temp=listmb.get(i);
                  if(temp.getUserName().equals(User))
                  {
                      return true;
                  }
        }
             
         return false;
   
    }
   public static String hashPassword(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[]b=md.digest();
        StringBuffer sb=new StringBuffer();
        for(byte b1:b)
        {
            sb.append(Integer.toHexString(b1&0xff).toString());
            
        }
        return sb.toString();
    }
    public static boolean checkSignin(String User,String Password) throws NoSuchAlgorithmException 
    {

      List<Member> listmb=MemberDao.findAll();
              for (int i = 0; i<listmb.size(); i++) {
                  Member temp=listmb.get(i);
                  if(temp.getUserName().equals(User) &&(temp.getPassword().equals(hashPassword(Password))))
                  {
                      
                      return true;
                             
                  }
        }
             
         return false;   
    }
    public static int findVaitro(String Username)
    {
         List<Member> listmb=MemberDao.findAll();
              for (int i = 0; i<listmb.size(); i++) {
                  Member temp=listmb.get(i);
                  if(temp.getUserName().equals(Username))
                  {
                      return temp.getVaiTro();
                  }
        }
             
         return -1;
    }
     public static int findidMember(String Username)
    {
         List<Member> listmb=MemberDao.findAll();
              for (int i = 0; i<listmb.size(); i++) {
                  Member temp=listmb.get(i);
                  if(temp.getUserName().equals(Username))
                  {
                      return temp.getIdMember();
                  }
        }
             
         return -1;
    }
    public static Member findInforMember(int idMember){
            Member mb=null;
            Session session=HibernateUtil.getSessionFactory().openSession();
            try {
            mb=(Member) session.get(Member.class, idMember);
        } catch (HibernateException e) {
                System.err.println(e);
        }finally{
                session.close();
            }
            return mb;
    }
    public static boolean updateProfile(Member mb)
    {
      
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            session.update(mb);
            transaction.commit();
            
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        }finally{
             session.close();

        }
        return true;
    }

}
   