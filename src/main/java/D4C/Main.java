package D4C;

import D4C.todo.features.Actions;
import D4C.todo.features.task.*;
import D4C.todo.features.user.DeleteUser;
import D4C.todo.features.user.EditUser;
import D4C.todo.features.user.SignIn;
import D4C.todo.features.user.SignUp;
import D4C.todo.model.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final SignUp signUp = new SignUp();

    private static final SignIn signIn = new SignIn();

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static Scanner sc;
    private static final Actions[] taskActionList = {

            new AddTask(), new CompleteTask(),
            new DeleteTask(), new EditTask(), new DisplayAllTasks(),
            new DisplayTask(), new SearchByDate(), new SearchByKeyword(),
            new DisplayActiveTasks(), new DisplayCompletedTasks()
    };

    private static final Actions[] userActionList = {
            new EditUser(), new DeleteUser()
    };

    /**
     * Method prints welcome message to the screen
     */
    public static void welcomeMenu() {
        logger.info("Welcome to TaskMaster!");
        logger.info("Please enter 1 to sign in");
        logger.info("Enter 2 to register as a new user");
        logger.info("Or O to exit the program");
    }

    /**
     * Method to sign in/up the user
     * @return an Optional containing the currently logged in User
     */
    public static Optional<User> enterChoice() {
        sc = new Scanner(System.in);
        logger.info("Please enter your response here");
        Optional<User> user;
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    exit();
                case 1:
                    user = signIn.run();
                    break;
                case 2:
                    user = signUp.run();
                default:
                    logger.error("Please enter either 1 or 2");
                    return null;
            }
        } catch (NumberFormatException e) {
            logger.info("Invalid user input");
            return null;
        }
        return user;
    }

    /**
     * Method to display the main menu message
     */
    public static void displayMenuMessage(){
        logger.info("Welcome valued user");
        logger.info("Please enter 1 to manage your tasks");
        logger.info("Enter 2 to edit your user details");
        logger.info("Enter 3 if you wish to terminate your account");
        logger.info("Enter 0 to exit");
    }

    /**
     * Method to handle main/home page interactivity
     * @param user -User object for currently signed in user
     */
    public static void homePage(Optional<User> user) {
        if (user.isEmpty()) {
            exit();
        }
        User u = user.get();
        while (true) {
            displayMenuMessage();
            sc = new Scanner(System.in);
            logger.info("Please enter your response here: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 0:
                        exit();
                    case 1:
                        manageTasks(u);
                    case 2:
                        executeAction(userActionList[0],u);
                        break;
                    case 3:
                        executeAction(userActionList[1],u);
                    default:
                        logger.error("Please enter either 1,2 or 3");
                }
            } catch (NumberFormatException e) {
                logger.info("Invalid user input");
            }
        }
    }

    /**
     * Method to display options for managing tasks eg adding, deleting etc
     */
    public static void listOptions(){
        logger.info("\n");
        logger.info("Enter 1 to add a new task");
        logger.info("Enter 2 to mark a task as done");
        logger.info("Enter 3 to remove a task");
        logger.info("Enter 4 to edit a task");
        logger.info("Enter 5 to view all tasks");
        logger.info("Enter 6 to view a single task");
        logger.info("Enter 7 to search for tasks by a keyword");
        logger.info("Enter 8 to search for tasks by date");
        logger.info("Enter 9 to view all incomplete tasks");
        logger.info("Enter 10 to view all completed tasks");
        logger.info("Or enter 0 to exit");

    }

    public static void manageTasks(User u){
        listOptions();
        sc = new Scanner(System.in);
        logger.info("Please enter your response here: ");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    exit();
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    executeAction(taskActionList[choice-1],u);
                    break;
                default:
                    logger.error("Invalid number input");
            }
        } catch (NumberFormatException e) {
            logger.info("Invalid user input");
        }
    }

    /**
     * Method to execute any of the task features
     * @param action        -   A class inheriting from D4C.todo.features.Actions
     * @param user          -   The currently signed in user object
     */
    public static void executeAction(Actions action, User user){
        action.showActionInfo();
        String[] ans =  action.readUserInput();
        action.executeAction(user,ans);
    }

    public  static void exit() {
        logger.info("Shutting down...");
        System.exit(0);
    }


    public static void main(String[] args) {
       welcomeMenu();
       Optional<User> u = enterChoice();
       homePage(u);
    }
}