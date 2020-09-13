package duke.util;

public class UI {
    public final static String REPLY_BAR = "――――――――――――――――――――――――――――――――――――――――――";
    public final static String ERROR_BAR = "!――――――!――――――!――― ERROR ―――!――――――!――――――!";
    public final static char tick = '\u2713';
    public final static char cross = '\u2717';

    // Indents and wraps all of Duke's replies in the bars.
    // If the response is "SILENCE", then do not print.
    // Has a single line version...
    public static void reply(String response) {
        if (response.equals("SILENCE")) {
            return; // Silence condition
        }

        System.out.println("\n" + REPLY_BAR + "\n >>> " + response + "\n" + REPLY_BAR + "\n");
    }

    // ...and a multiline version as well.
    public static void reply(String[] responses) {
        if (responses[0].equals("SILENCE")) {
            return; // Silence condition
        }

        System.out.println("\n" + REPLY_BAR);
        for (String eachResponse : responses) {
            System.out.println(" >>> " + eachResponse);
        }
        System.out.println(REPLY_BAR + "\n");
    }

    // And same thing for error messages!
    public static void error(Exception e, String errorMsg) {
        System.out.println("\n" + ERROR_BAR + "\n >>> " + errorMsg);
        System.out.println(" >>> Java says...\n");
        e.printStackTrace();
        System.out.println("\n" + ERROR_BAR + "\n");
    }

    public static void error(Exception e, String[] errorMsgs) {
        System.out.println("\n" + ERROR_BAR);
        for (String eachMsg : errorMsgs) {
            System.out.println(" >>> " + eachMsg);
        }
        System.out.println(" >>> Java says...");
        e.printStackTrace();
        System.out.println("\n" + ERROR_BAR + "\n");
    }

    // And again for error messages without exceptions!
    public static void error(String errorMsg) {
        System.out.println("\n" + ERROR_BAR + "\n >>> " + errorMsg + "\n" + ERROR_BAR + "\n");
    }

    public static void error(String[] errorMsgs) {
        System.out.println("\n" + ERROR_BAR);
        for (String eachMsg : errorMsgs) {
            System.out.println(" >>> " + eachMsg);
        }
        System.out.println(ERROR_BAR + "\n");
    }
}
