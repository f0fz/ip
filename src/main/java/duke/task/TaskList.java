package duke.task;

import duke.util.Command;
import java.util.ArrayList;
import duke.util.DukeException;

public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;

    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    // Adds task to taskList using the Command object
    public String[] addTask(Command command) {
        // First argument will always be the description of the task
        String taskName = command.getArgument(0).trim();
        // Extra argument for Deadline and Event
        String extraArg;

        switch(command.getCommand().toLowerCase()) {
        case "todo":
            taskList.add(new Todo(taskName));
            break;
        case "deadline":
            extraArg = command.getArgument(1).substring(3); // removing the "by "
            taskList.add(new Deadline(taskName, extraArg));
            break;
        case "event":
            extraArg = command.getArgument(1).substring(3); // removing the "at "
            taskList.add(new Event(taskName, extraArg));
        }
        taskCount++;
        return new String[] {"Added: " + taskList.get(taskCount-1).toString(),
                             "Now you have " + taskCount + " tasks."};
    }

    // Returns a string indicating completion of the task.
    // If it's already complete, it will say so by returning the right sentence.
    public String[] completeTask(String taskIDString) {
        if (taskIDString == null) {
            return new String[]{"You need to specify a task number!","Use list to check."};
        }

        int taskID = Integer.parseInt(taskIDString);
        if (taskID > taskCount) {
            return new String[] {"No such task", "You only have this many tasks: " + taskCount};
        }
        if (taskList.get(taskID-1).getDone()) {
            return new String[]{"This task is already complete!", "Did you perhaps mean another task?"};
        } else {
            taskList.get(taskID-1).setDone();
            return new String[]{"I've marked this task as done:", taskList.get(taskID-1).toString()};
        }
    }

    // Returns a string array of all the tasks to be done
    public String[] showTaskList() {
        String[] outputList = new String[taskCount + 1];
        outputList[0] = "Here are the tasks in your list:";

        Task eachTask;

        // Start from 1, since index 0 of outputList is occupied
        for (int i = 1; i <= taskCount; i++) {
            eachTask = taskList.get(i-1);
            outputList[i] = i + ". " + eachTask.toString();
        }
        return outputList;
    }

    // Removes a task based on its ID from showTaskList
    public String[] deleteTask(Command command) {
        int taskID = Integer.parseInt(command.getArgument(0));
        if (taskID > taskCount) {
            return new String[]{"No such task", "You only have this many tasks: " + taskCount};
        }

        taskCount--;

        Task removedTask = taskList.get(taskID-1);
        taskList.remove(taskID-1);
        return new String[]{"Removed the task as requested.", "The task: " + removedTask.toString()};
    }
}
