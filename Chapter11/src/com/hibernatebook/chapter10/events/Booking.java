package com.hibernatebook.chapter10.events;

public class Booking {
   public Booking(String name, String seat) {
      this.name = name;
      this.seat = seat;
   }
   
   Booking() {
   }   
   
   protected String getName() {
      return name;
   }

   protected void setName(String name) {
      this.name = name;
   }

   protected String getSeat() {
      return seat;
   }

   protected void setSeat(String seat) {
      this.seat = seat;
   }

   private String seat;
   private String name;
}
