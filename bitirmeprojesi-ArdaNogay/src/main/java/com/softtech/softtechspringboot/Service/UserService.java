package com.softtech.softtechspringboot.Service;

import com.softtech.softtechspringboot.Converter.UserMapper;
import com.softtech.softtechspringboot.Dto.UserDeleteDto;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Enum.ErrorEnums.UserErrorMessage;
import com.softtech.softtechspringboot.Exception.DoesNotExistExceptions;
import com.softtech.softtechspringboot.Exception.DuplicateEntityExceptions;
import com.softtech.softtechspringboot.Service.EntityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserEntityService userEntityService;
    private final PasswordEncoder passwordEncoder;

    public UserSaveAndUpdateRequestDto save(UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto){
        isUserNameExist(userSaveAndUpdateRequestDto);
        User user = UserMapper.INSTANCE.convertToUser(userSaveAndUpdateRequestDto);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userEntityService.save(user);
        UserSaveAndUpdateRequestDto saveRequestDto = UserMapper.INSTANCE.convertToUserSaveAndUpdateRequestDto(user);
        return saveRequestDto;
    }

    public UserSaveAndUpdateRequestDto update(Long id,UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto){
        userEntityService.entityExistValidation(id);
        userNameConflictControl(id, userSaveAndUpdateRequestDto);
        User user = userUpdateMapping(id, userSaveAndUpdateRequestDto);
        UserSaveAndUpdateRequestDto updatedRequestDto = UserMapper.INSTANCE.convertToUserSaveAndUpdateRequestDto(user);
        return updatedRequestDto;
            }

    public void delete(UserDeleteDto userDeleteDto){
        User user = deleteUserValidation(userDeleteDto);
        userEntityService.delete(user);
    }

    public List<UserSaveAndUpdateRequestDto> findAll(){
        List<User> userList = userEntityService.findAll();
        List<UserSaveAndUpdateRequestDto> requestDtoList = UserMapper.INSTANCE.convertToUserSaveAndUpdateRequestDtoList(userList);
        return requestDtoList;
    }

    private void isUserNameExist(UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto) {
        String userName = userEntityService.findByUserName(userSaveAndUpdateRequestDto.getUserName());
        if (userName != null) {
            throw new DoesNotExistExceptions(UserErrorMessage.HAS_DUPLICATE_USER_USERNAME);
        }
    }

    private User userUpdateMapping(Long id, UserSaveAndUpdateRequestDto userSaveRequestDto) {
        User user = userEntityService.getById(id);
        user.setUserName(userSaveRequestDto.getUserName());
        user.setPassword(userSaveRequestDto.getPassword());
        user.setName(userSaveRequestDto.getName());
        user.setSurname(userSaveRequestDto.getSurname());
        user = userEntityService.save(user);
        return user;
    }

    private void userNameConflictControl(Long id, UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto) {
        User userToCheck = userEntityService.getUserByUserName(userSaveAndUpdateRequestDto.getUserName());
        User user = userEntityService.getById(id);
        if(user.getId() != userToCheck.getId()){
            throw new DuplicateEntityExceptions(UserErrorMessage.HAS_DUPLICATE_USER_USERNAME);
        }
    }

    private User deleteUserValidation(UserDeleteDto userDeleteDto) {
        User user = userEntityService.getUserByUserName(userDeleteDto.getUserName());
        if(user==null){
            throw new DoesNotExistExceptions(UserErrorMessage.USER_NOT_FOUND_USERNAME);
        }
        return user;
    }
}
