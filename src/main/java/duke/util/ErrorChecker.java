package duke.util;

// All error checking functions should be in the form:
// if (correctCondition1)
//     if (correctCondition2)
//         ...
//             break;
// throw DukeException("____ command arguments not matching: _________");
// or, throw DukeException("____ command takes no arguments");

public class ErrorChecker {
    public static void checkTaskCmd(Command taskCmd) throws DukeException {
        switch(taskCmd.getCommand()) {
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


    public static void checkSaveLoad(Command cmd) throws DukeException{
        switch(cmd.getCommand()) {
        case "save":
            if (cmd.getArgCount() == 1) {
                break;
            }
            throw new DukeException("Save command arguments not matching: filename");
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


    public static void checkUtil(Command cmd) throws DukeException {
        switch(cmd.getCommand()) {
        case "list":
            if (cmd.getArgCount() == 0) {
                break;
            }
            throw new DukeException("List command takes no arguments");
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
        default:
            break; // do nothing
        }
    }


    public static void verifyCmd(Command cmd) throws DukeException {
        checkTaskCmd(cmd);
        checkSaveLoad(cmd);
        checkUtil(cmd);
    }
}
