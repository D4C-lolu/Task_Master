import D4C.todo.db.DB;
import D4C.todo.model.task.Status;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;


public class ServiceTest {

    private Service service;

    private DB db;

    @BeforeEach
    void setUp() {
        service = Service.getInstance();
        db = DB.getInstance();

        //Populate DB
        User user1 = new User("0001", "D4C", "kurohitsugi", "okeowo@gmail.com");
        User user2 = new User("0002", "Buchi", "shakaho", "dd@yahoo.com");
        User user3 = new User("00011", "Ada", "Jajajasss", "kfkwf@gmail.com");
        db.getUsers().put(user1.getEmail(), user1);
        db.getUsers().put(user2.getEmail(), user2);
        db.getUsers().put(user3.getEmail(), user3);

        Task task1 = new Task("0001", "Walk the dogs", "Walk 2 dogs to the park today", Status.ACTIVE, user1.getEmail());
        Task task2 = new Task("0002", "Take out the garbage", "Take trash out of kitchen", Status.ACTIVE, user1.getEmail());
        Task task3 = new Task("0003", "Defrag hard drive", "De frag the hard drive for  your PC today", Status.ACTIVE, user1.getEmail());
        Task task4 = new Task("0004", "Complete DSP assignment", "Write Matlab assignment for DSP", Status.ACTIVE, user2.getEmail());
        Task task5 = new Task("0005", "Get eggs", "Go to shoprite and get some eggs", Status.ACTIVE, user2.getEmail());
        Task task6 = new Task("0006", "Feed the fish", "Buy some fish food and feed the gold fish", Status.ACTIVE, user1.getEmail());

        db.tasks().put(task1.getUserEmail(), task1);
        db.tasks().put(task2.getUserEmail(), task2);
        db.tasks().put(task3.getUserEmail(), task3);
        db.tasks().put(task4.getUserEmail(), task4);
        db.tasks().put(task5.getUserEmail(), task5);
        db.tasks().put(task6.getUserEmail(), task6);


    }

    @AfterEach
    void tearDown() {
        db.getUsers().clear();
        db.tasks().clear();
    }


    @Test
    void checkThatUsersCanBeFetchedFromDB(){

        User user3 = new User("00011", "Ada", "Jajajasss", "kfkwf@gmail.com");
        Optional<User> u = service.login(user3.getEmail(), user3.getPassword());
        assert(u.equals(user3));
    }

    @Test
    void checkThatEmailsAreUnique(){
        User user3 = new User("00011", "Ada", "Jajajasss", "kfkwf@gmail.com");
        Optional<User> u = service.register(user3.getEmail(), , user3.getUsername(),user3.getPassword());
        assert(u.equals(null));
    }

    @Test
    void CheckThatTasksCanBeRetrieved(){

        User user1 = new User("0001", "D4C", "kurohitsugi", "okeowo@gmail.com");
        ListOrderedMap<String, Task> t = service.getAllTasks(user1.getEmail());
        assert(t.size()==3);
    }

    @Test
    void CheckThatTasksCanBeSaved(){
        User user1 = new User("0001", "D4C", "kurohitsugi", "okeowo@gmail.com");
        service.newTask(user1.getEmail(),"Get bread","Go to shoprite and get some bread");
        ListOrderedMap<String, Task> t = service.getAllTasks(user1.getEmail());
        assert(t.size()==4);

    }

    @Test
    void checkThatTasksCanBeDeleted(){
        User user1 = new User("0001", "D4C", "kurohitsugi", "okeowo@gmail.com");
        service.deleteTask(user1.getEmail(), "0004");
        ListOrderedMap<String, Task> t = service.getAllTasks(user1.getEmail());
        assert(t.size()==2);

    }

    
}

