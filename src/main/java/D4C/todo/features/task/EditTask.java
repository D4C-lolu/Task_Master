package D4C.todo.features.task;

import D4C.todo.features.Actions;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to edit a task
 */
public class EditTask extends Actions {

    private static final Logger logger = LogManager.getLogger(EditTask.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the details of the task to be edited:");
        logger.info("Id:  ");
        logger.info("");
    }


    @Override
    public String[] readUserInput() {

        logger.info("");
        logger.info("Please enter the Task id: ");
        Scanner in = new Scanner(System.in);
        String taskId = in.nextLine();
        String op;
        int option = 0;
        logger.info("Enter 1 to change the task name and 2 to change the task's description: ");
        try {
            option = Integer.parseInt(in.nextLine());
            op = String.valueOf(option);
        } catch (NumberFormatException e) {
            logger.error("Invalid Input.");
            return null;
        }


        String detail = "";
        switch (option) {
            case 1:
                logger.info("Now enter the new task name: ");
                detail = in.nextLine();
                break;
            case 2:
                logger.info("Now enter the new task description: ");
                detail = in.nextLine();
                break;
            default:
                logger.info("Invalid number. Enter either 1 or 2");
        }

        return new String[]{taskId,op,detail};
    }


    @Override
    public void executeAction(User user, String[] userInput) {

        if(userInput==null){
            logger.info("Invalid User Input");
            return;
        }
        int option = Integer.parseInt(userInput[1]);
        switch(option){
            case 1:
                Service.getInstance().updateTaskName(user.getEmail(),userInput[0],userInput[2]);
                logger.info("Task updated successfully");
                break;
            case 2:
                Service.getInstance().updateTaskDescription(user.getEmail(),userInput[0],userInput[2]);
                logger.info("Task updated successfully");
                break;
            default:
                logger.error("Invalid input");
        }

    }
}