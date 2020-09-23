package duke;

import duke.message.errorMsg;
import duke.message.replyMsg;
import duke.util.Command;
import duke.util.IO;
import duke.util.Parser;
import duke.util.UI;
import duke.task.TaskList;

import static duke.util.ErrorChecker.verifyCmd;

/**
 * The main class Duke. Only has Main and Stop methods.
 * The main event loop happens here.
 */

public class Duke {
    /**
     * Gracefully shuts down Duke. Updates the boolean that tells the main event loop to stop.
     * Checks if the user has any unsaved changes. If so, it will prevent shutdown unless it's a force quit.
     * @param hasSaved  boolean check for unsaved changes
     * @param forceQuit boolean for force quit
     * @return exit condition of main event loop
     */
    private static boolean stop(boolean hasSaved, boolean forceQuit) {
        if (!(hasSaved || forceQuit)) {
            UI.reply(replyMsg.UNSAVED_CHANGES);
            return false;
        } else {
            if (forceQuit) {
                UI.reply(replyMsg.BYE_NO_SAVE);
            } else {
                UI.reply(replyMsg.BYE);
            }
            return true;
        }
    }

    /**
     * Handles the complicated logic for executing the 'load' command.
     *
     * @param taskList      The main task list
     * @param latestCommand The latest command
     */
    private static void executeLoad(TaskList taskList, Command latestCommand) {
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
            UI.reply(replyMsg.WARN_OVERWRITE);
        }
    }


    /**
     * The entry point of application. Main event loop also happens here.
     * The Parser instance is used to get user input and it returns a Command object.
     * The Command object, which stores information from the user in an accessible interface,
     * is then used to pass information to the switch statement that holds the command logic.
     * @param args the input arguments from the command line
     */

    public static void main(String[] args) {
        UI.reply(replyMsg.GREET);

        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        boolean endLoop = false;
        Command latestCommand;

        // MAIN EVENT LOOP:
        while (!endLoop) {
            parser.getInput();
            latestCommand = parser.parseCommand();

            // For debugging:
            if (UI.getDebugMode()) {
                latestCommand.debug();
            }

            // Check if latest command is valid using verifyCmd
            try {
                verifyCmd(latestCommand, taskList.getTaskCount());
            } catch (Exception e) {
                UI.error(e, errorMsg.COMMAND_INVALID_ERROR);
                continue;
            }

            switch(latestCommand.getCommand()) {

            /////////////////////////////////////////////////////////////////////////
            // UTILITIES
            //
            case "bye": // Exit condition is here
                // Check whether no changes to save or whether user force quit
                endLoop = stop(taskList.checkWhetherSaved(),
                               latestCommand.getArgument(0).equals("force"));
                break;
            case "list": // List all tasks
                taskList.showTaskList();
                break;
            case "debug": // Toggle debug mode
                UI.toggleDebug();
                UI.reply(replyMsg.DEBUG_MODE_TOGGLE + UI.getDebugMode());
                break;

            /////////////////////////////////////////////////////////////////////////
            // MANAGING TASKS
            //
            case "todo": // Add a task to the task list
                // FALLTHROUGH
            case "deadline": // Add a task to the task list
                // FALLTHROUGH
            case "event": // Add a task to the task list
                taskList.addTask(latestCommand, false); // false for non-silent
                break;
            case "delete": // Delete a task
                taskList.deleteTask(latestCommand.getArgument(0));
                break;
            case "done": // Complete a task
                taskList.completeTask(latestCommand.getArgument(0));
                break;

            /////////////////////////////////////////////////////////////////////////
            // SAVING AND LOADING
            //
            case "save":
                taskList.saveTasks(latestCommand.getArgument(0));
                break;
            case "load":
                executeLoad(taskList, latestCommand);
                break;
            case "showsaves":
                IO.showSaves();
                break;

            /////////////////////////////////////////////////////////////////////////
            // UNKNOWN COMMAND
            //
            default:
                UI.error(errorMsg.COMMAND_UNRECOG_ERROR);
            }
        }

    }
}
