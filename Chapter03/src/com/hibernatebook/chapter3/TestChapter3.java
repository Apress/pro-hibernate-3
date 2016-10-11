package com.hibernatebook.chapter3;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import com.hibernatebook.chapter3.dao.AdvertDAO;
import com.hibernatebook.chapter3.dao.CategoryDAO;
import com.hibernatebook.chapter3.dao.HibernateHelper;
import com.hibernatebook.chapter3.dao.PhoneDAO;
import com.hibernatebook.chapter3.dao.UserDAO;


public class TestChapter3 {

   public static void main(String[] args) {
      Transaction tx = HibernateHelper.getSession().beginTransaction();
      TestChapter3 tc3 = new TestChapter3();
      try {
         if ((args.length == 1) && args[0].equals("init")) {
            tc3.populateDatabase();
            tx.commit();
            System.out.println("Test data created and committed");
         } else {
            tc3.displayDatabase();
            tx.commit();
         }
      } catch (AdException e) {
         tx.rollback();
      } catch (HibernateException e) {
         tx.rollback();
      }
   }

   public void displayDatabase() throws AdException {
      Iterator ci = cdao.getAllCategories().iterator();
      while (ci.hasNext()) {
         Category category = (Category) ci.next();

         System.out.println("========");
         System.out.println("Category: " + category.getTitle());

         Iterator ai = category.getAdverts().iterator();
         while (ai.hasNext()) {
            Advert ad = (Advert) ai.next();
            System.out.println("  " + ad.getTitle());
            System.out.println("  " + ad.getMessage());
            System.out.println(" Contact: " + ad.getUser().getName());
            Iterator pi = pdao.getPhone(ad.getUser()).iterator();
            while (pi.hasNext()) {
               Phone phone = (Phone) pi.next();
               System.out.println(" " + phone.getComment() + ": "
                     + phone.getNumber());
            }
            System.out.println("--------");
         }
      }
   }

   public void populateDatabase() throws AdException {
      Category cat1 = cdao.createCategory("Computing");
      Category cat2 = cdao.createCategory("Instruments");

      User dave = udao.createUser("dminter", "london");
      pdao.createPhone("Mobile", "07973 000 000", dave);
      pdao.createPhone("Home", "0208 000 000", dave);
      pdao.createPhone("Work", "0207 000 000", dave);

      User jeff = udao.createUser("jlinwood", "austin");
      pdao.createPhone("Cell", "555 000 001", jeff);
      pdao.createPhone("Home", "555 000 002", jeff);
      pdao.createPhone("Work", "555 000 003", jeff);

      cat1.getAdverts().add(
            adao.createAdvert(
                  "Sinclair Spectrum for Sale",
                  "48k, original box and packaging.", 
                  dave));
      cat1.getAdverts().add(
            adao.createAdvert(
                  "IBM PC for sale",
                  "Original, not clone. 640Kb.",
                  dave));
      cat1.getAdverts().add(
            adao.createAdvert(
                  "Apple II for sale",
                  "Complete with paddles. Call after 5pm", 
                  dave));
      cat2.getAdverts().add(
            adao.createAdvert(
                  "Elderly baby Grand Piano for sale",
                  "Overstrung. Badly out of tune.",
                  dave));
      cat2.getAdverts().add(
            adao.createAdvert(
                  "Trombone for sale",
                  "Slide missing. £1 + £30 p&p", 
                  dave));
      cat2.getAdverts().add(
            adao.createAdvert(
                  "Marimba wanted",
                  "Will offer up to £100",
                  dave));

      cat1.getAdverts().add(
            adao.createAdvert(
                  "Atari 2600 wanted",
                  "Will pay up to $10",
                  jeff));
      cat1.getAdverts().add(
            adao.createAdvert(
                  "Timex 2000 for sale",
                  "Some games, $30",
                  jeff));
      cat1.getAdverts().add(
            adao.createAdvert(
                  "Laptop",
                  "Unwanted gift from disgruntled author. Otherwise good condition",
                  jeff));
      
      cat2.getAdverts().add(
            adao.createAdvert(
                  "Piccolo",
                  "Tarnished but good sound.",
                  jeff));
      cat2.getAdverts().add(
            adao.createAdvert(
                  "Slightly used triangle",
                  "Not quite triangular anymore. $1",
                  jeff));
      cat2.getAdverts().add(
            adao.createAdvert(
                  "Timpani set",
                  "$30 total, call for postage",
                  jeff));

      cdao.update(cat1);
      cdao.update(cat2);
   }

   private final UserDAO udao = new UserDAO();

   private final PhoneDAO pdao = new PhoneDAO();

   private final AdvertDAO adao = new AdvertDAO();

   private final CategoryDAO cdao = new CategoryDAO();

}