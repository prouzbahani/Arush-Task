package com.arusha.main.domain;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Person First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Person Last Name is mandatory")
    private String lastName;
    @NotBlank(message = "Person Email is mandatory")
    @Email(message = "Please enter a valid email address")
    private String email;



    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(
            name = "person_document",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private Set<Document> personDocuments;


    public Person(String firstName, String lastName, String email, Set<Document> personDocuments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personDocuments = personDocuments;
    }

    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Document> getPersonDocuments() {
        return personDocuments;
    }

    public void setPersonDocuments(Set<Document> personDocuments) {
        this.personDocuments = personDocuments;
    }

    @Override
   public String toString()
    {
        return "Person Details: First Name: "+ firstName +", Last Name: " + lastName+ ", Email: " +  email + ", Person Document: " + toStringDocumentList();
    }


    public String toStringDocumentList()
    {
        String result="";
        for (Document document : personDocuments) {
            result+= document.getName()+", ";
        }
        return result+";";
    }
}
