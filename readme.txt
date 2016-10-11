---------------------------------------------
Pro Hibernate 3 by Dave Minter & Jeff Linwood
---------------------------------------------

As discussed in the book you'll need to download a variety of external
tools to run these examples; this is a quick precis; look in the book
for specifics.

J2SDK                 - http://java.sun.com/
Ant                   - http://ant.apache.org/
Hibernate             - http://hibernate.org/
Hibernate Annotations - http://annotations.hibernate.org/
HSQL                  - http://hsqldb.sourceforge.net/
FireBird              - http://firebird.sourceforge.net/
FireBird JDBC driver  - http://jaybirdwiki.firebirdsql.org/

Once you have downloaded and installed these tools you should update
the build.properties file in this directory with the appropriate
paths. You will then be able to build the examples from all the 
chapters with the following command:

   ant

Most of the build files within the chapter directories include a 
task to generate an appropriate HSQL database, and some include
tasks to run one or more of the examples.

For example:

   cd chapter01
   ant schemaexport
   ant populate
   ant connected
   ant hibernate

Archive Contents
================

chapter01
---------
The Message Of the Day example
   
chapter03
---------
The Adverts example

chapter04
---------
The annotations example. This requires JDK 1.5.x
Since the book went to print the Hibernate team have produced a better
Ant task for generate schemas as part of the Hibernate Tools project. 
Take a look at their web page for further details:
http://www.hibernate.org/hib_docs/tools/ant/index.html

chapter07
---------
The Criteria example

chapter08
---------
The HQL and HSQL example (uses the same entity classes as chapter07).

chapter09
---------
The deadlock and rollback examples. Note that these don't work as originally
described in the book; see the readme.txt in this chapter for more details.

chapter10
---------
The casestudy

chapter11
---------
The Events and Interceptors examples

chapter12
---------
The Filters example

chapter13
---------
The legacy database examples
