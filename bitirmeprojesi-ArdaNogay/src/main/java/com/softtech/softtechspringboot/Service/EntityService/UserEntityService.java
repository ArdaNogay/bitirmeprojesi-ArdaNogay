package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Enum.ErrorEnums.UserErrorMessage;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserEntityService extends BaseEntityService<User, UserDao> {

    public UserEntityService(UserDao dao){
        super(dao);
    }

    public String findByUserName(String userName){
        return getDao().findByUserName(userName);
    }


    public User getById(Long id){
        return getDao().getById(id);
    }

    public User getUserByUserName(String userName){
        User userByUserName = getDao().getUserByUserName(userName);
        if (userByUserName == null) {
            throw new EntityNotFoundExceptions(UserErrorMessage.USER_NOT_FOUND_USERNAME);
        }
        return userByUserName;
    }

}
