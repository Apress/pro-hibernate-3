package com.hibernatebook.casestudy.pojo;

/**
 * @author Dave Minter
 * @hibernate.class table="price" 
 */
public class Price {
   public Price() {
   }
   
   public Price(Product product, int dollars, int cents ) {
      setProduct(product);
      setDollars(dollars);
      setCents(cents);
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
    * @hibernate.property column="dollars"
    * type="int"
    * not-null="true"
    */
   public void setDollars(int dollars) {
      this.dollars = dollars;
   }

   /**
    * @hibernate.property column="cents"
    * type="int"
    * not-null="true"
    */
   public void setCents(int cents) {
      this.cents = cents;
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

   public int getDollars() {
      return dollars;
   }

   public int getCents() {
      return cents;
   }

   private int id;
   private Product product;
   private int dollars;
   private int cents;
}
