package com.hibernatebook.chapter3.dao;

import java.util.logging.Level;

import org.hibernate.HibernateException;

import com.hibernatebook.chapter3.AdException;
import com.hibernatebook.chapter3.Advert;
import com.hibernatebook.chapter3.User;


public class AdvertDAO extends DAO {
   public AdvertDAO() {
   }
   
   /**
    * Stores (and detaches) the advert
    * 
    * @param advert
    * @throws AdException
    */
   public Advert createAdvert(String title, String message, User user)
      throws AdException
   {
      try {
         Advert advert = new Advert(title,message,user);
         HibernateHelper.getSession().save(advert);
         return advert;
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }
   }
   
   public void deleteAdvert(Advert advert)
      throws AdException
   {
      try {
         HibernateHelper.getSession().update(advert); //Attach advert
         HibernateHelper.getSession().delete(advert); //Delete it from the database
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }     
   }
}
