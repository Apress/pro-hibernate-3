package com.hibernatebook.chapter10.events;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class EventExample {
   public static void main(String[] args) {
      Configuration config = new Configuration();
      
      // Apply this event listener (programmatically)
      config.setListener("save-update", new BookingSaveOrUpdateEventListener());

      SessionFactory factory = config.configure().buildSessionFactory();
      Session session = factory.openSession();
      
      Transaction tx = session.beginTransaction();

      // Make our dummy bookings...
      session.saveOrUpdate(new Booking("charles","R1"));
      session.saveOrUpdate(new Booking("camilla","R2"));      
      
      // The confirmation letters should not be sent
      // out until AFTER the commit completes.
      tx.commit();
   }
}
