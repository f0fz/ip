package duke.util;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Parser {
    // User's input
    protected String input;
    Scanner scan;


    public Parser() {
        scan = new Scanner(System.in);
    }


    public Parser(File f) {
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void getInput() {
        if (scan.hasNextLine()) {
            input = scan.nextLine();
        }
    }


    public boolean hasNextLine() {
        return scan.hasNextLine();
    }


    // The main function of the Parser class
    // Uses getUserInput to get the next command.
    // It creates a new Command object and populates it with the information
    // from the user command. It then returns the Command object.
    public Command parseCommand() {
        Command command = new Command();
        command.setCommand(input.split(" ")[0].toLowerCase());

        if (command.getCommand().length() == input.length()) {
            return command; // no args
        }

        input = input.substring(command.getCommand().length() + 1); // remove command from input plus the extra space

        // Arguments are of form " /opt argument", so we split by " /" to get "opt argument" strings
        String[] inputArgs = input.split(" /");

        for (String eachArg : inputArgs) {
            command.addArgument(eachArg);
        }

        return command;
    }
}
