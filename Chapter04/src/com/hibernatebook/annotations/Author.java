
package com.hibernatebook.annotations;

 
import java.util.*;
import javax.persistence.*;

@Entity
public class Author
{
    protected String name;
    protected String email;
    
    protected Set <Book> books = new HashSet<Book>();
    protected int id;
    
    
    @ManyToMany(mappedBy="authors")
    public Set<Book> getBooks()
    {
        return books;
    }
    public void setBooks(Set<Book> books)
    {
        this.books = books;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @Id (generate=GeneratorType.AUTO)
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
}
