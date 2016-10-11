package com.hibernatebook.chapter01;

public class MotdException extends Exception {
   public MotdException(String message) {
      super(message);
   }

   public MotdException(String message, Throwable cause) {
      super(message, cause);
   }
}

