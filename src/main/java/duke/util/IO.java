package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static duke.util.UI.error;


public class IO {
    static final String DEFAULT_PATH = "duke/data/";

    // The tasks are stored as 'add task' commands in the text file.
    // Use the Parser class to parse the commands and then use
    // the returned Command objects for the addTask command.
    public static Command[] readFile(String fileName) throws DukeException {
        File f = new File(DEFAULT_PATH + fileName + ".txt");
        if (!f.exists()) {
            throw new DukeException("IO.readFile: Save file does not exist");
        }

        Parser fileParser = new Parser(f);
        int taskNumber = 1;
        // First, read number of tasks
        if (fileParser.hasNextLine()) {
            fileParser.getInput();
            taskNumber = Integer.parseInt(fileParser.parseCommand().getCommand());
        }

        Command[] commandList = new Command[taskNumber];
        int taskIndex = 0;
        while (fileParser.hasNextLine()) {
            fileParser.getInput();
            commandList[taskIndex] = fileParser.parseCommand();
            taskIndex++;
        }

        return commandList;
    }


    private static void writeToFile(File saveFile, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(saveFile, true); // true to append data instead of overwrite
        fw.write(textToAdd + "\n");
        fw.close();
    }


    public static boolean saveFile(String fileName, String[] lines) {
        String pathName = DEFAULT_PATH + fileName + ".txt";
        try {
            File mySaveFile = new File(pathName);
            if (!mySaveFile.exists()){
                UI.reply("Creating a new save file...");
            } else {
                UI.reply("Overwriting old save file...");
                mySaveFile.delete();
            }
            mySaveFile.createNewFile();

            writeToFile(mySaveFile, Integer.toString(lines.length));
            for (String eachLine : lines) {
                writeToFile(mySaveFile, eachLine);
            }
        } catch (IOException e) {
            error(e, "IO.saveFile: Error encountered while writing to the file " + fileName + ".txt");
            return false;
        }
        return true;
    }


    public static void showSaves() {
        String[] saveNames = (new File(DEFAULT_PATH)).list();
        for (int i = 0; i < saveNames.length; i++) {
            saveNames[i] = saveNames[i].substring(0, saveNames[i].length()-4); // cut out .txt
        }

        String[] response = new String[]{"To load, type 'load filename'.",
                                         "Here's all your saves:"};
        String[] finalResponse = new String[saveNames.length + 2];

        System.arraycopy(response, 0, finalResponse, 0, 2);
        System.arraycopy(saveNames, 0, finalResponse, 2, saveNames.length);
        UI.reply(finalResponse);
    }
}
