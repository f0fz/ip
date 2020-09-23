package duke.task;

import duke.util.DateTime;

import java.time.LocalDateTime;

/**
 * The type Event, which extends Task.
 * Characterised by the 'at' attribute.
 */
public class Event extends Task {
    protected final String TOKEN = "[E]";
    protected final String STRING_FORMAT = "%s (at: %s)";
    protected final String COMMAND_FORMAT = "event %s /at %s";
    protected LocalDateTime at;

    /**
     * Instantiates a new Event.
     *
     * @param newName the name (description) of the Event task
     * @param at      the start-end time period
     * @param isDone  whether the task is complete
     */
    public Event(String newName, LocalDateTime at, boolean isDone){
        super(newName, isDone);
        this.at = at;
    }

    /**
     * Gets the 'by' date.
     *
     * @return the by
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Sets the 'at' time period.
     *
     * @param at the time period
     */
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    /**
     * Converts the task into string form for listing.
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(TOKEN), DateTime.getStringForm(at));
    }

    /**
     * Converts the task back into command form for storage.
     */
    @Override
    public String toCommand() {
        return String.format(COMMAND_FORMAT, super.toCommand(), DateTime.getCommandForm(at), (isDone ? " /done" : ""));
    }
}
