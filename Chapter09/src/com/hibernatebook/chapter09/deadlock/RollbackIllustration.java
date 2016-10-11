package com.hibernatebook.chapter09.deadlock;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class RollbackIllustration {
   public static void main(String[] args) {
      SessionFactory factory = new Configuration().configure().buildSessionFactory();
      Publisher p = null;
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      p = (Publisher)session.createQuery("from Publisher where username='dave'").uniqueResult();
      tx.commit();

      System.out.println("Username of p acquired from DB... " + p.getUsername());
      System.out.println("Begin transaction:");
      tx = session.beginTransaction();
      System.out.println("Change name of p");
      p.setUsername("quentin");
      System.out.println("Username of p before rollback... " + p.getUsername());
      tx.rollback();
      System.out.println("Username of p after rollback... " + p.getUsername());

      tx = session.beginTransaction();
      System.out.println("About to refresh p!");
      session.refresh(p);
      System.out.println("Username of p after refresh... " + p.getUsername());
      tx.commit();
      System.out.println("Username of p after commit... " + p.getUsername());

   }
}
