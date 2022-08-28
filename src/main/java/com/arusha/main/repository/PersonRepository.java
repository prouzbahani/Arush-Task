package com.arusha.main.repository;

import com.arusha.main.domain.Document;
import com.arusha.main.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.TypedQuery;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByFirstNameAndLastName(String firstName,String lastName);

    @Query(value = "SELECT DISTINCT P FROM Person P INNER JOIN P.personDocuments C")
    List<Person> findPersonsWithDocuments();

    @Query("SELECT DISTINCT P FROM Person P INNER JOIN P.personDocuments C WHERE P.firstName = :firstName and P.lastName = :lastName")
    List<Person> getOnePersonsWithDocuments(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);


}
