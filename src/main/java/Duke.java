import java.util.Scanner;

public class Duke {

    // Indents and wraps all of Duke's replies in the --- bars.
    // Has a single line version...
    private static void reply(String response) {
        String bar = "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n";
        System.out.println(bar + " >>> " + response + "\n" + bar);
    }
    // and a multiline version as well.
    private static void reply(String[] responses) {
        String bar = "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n";
        System.out.println(bar);
        for (String eachResponse : responses) {
            System.out.println(" " + eachResponse + "\n");
        }
        System.out.println(bar);
    }

    // Gracefully shuts down Duke.
    // Doesn't do much now, but I'm guessing there will be file IO next time?
    private static void stop() {
        reply("Bye. Hope to see you again soon!");
    }

    // Main function. Main event loop happens here.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskList todoList = new TaskList();

        String[] greeting = {"Hello! I'm Duke", "What can I do for you?"};
        reply(greeting);

        // Main event loop!
        String userInput = "";
        while (!userInput.toLowerCase().equals("bye")) {
            userInput = scan.nextLine();

            switch(userInput.toLowerCase()) {
            case "bye": // Exit condition is here
                stop();
                break;
            case "list": // List all tasks
                reply(todoList.listTasks());
                break;
            default: // Add a task to the task list
                reply(todoList.addTask(userInput));
            }
        }

    }
}
