package com.hibernatebook.casestudy.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


abstract public class DAO {
   protected DAO() {
   }
   
   /**
    * Obtains a sesison from the factory. Closing
    * the session is the responsibility of the calling
    * code.
    * 
    * @return A new Session instance
    */
   protected Session getSession() {
      return getFactory().openSession();
   }
   
   /**
    * Returns a single static instance of a SessionFactory
    * whenever called. If the instance has not been initialised
    * this call will also create the instance.
    * 
    * @return A single SessionFactory instance
    */
   private static SessionFactory getFactory() {
      synchronized(factoryLock) {
         if( factory == null ) {
            factory = new Configuration().configure().buildSessionFactory();
         }
      }
      return factory;
   }
   
   /**
    * Closes a session which is believed to be
    * open. If a problem occurs while trying to
    * do this, the exception is logged rather than
    * being thrown.
    * 
    * @param session The session to close.
    */
   protected void close(Session session) {
      try {
         if( session != null ) {
            session.close();
         }
      } catch( HibernateException e ) {
         log.warn("Could not close ostensibly open session object.",e);
      }
   }

   /**
    * Rolls back a transaction which is believed to be
    * in progress. If a problem occurs while trying to
    * do this, the exception is logged rather than
    * being thrown.
    * 
    * @param tx The transaction to close.
    */
   protected void rollback(Transaction tx) {
      try {
         if( tx != null ) {
            tx.rollback();
         }
      } catch( HibernateException e ) {
         log.warn("Could not roll back ostensibly open transaction.",e);
      }
   }

   protected static final Logger log = Logger.getLogger(DAO.class);
   private static Object factoryLock = new Object();
   private static SessionFactory factory = null;
}
