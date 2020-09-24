package duke.util;

/**
 * The type UI. All printing statements and formatting logic are contained here.
 */
public class UI {
    public final static String REPLY_BAR = "――――――――――――――――――――――――――――――――――――――――――";
    public final static String ERROR_BAR = "!――――――!――――――!――――――!――――――!――――――!――――――!";
    public final static String DEBUG_BAR = "――――――――――――――?――――DEBUG――――?――――――――――――――";

    public final static String REPLY_INDENT = " >>> ";
    public final static String DEBUG_HEADER = "Stack trace here:";

    public final static char TICK = '\u2713';
    public final static char CROSS = '\u2717';

    public static boolean isDebugMode = false;

    /**
     * Checks whether debug mode is on.
     *
     * @return the debug mode boolean
     */
    public static boolean getDebugMode() {
        return isDebugMode;
    }

    /**
     * Toggle debug mode.
     */
    public static void toggleDebug() {
        isDebugMode = !isDebugMode;
    }

    /**
     * Wraps strings in nice formatting.
     * This is the single string version.
     *
     * @param response the string to be printed
     */
    public static void reply(String response) {
        System.out.println("\n" + REPLY_BAR + "\n >>> " + response + "\n" + REPLY_BAR + "\n");
    }

    /**
     * Wraps strings in nice formatting.
     * This is the multiple strings version.
     *
     * @param responses the list of strings to be printed
     */
    public static void reply(String[] responses) {
        System.out.println("\n" + REPLY_BAR);
        for (String eachResponse : responses) {
            System.out.println(REPLY_INDENT + eachResponse);
        }
        System.out.println(REPLY_BAR + "\n");
    }

    /**
     * Prints out error messages wrapped in nice formatting.
     * Also prints out the message of the Exception thrown.
     *
     * @param e        the Exception thrown
     * @param errorMsg the accompanying error message
     */
    public static void error(Exception e, String errorMsg) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR + "\n" + REPLY_INDENT + errorMsg);
        if (getDebugMode()) {
            System.out.println(REPLY_INDENT + DEBUG_HEADER);
            e.printStackTrace();
        } else {
            System.out.println(REPLY_INDENT + e.getMessage());
        }
        System.out.println(BAR + "\n");
    }

    /**
     * Prints out error messages wrapped in nice formatting, multiline version.
     * Also prints out the message of the Exception thrown.
     *
     * @param e         the Exception thrown
     * @param errorMsgs the array of accompanying error messages
     */
    public static void error(Exception e, String[] errorMsgs) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR);
        for (String eachMsg : errorMsgs) {
            System.out.println(REPLY_INDENT + eachMsg);
        }

        if (getDebugMode()) {
            System.out.print(REPLY_INDENT + DEBUG_HEADER);
            e.printStackTrace();
        } else {
            System.out.println(REPLY_INDENT + e.getMessage());
        }
        System.out.println(BAR + "\n");
    }

    /**
     * Prints out error messages wrapped in nice formatting.
     *
     * @param errorMsg the accompanying error message
     */
    public static void error(String errorMsg) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR + "\n" + REPLY_INDENT + errorMsg + "\n" + BAR + "\n");
    }

    /**
     * Prints out error messages wrapped in nice formatting, multiline version.
     *
     * @param errorMsgs the array of accompanying error messages
     */
    public static void error(String[] errorMsgs) {
        String BAR = (getDebugMode()) ? DEBUG_BAR : ERROR_BAR;
        System.out.println("\n" + BAR);
        for (String eachMsg : errorMsgs) {
            System.out.println(REPLY_INDENT + eachMsg);
        }
        System.out.println(BAR + "\n");
    }

}
