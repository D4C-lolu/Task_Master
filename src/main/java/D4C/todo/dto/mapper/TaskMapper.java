package D4C.todo.dto.mapper;

import D4C.todo.dto.task.TaskCreationDTO;
import D4C.todo.model.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * D4C.todo.dto.mapper.TaskMapper is a class for mapping DTO's tp Task objects
 */
@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task creationDTOtoTask(TaskCreationDTO taskCreationDTO);
}
