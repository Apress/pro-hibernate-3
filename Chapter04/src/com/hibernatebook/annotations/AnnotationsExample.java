package com.hibernatebook.annotations;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class AnnotationsExample
{

    public static void main(String[] args)
    {
        try
        {
            AnnotationConfiguration cfg = ExampleConfiguration.getConfig();
            SchemaExport se = new SchemaExport(cfg);
            se.create(true, true);
            SessionFactory sf = cfg.buildSessionFactory();
            Session session = sf.openSession();
            Transaction trans = session.beginTransaction();

            Publisher pub = new Publisher();
            pub.setName("Apress");

            Author jeff = new Author();
            jeff.setName("Jeff");
            session.save(jeff);
            
            Author dave = new Author();
            dave.setName("Dave");
            session.save(dave);
            
            Book book = new Book();
            book.setTitle("Pro Hibernate 3 ");
            book.setPages(200);
            book.setPublicationDate(new Date());
            book.getAuthors().add(jeff);
            book.getAuthors().add(dave);

            pub.getBooks().add(book);

            ComputerBook compBook = new ComputerBook();
            compBook.setTitle("Building Portals with the Java Portlet API");
            compBook.setPages(350);
            compBook.setSoftwareName("Apache Pluto");
            compBook.getAuthors().add(jeff);
            compBook.getAuthors().add(dave);
            
            pub.getBooks().add(compBook);
            
            session.save(pub);
            
            jeff.getBooks().add(book);
            dave.getBooks().add(book);

            jeff.getBooks().add(compBook);
            dave.getBooks().add(compBook);

            trans.commit();
            
            displayData(session);
            
            session.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void displayData(Session session)
    {
        Transaction trans = session.beginTransaction();
        Query pubQuery = session.createQuery("from Publisher p");
        pubQuery.setMaxResults(1);
        Publisher pub = (Publisher) pubQuery.uniqueResult();
        trans.commit();
        
        if (pub == null)
        {
            System.out.println("No publishers found");
            return;
        }
        System.out.println("Publisher name: " + pub.getName());
        Set<Book> books = pub.getBooks(); 
        if (books != null)
        {
            Iterator<Book> iter = books.iterator();
            while (iter.hasNext())
            {
                Book book = iter.next();
                System.out.println("Title: " + book.getTitle());
                
                Set<Author> authors = book.getAuthors();
                if (authors != null)
                {
                    Iterator<Author> aIter = authors.iterator();
                    while (aIter.hasNext())
                    {
                        Author author = aIter.next();
                        System.out.println("Author: " + author.getName());
                    }
                }
                
                if (book instanceof ComputerBook)
                {
                    ComputerBook compBook = (ComputerBook) book;
                    System.out.println("Software: " +compBook.getSoftwareName());
                }
            }
        }
        
        
        
    }
    
}