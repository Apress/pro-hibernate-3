package com.hibernatebook.casestudy.pojo;

/**
 * @author Dave Minter
 * @hibernate.class table="lineitem" 
 */
public class LineItem {
   public LineItem() {      
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
    * @hibernate.many-to-one column="price"
    * class="com.hibernatebook.casestudy.pojo.Price"
    * not-null="true"
    */
   public void setPrice(Price price) {
      this.price = price;
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
   public int getQuantity() {
      return quantity;
   }

   public int getId() {
      return id;
   }

   public Price getPrice() {
      return price;
   }

   public Product getProduct() {
      return product;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   
   private int id;
   private Price price;
   private Product product;
   private int quantity;
}
