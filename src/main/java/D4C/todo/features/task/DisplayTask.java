package D4C.todo.features.task;


import D4C.todo.features.Actions;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

/**
 * Class holds methods to view a single task in detail
 */
public class DisplayTask extends Actions {

    private static final Logger logger = LogManager.getLogger(DisplayTask.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the details of the task:");
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

        Optional<Task> task = Service.getInstance().getTask(user.getEmail(), userInput[0]);
        if(task.isPresent()){
            Task t = task.get();
            logger.info("Name: {}",t.getName());
            logger.info("Id: {}",t.getId());
            logger.info("Description: {}",t.getDescription());
            logger.info("Status: {}",t.getStatus());
            logger.info("Date: {}",t.getDateAdded());
        }
        else{
            logger.info("No Task with that ID exists");
        }
    }
}