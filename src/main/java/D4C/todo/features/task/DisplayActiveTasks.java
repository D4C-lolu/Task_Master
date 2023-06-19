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
 * Class holds methods to view all incomplete tasks
 */
public class DisplayActiveTasks extends Actions {

    private static final Logger logger = LogManager.getLogger(DisplayActiveTasks.class);

    @Override
    public void showActionInfo() {

    }


    @Override
    public String[] readUserInput() {

        return new String[]{};
    }


    @Override
    public void executeAction(User user, String[] userInput) {
        logger.info("Incomplete Tasks of User :{}", user.getUsername());
        ListOrderedMap<String, Task> taskList = Service.getInstance().getCurrentTasks(user.getEmail());
        if (taskList.size() == 0) {
            logger.info("No currently active tasks");
            logger.info("Enjoy your free time");
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