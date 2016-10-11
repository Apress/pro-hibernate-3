package com.hibernatebook.legacy;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestHarness {
   public static void main(String[] argv) {
      SessionFactory sessions = new Configuration().configure().buildSessionFactory();
      Session session = sessions.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         List list = session.createQuery("FROM Product").list();
         Iterator i = list.iterator();
         while(i.hasNext()) {
            Object obj = i.next();
            Product product = (Product)obj;
            System.out.print("SKU: " + product.getData().getSKU());
            System.out.println(", Color: " + product.getColor().getName());
         }
         
         Client client = new Client("f1","f2","f3","f4","f5");
         session.persist(client);
         
         /*
         list = session.createQuery("FROM Client").list();
         i = list.iterator();
         while(i.hasNext()) {
            Object obj = i.next();
            Client client = (Client)obj;
            System.out.println(client.getName());
         }
         */
         
         tx.commit();
      } catch ( HibernateException e ) {
         e.printStackTrace();
      } finally {
         session.close();
      }

   }
}
