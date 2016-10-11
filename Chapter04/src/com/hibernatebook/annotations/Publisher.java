package com.hibernatebook.annotations;

import javax.persistence.*;
import java.util.*;

@Entity
public class Publisher
{
    protected String name;
    protected Set <Book> books = new HashSet<Book>();
    protected int id;
    
    @OneToMany(mappedBy="publisher")
    public Set<Book> getBooks()
    {
        return books;
    }
    public void setBooks(Set<Book>	 books)
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
