package duke;

import duke.util.Command;
import duke.util.Parser;
import duke.task.TaskList;

import static duke.util.UI.reply;

public class Duke {
    // Gracefully shuts down Duke.
    // Doesn't do much now, but I'm guessing there will be file IO next time?
    private static void stop() {
        reply("Bye. Hope to see you again soon!");
    }


    // Main function. Main event loop happens here.
    // The Parser instance is used to get user input and it returns a Command object.
    // The Command object, which stores information from the user in an accessible interface,
    // is then used to pass information to the switch statement that holds the command logic.
    public static void main(String[] args) {
        String[] greetings = {"Hello! I'm Duke", "What can I do for you?"};
        reply(greetings);

        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        boolean endLoop = false;
        Command latestCommand;

        // MAIN EVENT LOOP:
        while (!endLoop) {
            parser.getUserInput();
            latestCommand = parser.parseCommand();
            // DEBUG:
            // latestCommand.debug();

            Exception err = null; // To hold any exceptions that occur

            // SWITCH: Handles the latest command.
            // The functions in other classes are written to return neat strings so that they can be wrapped by the
            // 'reply' function. This is to shove all formatting logic into the functions to keep things neat here.
            switch(latestCommand.getCommand()) {
            case "bye": // Exit condition is here
                stop();
                endLoop = true;
                break;
            case "list": // List all tasks
                reply(taskList.showTaskList());
                break;
            case "done": // Complete a task
                reply(taskList.completeTask(latestCommand.getArgument(0)));
                break;
            case "todo": // Add a task to the task list
                // FALLTHROUGH
            case "deadline": // Add a task to the task list
                // FALLTHROUGH
            case "event": // Add a task to the task list
                reply(taskList.addTask(latestCommand));
                break;
            default:
                reply("Invalid command! Please try again...");
            }
        }

    }
}
