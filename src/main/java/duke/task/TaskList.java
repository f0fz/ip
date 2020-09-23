package duke.task;

import duke.message.errorMsg;
import duke.message.replyMsg;
import duke.util.Command;
import java.util.ArrayList;

import duke.util.DateTime;
import duke.util.IO;
import duke.util.UI;

/**
 * Represents a list of tasks. No hard limit on number.
 *
 * The type TaskList does the heavy lifting of managing all tasks. It's the intermediary of the tasks and the IO,
 * and there's some big functions in here.
 *
 * All tasks are stored in ArrayList taskList, and counted by Integer taskCount.
 * Boolean hasSaved keeps track of whether there are any unsaved changes.
 *
 * ALL ERROR CHECKING IS DONE OUTSIDE THIS CLASS!
 */

public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;
    private boolean hasSaved;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
        hasSaved = true;
    }

    /**
     * Check whether all changes are saved.
     *
     * @return the boolean
     */
    public boolean checkWhetherSaved() {
        return hasSaved;
    }

    /**
     * Gets task count.
     *
     * @return the task count
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Clear all tasks.
     */
    public void clearAllTasks() {
        taskList = new ArrayList<>();
        taskCount = 0;
        hasSaved = false; // list is technically edited
    }

    /**
     * Adds task to taskList using the Command object.
     * If silent is true, then do not print a message. This is used mainly for loading from saves.
     *
     * @param command the command object itself
     * @param silent  if true, do not print confirmation that task was added
     */
    public void addTask(Command command, boolean silent) {
        // First argument will always be the description of the task
        String taskName = command.getArgument(0).trim();
        // Third argument is optional - isDone
        boolean isDone = command.getArgument(command.getArgCount()-1).toLowerCase().equals("done");

        // Second argument is 'at/by' for Deadline and Event
        String timeArg;
        switch(command.getCommand().toLowerCase()) {
        case "todo":
            taskList.add(new Todo(taskName, isDone));
            break;
        case "deadline":
            timeArg = command.getArgument(1).substring(3); // removing the "by "
            taskList.add(new Deadline(taskName, DateTime.parseDate(timeArg), isDone));
            break;
        case "event":
            timeArg = command.getArgument(1).substring(3); // removing the "by "
            taskList.add(new Event(taskName, DateTime.parseDate(timeArg), isDone));
            break;
        }
        taskCount++;
        hasSaved = false; // list is edited
        if (!silent) {
            UI.reply(new String[]{
                    String.format(replyMsg.TASK_ADD_COMPLETE_1, taskList.get(taskCount - 1).toString()),
                    String.format(replyMsg.TASK_ADD_COMPLETE_2, taskCount)
            });
        }
    }


    /**
     * Completes an existing task based on its task ID.
     *
     * @param taskIDString the task ID in string form
     */
    public void completeTask(String taskIDString) {
        int taskID = Integer.parseInt(taskIDString);

        if (taskList.get(taskID-1).getDone()) {
            UI.reply(replyMsg.TASK_ALREADY_DONE);
        } else {
            hasSaved = false; // list is edited
            taskList.get(taskID-1).setDone();
            UI.reply(new String[]{
                    replyMsg.TASK_DONE_SUCCESS,
                    taskList.get(taskID-1).toString()
            });
        }
    }

    /**
     * Deletes an existing task based on its task ID.
     *
     * @param taskIDString the task id string
     */
    public void deleteTask(String taskIDString) {
        int taskID = Integer.parseInt(taskIDString);
        hasSaved = false; // list is edited
        taskCount--;
        Task removedTask = taskList.get(taskID-1);
        taskList.remove(taskID-1);
        UI.reply(new String[]{
                replyMsg.TASK_DELETE_SUCCESS,
                removedTask.toString()
        });
    }

    /**
     * SHows all tasks with a matching substring in the task description
     *
     * @param findString String to search for
     */
    public void findTask(String findString) {
        ArrayList<Task> foundTaskList = new ArrayList<>();
        for (Task eachTask : taskList) {
            if (eachTask.getName().contains(findString)) {
                foundTaskList.add(eachTask);
            }
        }


        displayTaskList(foundTaskList, String.format(replyMsg.TASK_FIND_HEADER, findString),
                                       String.format(replyMsg.TASK_FIND_NOTHING, findString));
    }

    /**
     * Shows all tasks in the task list.
     */
    public void listAllTasks() {
        displayTaskList(taskList, replyMsg.TASK_LIST_HEADER, replyMsg.TASK_LIST_EMPTY);
    }

    /**
     * Displays all tasks in the given task list.
     * @param givenTaskList ArrayList of tasks
     * @param taskListHeader String to print as header
     * @param noTaskMessage String to print if there are no tasks in the list
     */
    public void displayTaskList(ArrayList<Task> givenTaskList, String taskListHeader, String noTaskMessage) {
        int givenTaskCount = givenTaskList.size();
        if (givenTaskCount == 0) {
            UI.reply(noTaskMessage);
            return;
        }

        String[] outputList = new String[givenTaskCount + 1];
        outputList[0] = taskListHeader;

        Task eachTask;
        // Start from 1, since index 0 of outputList is occupied
        for (int i = 1; i <= givenTaskCount; i++) {
            eachTask = givenTaskList.get(i-1);
            outputList[i] = i + ". " + eachTask.toString();
        }
        UI.reply(outputList);
    }

    /**
     * Save tasks to disk to a text file with the given name in directory 'data'. File does not need to exist.
     *
     * @param fileName the file name
     */
    public void saveTasks(String fileName) {
        hasSaved = true; // list is saved!

        String[] linesToWrite = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            linesToWrite[i] = taskList.get(i).toCommand();
        }

        if (IO.saveFile(fileName, linesToWrite)) {
            UI.reply(replyMsg.TASK_SAVE_SUCCESS);
        }
    }

    /**
     * Load tasks from disk from a text file with the given name in directory 'data'. File needs to exist.
     *
     * @param fileName the file name
     */
    public void loadTasks(String fileName) {
        try {
            Command[] commandList = IO.readFile(fileName);
            for (Command eachCommand : commandList) {
                addTask(eachCommand, true);
            }
            UI.reply(new String[]{
                    replyMsg.TASK_LOAD_SUCCESS_1,
                    String.format(replyMsg.TASK_LOAD_SUCCESS_2, taskCount)
            });
            hasSaved = true; // list is unchanged
        } catch (Exception e) {
            UI.error(e, errorMsg.READ_FILE_ERROR + fileName);
        }
    }
}
