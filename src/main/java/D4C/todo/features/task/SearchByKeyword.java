package D4C.todo.features.task;

import D4C.todo.features.Actions;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to view all tasks containing a key word
 */
public class SearchByKeyword extends Actions {

    private static final Logger logger = LogManager.getLogger(SearchByKeyword.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the word to be searched for as shown:  ");
        logger.info("Keyword: ");
        logger.info("");
    }


    @Override
    public String[] readUserInput() {

        logger.info("");
        logger.info("Enter keyword here: ");
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        return new String[]{word};

    }


    @Override
    public void executeAction(User user, String[] userInput) {

        if(userInput==null){
            logger.info("Invalid User Input");
            return;
        }
        logger.info("Tasks of User:{} containing \"{}\" ", user.getUsername(),userInput[0]);
        ListOrderedMap<String, Task> taskList = Service.getInstance().findTaskByKeyWord(user.getEmail(),userInput[0]);
        if (taskList.size() == 0) {
            logger.info("No tasks found");
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
