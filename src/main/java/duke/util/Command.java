package duke.util;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * The type Command. Any user input/file input is parsed by Parser and turned into a Command object.
 * No validation going on in this type... it's really just to hold information in a 'command' context.
 */
public class Command {
    final int MAX_ARGS = 10;
    protected String command;     // The actual command. E.g. "list", "bye"
    protected int argCount;       // The number of additional arguments
    protected String[] arguments; // Stores all additional arguments. E.g. "by tomorrow", "at 1-5pm"
    protected LocalDate date;     // For any possible dates

    /**
     * Instantiates a new Command.
     */
    public Command() {
        arguments = new String[MAX_ARGS];
    }

    /**
     * Sets the 'command' of the Command object.
     *
     * @param command the command itself
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Gets the 'command' of the Command object.
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Adds an argument to the Command object.
     *
     * @param argument the argument to be added
     */
    public void addArgument(String argument) {
        if (argCount == MAX_ARGS) {
            return;
        }
        arguments[argCount] = argument;
        argCount++;
    }

    /**
     * Gets an argument from the arguments array.
     *
     * @param index the index of the argument
     * @return the argument
     */
    public String getArgument(int index) {
        if (index > argCount-1) {
            return "";
        } else {
            return arguments[index];
        }
    }

    /**
     * Gets the total argument count.
     *
     * @return the arg count
     */
    public int getArgCount() {
        return argCount;
    }


    /**
     * To display the Command object's information for debugging.
     */
    public void debug() {
        UI.error(new String[]{"Command breakdown:", "Command: " + command, "Arguments: " + Arrays.toString(arguments)});
    }
}
