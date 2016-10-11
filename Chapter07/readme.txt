This is the source code for Chapter 7 in Pro Hibernate 3.  The database is the same for both Chapter 7 and 8 - use the schemaexport to generate the database script for your database, or create the tables with the same script.

Within the CriteriaExample class, the examples used in the chapters are individual methods. You may call them from the main() method after you begin the transaction. For instance,by replacing the following line in CriteriaExample:

        example.executeDistinctCriteria(session);

with the next line:
        
	example.executeSimpleCriteria(session);

You will demonstrate a different example.

Set up your database by uncommenting the following lines in the main() method.

        //example.populate(session);
        //trans.commit();
        //trans = session.beginTransaction();

The populate() method adds data to the database that the examples will operate on.