package com.SBP1.digital_library.service;

import com.SBP1.digital_library.model.Author;
import com.SBP1.digital_library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthor(String email){
        return authorRepository.findByEmail(email);
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }
}
