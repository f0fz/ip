package duke.task;

import duke.util.DateTime;
import java.time.LocalDateTime;

/**
 * The type Deadline, which extends Task.
 * Characterised by the 'by' attribute.
 */
public class Deadline extends Task {
    protected final String TOKEN = "[D]";
    protected final String STRING_FORMAT = "%s (by: %s)";
    protected final String COMMAND_FORMAT = "deadline %s /by %s";
    protected LocalDateTime by;

    /**
     * Instantiates a new Deadline.
     *
     * @param newName the name (description) of the deadline task
     * @param by      the date by which to complete it
     * @param isDone  whether the task is complete
     */
    public Deadline(String newName, LocalDateTime by, boolean isDone){
        super(newName, isDone);
        this.by = by;
    }

    /**
     * Gets the 'by' date.
     *
     * @return the date
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets the 'by' date.
     *
     * @param by the date
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Converts the task into string form for listing.
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(TOKEN), DateTime.getStringForm(by));
    }

    /**
     * Converts the task back into command form for storage.
     */
    @Override
    public String toCommand() {
        return String.format(COMMAND_FORMAT, super.toCommand(), DateTime.getCommandForm(by), (isDone ? " /done" : ""));
    }
}
