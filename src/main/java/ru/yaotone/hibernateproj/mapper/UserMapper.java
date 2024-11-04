package ru.yaotone.hibernateproj.mapper;

import org.mapstruct.Mapper;
import ru.yaotone.hibernateproj.DTO.UpdateUserDTO;
import ru.yaotone.hibernateproj.DTO.UserDTO;
import ru.yaotone.hibernateproj.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDTOToUser(UserDTO userDTO);

    User updateUserDTOToUser(UpdateUserDTO userDTO);
}
