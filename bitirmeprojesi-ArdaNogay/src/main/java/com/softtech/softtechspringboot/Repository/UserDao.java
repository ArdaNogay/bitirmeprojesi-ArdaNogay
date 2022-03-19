package com.softtech.softtechspringboot.Repository;

import com.softtech.softtechspringboot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    @Query("select userName from User")
    String findByUserName(String name);

    User getUserByUserName(String userName);
}
