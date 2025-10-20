package com.SBP1.digital_library.repository;

import com.SBP1.digital_library.dto.response.UserFilterResponse;
import com.SBP1.digital_library.model.LibUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        LibUser user1 = LibUser.builder().phoneNo("7708632978").name("Boopathy").email("boopathy@gmail.com").build();
        userRepository.save(user1);
        LibUser user2 = LibUser.builder().phoneNo("9514842978").name("Boopathy").email("boopathy2@gmail.com").build();
        userRepository.save(user2);
    }

    @Test
    public void testFindByName(){
        List<UserFilterResponse> list = userRepository.findByName("Boopathy");
        assertEquals(2, list.size());
    }
}
