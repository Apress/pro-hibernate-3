package com.hibernatebook.legacy;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ExplicitConfigurationExample {
   public static void main(String[] args) {
      File configFile = new File("hibernate3.cfg.xml");
      Configuration v3Config = new Configuration();
      v3Config.configure(configFile);
      SessionFactory sessionFactory = 
         v3Config.buildSessionFactory();
      
      Session session = sessionFactory.openSession();
      // ...
   }
}
