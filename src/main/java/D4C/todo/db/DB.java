package D4C.todo.db;

import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.set.ListOrderedSet;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * D4C.todo.db.DB is a singleton class acting as a Database for the application.
 * It stores the list of users registered in the application
 */
public class DB {

    private static final DB _instance;

    /**
     * Key is email of the user
     */
    private BidiMap<String, User> userBidiMap;

    /**
     * Key is User's Email
     */
    private MultiValuedMap<String, Task> userTasks;


    /**
     * This method initializes the DB Class
     */
    private DB() {
        this.userBidiMap = new DualHashBidiMap<>();
        this.userTasks = new ArrayListValuedHashMap<>();
    }

    static {
        try {
            _instance = new DB();
        } catch (Exception e) {
            throw new RuntimeException("Error starting up DB");
        }
    }

    /**
     * @return DB._instance
     */
    public static DB getInstance() {
        return _instance;
    }

    /**
     * @return DB.userBidiMap
     */
    public BidiMap<String, User> getUsers() {
        return userBidiMap;
    }

    /**
     * @param email     -   User email
     * @return          -   Map of task Id and user Tasks
     */
    public ListOrderedMap<String,Task> getTasks(String email) {
        ListOrderedSet<Task> s = ListOrderedSet.listOrderedSet(new HashSet<>(this.userTasks.get(email)));

        return ListOrderedMap.listOrderedMap(s.stream().collect(Collectors.toMap(Task::getId, Function.identity())));
    }

    /**
     * @return      -   DB.userTasks
     */
    public MultiValuedMap<String, Task> tasks(){
        return userTasks;
    }

    /**
     * Method to save User and Task Objects to a file
     * @throws FileNotFoundException if file does not exist
     */
    public void save() throws FileNotFoundException {
        //Save Users
        Properties properties = new Properties();
        try {
            MapIterator<String, User> iterator = getUsers().mapIterator();
            while (iterator.hasNext()) {
                properties.put(iterator.getKey(), iterator.getValue());
            }

            properties.store(new FileOutputStream("data.userProperties"), null);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        //Save Tasks
        try{
            MapIterator<String, Task> taskMapIterator = tasks().mapIterator();
            while (taskMapIterator.hasNext()) {
                properties.put(taskMapIterator.getKey(), taskMapIterator.getValue());
            }

            properties.store(new FileOutputStream("data.taskProperties"), null);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * Method to read User and Task objects from a file
     * @throws IOException if file is not found
     */
    public void read() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("data.userProperties"));

        for (String key : properties.stringPropertyNames()) {
            getUsers().put(key, (User) properties.get(key));
        }

        properties.clear();

        properties.load(new FileInputStream(("data.taskProperties")));
        for (String key : properties.stringPropertyNames()) {
            tasks().put(key, (Task) properties.get(key));
        }

    }

}
