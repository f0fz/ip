import java.util.Arrays;

public class Command {
    protected String command;
    protected int argCount;
    protected String[] arguments;

    public Command() {
        arguments = new String[10];
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void addArgument(String argument) {
        if (argCount == 9) {
            return;
        }
        arguments[argCount] = argument;
        argCount++;
    }

    public String getArgument(int id) {
        return arguments[id];
    }

    // For debug purposes
    public void debug() {
        System.out.println("Command: " + command);
        System.out.println("Arguments: " + Arrays.toString(arguments));
    }
}
