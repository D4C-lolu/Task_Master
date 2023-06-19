package D4C.todo.dao;

import D4C.todo.db.DB;
import D4C.todo.dto.mapper.UserMapper;
import D4C.todo.dto.user.UserCreationDTO;
import D4C.todo.dto.user.UserDetailsDTO;
import D4C.todo.model.user.User;

import java.util.Optional;


/**
 * D4C.todo.dao.UserDAO is a class with methods for accessing the User data store
 */
public class UserDAO {
    private final DB db;

    private final UserMapper userMapper;


    public UserDAO() {
        this.db = DB.getInstance();
        this.userMapper = UserMapper.INSTANCE;
    }

    /**
     * Method gets a user object using the email as the key
     *
     * @param email -   user's email
     * @return -   Optional containing user object or null
     */
    public Optional<User> getUser(String email) {
        User u = db.getUsers().get(email);
        return Optional.ofNullable(u);
    }


    /**
     * Method creates a user object
     *
     * @param ucDTO -   userCreation DTO
     * @return -   Optional containing User Object
     */
    public Optional<User> createUser(UserCreationDTO ucDTO) {
        User u = userMapper.creationDTOtoUser(ucDTO);
        db.getUsers().put(u.getEmail(), u);
        return Optional.of(u);
    }

    /**
     * Method updates the details of a user object
     *
     * @param udDTO -   User Details DTO
     */
    public void updateUserDetails(UserDetailsDTO udDTO) {
        User u = db.getUsers().get(udDTO.getEmail());
        u.setPassword(udDTO.getPassword());
        u.setUsername(udDTO.getUsername());
    }

    public void deleteUserAccount(String email) {
        //Remove user
        db.getUsers().remove(email);
        //Remove tasks
        db.tasks().remove(email);
    }

    /**
     * Method to calculate the number of users
     * @return  -   number of registered users
     */
    public int getUserCount(){
        return db.getUsers().size();
    }

}
