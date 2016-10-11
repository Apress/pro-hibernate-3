package com.hibernatebook.chapter3.dao;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernatebook.chapter3.AdException;
import com.hibernatebook.chapter3.User;

public class UserDAO extends DAO {
   public UserDAO() {
   }

   public User getUser(String username) throws AdException {
      try {
         Session session = HibernateHelper.getSession();

         Query q = session.createQuery("from User u where u.name= :username");
         q.setString("username", username);
         List results = q.list();

         User user = null;
         if (results.size() == 1) {
            user = (User) results.get(0);
         }

         return user;
      } catch (HibernateException e) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("", e);
      }
   }

   public User createUser(String username, String password) throws AdException {
      try {
         User user = new User(username, password);
         Session session = HibernateHelper.getSession();
         session.save(user);
         return user;
      } catch (HibernateException e) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("", e);
      }
   }

   public void deleteUser(String username) throws AdException {
      try {
         User user = getUser(username);
         HibernateHelper.getSession().update(user);
         HibernateHelper.getSession().delete(user);
      } catch (HibernateException e) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("", e);
      }
   }
}