package com.epam.springcore.repository;

import com.epam.springcore.config.TestConfig;
import com.epam.springcore.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.saveAllAndFlush(List.of(
                new User(1L, "a", "a", "a", "a", true),
                new User(2L, "a", "a", "b", "a", true),
                new User(3L, "a", "a", "c", "a", false)
        ));
    }

    @Test
    public void findByUsernameTest() {
        User user = userRepository.getUserByUsername("a");
        User user1 = new User(1L, "a", "a", "a", "a", true);
        assertThat(user.equals(user1));
    }

    @Test
    public void existsByUsernameTest() {
        boolean check = userRepository.existsByUsername("b");
        boolean expected = true;
        assert check == expected;
    }
}
