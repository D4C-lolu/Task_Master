package D4C.todo.features.task;


import D4C.todo.features.Actions;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to delete a Task
 */
public class DeleteTask extends Actions {

    private static final Logger logger = LogManager.getLogger(DeleteTask.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the details of the task to be deleted:");
        logger.info("Id:  ");
        logger.info("");
    }


    @Override
    public String[] readUserInput() {

        logger.info("");
        logger.info("Please enter the Task id: ");
        Scanner in = new Scanner(System.in);
        String taskId = in.nextLine();

        return new String[]{taskId};
    }


    @Override
    public void executeAction(User user, String[] userInput) {
        Service.getInstance().deleteTask(user.getEmail(), userInput[0]);
    }
}