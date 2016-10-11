package com.hibernatebook.legacy;
public class Color {
   protected Color() {
   }

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
    * @return Returns the name.
    */
   public String getName() {
      return name;
   }
   
   /**
    * @param name The name to set.
    */
   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      return getName();
   }

   private int id;
   private String name;
}
