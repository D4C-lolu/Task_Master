package D4C.todo.features.task;


import D4C.todo.features.Actions;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class holds methods to view all completed tasks
 */
public class DisplayCompletedTasks extends Actions {

    private static final Logger logger = LogManager.getLogger(DisplayCompletedTasks.class);

    @Override
    public void showActionInfo() {

    }


    @Override
    public String[] readUserInput() {

        return new String[]{};
    }


    @Override
    public void executeAction(User user, String[] userInput) {
        logger.info("Currently active tasks of User :{}", user.getUsername());
        ListOrderedMap<String, Task> taskList = Service.getInstance().getCompletedTasks(user.getEmail());
        if (taskList.size() == 0) {
            logger.info("No completed tasks yet");
        } else {
            logger.info("\n");
            MapIterator<String, Task> iterator = taskList.mapIterator();
            while (iterator.hasNext()) {
                Task t = iterator.getValue();
                logger.info("Task: {} with Task ID: {}. Status: {} ,DateAdded: {}, ", t.getName(), t.getId(),
                        t.getStatus(), D4C.util.FormatDate.formatDate(t.getDateAdded()));
            }
        }
        logger.info("\n");

    }
}