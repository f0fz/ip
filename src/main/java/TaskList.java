public class TaskList {
    private Task[] taskList;
    private int taskCount;

    public TaskList() {
        taskList = new Task[100];
        taskCount = 0;
    }

    // Adds task to taskList
    public String addTask(String taskName) {
        if (taskName.equals("")){
            return "Can't add a blank line to the task list!";
        } else {
            taskList[taskCount] = new Task(taskName);
            taskCount++;
            return "Added: " + taskName;
        }

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
            return new String[]{"I've marked this task as done:", "[✓] " + taskList[taskID-1].getName()};
        }
    }

    // Returns a string array of all the tasks to be done
    public String[] listTasks() {
        String[] outputList = new String[taskCount + 1];
        outputList[0] = "Here are the tasks in your list:";

        Task eachTask;
        String taskName;
        String checkbox;

        // Start from 1, since index 0 if outputList is occupied
        for (int i = 1; i <= taskCount; i++) {
            eachTask = taskList[i];
            taskName = eachTask.getName();
            checkbox = eachTask.getDone() ? "[✓] " : "[✗] ";
            outputList[i] = Integer.toString(i) + ". " + checkbox + taskName;
        }
        return outputList;
    }
}
