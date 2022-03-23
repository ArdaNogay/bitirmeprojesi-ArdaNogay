package com.softtech.softtechspringboot.Service;


import com.softtech.softtechspringboot.Converter.UserMapper;
import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.Entity.User;
import com.softtech.softtechspringboot.Enum.ErrorEnums.GeneralErrorMessage;
import com.softtech.softtechspringboot.Enum.ErrorEnums.UserErrorMessage;
import com.softtech.softtechspringboot.Exception.DoesNotExistExceptions;
import com.softtech.softtechspringboot.Exception.DuplicateEntityExceptions;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Exception.InvalidParameterExceptions;
import com.softtech.softtechspringboot.Service.EntityService.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldSave() {
        UserSaveAndUpdateRequestDto requestDto = mock(UserSaveAndUpdateRequestDto.class);
        when(requestDto.getUserName()).thenReturn("test");
        when(requestDto.getPassword()).thenReturn("123");

        User user = mock(User.class);

        when(passwordEncoder.encode(anyString())).thenReturn("1235_asd");
        when(userEntityService.save(any())).thenReturn(user);
        UserSaveAndUpdateRequestDto result = userService.save(requestDto);

        assertEquals("1235_asd", result.getPassword());
    }

    @Test
    void shouldNotSaveWhenUserNameIsExist() {
        UserSaveAndUpdateRequestDto requestDto = mock(UserSaveAndUpdateRequestDto.class);

        when(requestDto.getUserName()).thenReturn("userName");
        when(userEntityService.findByUserName(requestDto.getUserName())).thenReturn("userName");

        assertThrows(DoesNotExistExceptions.class, () -> userService.save(requestDto));
    }

    @Test
    void shouldNotSaveWhenParameterIsNull() {
        assertThrows(NullPointerException.class, () -> userService.save(null));
    }

    @Test
    void shouldUpdate() {
        UserSaveAndUpdateRequestDto requestDto = mock(UserSaveAndUpdateRequestDto.class);
        when(requestDto.getUserName()).thenReturn("test");

        User user1 = mock(User.class);
        when(user1.getId()).thenReturn(1L);
        when(user1.getPassword()).thenReturn("1234_123");
        user1.setName("test1");

        User user2 = mock(User.class);
        when(user2.getId()).thenReturn(1L);
        user2.setName("test2");

        when(userEntityService.getUserByUserName(anyString())).thenReturn(user1);
        when(userEntityService.getByIdWithControl(anyLong())).thenReturn(user2);
        when(userEntityService.save(any())).thenReturn(user1);

        UserSaveAndUpdateRequestDto result = userService.update(1L, requestDto);

        assertEquals("1234_123", result.getPassword());
    }

    @Test
    void shouldNotUpdateWhenUserNameExists() {
        UserSaveAndUpdateRequestDto requestDto = mock(UserSaveAndUpdateRequestDto.class);
        when(requestDto.getUserName()).thenReturn("test");

        User user1 = mock(User.class);
        when(user1.getId()).thenReturn(1L);
        user1.setName("test1");

        User user2 = mock(User.class);
        when(user2.getId()).thenReturn(2L);
        user2.setName("test2");

        when(userEntityService.getUserByUserName(anyString())).thenReturn(user1);
        when(userEntityService.getByIdWithControl(anyLong())).thenReturn(user2);

        DuplicateEntityExceptions e = assertThrows(DuplicateEntityExceptions.class, () -> userService.update(1L, requestDto));

        assertEquals(UserErrorMessage.HAS_DUPLICATE_USER_USERNAME, e.getBaseErrorMessage());

        verify(userEntityService).validateEntityExist(anyLong());

    }

    @Test
    void shouldDelete() {
        userService.delete(anyLong());
        verify(userEntityService).deleteByIdWithControl(any());
    }

    @Test
    void shouldNotDeleteWhenIdNotExist() {
        doThrow(new InvalidParameterExceptions(GeneralErrorMessage.INVALID_REQUEST)).when(userEntityService).deleteByIdWithControl(anyLong());
        InvalidParameterExceptions result = assertThrows(InvalidParameterExceptions.class, () -> userService.delete(anyLong()));
        verify(userEntityService).deleteByIdWithControl(any());
        assertNotNull(result);
    }

    @Test
    void shouldFindAll() {

        User user = mock(User.class);
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserSaveAndUpdateRequestDto> requestDtoList = userService.findAll();

        assertEquals(1, requestDtoList.size());

    }

    @Test
    void shouldFindAllWhenUserListIsEmpty() {

        List<User> userList = new ArrayList<>();

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserSaveAndUpdateRequestDto> requestDtoList = userService.findAll();

        assertEquals(0, requestDtoList.size());
    }


    @Test
    void shouldNotFindAllWhenUserListIsNull() {

        when(userEntityService.findAll()).thenThrow(new EntityNotFoundExceptions(GeneralErrorMessage.ENTITIES_NOT_FOUND));

        EntityNotFoundExceptions e = assertThrows(EntityNotFoundExceptions.class, () -> userService.findAll());

        assertEquals(GeneralErrorMessage.ENTITIES_NOT_FOUND, e.getBaseErrorMessage());
    }
}