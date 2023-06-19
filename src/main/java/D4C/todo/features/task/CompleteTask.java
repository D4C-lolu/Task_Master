package D4C.todo.features.task;


import D4C.todo.features.Actions;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to mark a task as completed
 */
public class CompleteTask extends Actions {

    private static final Logger logger = LogManager.getLogger(CompleteTask.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the details of the task to be marked as complete:");
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

       Service.getInstance().endTask(user.getEmail(),userInput[0]);
       logger.info("Task has been marked as complete");

    }
}