
package com.hibernatebook.annotations;

import java.util.*;

import javax.persistence.*;

@Entity (access=AccessType.PROPERTY)
public class Book
{
    protected String title;
    
    protected Publisher publisher;
    protected Set <Author> authors = new HashSet<Author>();
    
    protected int pages;
    protected int id;
    
    protected Date publicationDate;
        
    public int getPages()
    {
        return pages;
    }
    public void setPages(int pages)
    {
        this.pages = pages;
    }
    @Column(name="working_title",length=200,nullable=false)
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
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
    
    @Transient
    public Date getPublicationDate()
    {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate)
    {
        this.publicationDate = publicationDate;
    }
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="publisher_id")
    public Publisher getPublisher()
    {
        return publisher;
    }
    public void setPublisher(Publisher publisher)
    {
        this.publisher = publisher;
    }
    
    @ManyToMany
    public Set<Author> getAuthors()
    {
        return authors;
    }
    public void setAuthors(Set<Author> authors)
    {
        this.authors = authors;
    }    
}
