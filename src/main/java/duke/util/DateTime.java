package duke.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateTime type. Encapsulates all logic for the LocalDateTime class of objects.
 */
public class DateTime {
    private static final DateTimeFormatter commandForm = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
    private static final DateTimeFormatter outputForm = DateTimeFormatter.ofPattern("E HH:mm, d MMM yyyy");
    /**
     * Convert a string into a LocalDateTime object with the slashForm format.
     *
     * @param input String to be parsed
     * @return LocalDate object
     */
    public static LocalDateTime parseDate(String input) {
        return LocalDateTime.parse(input, commandForm);
    }

    /**
     * Checks whether a given string is a valid date or not.
     *
     * @param input The string to be checked
     * @return boolean of whether the string is a valid date
     */
    public static boolean checkValidDate(String input) {
        try {
            parseDate(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getStringForm(LocalDateTime inputDate){
        return outputForm.format(inputDate);
    }

    public static String getCommandForm(LocalDateTime inputDate){
        return commandForm.format(inputDate);
    }
}
