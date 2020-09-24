package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import duke.constant.ErrorMsg;
import duke.constant.ReplyMsg;

import static duke.util.UI.error;


/**
 * The type IO. Only contains static methods and constants.
 * Holds all the logic for reading and writing to files. Handles exceptions wherever it can.
 */
public class IO {
    static final String DEFAULT_PATH = "data/";
    static final String FILE_EXT = ".txt";

    /**
     * Reads a save file based on the file name from the DEFAULT_PATH directory.
     * The tasks are stored as 'add task' commands in the text file.
     * Uses the Parser class to parse the commands and then uses the returned Command objects for the addTask command.
     *
     * @param fileName the name of the save file (without extension)
     * @return the list of Commands
     * @throws FileNotFoundException if the save file is not found
     */
    public static Command[] readFile(String fileName) throws FileNotFoundException {
        File f = new File(DEFAULT_PATH + fileName + FILE_EXT);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }

        Parser fileParser = new Parser(f);
        int taskNumber = 1;
        // First, read number of tasks in order to initialise the commandList
        if (fileParser.hasNextLine()) {
            fileParser.getInput();
            taskNumber = Integer.parseInt(fileParser.parseCommand().getCommand());
        }

        // Then, put each command into the commandList via parseCommand
        Command[] commandList = new Command[taskNumber];
        int taskIndex = 0;
        while (fileParser.hasNextLine()) {
            fileParser.getInput();
            commandList[taskIndex] = fileParser.parseCommand();
            taskIndex++;
        }

        return commandList;
    }


    /**
     * A simple method to write to the file. A wrapper for FileWriter so I don't have to deal with it messing
     * up other things in other methods.
     *
     * @param saveFile the name of the save file (without extension)
     * @param textToAdd the string to be written to the file
     * @throws IOException if something goes wrong while writing to the file
     */
    private static void writeToFile(File saveFile, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(saveFile, true); // true to append data instead of overwrite
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Writes the converted tasks from TaskList into the specified save file. If the save file doesn't exist,
     * create it. If the directory DEFAULT_PATH doesn't exist at root, then create it too.
     *
     * @param fileName the name of the save file
     * @param lines    the converted tasks to be saved
     * @return the boolean of whether the save was successful
     */
    public static boolean saveFile(String fileName, String[] lines) {
        String pathName = DEFAULT_PATH + fileName + FILE_EXT;
        try {
            // Make the data folder to save in if it doesn't already exist
            if (!(new File(DEFAULT_PATH)).exists()) {
                (new File(DEFAULT_PATH)).mkdir();
                UI.reply(ReplyMsg.SAVE_DIR_NOT_EXIST);
            }

            // Create or overwrite the save file
            File mySaveFile = new File(pathName);
            if (!mySaveFile.exists()){
                UI.reply(ReplyMsg.SAVE_CREATING_FILE);
            } else {
                UI.reply(ReplyMsg.SAVE_OVERWRITE_FILE);
                mySaveFile.delete();
            }
            mySaveFile.createNewFile();

            // Write to the save file
            writeToFile(mySaveFile, Integer.toString(lines.length));
            for (String eachLine : lines) {
                writeToFile(mySaveFile, eachLine);
            }
        } catch (IOException e) {
            error(e, ErrorMsg.SAVE_FILE_ERROR + fileName + FILE_EXT);
            return false;
        }
        return true;
    }

    /**
     * Show all save files in the DEFAULT_PATH directory.
     */
    public static void showSaves() {
        String[] saveNames = (new File(DEFAULT_PATH)).list();
        if (saveNames.length == 0) {
            UI.reply(ReplyMsg.SAVE_NONE_FOUND);
            return;
        }

        for (int i = 0; i < saveNames.length; i++) {
            saveNames[i] = saveNames[i].substring(0, saveNames[i].length() - FILE_EXT.length()); // cut out .txt
        }

        String[] response = ReplyMsg.SAVE_LIST_SAVES;
        String[] finalResponse = new String[response.length + saveNames.length];

        // Concatenate the response and saveNames arrays to send to UI.reply
        System.arraycopy(response, 0, finalResponse, 0, 2);
        System.arraycopy(saveNames, 0, finalResponse, 2, saveNames.length);
        UI.reply(finalResponse);
    }
}
