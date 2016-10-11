package com.hibernatebook.legacy;


public class ProductData {
   protected ProductData() {
   }
   
   public ProductData(int sku) {
      this.sku = sku;
   }
   
   private int id;
   private int sku;
   
   /**
    * @return Returns the id.
    */
   public int getId() {
      return id;
   }
   /**
    * @param id The id to set.
    */
   public void setId(int id) {
      this.id = id;
   }
   /**
    * @return Returns the sku.
    */
   public int getSKU() {
      return sku;
   }
   /**
    * @param sku The sku to set.
    */
   public void setSKU(int sku) {
      this.sku = sku;
   }
}
