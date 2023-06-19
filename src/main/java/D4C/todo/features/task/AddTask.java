package D4C.todo.features.task;

import D4C.todo.features.Actions;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;


/**.
 * AddTask holds all methods related to adding new tasks to our
 * TodoList
 *
 */
public class AddTask extends Actions {

    private static final Logger logger = LogManager.getLogger(AddTask.class);
    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the details of the new task as prompted:");
        logger.info("Name:  , Description: ");
        logger.info("");
    }



    @Override
    public String[] readUserInput() {

            logger.info("");
            logger.info("Please enter the name of the task: ");
            Scanner in = new Scanner(System.in);
            String taskName = in.nextLine();
            logger.info("Now enter its description: ");
            String taskDescription =in.nextLine();
        return new String[]{taskName,taskDescription};
    }


    @Override
    public void executeAction(User user, String[] userInput) {
        if(userInput==null){
            logger.info("Invalid User Input");
            return;
        }
        Optional<Task> t =  Service.getInstance().newTask(user.getEmail(),userInput[0],userInput[1]);
        if(t.isPresent()){
            logger.info("Task added successfully");
        }
        else{
            logger.info("Error occurred adding task");
        }

    }
}