package D4C.todo.model.user;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * D4C.todo.model.user.User is a class representing an application user
 */
public class User {

    private String id;

    private String username;

    private String password;

    //Natural Key
    private String email;

    private LocalDateTime signupDate;



    public User(){
        this.signupDate = LocalDateTime.now();
    }

    /**
     *
     * @param id        -       User.id
     * @param username  -       User.username
     * @param password  -       User.password
     * @param email     -       User.email
     */
    public User(String id,String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.signupDate = LocalDateTime.now();
    }

    /**
     * @return - User.id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - User's id to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return - User.username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username  -  User's username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return - User.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password - User's password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return - User.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email - User's email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return User.signupDate
     */
    public LocalDateTime getSignupDate() {
        return signupDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
