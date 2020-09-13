package duke.task;

import duke.util.Command;
import duke.util.IO;
import duke.util.UI;

public class TaskList {
    private Task[] taskList;
    private int taskCount;

    public TaskList() {
        taskList = new Task[100];
        taskCount = 0;
    }


    public int getTaskCount() {
        return taskCount;
    }


    public void clearAllTasks() {
        taskList = new Task[100];
        taskCount = 0;
    }


    // Adds task to taskList using the Command object
    public void addTask(Command command, boolean silent) {
        // First argument will always be the description of the task
        String taskName = command.getArgument(0).trim();
        // Second argument is 'at/by' for Deadline and Event
        String timeArg;
        // Third argument is optional - isDone
        boolean isDone = (command.getArgument(command.getArgCount()-1).toLowerCase().equals("done"));

        switch(command.getCommand().toLowerCase()) {
        case "todo":
            taskList[taskCount] = new Todo(taskName, isDone);
            break;
        case "deadline":
            timeArg = command.getArgument(1).substring(3); // removing the "by "
            taskList[taskCount] = new Deadline(taskName, timeArg, isDone);
            break;
        case "event":
            timeArg = command.getArgument(1).substring(3); // removing the "at "
            taskList[taskCount] = new Event(taskName, timeArg, isDone);
        }
        taskCount++;
        if (!silent) {
            UI.reply(new String[]{"Added: " + taskList[taskCount - 1].toString(),
                    "Now you have " + taskCount + " tasks."});
        }
    }


    // Returns a string indicating completion of the task.
    // If it's already complete, it will say so by returning the right sentence.
    public void completeTask(String taskIDString) {
        if (taskIDString == null) {
            UI.error(new String[]{"You need to specify a task number!","Use list to check."});
            return;
        }

        int taskID = Integer.parseInt(taskIDString);
        if (taskID > taskCount) {
            UI.error(new String[] {"Task ID greater than task count!", "Current task count: " + taskCount});
        }
        else if (taskList[taskID-1].getDone()) {
            UI.reply(new String[]{"This task is already complete!", "Did you perhaps mean another task?"});
        } else {
            taskList[taskID-1].setDone();
            UI.reply(new String[]{"I've marked this task as done:", taskList[taskID-1].toString()});
        }
    }

    // Returns a string array of all the tasks to be done
    public void showTaskList() {
        String[] outputList = new String[taskCount + 1];
        outputList[0] = "Here are the tasks in your list:";

        Task eachTask;

        // Start from 1, since index 0 of outputList is occupied
        for (int i = 1; i <= taskCount; i++) {
            eachTask = taskList[i-1];
            outputList[i] = i + ". " + eachTask.toString();
        }
        UI.reply(outputList);
    }

    public void saveTasks(String fileName) {
        String[] linesToWrite = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            linesToWrite[i] = taskList[i].toCommand();
        }

        if (IO.saveFile(fileName, linesToWrite)) {
            UI.reply(new String[]{"All files successfully saved!", "You can now close the program."});
        }
    }

    public void loadTasks(String fileName) {
        try {
            Command[] commandList = IO.readFile(fileName);
            for (Command eachCommand : commandList) {
                addTask(eachCommand, true);
            }
            UI.reply(new String[]{"All tasks loaded!", "Total number of tasks: " + taskCount });
        } catch (Exception e) {
            UI.error(e, "TaskList.loadTasks: Can't read file!");
        }
    }
}
