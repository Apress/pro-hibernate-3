package com.hibernatebook.chapter01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class which can display a message of the day from the database -
 * illustrates the volume of boilerplate required for correct JDBC usage.
 */
public class ConnectedMotd {

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

   public static Motd getMotd(int messageId) throws MotdException {
      Connection c = null;
      PreparedStatement p = null;
      Motd message = null;

      try {

         // This would tend to reduce to a single line from three
         Class.forName("org.hsqldb.jdbcDriver");
         c = DriverManager.getConnection(
               "jdbc:hsqldb:file:messagedb", "sa", "");
         p = c.prepareStatement("select message from motd where id = ?");

         p.setInt(1, messageId);
         ResultSet rs = p.executeQuery();

         if (rs.next()) {
            String text = rs.getString(1);
            message = new Motd(messageId, text);

            if (rs.next()) {
               log.warning("Multiple messages retrieved for message ID: "
                     + messageId);
            }
         }

      } catch (Exception e) {
         log.log(Level.SEVERE, "Could not acquire message", e);
         throw new MotdException(
               "Failed to retrieve message from the database.", e);
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

      return message;
   }

   private static final Logger log = Logger.getAnonymousLogger();
}