package com.hibernatebook.chapter3.dao;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernatebook.chapter3.AdException;
import com.hibernatebook.chapter3.Phone;
import com.hibernatebook.chapter3.User;

public class PhoneDAO extends DAO {
   public PhoneDAO() {
   }
   
   public List getPhone(User user)
      throws AdException
   {
      try {
         Session session = HibernateHelper.getSession();         
         Query q = session.createQuery("from Phone p where p.user= :user");
         q.setEntity("user",user);
         List results = q.list();
         return results;
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }      
   }
   
   public Phone createPhone(String comment, String number, User user)
      throws AdException
   {
      try {
         Phone phone = new Phone(user,number,comment);
         HibernateHelper.getSession().save(phone);
         return phone;
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }
   }
   
   public void deletePhone(Phone phone)
      throws AdException
   {
      try {
         HibernateHelper.getSession().update(phone);
         HibernateHelper.getSession().delete(phone);
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }      
   }
}
