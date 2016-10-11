package com.hibernatebook.chapter09.deadlock;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ThreadedSessionAccess {

   public static void main(String[] args) {
      SessionFactory factory = new Configuration().configure().buildSessionFactory();
      Session session = factory.openSession();
      Query query = session.createQuery("from Publisher order by username");
      List list = query.list();
      Iterator i = list.iterator();
      while(i.hasNext()) {
         Publisher p = (Publisher)i.next();
         System.out.println(p.getUsername());
      }
      System.out.println("Step 1.");
      session.close();
      session.reconnect();
      query = session.createQuery("from Publisher order by username");
      list = query.list();
      i = list.iterator();
      while(i.hasNext()) {
         Publisher p = (Publisher)i.next();
         System.out.println(p.getUsername());
      }
      System.out.println("Step 2.");

   }
}
