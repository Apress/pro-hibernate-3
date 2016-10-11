package com.hibernatebook.chapter01;

import java.io.Serializable;

public class Motd implements Serializable {
   protected Motd() {
   }

   public Motd(int id, String message) {
      this.id = id;
      this.message = message;
   }
    
   public int getId() {
      return id;
   }

   public String getMessage() {
      return message;
   }
   
   public void setId(int id) {
      this.id = id;
   }

   public void setMessage(String message) {
      this.message = message;
   }
   
   private int id;
   private String message;
}