package duke.constant;

public class ErrorMsg {
    public static final String COMMAND_INVALID_ERROR = "Command entered is invalid!",
                               COMMAND_UNRECOG_ERROR = "Command is not recognised, please try again.";

    public static final String SAVE_FILE_ERROR       = "Could not write to the file ",
                               READ_FILE_ERROR       = "Could not read save file ",
                               PARSER_NO_FILE_ERROR  = "Parser could not find file specified!";

    public static final String TODO_ERROR      = "Todo command argument not matching: description",
                               DEADLINE_ERROR  = "Deadline command arguments not matching: description /by deadline",
                               EVENT_ERROR     = "Event command arguments not matching: description /at startToEnd",
                               SAVE_ERROR      = "Save command arguments not matching: filename",
                               LOAD_ERROR      = "Load command arguments not matching: filename" +
                                                 "(for overwrite: filename overwrite)",
                               FIND_ERROR      = "Find command argument not matching: searchText",
                               SHOW_SAVE_ERROR = "ShowSaves command takes no arguments",
                               EXIT_ERROR      = "Bye command takes no arguments (for force quit: force-quit)";

    public static final String GENERIC_NO_ARG_ERROR     = " command takes no arguments",
                               GENERIC_INVALID_ID_ERROR = " command arguments not matching: <task ID> " +
                                                          "(task ID should be in range)";

}
