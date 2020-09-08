package duke.util;

public class UI {
    public final static String REPLY_BAR = "――――――――――――――――――――――――――――――――――――――――――";
    public final static char tick = '\u2713';
    public final static char cross = '\u2717';

    // Indents and wraps all of Duke's replies in the bars.
    // Has a single line version...
    public static void reply(String response) {
        System.out.println("\n" + REPLY_BAR + "\n >>> " + response + "\n" + REPLY_BAR + "\n");
    }


    // ...and a multiline version as well.
    public static void reply(String[] responses) {
        System.out.println("\n" + REPLY_BAR);
        for (String eachResponse : responses) {
            System.out.println(" >>> " + eachResponse);
        }
        System.out.println(REPLY_BAR + "\n");
    }
}
