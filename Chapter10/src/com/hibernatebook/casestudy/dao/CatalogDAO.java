package com.hibernatebook.casestudy.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernatebook.casestudy.pojo.*;


public class CatalogDAO extends DAO {
   public CatalogDAO()
      throws DAOException
   {
      super();
   }

   public void addProduct(Catalog catalog, Product product, Price price)
      throws DAOException
   {
      Session session = null;
      Transaction tx = null;
      try {
         session = getSession();
         tx = session.beginTransaction();
         CatalogEntry entry = new CatalogEntry(product,price);
         catalog.getCatalogEntries().add(entry);
         session.saveOrUpdate(catalog);
         tx.commit();
      } catch( HibernateException e ) {
         rollback(tx);
         throw new DAOException("Could not add product: " + product,e);
      } finally {
         close(session);
      }
   }

   public void removeProduct(Product product)
      throws DAOException
   {
      Session session = null;
      Transaction tx = null;
      try {
         session = getSession();
         tx = session.beginTransaction();
         Query query = session.createQuery("delete from CatalogEntry where product = :product");
         query.setEntity("product",product);
         int rows = query.executeUpdate();
         tx.commit();
         if( rows == 0 )
           throw new DAOException("The specified product was not in the catalog, and so could not be deleted: " + product,null);
      } catch( HibernateException e ) {
         tx.rollback();
         throw new DAOException("Could not add product: " + product,e);
      } finally {
         close(session);
      }
   }

}
