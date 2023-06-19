package D4C.todo.dto.mapper;

import D4C.todo.dto.user.UserCreationDTO;
import D4C.todo.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * D4C.todo.dto.mapper.UserMapper is a class for mapping DTO's tp User objects
 */
@Mapper(uses={TaskMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User creationDTOtoUser(UserCreationDTO userCreationDTO);

}
