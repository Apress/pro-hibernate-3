package com.hibernatebook.chapter01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Populates the HSQL Database with a message
 */
public class ConnectedPopulate {

   public static void main(String[] args) {
      try {
         setMotd(42,"This is the message of the day");
      } catch (MotdException e) {
         System.err.println("Couldn't get the message: " + e);
      }
   }

   public static void setMotd(int messageId,String message)
      throws MotdException
   {
      Connection c = null;
      PreparedStatement p = null;

      try {

         // This would tend to reduce to a single line from three
         Class.forName("org.hsqldb.jdbcDriver");
         c = DriverManager.getConnection("jdbc:hsqldb:file:messagedb", "sa", "");
         p = c.prepareStatement("insert into motd (id,message) values (?,?)");

         p.setInt(1, messageId);
         p.setString(2,message);
         p.executeUpdate();

      } catch (Exception e) {
         log.log(Level.SEVERE, "Could not store message", e);
         throw new MotdException(
               "Failed to store message in the database.", e);
      } finally {
         if (p != null) {
            try {
               p.close();
            } catch (SQLException e) {
               log.log(Level.WARNING,
                     "Could not close ostensibly open statement.", e);
            }
         }

         if (c != null) {
            try {
               c.close();
            } catch (SQLException e) {
               log.log(Level.WARNING,
                     "Could not close ostensibly open connection.", e);
            }
         }
      }
   }

   private static final Logger log = Logger.getAnonymousLogger();
}