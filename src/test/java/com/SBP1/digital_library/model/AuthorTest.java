package com.SBP1.digital_library.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    @Test
    public void getEmailTest(){
        Author author = new Author();
        author.setEmail("boopathy@gmail.com");
        assertEquals("boopathy@gmail.com",author.getEmail());
    }
}
