package D4C.todo.model.task;


import java.time.LocalDateTime;
import java.util.Objects;

/**
 * D4C.todo.model.task.Task is a class representing a user TODO
 */
public class Task {

    private String id;

    private String name;

    private String description;

    private Status status;

    private LocalDateTime dateAdded;

    private String userEmail;

    public Task() {
        this.dateAdded = LocalDateTime.now();
    }

    /**
     * @param id          -   task.id
     * @param name        -   task.name
     * @param description -   task.description
     * @param status      -   task.status
     * @param userEmail      -   task.userEmail
     */
    public Task(String id, String name, String description, Status status, String userEmail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.userEmail = userEmail;
        this.dateAdded = LocalDateTime.now();
    }

    /**
     * @return - task.id
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
     * @return - task.name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - Task's name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return - task.description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description -task's description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return - task.status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status - task's status to be set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return - task.dateAdded
     */
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    /**
     * @return - task.userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail - task's userEmail to be set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
                ", dateAdded=" + dateAdded +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
