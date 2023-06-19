package D4C.todo.features;

import D4C.todo.model.user.User;

/**
 * D4C.todo.features.Actions holds all methods to be shared among all features
 */
public abstract class Actions {


    /**
     * Method to show all actions that can be carried out via a feature
     */
    public abstract void showActionInfo();

    /**
     * Method to read all user Input
     * @return  User Input
     */
    public abstract String[] readUserInput();

    /**
     * Method to execute the selected action
     * @param user      - Signed in User object
     * @param userInput - An array of the user's inputted values
     */
    public abstract void executeAction(User user, String[] userInput);


}