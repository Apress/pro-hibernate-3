package com.hibernatebook.casestudy.pojo;

/**
  * @author Dave Minter
  * @hibernate.class table="product" 
  */
public class Product {
   public Product() {
   }
   
   public Product(String description, String sku) {
      setDescription(description);
      setSku(sku);
   }
   
   /**
    * @hibernate.id column="id"
    * generator-class="native"
    * type="int" 
    */
   public int getId() {
      return id;
   }

   /** 
    * @hibernate.property column="description"
    * type="text"
    * length="255"
    */
   public String getDescription() {
      return description;
   }

   /**
    * @hibernate.property column="sku" 
    * type="text" 
    * length="16" 
    * not-null="true" 
    * unique="true"
    */
   public String getSku() {
      return sku;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setSku(String sku) {
      this.sku = sku;
   }
   
   public String toString() {
      return "Product { id=" + id + ", description=\"" + description + "\", sku=" + sku + "}";
   }

   private int id;
   private String sku;
   private String description;
}
