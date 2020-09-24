package duke.util;

import duke.constant.CommandStr;
import duke.constant.ErrorMsg;

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
        case CommandStr.TODO_CMD:
            if (taskCmd.getArgCount() == 1) {
                break;
            }
            if (taskCmd.getArgCount() == 2) {
                if (taskCmd.getArgument(1).equals(CommandStr.DONE_OPT)) {
                    break;
                }
            }
            throw new DukeException(ErrorMsg.TODO_ERROR);

        // 2 arguments (description, by) , 1 optional arguments (done)
        // Validity of date 'by'
        case CommandStr.DEADLINE_CMD:
            if ((taskCmd.getArgCount() == 2)) {
                if (taskCmd.getArgument(1).split(" ")[0].equals(CommandStr.BY_ARG)) {
                    if (DateTime.checkValidDate(taskCmd.getArgument(1).substring(3))){
                        break;
                    }
                }
            }
            if (taskCmd.getArgCount() == 3) {
                if (taskCmd.getArgument(2).equals(CommandStr.DONE_OPT)) {
                    break;
                }
            }
            throw new DukeException(ErrorMsg.DEADLINE_ERROR);

        // 2 arguments (description, at) , 1 optional arguments (done)
        // Validity of date 'at'
        case CommandStr.EVENT_CMD:
            if ((taskCmd.getArgCount() == 2)) {
                if (taskCmd.getArgument(1).split(" ")[0].equals(CommandStr.AT_ARG)) {
                    if (DateTime.checkValidDate(taskCmd.getArgument(1).substring(3))){
                        break;
                    }
                }
            }
            if (taskCmd.getArgCount() == 3) {
                if (taskCmd.getArgument(2).equals(CommandStr.DONE_OPT)) {
                    break;
                }
            }
            throw new DukeException(ErrorMsg.EVENT_ERROR);
        default:
            break; // do nothing
        }
    }

    /**
     * Check an IO command for validity.
     * Currently checks: save, load, show-saves
     *
     * @param cmd the command
     * @throws DukeException if the command is invalid
     */
    public static void checkSaveLoad(Command cmd) throws DukeException{
        switch(cmd.getCommand()) {
        // 1 argument, 0 optional arguments
        case CommandStr.SAVE_CMD:
            if (cmd.getArgCount() == 1) {
                break;
            }
            throw new DukeException(ErrorMsg.SAVE_ERROR);

        // 1 argument, 1 optional argument (YES)
        case CommandStr.LOAD_CMD:
            if (cmd.getArgCount() == 2){
                if (cmd.getArgument(1).equals(CommandStr.OVERWRITE_OPT)) {
                    break;
                }
            }
            if (cmd.getArgCount() == 1){
                break;
            }
            throw new DukeException(ErrorMsg.LOAD_ERROR);
        case CommandStr.SHOW_SAVE_CMD:
            if (cmd.getArgCount() == 0) {
                break;
            }
            throw new DukeException(ErrorMsg.SHOW_SAVE_ERROR);
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
    public static void checkUtil(Command cmd, int taskCount) throws DukeException {
        switch(cmd.getCommand()) {
        // 0 arguments, 0 optional arguments
        case CommandStr.LIST_T_CMD:
            // FALLTHROUGH
        case CommandStr.DEBUG_CMD:
            if (cmd.getArgCount() == 0) {
                break;
            }
            throw new DukeException(cmd.getCommand() + ErrorMsg.GENERIC_NO_ARG_ERROR);

        // 0 arguments, 1 optional argument (force)
        case CommandStr.EXIT_CMD:
            if (cmd.getArgCount() == 0) {
                break;
            }
            if (cmd.getArgCount() == 1) {
                if (cmd.getArgument(0).equals(CommandStr.F_QUIT_OPT)) {
                    break;
                }
            }
            throw new DukeException(ErrorMsg.EXIT_ERROR);

        // 1 argument (int, task ID), 0 optional arguments
        case CommandStr.DONE_T_CMD:
            // FALLTHROUGH
        case CommandStr.DEL_T_CMD:
            if (cmd.getArgCount() == 1) {
                if (isNumeric(cmd.getArgument(0))) {
                    int doneID = Integer.parseInt(cmd.getArgument(0));
                    if (doneID <= taskCount && doneID > 0) {
                        break;
                    }
                }
            }
            throw new DukeException(cmd.getCommand() + ErrorMsg.GENERIC_INVALID_ID_ERROR);
        // 1 argument (string), 0 optional arguments
        case CommandStr.FIND_T_CMD:
            if (cmd.getArgCount() == 1) {
                break;
            }
            throw new DukeException(ErrorMsg.FIND_ERROR);
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
