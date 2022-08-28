package com.arusha.main;

import com.arusha.main.domain.Document;
import com.arusha.main.domain.Person;
import com.arusha.main.repository.DocumentRepository;
import com.arusha.main.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class TaskApplicationTests {


    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    PersonRepository personRepository;

    ///Initialize variables and objects
    Set<Document> documents = new HashSet<Document>();
    List<Person> personList = new ArrayList<>();
    List<Document> documentsList = new ArrayList<>();
    Document Art = new Document("Art", Instant.now(), Instant.now(),"Art Desc");
    Document Sport = new Document("Sport", Instant.now(), Instant.now(),"Sport Desc");
    Document Book = new Document("Book", Instant.now(), Instant.now(),"Book Desc");
    Person Ali = new Person("Ali", "AliZadeh", "Ali@gmail.com", documents);
    Person Reza = new Person("Reza", "RezaZadeh", "Reza@gmail.com", documents);
    Person Hosein = new Person("Hosein", "HoseinZadeh", "Hosein@gmail.com", documents);


    @Test
    void main() {

        ///Remove last data
        personRepository.deleteAll();
        documentRepository.deleteAll();
		/// main operations
        saveDocuments();
        getAllDocuments();
        savePersons();
        getAllPersons();
        updateDocument();
        updatePerson();
        removePerson();
        getAllPersonsWithDocuments();
        getOnePersonsWithDocuments();

    }


    @Test
    void saveDocuments() {
        documentRepository.save(Art);
        documentRepository.save(Sport);
        documentRepository.save(Book);
    }

    @Test
    void savePersons() {

		///save Ali with three Document
        documents.add(Art);
        documents.add(Sport);
        documents.add(Book);
        personRepository.save((Ali));
		///save Reza with two Document
        documents.remove(Art);
        personRepository.save((Reza));
		///save Hosein with one Document
        documents.remove(Sport);
        personRepository.save((Hosein));

    }

    @Test
    void getAllDocuments() {

        documentsList = documentRepository.findAll();
        documentsList.stream().forEach(x -> System.out.println(x.toString()));

    }

    @Test
    void getAllPersons() {

        personList = personRepository.findAll();
        personList.stream().forEach(x -> System.out.println(x.toString()));
    }

    @Test
    void updateDocument() {

        Document updateCandidateDocument = documentRepository.findByName("Art");
        if (updateCandidateDocument != null) {
            updateCandidateDocument.setName("updated_Art");
            updateCandidateDocument.setUpdated(Instant.now());
            documentRepository.save(updateCandidateDocument);
            System.out.println("New Document: " + documentRepository.findByName("updated_Art").toString());
        }
    }

    @Test
    void updatePerson() {

        Person updateCandidatePerson = personRepository.findByFirstNameAndLastName("Hosein", "HoseinZadeh");
        if (updateCandidatePerson != null) {
            updateCandidatePerson.setFirstName("updated_Hosein");
            updateCandidatePerson.setEmail("updated_email@g.com");
            updateCandidatePerson.getPersonDocuments().remove("Sport");
            personRepository.save(updateCandidatePerson);
            System.out.println("New Person: " + personRepository.findByFirstNameAndLastName("updated_Hosein", "HoseinZadeh").toString());
        }
    }

    @Test
    void removePerson() {

        Person updatedPerson = personRepository.findByFirstNameAndLastName("Reza", "RezaZadeh");
        if (updatedPerson != null) {
            updatedPerson.getPersonDocuments().removeAll(updatedPerson.getPersonDocuments());
            personRepository.save(updatedPerson);
            personRepository.delete(updatedPerson);
        }

    }

    @Test
    void getAllPersonsWithDocuments() {

        personList = personRepository.findPersonsWithDocuments();
        personList.stream().forEach(x -> System.out.println(x.toString()));

    }

    @Test
    void getOnePersonsWithDocuments() {

        personList = personRepository.getOnePersonsWithDocuments("Ali","AliZadeh");
        personList.stream().forEach(x -> System.out.println(x.toString()));

    }


}
