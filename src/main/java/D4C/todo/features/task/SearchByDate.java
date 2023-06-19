package D4C.todo.features.task;


import D4C.todo.features.Actions;
import D4C.todo.model.task.Task;
import D4C.todo.model.user.User;
import D4C.todo.service.Service;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class holds methods to view all tasks created on a particular date
 */
public class SearchByDate extends Actions {

    private static final Logger logger = LogManager.getLogger(SearchByDate.class);

    @Override
    public void showActionInfo() {
        logger.info("");
        logger.info("Enter the date you wish to search for in the following order");
        logger.info("Year:(1-12), Month((1-12), Day(0-31)  ");
        logger.info("");
    }


    @Override
    public String[] readUserInput() {

        logger.info("");
        try{
            Scanner in = new Scanner(System.in);
            logger.info("Please enter the year: ");
            int year = Integer.parseInt(in.nextLine());
            if (year<0){
                logger.error("Invalid year");
                return null;
            }
            logger.info("Please enter the Month: ");
            int month = Integer.parseInt(in.nextLine());
            //Ensure month does not exceed bounds
            if (month<0 || month>12){
                logger.error("Invalid month");
                return null;
            }
            int day = Integer.parseInt(in.nextLine());
            if (day<0 || day >31){
                logger.error("Invalid day");
                return null;
            }

            return new String[]{String.valueOf(year),String.valueOf(month),String.valueOf(day)};
        }catch(NumberFormatException e){
            logger.error("Please enter valid numbers");
        }
        return null;

    }


    @Override
    public void executeAction(User user, String[] userInput) {

        if(userInput==null){
            logger.info("Invalid User Input");
            return;
        }
        logger.info("Tasks of User:{}  created on {}-{}-{} ", user.getUsername(),userInput[0],userInput[1],userInput[2]);
        ListOrderedMap<String, Task> taskList = Service.getInstance().findTaskByDate(user.getEmail(),userInput);
        if (taskList.size() == 0) {
            logger.info("No tasks found");
        } else {
            logger.info("\n");
            MapIterator<String, Task> iterator = taskList.mapIterator();
            while (iterator.hasNext()) {
                Task t = iterator.getValue();
                logger.info("Task: {} with Task ID: {}. Status: {} ,DateAdded: {}, ", t.getName(), t.getId(),
                        t.getStatus(), D4C.util.FormatDate.formatDate(t.getDateAdded()));
            }
        }
        logger.info("\n");

    }
}