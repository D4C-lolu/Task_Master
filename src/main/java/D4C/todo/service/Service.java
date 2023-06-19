package D4C.todo.service;


import D4C.todo.dao.TaskDAO;
import D4C.todo.dao.UserDAO;
import D4C.todo.db.DB;
import D4C.todo.dto.task.TaskCreationDTO;
import D4C.todo.dto.task.TaskDetailsDTO;
import D4C.todo.dto.user.UserCreationDTO;
import D4C.todo.dto.user.UserDetailsDTO;
import D4C.todo.model.task.Status;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * D4C.todo.service.Service is a singleton class containing methods
 * to manipulate the User and Task objects
 */
public class Service {

    private static final Logger logger = LogManager.getLogger(Service.class);

    private static final Service _instance;

    private static UserDAO userDAO;

    private static TaskDAO taskDAO;

    private Service() {
        userDAO = new UserDAO();
        taskDAO = new TaskDAO();
    }

    static {
        try {
            _instance = new Service();

        } catch (Exception e) {
            throw new RuntimeException("Error starting up application");
        }
    }

    public static Service getInstance() {
        return _instance;
    }

    /**
     * Method to sign in a user
     *
     * @param email    -   User email
     * @param password -   User password
     * @return -   Optional containing User object
     */
    public Optional<User> login(String email, String password) {
        //Check if user exists
        Optional<User> u = userDAO.getUser(email);
        u.ifPresent((user) -> {
            if (!user.getPassword().equals(password)) {
                logger.info("Incorrect User details");
            }
        });
        return u;
    }


    /**
     * Method to register a new user
     *
     * @param email    -   New user's email
     * @param username -   New user's username
     * @param password -   New user's password
     * @return -   Optional containing new user object
     */
    public Optional<User> register(String email, String username, String password) {


        //Check password length

        if (password.length() < 8) {
            logger.info("Password must be 8 characters longer or more");
            return null;
        }

        //Check if email is already in use
        Optional<User> u = userDAO.getUser(email);
        if (u.isPresent()) {
            logger.info("Email is already in use");
            return null;
        }

        //Check if email is a valid email
        //OWASP regex for email validation
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.matches(regexPattern, email)) {
            logger.info("Invalid email address");
            return null;
        }

        //Generate id
        int num = userDAO.getUserCount();
        String serial = String.valueOf(num);

        UserCreationDTO userCreationDTO = new UserCreationDTO(serial, username, password, email);
        Optional<User> user = userDAO.createUser(userCreationDTO);

        return user;
    }

    /**
     * Method gets a single task belonging to a user
     * @param email         -   User.email
     * @param taskId        -   Task.id
     * @return              -   Optional containg task
     */
    public Optional<Task> getTask(String email, String taskId){
       return taskDAO.getTask(email,taskId);
    }


    /**
     * Method gets all User tasks
     *
     * @param email -   User email
     * @return -   List of User Tasks
     */
    public ListOrderedMap<String, Task> getAllTasks(String email) {
        return taskDAO.getUserTasks(email);
    }

    /**
     * Method updates the name of a user object
     *
     * @param email    -   User's email
     * @param username -   New user's username
     */
    public void updateUserName(String email, String username) {
        Optional<User> u = userDAO.getUser(email);
        u.ifPresent(user -> {
            UserDetailsDTO uDTO = new UserDetailsDTO(email, username, user.getPassword());
            userDAO.updateUserDetails(uDTO);
        });
    }

    /**
     * Method to update a user's password
     *
     * @param email    -   User's email
     * @param password -   User's new password
     */
    public void updateUserPassword(String email, String password) {
        Optional<User> u = userDAO.getUser(email);
        u.ifPresent(user -> {
            UserDetailsDTO uDTO = new UserDetailsDTO(email, user.getUsername(), password);
            userDAO.updateUserDetails(uDTO);
        });
    }

    /**
     * Method to update the name of a task
     *
     * @param email  -   User email
     * @param taskId -   Task id
     * @param name   -   New task name
     */
    public void updateTaskName(String email, String taskId, String name) {
        Optional<Task> t = taskDAO.getTask(email, taskId);
        t.ifPresent(task -> {
            TaskDetailsDTO tDTO = new TaskDetailsDTO(taskId, name, task.getDescription(), task.getStatus());
            taskDAO.updateTask(email, tDTO);
        });
    }

    /**
     * Method to update a task's description
     *
     * @param email       -   User.email
     * @param taskId      -   Task.id
     * @param description -   New task description
     */
    public void updateTaskDescription(String email, String taskId, String description) {
        Optional<Task> t = taskDAO.getTask(email, taskId);
        t.ifPresent(task -> {
            TaskDetailsDTO tDTO = new TaskDetailsDTO(taskId, task.getName(), description, task.getStatus());
            taskDAO.updateTask(email, tDTO);
        });
    }

    /**
     * Method to get all unfinished tasks
     *
     * @param email -   User.email
     * @return -   Map of tasks with task id as key
     */
    public ListOrderedMap<String, Task> getCurrentTasks(String email) {
        return taskDAO.getActiveTasks(email);
    }

    /**
     * Method to create a new task
     *
     * @param email       -   User.email
     * @param name        -   Task.name
     * @param description -   Task.description
     * @return -   Optional containing task object
     */
    public Optional<Task> newTask(String email, String name, String description) {

        Optional<User> u = userDAO.getUser(email);
        Optional<Task> t = null;
        if (u.isPresent()) {
            User user = u.get();
            String userSerial = user.getId();
            String taskSerial = userSerial + String.valueOf(taskDAO.getTaskCount(email));
            TaskCreationDTO tcDTO = new TaskCreationDTO(taskSerial, name, description, Status.ACTIVE, email);
            t = taskDAO.addTask(email, tcDTO);
        }
        return t;
    }

    /**
     * Method to mark a task as complete
     *
     * @param email  -   User email
     * @param taskId -   Task id
     */
    public void endTask(String email, String taskId) {
        taskDAO.endTask(email, taskId);

    }

    /**
     * Method to get all finished tasks
     *
     * @param email -   User.email
     * @return -   Map of tasks with task id as key
     */
    public ListOrderedMap<String, Task> getCompletedTasks(String email) {
        return taskDAO.getCompleteTasks(email);
    }

    /**
     * Method to delete a Task
     *
     * @param email     -   User email
     * @param taskId    -   Task object id
     */
    public void deleteTask(String email, String taskId) {
        Optional<User> user = userDAO.getUser(email);
        Optional<Task> task = taskDAO.getTask(email,taskId);
        if (user.isPresent() && task.isPresent()){
            taskDAO.deleteTask(email, task.get());
            logger.info("Task deleted successfully");
        }
        else{
            logger.info("Invalid Task id");
        }
    }

    /**
     * Method to search lists of task for a keyword in its name or description fields
     *
     * @param email -   User email
     * @param text  -   text/keyword
     * @return -   Map of tasks with id as keys
     */
    public ListOrderedMap<String, Task> findTaskByKeyWord(String email, String text) {
        return taskDAO.searchTasks(email, text);
    }

    /**
     * Method to search through the list of tasks for tasks added on a particular date
     *
     * @param email -   User's email
     * @param time  -   date string in the format yy-mm-dd
     * @return -   Map of tasks with id as keys
     */
    public ListOrderedMap<String, Task> findTaskByDate(String email, String[] date) {
        ListOrderedMap<String, Task> map = new ListOrderedMap<>();
        try {

            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            LocalDateTime dt = LocalDateTime.of(year, month, day, 0, 0);
            map = taskDAO.filterByTime(email, dt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please input a valid date");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
        return map;
    }

    /**
     * Method to load Objects from file storage
     */
    public void load() {
        try {
            DB.getInstance().read();
        } catch (IOException e) {
            logger.info("DB File not found");

        }
    }

    /**
     * Method to save all objects in a file
     */
    public void save(){
        try{
            DB.getInstance().save();
        }
        catch(FileNotFoundException f){
            logger.info("DB File not Found");
        }
    }

    /**
     * Method to delete User account and all tasks associated with them
     * @param email     -   User.email
     */
    public void deleteUser(String email){
        userDAO.deleteUserAccount(email);
    }

}
