package D4C.todo.dto.task;

import D4C.todo.model.task.Status;
import D4C.todo.model.task.Task;
import java.util.Objects;

/**
 * D4C.todo.dto.task.TaskDetailsDTO is a class representing a Data
 * Access Object for updating the details of a task
 */
public class TaskDetailsDTO {

    private String id;

    private String name;

    private String description;

    private Status status;


    public TaskDetailsDTO() {
    }

    /**
     * @param id          -   task.id
     * @param name        -   task.name
     * @param description -   task.description
     * @param status      -   task.status
     */
    public TaskDetailsDTO(String id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    /**
     * @return - TaskDetailDTO.id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - Task's id to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return - TaskDetailDTO.name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - TaskDetailDTO's name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return - TaskDetailDTO.description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description -TaskDetailDTO's description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return - TaskDetailDTO.status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status - TaskDetailDTO's status to be set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
