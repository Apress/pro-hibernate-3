package com.hibernatebook.casestudy;

import com.hibernatebook.casestudy.dao.CatalogDAO;
import com.hibernatebook.casestudy.dao.DAOException;
import com.hibernatebook.casestudy.pojo.Catalog;
import com.hibernatebook.casestudy.pojo.Price;
import com.hibernatebook.casestudy.pojo.Product;

public class TestHarness {
   public static void main(String[] args)
      throws DAOException
   {
      /*
      System.out.println("Create SessionFactory");
      SessionFactory sessions = new Configuration().configure().buildSessionFactory();
      System.out.println("Open session");
      Session session = sessions.openSession();
      System.out.println("Close session");
      session.close();
      System.out.println("Done.");
      */
      CatalogDAO dao = new CatalogDAO();
      Catalog catalog = new Catalog("Spring Catalog");      
      Product product = new Product("Sunglasses","00000002");
      Price price = new Price(product,4,45); // $4.45
      dao.addProduct(catalog,product,price);

      
   }
}
