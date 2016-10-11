package com.hibernatebook.legacy;

import java.io.Serializable;

public class ProductKey implements Serializable {
   public ProductKey() {
   }
   
   public int getColorId() {
      return colorId;
   }

   public void setColorId(int colorId) {
      this.colorId = colorId;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
   
   public int hashCode() {
      return id ^ colorId;
   }
   
   public boolean equals(Object obj) {
      if(!(obj instanceof ProductKey)) return false;
      ProductKey that = (ProductKey)obj;
      return ((this.id == that.id) && (this.colorId == that.colorId));
   }

   private int id;
   private int colorId;
}