package D4C.todo.dto.task;

import D4C.todo.model.task.Status;

/**
 * D4C.todo.dto.task.TaskCreationDTO is a class representing a Data Transfer
 * Object for the creation of a Task
 */
public class TaskCreationDTO {

    private String id;
    private String name;

    private String description;

    private Status status;

    private String userEmail;

    public TaskCreationDTO(){}

    /**
     * @param id            -   TaskCreationDTO.id
     * @param name          -   TaskCreationDTO.name
     * @param description   -   TaskCreationDTO.description
     * @param status        -   TaskCreationDTO.status
     * @param userEmail     -   TaskCreationDTO.userEmail
     */
    public TaskCreationDTO(String id,String name, String description, Status status,String userEmail ){
        this.id=id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.userEmail = userEmail;
    }

    /**
     * @return TaskCreationDTO.id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - TaskCreationDTO's id to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return - TaskCreationDTO.name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - TaskCreationDTO'S name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return - TaskCreationDTO.description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description - TaskCreationDTO's description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return description - TaskCreationDTO.status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status - TaskCreationDTO's status to be set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return - TaskCreationDTO.userEmail
     */
    public String getUserId() {
        return userEmail;
    }

    /**
     * @param userEmail - TaskCreationDTO's userEmail to be set
     */
    public void setUserId(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "TaskCreationDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
