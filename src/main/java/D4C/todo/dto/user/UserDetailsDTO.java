package D4C.todo.dto.user;

/**
 * D4C.todo.dto.user.UserDetailsDTO is a class representing a
 * Data Transfer Object for updating a user's details
 */
public class UserDetailsDTO {

    private String email;

    private String username;

    private String password;

    public UserDetailsDTO(){}

    /**
     * @param email     -   UserDetailsDTO.email
     * @param username  -   UserDetailsDTO.username
     * @param password  -   UserDetailsDTO.password
     */
    public UserDetailsDTO(String email, String username, String password){
        this.email=email;
        this.username = username;
        this.password = password;
    }

    /**
     * @return  -   UserDetailsDTO.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email -   UserDetailsDTO's email to be sent
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return  -   UserDetailsDTO.username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username  -   UserDetailsDTO's username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return  -   UserDetailsDTO.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password  -   UserDetailsDTO's password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
