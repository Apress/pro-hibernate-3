package com.hibernatebook.legacy;


public class Client {
   protected Client() {
   }

   public Client(String name,String number, String streetname, String town, String city) {
      this.name = name;
      this.number = number;
      this.streetname = streetname;
      this.town = town;
      this.city = city;
   }
   
   private int id;
   private String name;
   private String number;
   private String streetname;
   private String town;
   private String city;
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
   /**
    * @return Returns the number.
    */
   public String getNumber() {
      return number;
   }
   /**
    * @param number The number to set.
    */
   public void setNumber(String number) {
      this.number = number;
   }
   /**
    * @return Returns the streetname.
    */
   public String getStreetname() {
      return streetname;
   }
   /**
    * @param streetname The streetname to set.
    */
   public void setStreetname(String streetname) {
      this.streetname = streetname;
   }
   /**
    * @return Returns the town.
    */
   public String getTown() {
      return town;
   }
   /**
    * @param town The town to set.
    */
   public void setTown(String town) {
      this.town = town;
   }
   /**
    * @return Returns the city.
    */
   public String getCity() {
      return city;
   }
   /**
    * @param city The city to set.
    */
   public void setCity(String city) {
      this.city = city;
   }
}
