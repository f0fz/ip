package duke;

import duke.util.Command;
import duke.util.IO;
import duke.util.Parser;
import duke.util.UI;
import duke.task.TaskList;


public class Duke {
    // Gracefully shuts down Duke.
    // Doesn't do much now, but I'm guessing there will be file IO next time?
    private static void stop() {
        UI.reply("Bye. Hope to see you again soon!");
    }


    // Main function. Main event loop happens here.
    // The Parser instance is used to get user input and it returns a Command object.
    // The Command object, which stores information from the user in an accessible interface,
    // is then used to pass information to the switch statement that holds the command logic.
    public static void main(String[] args) {
        String[] greetings = {"Hello! I'm Duke", "What can I do for you?"};
        UI.reply(greetings);

        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        boolean endLoop = false;
        Command latestCommand;

        // MAIN EVENT LOOP:
        while (!endLoop) {
            parser.getInput();
            latestCommand = parser.parseCommand();
            // For debugging:
            // latestCommand.debug();

            // Switch handles all commands
            switch(latestCommand.getCommand()) {

            /////////////////////////////////////////////////////////////////////////
            // UTILITIES
            //
            case "bye": // Exit condition is here
                stop();
                endLoop = true;
                break;
            case "list": // List all tasks
                taskList.showTaskList();
                break;
            case "done": // Complete a task
                taskList.completeTask(latestCommand.getArgument(0));
                break;

            /////////////////////////////////////////////////////////////////////////
            // ADDING A NEW TASK
            //
            case "todo": // Add a task to the task list
                // FALLTHROUGH
            case "deadline": // Add a task to the task list
                // FALLTHROUGH
            case "event": // Add a task to the task list
                taskList.addTask(latestCommand, false); // false for non-silent
                break;

            /////////////////////////////////////////////////////////////////////////
            // SAVING AND LOADING
            //
            case "save":
                taskList.saveTasks(latestCommand.getArgument(0));
                break;
            case "load":
                // if there are no tasks or user has already specified /YES to overwrite current tasks,
                if (taskList.getTaskCount() == 0 ||
                        (latestCommand.getArgCount() >= 2 &&
                        latestCommand.getArgument(1).equals("YES"))) {

                    // clear then load tasks
                    taskList.clearAllTasks();
                    taskList.loadTasks(latestCommand.getArgument(0));
                }
                else {
                    // else, alert the user
                    UI.reply(new String[]{"Are you sure? This will replace all your current tasks.",
                                          "If you're sure, type load <filename> /YES."});
                }
                break;
            case "showsaves":
                IO.showSaves();
                break;

            /////////////////////////////////////////////////////////////////////////
            // UNKNOWN COMMAND
            //
            default:
                UI.error("Invalid command! Please try again...");
            }
        }

    }
}
