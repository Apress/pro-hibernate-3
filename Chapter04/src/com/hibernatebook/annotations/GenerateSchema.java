
package com.hibernatebook.annotations;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GenerateSchema
{

    public static void main(String[] args)
    {
       // Amended to allow a path (to the hibernate.cfg.xml) to
       // be passed to the configuration.
        AnnotationConfiguration cfg = ExampleConfiguration.getConfig();;
        SchemaExport schemaExport = new SchemaExport(cfg);
        schemaExport.create(true, true);
    }
}
