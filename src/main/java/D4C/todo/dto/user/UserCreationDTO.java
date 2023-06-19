package D4C.todo.dto.user;

/**
 * D4C.todo.dto.user.UserCreationDTO is a class representing a Data Transfer Object
 * for creating a user
 */
public class UserCreationDTO {

    private String id;

    private String username;

    private String password;

    private String email;

    public UserCreationDTO(){}

    /**
     * @param id            -   UserCreationDTO.id
     * @param username      -   UserCreationDTO.name
     * @param password      -   UserCreationDTO.password
     * @param email         -   UserCreationDTO.email
     */
    public UserCreationDTO(String id,String username, String password, String email) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * @return UserCreationDTO.id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - UserCreationDTO's id to be set
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * @return UserCreationDTO.username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username - UserCreationDTO's username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return - UserCreationDTO.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password - UserCreationDTO's password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return UserCreationDTO.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email - UserCreationDTO's email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserCreationDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
