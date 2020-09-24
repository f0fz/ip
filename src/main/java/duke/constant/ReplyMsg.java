package duke.constant;

/**
 * Constants for UI.reply().
 * All non-error messages for the user are here.
 */
public class ReplyMsg {
    // TaskList
    public static final String TASK_ADD_COMPLETE_1 = "Added: %s",
                               TASK_ADD_COMPLETE_2 = "Now you have %d tasks.";
    public static final String TASK_DONE_SUCCESS   = "I've marked this task as complete:";
    public static final String TASK_DELETE_SUCCESS = "Removed the task as requested:";
    public static final String TASK_LIST_HEADER    = "Here are the tasks in your list:",
                               TASK_LIST_EMPTY     = "You currently have no tasks.";
    public static final String TASK_LOAD_SUCCESS_1 = "Successfully loaded from save file!",
                               TASK_LOAD_SUCCESS_2 = "Now you have %d tasks.";
    public static final String TASK_FIND_HEADER    = "For the search term '%s', these are the results:",
                               TASK_FIND_NOTHING   = "No tasks were found matching the search term '%s'.";

    public static final String[] TASK_ALREADY_DONE = new String[] {
                                                        "This task is already complete!",
                                                        "Did you mean another task?"};
    public static final String[] TASK_SAVE_SUCCESS = new String[] {
                                                        "All files successfully saved!",
                                                        "You can now close the program."};

    // IO
    public static final String SAVE_DIR_NOT_EXIST  = "Could not find save directory. Creating now...",
                               SAVE_CREATING_FILE  = "Creating a new save file...",
                               SAVE_OVERWRITE_FILE = "Overwriting old save file...";
    public static final String[] SAVE_NONE_FOUND   = new String[] {
                                                        "No saves found!",
                                                        "To save your current task list, use 'save <filename>'"};
    public static final String[] SAVE_LIST_SAVES   = new String[]{
                                                        "To load, type 'load <filename>'.",
                                                        "Here's all your saves:"};

    // Main
    public static final String BYE                 = "Bye. Hope to see you again soon!";
    public static final String DEBUG_MODE_TOGGLE   = "Toggled debug mode to: ";
    public static final String[] UNSAVED_CHANGES   = new String[]{
                                         "Hold on! You have unsaved changes!",
                                         "Type 'save <filename>' to save your changes if you want to.",
                                         "Otherwise, type 'bye force-quit' to exit without saving."};
    public static final String[] BYE_NO_SAVE       = new String[]{
                                                        "Discarding changes...",
                                                        "Bye. Hope to see you again soon!"};
    public static final String[] GREET             = new String[]{
                                                         "Hello! I'm Duke",
                                                         "What can I do for you?"};
    public static final String[] WARN_OVERWRITE    = new String[]{
                                                         "Are you sure? This will replace all your current tasks.",
                                                         "If you're sure, type load <filename> /overwrite."};
}
