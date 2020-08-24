import java.util.Arrays;

public class TaskList {
    private String[] taskList;
    private int taskCount;

    TaskList() {
        taskList = new String[100];
        taskCount = 0;
    }

    // Adds task to taskList
    public String addTask(String newTask) {
        taskList[taskCount] = newTask;
        taskCount++;

        return "Added: " + newTask;
    }

    // Returns a string array of all the tasks to be done
    public String[] listTasks() {
        String[] outputList = new String[taskCount];
        String eachTask;
        for (int currentNo = 0; currentNo < taskCount; currentNo++) {
            eachTask = taskList[currentNo];
            outputList[currentNo] = Integer.toString(currentNo+1) + ". " + eachTask;
        }
        return outputList;
    }
}
