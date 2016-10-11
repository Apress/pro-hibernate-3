package com.hibernatebook.chapter3.dao;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.hibernatebook.chapter3.AdException;
import com.hibernatebook.chapter3.Category;


public class CategoryDAO extends DAO {
   public CategoryDAO() {
   }
   
   public List getAllCategories()
      throws AdException
   {
      try {
         Query q = HibernateHelper.getSession().createQuery("from Category");
         List list = q.list();
         return list;
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }
   }
   
   public Category createCategory(String title)
      throws AdException
   {
      try {
         Category category = new Category(title);
         HibernateHelper.getSession().save(category);
         return category;
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }
   }
   
   public void deleteCategory(Category category)
      throws AdException
   {
      try {
         HibernateHelper.getSession().update(category); //Attach user
         HibernateHelper.getSession().delete(category); //Delete it from the database
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }
   }
   
   public void update(Category category)
      throws AdException
   {
      try {
         HibernateHelper.getSession().update(category); //Attach user
      } catch ( HibernateException e ) {
         log.log(Level.SEVERE, "", e);
         throw new AdException("",e);
      }
   }
}
