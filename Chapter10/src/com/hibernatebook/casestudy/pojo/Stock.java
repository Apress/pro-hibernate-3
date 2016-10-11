package com.hibernatebook.casestudy.pojo;

/**
 * @author Dave Minter
 * @hibernate.class table="stock" 
 */
public class Stock {
   public Stock() {
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
    * @hibernate.many-to-one column="product"
    * class="com.hibernatebook.casestudy.pojo.Product"
    * not-null="true"
    */
   public void setProduct(Product product) {
      this.product = product;
   }

   /**
    * @hibernate.property column="quantity"
    * not-null="true"
    * type="int"
    */
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public int getId() {
      return id;
   }

   public int getQuantity() {
      return quantity;
   }

   public Product getProduct() {
      return product;
   }

   private int id;
   private int quantity;
   private Product product;
}
