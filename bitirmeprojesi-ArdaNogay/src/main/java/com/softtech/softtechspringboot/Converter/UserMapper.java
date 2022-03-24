package com.softtech.softtechspringboot.Converter;

import com.softtech.softtechspringboot.Dto.UserSaveAndUpdateRequestDto;
import com.softtech.softtechspringboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserSaveAndUpdateRequestDto convertToUserSaveAndUpdateRequestDto(User user);

    User convertToUser(UserSaveAndUpdateRequestDto userSaveAndUpdateRequestDto);

    List<UserSaveAndUpdateRequestDto> convertToUserSaveAndUpdateRequestDtoList(List<User> userList);
}
