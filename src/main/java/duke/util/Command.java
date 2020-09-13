package duke.util;

import java.util.Arrays;

public class Command {
    final int MAX_ARGS = 10;

    protected String command;     // The actual command. E.g. "list", "bye"
    protected int argCount;       // The number of additional arguments
    protected String[] arguments; // Stores all additional arguments. E.g. "by tomorrow", "at 1-5pm"

    public Command() {
        arguments = new String[MAX_ARGS];
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void addArgument(String argument) {
        if (argCount == MAX_ARGS) {
            return;
        }
        arguments[argCount] = argument;
        argCount++;
    }

    public String getArgument(int id) {
        if (id > argCount-1) {
            return "";
        } else {
            return arguments[id];
        }
    }

    public int getArgCount() {
        return argCount;
    }
    // For debug purposes
    public void debug() {
        System.out.println("Command: " + command);
        System.out.println("Arguments: " + Arrays.toString(arguments));
    }
}
