package com.softtech.softtechspringboot.repository;

import com.softtech.softtechspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User getUserByUserName(String userName);
}
