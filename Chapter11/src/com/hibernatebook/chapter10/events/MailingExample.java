package com.hibernatebook.chapter10.events;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MailingExample {
   public static void main(String[] argv) {
      Configuration config = new Configuration();
      
      // Apply this interceptor at a global level...
      // config.setInterceptor(new BookingInterceptor());
      
      // Apply this event listener (programmatically)
      config.setListener("save-update", new BookingSaveOrUpdateEventListener());
      
      // getSessionEventListenerConfig
      
      
      SessionFactory factory = config.configure().buildSessionFactory();
      Session session = factory.openSession();
      
      // Interceptor could alternatively be applied here:
      // session.setInterceptor(new BookingInterceptor());
      
      Transaction tx = session.beginTransaction();

      // Make our dummy bookings...
      session.saveOrUpdate(new Booking("charles","R1"));
      session.saveOrUpdate(new Booking("camilla","R2"));      
      
      // The confirmation letters should not be sent
      // out until AFTER the commit completes.
      tx.commit();
   }
}
