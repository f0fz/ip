package duke.util;

/**
 * The static type ErrorChecker.
 *
 *  All error checking functions should be in the form:
 *  if (correctCondition1)
 *      if (correctCondition2)
 *          ...
 *              break;
 *  throw DukeException("____ command arguments not matching: _________");
 *  or, throw DukeException("____ command takes no arguments");
 *
 *  All 3 checkXXX functions will run successively. If no exceptions are thrown, then
 *  nothing will happen and all is well. Else, the main loop in Duke will catch the exception
 *  and print the error message.
 *
 *  TODO: Make separate functions to check things like mandatory/optional arguments, number of arguments, etc.
 */
public class ErrorChecker {

    /**
     * Checks if a string is numeric
     *
     * @param str the string
     * @return the boolean
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Check a 'task' command for validity.
     * Currently checks: to-do, deadline, event
     *
     * @param taskCmd the task command
     * @throws DukeException if the command is invalid
     */
    public static void checkTaskCmd(Command taskCmd) throws DukeException {
        switch(taskCmd.getCommand()) {

        // 1 argument (description) , 1 optional arguments (done)
        case "todo":
            if (taskCmd.getArgCount() == 1) {
                break;
            }
            if (taskCmd.getArgCount() == 2) {
                if (taskCmd.getArgument(1).equals("done")) {
                    break;
                }
            }
            throw new DukeException("Todos command argument not matching: description");

        // 2 arguments (description, by) , 1 optional arguments (done)
        case "deadline":
            if ((taskCmd.getArgCount() == 2)) {
                if (taskCmd.getArgument(1).split(" ")[0].equals("by")) {
                    break;
                }
            }
            if (taskCmd.getArgCount() == 3) {
                if (taskCmd.getArgument(2).equals("done")) {
                    break;
                }
            }
            throw new DukeException("Deadline command arguments not matching: description /by deadline");

        // 2 arguments (description, at) , 1 optional arguments (done)
        case "event":
            if ((taskCmd.getArgCount() == 2)) {
                if (taskCmd.getArgument(1).split(" ")[0].equals("at")) {
                    break;
                }
            }
            if (taskCmd.getArgCount() == 3) {
                if (taskCmd.getArgument(2).equals("done")) {
                    break;
                }
            }
            throw new DukeException("Event command arguments not matching: description /at startToEnd");
        default:
            break; // do nothing
        }
    }

    /**
     * Check an IO command for validity.
     * Currently checks: save, load, showsaves
     *
     * @param cmd the command
     * @throws DukeException if the command is invalid
     */
    public static void checkSaveLoad(Command cmd) throws DukeException{
        switch(cmd.getCommand()) {
        // 1 argument, 0 optional arguments
        case "save":
            if (cmd.getArgCount() == 1) {
                break;
            }
            throw new DukeException("Save command arguments not matching: filename");

        // 1 argument, 1 optional argument (YES)
        case "load":
            if (cmd.getArgCount() == 2){
                if (cmd.getArgument(1).equals("YES")) {
                    break;
                }
            }
            if (cmd.getArgCount() == 1){
                break;
            }
            throw new DukeException("Load command arguments not matching: filename " +
                                    "(for overwrite: filename /YES)");
        case "showsaves":
            if (cmd.getArgCount() == 0) {
                break;
            }
            throw new DukeException("ShowSaves command takes no arguments");
        default:
            break; // do nothing
        }
    }

    /**
     * Check utility commands for validity.
     * Currently checks: list, debug, done, bye, delete
     *
     * @param cmd       the command
     * @param taskCount the current task count
     * @throws DukeException if the command is invalid
     */
// Checking: list, bye, done, delete
    public static void checkUtil(Command cmd, int taskCount) throws DukeException {
        switch(cmd.getCommand()) {
        // 0 arguments, 0 optional arguments
        case "list":
            // FALLTHROUGH
        case "debug":
            if (cmd.getArgCount() == 0) {
                break;
            }
            throw new DukeException(cmd.getCommand() + " command takes no arguments");

        // 0 arguments, 1 optional argument (force)
        case "bye":
            if (cmd.getArgCount() == 0) {
                break;
            }
            if (cmd.getArgCount() == 1) {
                if (cmd.getArgument(0).equals("force")) {
                    break;
                }
            }
            throw new DukeException("Bye command takes no arguments (for force quit: /force)");

        // 1 argument (int), 0 optional arguments
        case "done":
            // FALLTHROUGH
        case "delete":
            if (cmd.getArgCount() == 1) {
                if (isNumeric(cmd.getArgument(0))) {
                    int doneID = Integer.parseInt(cmd.getArgument(0));
                    if (doneID <= taskCount && doneID > 0) {
                        break;
                    }
                }
            }
            throw new DukeException(cmd.getCommand() +
                                    " command arguments not matching: <task ID> (task ID should be in range)");
        default:
            break; // do nothing
        }
    }


    /**
     * The function that verifies every command currently available.
     *
     * @param cmd       the command
     * @param taskCount the current task count
     * @throws DukeException if the command is invalid
     */
    public static void verifyCmd(Command cmd, int taskCount) throws DukeException {
        checkTaskCmd(cmd);
        checkSaveLoad(cmd);
        checkUtil(cmd, taskCount);
    }
}
