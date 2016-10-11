package com.hibernatebook.legacy;

public class Product {
   protected Product() {
   }
   
   public Product(ProductData data, Color color) {
      this.data = data;
      this.color = color;
   }
   
   public void setKey(ProductKey key) {
      this.key = key;
   }

   public ProductKey getKey() {
      return this.key;
   }
   
   public void setColor(Color color) {
      this.color = color;
   }
   
   public Color getColor() {
      return this.color;
   }
   
   public void setData(ProductData data) {
      this.data = data;
   }
   
   public ProductData getData() {
      return this.data;
   }
   
   private ProductKey key;
   private Color color;
   private ProductData data;
}
