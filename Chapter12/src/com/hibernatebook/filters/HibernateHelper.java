
package com.hibernatebook.filters;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

   private HibernateHelper() {
   }

   public static Session getSession() {
      Session session = (Session)HibernateHelper.session.get();
      if( session == null ) {
         session = sessionFactory.openSession();
         HibernateHelper.session.set(session);
      }
      return session;
   }
   
   private static final ThreadLocal session = new ThreadLocal();
   private static final ThreadLocal transaction = new ThreadLocal();   
   private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
}
