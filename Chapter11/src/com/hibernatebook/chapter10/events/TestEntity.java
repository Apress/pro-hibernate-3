package com.hibernatebook.chapter10.events;

/**
 * Used by the EventExerciser test as the data item.
 */
public class TestEntity {
   public TestEntity(){
   }
   
   public TestEntity(String value) {
      this.value = value;
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
    * @return Returns the value.
    */
   public String getValue() {
      return value;
   }
   /**
    * @param value The value to set.
    */
   public void setValue(String value) {
      this.value = value;
   }
   
   private int id;
   private String value;
}
