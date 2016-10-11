
package com.hibernatebook.annotations;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@InheritanceJoinColumn(name="BOOK_ID")
public class ComputerBook extends Book
{
    protected String softwareName;
    
    public String getSoftwareName()
    {
        return softwareName;
    }
    public void setSoftwareName(String softwareName)
    {
        this.softwareName = softwareName;
    }
}
