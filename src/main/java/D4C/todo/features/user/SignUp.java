package D4C.todo.features.user;


import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

/**
 * .
 * SignUp holds all methods related to adding new users
 */
public class SignUp {

    private static final Logger logger = LogManager.getLogger(SignUp.class);

    public void showActionInfo() {
        logger.info("");
        logger.info("Enter your details as prompted :");
        logger.info("Username:  ,Email:    ,Password(8 characters or more): ");
        logger.info("");
    }


    public String[] readUserInput() {

        logger.info("");
        logger.info("Please enter your username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        logger.info("Now enter your email address: ");
        String email = in.nextLine();
        logger.info("Enter your password: ");
        String password = in.nextLine();
        return new String[]{username, email, password};
    }


    public Optional<User> executeAction(String[] userInput) {

        if (userInput == null) {
            logger.info("Invalid User Input");
            return null;
        }
        Optional<User> u = Service.getInstance().register(userInput[1], userInput[0], userInput[2]);
        if (u.isPresent()) {
            logger.info("User added successfully");
            return u;
        } else {
            logger.info("Invalid details");

        }
        return null;
    }

    public Optional<User> run(){
        showActionInfo();
        String[] ans = readUserInput();
        Optional<User> u = executeAction(ans);
        return u;
    }
}