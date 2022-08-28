package com.arusha.main.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
public class Document {



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Document Name is mandatory")
    private String name;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Document creation Date is mandatory")
    private Instant creationDate;
    @NotNull(message = "Document last Updated Date is mandatory")
    private Instant lastUpdatedDate;
    private String description;

    @ManyToMany(fetch =FetchType.EAGER,mappedBy = "personDocuments")
    Set<Person> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return creationDate;
    }

    public void setCreated(Instant created) {
        this.creationDate = created;
    }

    public Instant getUpdated() {
        return lastUpdatedDate;
    }

    public void setUpdated(Instant updated) {
        this.lastUpdatedDate = updated;
    }



    public Document(String name, Instant created, Instant updated,String description) {
        this.name = name;
        this.creationDate = created;
        this.lastUpdatedDate = updated;
        this.description = description;

    }

    public Document() {

    }

    @Override
    public String toString()
    {
        return "Document Details: Name: "+ name +", Creation Date: " + creationDate.toString().substring(0,19)+ ", Last Updated Date: " +  lastUpdatedDate.toString().substring(0,19)+ description.toString()+ ", Persons: "+toStringPersonList();
    }

    public String toStringPersonList()
    {
        String result="";
        for (Person p : persons) {
            result+= p.getFirstName()+" "+p.getLastName()+", ";
        }
        return result+";";
    }

}
