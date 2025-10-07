package com.SBP1.digital_library.repository;

import com.SBP1.digital_library.model.Author;
import com.SBP1.digital_library.model.AuthorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, AuthorCompositeKey> {
    Author findByEmail(String email);
}
