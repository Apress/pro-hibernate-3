package com.hibernatebook.casestudy.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dave Minter
 * @hibernate.class table="order" 
 */
public class Order {
   public Order() {      
   }
   
   /**
    * @hibernate.id column="id"
    * generator-class="native"
    * type="int" 
    */
   public void setId(int id) {
      this.id = id;
   }

   /**
    * @hibernate.set role="lineItems" table="order_lineitem"
    * @hibernate.collection-key column="order_id"
    * @hibernate.collection-many-to-many class="com.hibernatebook.casestudy.pojo.LineItem" column="lineitem_id"
    */
   public void setLineItems(Set lineItems) {
      this.lineItems = lineItems;
   }
   
   /**
    * @hibernate.property column="expedited"
    * type="boolean"
    * not-null="true"
    */
   public void setExpedited(boolean expedited) {
      this.expedited = expedited;
   }

   public int getId() {
      return id;
   }

   public Set getLineItems() {
      return lineItems;
   }

   public boolean isExpedited() {
      return expedited;
   }

   private int id;
   private Set lineItems = new HashSet();
   private boolean expedited = false;
}
