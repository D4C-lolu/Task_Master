package D4C.todo.features.user;


import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

/**
 * .
 * SignUp holds all methods related to logging in users
 */
public class SignIn {

    private static final Logger logger = LogManager.getLogger(SignIn.class);

    public void showActionInfo() {
        logger.info("");
        logger.info("Enter your details as prompted :");
        logger.info("Email:    ,Password:");
        logger.info("");
    }


    public String[] readUserInput() {

        logger.info("");
        Scanner in = new Scanner(System.in);
        logger.info("Please enter your email address: ");
        String email = in.nextLine();
        logger.info("Enter your password: ");
        String password = in.nextLine();
        return new String[]{email, password};
    }


    public Optional<User> executeAction(String[] userInput) {

        if (userInput == null) {
            logger.info("Invalid User Input");
            return null;
        }
        Optional<User> u = Service.getInstance().login(userInput[0], userInput[1]);
        if (u.isPresent()) {
            logger.info("Log In successful");
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