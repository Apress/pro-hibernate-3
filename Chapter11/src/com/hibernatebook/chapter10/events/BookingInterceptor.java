package com.hibernatebook.chapter10.events;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class BookingInterceptor implements Interceptor {

   public BookingInterceptor() {
   }

   private ThreadLocal stored = new ThreadLocal();

   public void afterTransactionBegin(Transaction tx) {
      stored.set(new HashSet());
   }

   public void afterTransactionCompletion(Transaction tx) {
      if (tx.wasCommitted()) {
         Iterator i = ((Collection) stored.get()).iterator();
         while (i.hasNext()) {
            Booking b = (Booking) i.next();
            sendMail(b);
         }
      }
      stored.set(null);
   }

   public boolean onSave(Object entity, Serializable id,
         Object[] state, String[] propertyNames, Type[] types)
         throws CallbackException {
      ((Collection) stored.get()).add(entity);
      return false;
   }

   private void sendMail(Booking b) {
      System.out.print("Name: " + b.getName());
      System.out.println(", Seat: " + b.getSeat());
   }

   // Directly amend the object (works regardless
   // of return)
   //
   //Booking b = (Booking)entity;
   //b.setName("Foiled!");

   // Ament the state (only works if the flag is set)
   // state[1] = "again";

   public void beforeTransactionCompletion(Transaction tx) {
   }

   public int[] findDirty(Object entity, Serializable id,
         Object[] currentState, Object[] previousState,
         String[] propertyNames, Type[] types) {
      return null;
   }

   public Object getEntity(String entityName, Serializable id)
         throws CallbackException {
      return null;
   }

   public String getEntityName(Object object) throws CallbackException {
      return null;
   }

   public Object instantiate(String entityName, EntityMode entityMode,
         Serializable id) throws CallbackException {
      return null;
   }

   public Boolean isTransient(Object object) {
      return null;
   }

   public void onDelete(Object entity, Serializable id, Object[] state,
         String[] propertyNames, Type[] types) throws CallbackException {
   }

   public boolean onFlushDirty(Object entity, Serializable id,
         Object[] currentState, Object[] previousState,
         String[] propertyNames, Type[] types) throws CallbackException {
      return false;
   }

   public boolean onLoad(Object entity, Serializable id,
         Object[] state, String[] propertyNames, Type[] types)
         throws CallbackException {
      return false;
   }

   public void postFlush(Iterator entities) throws CallbackException {
   }

   public void preFlush(Iterator entities) throws CallbackException {
   }
}