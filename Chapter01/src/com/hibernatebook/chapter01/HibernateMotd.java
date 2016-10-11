package com.hibernatebook.chapter01;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateMotd {

   public static void main(String[] args) {
      if (args.length != 1) {
         System.err.println("Nope, enter one message number");
      } else {
         try {
            int messageId = Integer.parseInt(args[0]);
            Motd motd = getMotd(messageId); 
            if (motd != null) {
               System.out.println(motd.getMessage());
            } else {
               System.out.println("No such message");
            }
         } catch (NumberFormatException e) {
            System.err.println("You must enter an integer - " + args[0]
                  + " won't do.");
         } catch (MotdException e) {
            System.err.println("Couldn't get the message: " + e);
         }
      }
   }

   public static Motd getMotd(int messageId)
      throws MotdException
   {
      SessionFactory sessions = new Configuration().configure().buildSessionFactory();
      Session session = sessions.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();

         Motd motd = (Motd)session.get(Motd.class, new Integer(messageId));

         tx.commit();
         tx = null;
         return motd;
      } catch ( HibernateException e ) {
         if ( tx != null ) tx.rollback();
         log.log(Level.SEVERE, "Could not acquire message", e);
         throw new MotdException(
               "Failed to retrieve message from the database.",e);
      } finally {
         session.close();
      }
   }
   
   private static final Logger log = Logger.getAnonymousLogger();
}
