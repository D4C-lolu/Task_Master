package D4C.todo.dao;


import D4C.todo.db.DB;
import D4C.todo.dto.mapper.TaskMapper;
import D4C.todo.dto.task.TaskCreationDTO;
import D4C.todo.dto.task.TaskDetailsDTO;
import D4C.todo.model.task.Status;
import D4C.todo.model.task.Task;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.map.ListOrderedMap;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * D4C.todo.dao.TaskDAO is a class with methods for accessing the Task data store
 */
public class TaskDAO {
    private final DB db;

    private final TaskMapper taskMapper;

    public TaskDAO() {
        this.db = DB.getInstance();
        this.taskMapper = TaskMapper.INSTANCE;
    }

    /**
     * Method gets all User tasks
     *
     * @param email -   User email
     * @return -   List of User Tasks
     */
    public ListOrderedMap<String, Task> getUserTasks(String email) {
        return db.getTasks(email);

    }

    /**
     * Method to retrieve a Task
     *
     * @param email  -   User email
     * @param taskId -   Task id
     * @return -   Optional containing a task object
     */
    public Optional<Task> getTask(String email, String taskId) {

        Task t = db.getTasks(email).get(taskId);
        return Optional.ofNullable(t);
    }

    /**
     * Method to add a Task to a User's collection
     *
     * @param email -   User email
     * @param tcDTO -   Task Creation DTO
     * @return -   Optional containing Task object
     */
    public Optional<Task> addTask(String email, TaskCreationDTO tcDTO) {
        Task t = taskMapper.creationDTOtoTask(tcDTO);
        db.tasks().put(email, t);
        return Optional.ofNullable(t);
    }

    /**
     * Method to mark a task as complete
     *
     * @param email  -   User email
     * @param taskId -   Task id
     */
    public void endTask(String email, String taskId) {
        Task t = db.getTasks(email).get(taskId);
        t.setStatus(Status.COMPLETE);
    }

    /**
     * Method to get all currently active tasks for a user
     *
     * @param email -   User email
     * @return -   Map of Tasks with the id as the key
     */
    public ListOrderedMap<String, Task> getActiveTasks(String email) {
        ListOrderedMap<String, Task> taskList = db.getTasks(email);
        CollectionUtils.filter(taskList.values(), input -> input.getStatus().equals(Status.ACTIVE));
        return taskList;
    }

    /**
     * Method to get all completed tasks for a user
     *
     * @param email -   User email
     * @return -   Map of Tasks with the id as the key
     */
    public ListOrderedMap<String, Task> getCompleteTasks(String email) {
        ListOrderedMap<String, Task> taskList = db.getTasks(email);
        CollectionUtils.filter(taskList.values(), input -> input.getStatus().equals(Status.COMPLETE));
        return taskList;
    }


    /**
     * Method to update the details of a user's task
     *
     * @param email -   User email
     * @param t     -   Task object
     */
    public void updateTask(String email, TaskDetailsDTO t) {
        Task task = db.getTasks(email).get(t.getId());
        task.setName(t.getName());
        task.setDescription(t.getDescription());
        task.setStatus(t.getStatus());
    }

    /**
     * Method to delete a Task
     *
     * @param email -   User email
     * @param t     -   Task object
     */
    public void deleteTask(String email, Task t) {
        db.tasks().removeMapping(email, t);
    }


    /**
     * Method to search lists of task for a keyword in its name or description fields
     *
     * @param email -   User email
     * @param text  -   text/keyword
     * @return -   Map of tasks with id as keys
     */
    public ListOrderedMap<String, Task> searchTasks(String email, String text) {
        String t = text.toLowerCase();

        ListOrderedMap<String, Task> tasks = db.getTasks(email);
        //Check task descriptions or name for occurrences of text
        CollectionUtils.filter(tasks.values(), new Predicate<Task>() {
            @Override
            public boolean evaluate(Task task) {
                return task.getDescription().toLowerCase().contains(t)
                        || task.getName().toLowerCase().contains(t);
            }
        });

        return tasks;
    }

    /**
     * Method to search through the list of tasks for tasks added on a particular date
     *
     * @param email     -   User's email
     * @param dt        -   DateTime object
     * @return          -   Map of tasks with id as keys
     */
    public ListOrderedMap<String, Task> filterByTime(String email, LocalDateTime dt) {
        ListOrderedMap<String, Task> tasks = db.getTasks(email);

        CollectionUtils.filter(tasks.values(), new Predicate<Task>() {
            @Override
            public boolean evaluate(Task task) {
                ZoneId zone = ZoneId.of("Africa/Lagos");
                ZoneOffset zoneOffSet = zone.getRules().getOffset(LocalDateTime.now());
                Instant instant1 = task.getDateAdded().toInstant(zoneOffSet)
                        .truncatedTo(ChronoUnit.DAYS);
                Instant instant2 = dt.toInstant(zoneOffSet).truncatedTo(ChronoUnit.DAYS);
                return instant1.equals(instant2);
            }
        });


        return tasks;

    }

    /**
     * Method to get number of tasks a user has
     *
     * @param email -   User.email
     * @return -   Count of user tasks
     */
    public int getTaskCount(String email) {
        return db.getTasks(email).size();
    }
}
