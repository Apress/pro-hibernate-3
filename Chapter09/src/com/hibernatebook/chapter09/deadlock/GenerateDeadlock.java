package com.hibernatebook.chapter09.deadlock;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GenerateDeadlock {

   public abstract static class Task implements Runnable {

      // Specify the name of the task to carry out
      // and the name of the user to look up and
      // modify
      public Task(String taskName, String username) {
         this.username = username;
         this.taskName = taskName;
      }

      abstract public void step1(Session session);

      abstract public void step2(Session session);

      // Carry out two database steps with a pause between
      // them.
      public void run() {
         Session session = sessions.openSession();
         Transaction tx = null;
         try {
            System.out.println(taskName + " begins a transaction.");
            tx = session.beginTransaction();

            // Step 1
            System.out.println(taskName + " step 1");
            step1(session);

            // Pause to ensure proper ordering
            // of the steps. You would never
            // normally do this!
            pause();

            // Step 2
            System.out.println(taskName + " step 2");
            step2(session);

            tx.commit();
            System.out
                  .println(taskName + " committed its transaction.");
            tx = null;
         } catch (HibernateException e) {
            if (tx != null)
               tx.rollback();
            System.out.println(taskName
                  + " rolled back its transaction: " + e);
         } finally {
            System.out.println("Session for " + taskName + " closed.");
            session.close();
         }
      }

      private void pause() {
         setPaused(true);
         while (isPaused()) {
            synchronized (this) {
               try {
                  System.out.println("Pausing " + taskName);
                  wait();
               } catch (InterruptedException e) {
                  // No need to process this.
               }
            }
         }
         System.out.println(taskName + " awoken, continuing...");
      }

      public boolean isPaused() {
         return isPaused;
      }

      public void setPaused(boolean isPaused) {
         this.isPaused = isPaused;
         if (!isPaused) {
            synchronized (this) {
               notifyAll();
            }
         }
      }

      protected String taskName;

      protected String username;

      private boolean isPaused = false;
   }

   private static SessionFactory sessions = new Configuration()
         .configure().buildSessionFactory();

   /*
   // Create a user in the database - the alternative
   // implementation making standard usage of the
   // session's own transaction methods but using
   // the default (global) isolation level.
   public static void createUser(String username)
         throws HibernateException {
      Session session = sessions.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();

         // Normal usage of the Session here...
         Publisher p = new Publisher(username);
         Subscriber s = new Subscriber(username);
         session.saveOrUpdate(p);
         session.saveOrUpdate(s);

         tx.commit();
         tx = null;
      } catch (HibernateException e) {
         // Handle a failed transaction
         if (tx != null)
            tx.rollback();
      } finally {
         // Close the session
         session.close();
      }
   }
   */

   // Create a user in the database - illustrates
   // explicit setting of the isolation transaction
   // for a single transaction.
   public static void createUser(String username)
         throws HibernateException {
      Session session = sessions.openSession();
      Connection conn = null;

      int isolation = -1;
      try {
         // Obtain the JDBC connection
         conn = session.connection();

         // Determine the initial isolation level of the transaction
         isolation = conn.getTransactionIsolation();

         // Explicitly set the transaction's isolation level
         // before beginning the transaction.
         conn
               .setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
         conn.setAutoCommit(false);

         // Normal usage of the Session here...
         Publisher p = new Publisher(username);
         Subscriber s = new Subscriber(username);
         session.saveOrUpdate(p);
         session.saveOrUpdate(s);

         // Force data to be written to the database
         // BEFORE the transaction is committed.
         session.flush();
         // Commit the transaction
         conn.commit();
      } catch (Exception e1) {
         // Handle a failed transaction
         try {
            if (conn != null) {
               conn.rollback();
            }
         } catch (SQLException e2) {
            System.out.println("Could not rollback the connection: "
                  + e2);
         }
      } finally {
         // Restore the original isolation of the committed
         // or rolled back transaction
         if (conn != null) {
            if (isolation != -1) {
               try {
                  conn.setTransactionIsolation(isolation);
               } catch (SQLException e2) {
                  System.out
                        .println("Could not restore the connection isolation level: "
                              + e2);
               }
            }
         }

         // Close the session
         session.close();
      }
   }

   public static void main(String[] argv) {
      String username = "test";

      // Firstly we'll create the Publisher and
      // Subscriber.
      createUser(username);

      // We create a task (ready to be run) which will
      // update the Publisher, pause, then update the
      // Subscriber.
      Task taskA = new Task("Task A", username) {
         public void step1(Session session) {
            Query query = session
                  .createQuery("from Publisher where :foo = username");
            query.setString("foo", username);
            Publisher p = (Publisher) query.uniqueResult();
            p.setUsername("jeff");
         }

         public void step2(Session session) {
            Query query = session
                  .createQuery("from Subscriber where :foo = username");
            query.setString("foo", username);
            Subscriber s = (Subscriber) query.uniqueResult();
            s.setUsername("jeff");
         }
      };

      // We create a task (ready to be run) which will
      // update the Subscriber, pause, then update the
      // Publisher (the opposite order to TaskA).
      Task taskB = new Task("Task B", username) {
         public void step1(Session session) {
            Query query = session
                  .createQuery("from Subscriber where username = :who");
            query.setString("who", username);
            Subscriber s = (Subscriber) query.uniqueResult();
            s.setUsername("dave");
         }

         public void step2(Session session) {
            Query query = session
                  .createQuery("from Publisher where username = :who");
            query.setString("who", username);
            Publisher p = (Publisher) query.uniqueResult();
            p.setUsername("dave");
         }
      };

      // Run the first step of task A to completion
      new Thread(taskA).start();
      while (!taskA.isPaused())
         ;
      System.out.println("Task A is complete (paused)");

      // Run the first step of task B to completion
      new Thread(taskB).start();
      while (!taskB.isPaused())
         ;
      System.out.println("Task B is complete (paused)");

      // Both tasks have completed their first steps - we'll now
      // release the brakes from both of them, and see what
      // happens.

      taskA.setPaused(false);
      taskB.setPaused(false);
   }
}