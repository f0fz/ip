package duke.task;

import duke.util.Command;

public class TaskList {
    private Task[] taskList;
    private int taskCount;

    public TaskList() {
        taskList = new Task[100];
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
            taskList[taskCount] = new Todo(taskName);
            break;
        case "deadline":
            extraArg = command.getArgument(1).substring(3); // removing the "by "
            taskList[taskCount] = new Deadline(taskName, extraArg);
            break;
        case "event":
            extraArg = command.getArgument(1).substring(3); // removing the "at "
            taskList[taskCount] = new Event(taskName, extraArg);
        }
        taskCount++;
        return new String[] {"Added: " + taskList[taskCount-1].toString(),
                             "Now you have " + Integer.toString(taskCount) + " tasks."};
    }

    // Returns a string indicating completion of the task.
    // If it's already complete, it will say so by returning the right sentence.
    public String[] completeTask(String taskIDString) {
        if (taskIDString == null) {
            return new String[]{"You need to specify a task number!","Use list to check."};
        }

        int taskID = Integer.parseInt(taskIDString);
        if (taskList[taskID-1].getDone()) {
            return new String[]{"This task is already complete!", "Did you perhaps mean another task?"};
        } else {
            taskList[taskID-1].setDone();
            return new String[]{"I've marked this task as done:", taskList[taskID-1].toString()};
        }
    }

    // Returns a string array of all the tasks to be done
    public String[] showTaskList() {
        String[] outputList = new String[taskCount + 1];
        outputList[0] = "Here are the tasks in your list:";

        Task eachTask;

        // Start from 1, since index 0 of outputList is occupied
        for (int i = 1; i <= taskCount; i++) {
            eachTask = taskList[i-1];
            outputList[i] = i + ". " + eachTask.toString();
        }
        return outputList;
    }
}
