package D4C.todo.features.user;


import D4C.todo.features.Actions;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to edit a user
 */
public class EditUser extends Actions {

    private static final Logger logger = LogManager.getLogger(EditUser.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the new User details :");
        logger.info("");
    }


    @Override
    public String[] readUserInput() {

        logger.info("");
        Scanner in = new Scanner(System.in);
        String op;
        int option = 0;
        logger.info("Enter 1 to change your username and 2 to change your password: ");
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
                logger.info("Now enter the new username: ");
                detail = in.nextLine();
                break;
            case 2:
                logger.info("Now enter the new password: ");
                detail = in.nextLine();
                break;
            default:
                logger.info("Invalid number. Enter either 1 or 2");
        }

        return new String[]{op,detail};
    }


    @Override
    public void executeAction(User user, String[] userInput) {

        if(userInput==null){
            logger.info("Invalid User Input");
            return;
        }
        int option = Integer.parseInt(userInput[0]);
        switch(option){
            case 1:
                Service.getInstance().updateUserName(user.getEmail(),userInput[1]);
                logger.info("Username updated successfully");
                break;
            case 2:
                Service.getInstance().updateUserPassword(user.getEmail(),userInput[1]);
                logger.info("Password updated successfully");
                break;
            default:
                logger.error("Invalid input");
        }

    }
}