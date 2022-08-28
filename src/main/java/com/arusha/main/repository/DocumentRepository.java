package com.arusha.main.repository;

import com.arusha.main.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {

    Document findByName(String name);
}
