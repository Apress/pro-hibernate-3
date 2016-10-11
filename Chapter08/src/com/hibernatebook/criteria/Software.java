package com.hibernatebook.criteria;

public class Software extends Product
{
    private String version;
    
    public Software()
    {
        super();
    }
    
    public Software(String name, String description, double price, String version)
    {
        super(name, description, price);
        this.setVersion(version);
    }

    public String getVersion()
    {
        return version;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }
}
