package duke.util;

/**
 * The Exception type DukeException. Thrown when commands are invalid but no Java is broken.
 * No plans for expansion.
 */
public class DukeException extends Exception{
    /**
     * Instantiates a new DukeException.
     *
     * @param errorMessage the error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
