package com.SBP1.digital_library.repository;

import com.SBP1.digital_library.model.Author;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Note: @DataJpaTest will automatically create a in memory H2 DB by default, even if we are not provide properties.
@DataJpaTest(properties = {"spring.datasource.url=jdbc:h2:mem/testdb", "spring.jpa.hibernate.ddl-auto=create-drop"})
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    //Note: setup() is a usual method name used in most cases, we can use some other names too
    @BeforeEach
    public void setup(){
        Author author = Author.builder().id("1").name("Boopathy").email("boopathy@gmail.com").build();
        authorRepository.save(author);
    }

    @Test
    public void findByEmailTest(){
        Author author = authorRepository.findByEmail("boopathy@gmail.com");
        assertEquals(author.getEmail(), "boopathy@gmail.com");
    }
}
