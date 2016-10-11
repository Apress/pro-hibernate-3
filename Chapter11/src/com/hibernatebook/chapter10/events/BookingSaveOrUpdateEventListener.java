package com.hibernatebook.chapter10.events;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;


public class BookingSaveOrUpdateEventListener 
   extends DefaultSaveOrUpdateEventListener
{
   public Serializable onSaveOrUpdate(SaveOrUpdateEvent event)
         throws HibernateException {
      if( event.getObject() instanceof Booking ) {
         Booking booking = (Booking)event.getObject();
         System.out.println("Preparing to book seat " + booking.getSeat());

         if( booking.getSeat().equalsIgnoreCase("R1")) {
            System.out.println("Royal box booked");
            System.out.println("Conventional booking not recorded.");
            
            // By returning null instead of invoking the
            // default behaviour we prevent the invocation
            // of saveOrUpdate on the Session from having 
            // any effect on the database!
            return null;
         }
      }
      
      // The default behaviour:
      return super.onSaveOrUpdate(event);
   }
}
