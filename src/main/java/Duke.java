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

        String[] greeting = {"Hello! I'm Duke", "What can I do for you?"};
        reply(greeting);

        // Main event loop!
        String userInput;
        while (true) {
            userInput = scan.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                stop();
                break; // Exit condition is here...
            }

            // and everything else is below here.
            reply(userInput);
        }

    }
}
