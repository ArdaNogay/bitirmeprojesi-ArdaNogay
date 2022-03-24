package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserEntityService extends BaseEntityService<User, UserDao> {

    public UserEntityService(UserDao dao) {
        super(dao);
    }

    public User getUserByUserName(String userName) {
        return getDao().getUserByUserName(userName);
    }

}
