package duke.util;

public class UI {
    public final static String REPLY_BAR = "――――――――――――――――――――――――――――――――――――――――――";
    public final static String ERROR_BAR = "!――――――!――――――!――――――!――――――!――――――!――――――!";
    public final static String DEBUG_BAR = "?――――――?――――――?――――――?――――――?――――――?――――――?";
    public final static char tick = '\u2713';
    public final static char cross = '\u2717';

    public static boolean isDebugMode = false;

    public static boolean getDebugMode() {
        return isDebugMode;
    }

    public static void toggleDebug() {
        isDebugMode = !isDebugMode;
    }


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
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR + "\n >>> " + errorMsg);
        if (getDebugMode()) {
            System.out.println(" >>> Debug...");
            e.printStackTrace();
        } else {
            System.out.print(" >>> ");
            System.out.println(e.getMessage());
        }
        System.out.println(BAR + "\n");
    }

    public static void error(Exception e, String[] errorMsgs) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR);
        for (String eachMsg : errorMsgs) {
            System.out.println(" >>> " + eachMsg);
        }

        if (getDebugMode()) {
            System.out.println(" >>> Debug...");
            e.printStackTrace();
        } else {
            System.out.println(" >>> ");
            System.out.print(e.getMessage());
        }
        System.out.println(BAR + "\n");
    }

    // And again for error messages without exceptions!
    public static void error(String errorMsg) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR + "\n >>> " + errorMsg + "\n" + BAR + "\n");
    }

    public static void error(String[] errorMsgs) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR);
        for (String eachMsg : errorMsgs) {
            System.out.println(" >>> " + eachMsg);
        }
        System.out.println(BAR + "\n");
    }

}
