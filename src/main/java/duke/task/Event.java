package duke.task;

/**
 * The type Event, which extends Task.
 * Characterised by the 'at' attribute.
 */
public class Event extends Task {
    protected String token = "[E]";
    protected String at;

    /**
     * Instantiates a new Event.
     *
     * @param newName the name (description) of the Event task
     * @param at      the start-end time period
     * @param isDone  whether the task is complete
     */
    public Event(String newName, String at, boolean isDone){
        super(newName, isDone);
        this.at = at;
    }

    /**
     * Gets the 'by' date.
     *
     * @return the by
     */
    public String getAt() {
        return at;
    }

    /**
     * Sets the 'at' time period.
     *
     * @param at the time period
     */
    public void setAt(String at) {
        this.at = at;
    }

    /**
     * Converts the task into string form for listing.
     */
    @Override
    public String toString() {
        return super.toString("[E]") + " (at: " + at + ")";
    }

    /**
     * Converts the task back into command form for storage.
     */
    @Override
    public String toCommand() {
        return "event " + super.toCommand() + " /at " + at + (isDone ? " /done" : "");
    }
}
