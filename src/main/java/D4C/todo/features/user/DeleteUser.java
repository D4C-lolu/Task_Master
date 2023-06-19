package D4C.todo.features.user;


import D4C.todo.features.Actions;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to delete a User
 */
public class DeleteUser extends Actions {

    private static final Logger logger = LogManager.getLogger(DeleteUser.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Are you sure you wish to proceed?:");
        logger.info("");
    }


    @Override
    public String[] readUserInput() {

        logger.info("");
        logger.info("Please type your username to confirm deletion and any other key to exit: ");
        Scanner in = new Scanner(System.in);
        String ans= in.nextLine();

        return new String[]{ans};
    }

    @Override
    public void executeAction(User user, String[] userInput) {
        if (userInput[0].equals(user.getUsername())){
         Service.getInstance().deleteUser(user.getEmail());
         logger.info("Account Deleted");
         logger.info("Goodbye");
         logger.info("Exiting....");
         System.exit(0);
        }
    }
}