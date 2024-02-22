package com.epam.springcore.repository;

import com.epam.springcore.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeAll
    public void setUp() {
        userRepository.saveAllAndFlush(List.of(
                new User(1L, "a", "a", "a", "a", true),
                new User(2L, "a", "a", "b", "a", true),
                new User(3L, "a", "a", "c", "a", false)
        ));
    }

    @Test
    public void findByUsernameTest() {
        User user = userRepository.findUserByUsername("a").get();
        User user1 = new User(1L, "a", "a", "a", "a", true);
        assertThat(user.equals(user1));
    }

    @Test
    public void existsByUsernameTest() {
        boolean check = userRepository.existsByUsername("b");
        boolean expected = true;
        assert check == expected;;
    }
}
