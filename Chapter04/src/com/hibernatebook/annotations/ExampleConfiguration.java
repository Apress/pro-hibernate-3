
package com.hibernatebook.annotations;

import org.hibernate.cfg.AnnotationConfiguration;

public class ExampleConfiguration
{
    public static AnnotationConfiguration getConfig() {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.addAnnotatedClass(Author.class);
        cfg.addAnnotatedClass(Book.class);
        cfg.addAnnotatedClass(ComputerBook.class);
        cfg.addAnnotatedClass(Publisher.class);
        return cfg;
    }
}
