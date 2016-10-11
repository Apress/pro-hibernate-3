package com.hibernatebook.casestudy.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @hibernate.class
 * table="catalog" 
 */
public class Catalog {
   // The default constructor
   // required by Hibernate
   protected Catalog() {      
   }
   
   // A convenience constructor for 
   // client code
   public Catalog(String description) {
      setDescription(description);
   }
   
   /**
    * @hibernate.id column="id"
    * generator-class="native"
    * type="int" 
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * @hibernate.set role="catalogEntries" 
    * table="catalog_catalogentry" 
    * cascade="save-update"
    * @hibernate.collection-key 
    * column="catalog_id"
    * @hibernate.collection-many-to-many 
    * class="com.hibernatebook.casestudy.pojo.CatalogEntry" 
    * column="catalogentry_id"
    */
   public void setCatalogEntries(Set catalogEntries) {
      this.catalogEntries = catalogEntries;
   }
   
   /**
    * @hibernate.property column="description"
    * type="string"
    * not-null="true"
    */
   public void setDescription(String description) {
      this.description = description;
   }

   public int getId() {
      return id;
   }

   public Set getCatalogEntries() {
      return catalogEntries;
   }
   
   public String getDescription() {
      return description;
   }

   private int id;
   private Set catalogEntries = new HashSet();
   private String description;
}
