package duke.util;

import duke.message.errorMsg;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * The type Parser. All Scanners belong here. Data goes into the Parser object through Scanners, and
 * data leaves the Parser object as Commands. The Parser doesn't care whether the Commands are valid,
 * it just puts them into that form and complains if it's wrong.
 */
public class Parser {
    protected String input; // the current input string
    Scanner scan;

    /**
     * Instantiates a new Parser (meant for the user).
     */
    public Parser() {
        scan = new Scanner(System.in);
    }

    /**
     * Instantiates a new Parser (meant for files).
     *
     * @param f the file to scan for loading
     */
// If the Parser is meant for a file
    public Parser(File f){
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Likely will never happen. Only putting this because IntelliJ told me to.
            UI.error(errorMsg.PARSER_NO_FILE_ERROR);
        }
    }


    /**
     * Gets input using the Scanner.
     */
    public void getInput() {
        if (scan.hasNextLine()) {
            input = scan.nextLine();
        }
    }


    /**
     * Checks whether the next line exists.
     * This function exists so I can check 'hasNextLine' outside of the Parser class.
     *
     * @return the boolean
     */
    public boolean hasNextLine() {
        return scan.hasNextLine();
    }


    /**
     * The main function of the Parser class; parses the input string read by getInput.
     * It creates a new Command object and populates it with the information from the user command.
     *
     * @return the Command object created
     */
    public Command parseCommand() {
        Command command = new Command();
        command.setCommand(input.split(" ")[0].toLowerCase());

        if (command.getCommand().length() == input.length()) {
            return command; // if no args
        }

        input = input.substring(command.getCommand().length() + 1); // remove command from input plus the extra space

        // Arguments are of form " /opt argument", so we split by " /" to get "opt argument" strings
        // e.g. "arg0 /opt1 arg1 /opt2 /opt3 arg3" -> arg0, opt1 arg1, opt2, opt3 arg3
        String[] inputArgs = input.split(" /");

        // Some commands like 'bye /force' have arg0 in the form '/arg0'
        // If so, remove the '/' at the front
        if (inputArgs[0].charAt(0) == '/'){
            inputArgs[0] = inputArgs[0].substring(1);
        }


        for (String eachArg : inputArgs) {
            command.addArgument(eachArg);
        }

        return command;
    }
}
