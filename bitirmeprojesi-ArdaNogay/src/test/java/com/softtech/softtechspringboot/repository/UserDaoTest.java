package com.softtech.softtechspringboot.repository;

import com.softtech.softtechspringboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void getUserByUserName() {
        User result = userDao.getUserByUserName("Test3");
        assertNotNull(result);
        assertEquals("Test3", result.getUserName());
    }
}