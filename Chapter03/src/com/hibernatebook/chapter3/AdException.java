package com.hibernatebook.chapter3;


public class AdException extends Exception {
   public AdException(String message) {
      super(message);
   }
   
   public AdException(String message, Throwable cause) {
      super(message,cause);
   }
}
