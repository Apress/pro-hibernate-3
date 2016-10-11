package com.hibernatebook.casestudy.pojo;

/**
 * @author Dave Minter
 * @hibernate.class table="catalogentry" 
 */
public class CatalogEntry {
   public CatalogEntry() {      
   }
   
   public CatalogEntry(Product product, Price price) {
      setProduct(product);
      setPrice(price);
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
    * cascade="save-update"
    */
   public void setPrice(Price price) {
      this.price = price;
   }

   /**
    * @hibernate.many-to-one column="product"
    * class="com.hibernatebook.casestudy.pojo.Product"
    * not-null="true"
    * cascade="save-update"
    */
   public void setProduct(Product product) {
      this.product = product;
   }

   public int getId() {
      return id;
   }

   public Product getProduct() {
      return product;
   }

   public Price getPrice() {
      return price;
   }

   private int id;
   private Product product;
   private Price price;
}
