package duke.task;

/**
 * The type Deadline, which extends Task.
 * Characterised by the 'by' attribute.
 */
public class Deadline extends Task {
    protected String token = "[D]";
    protected String by;

    /**
     * Instantiates a new Deadline.
     *
     * @param newName the name (description) of the deadline task
     * @param by      the date by which to complete it
     * @param isDone  whether the task is complete
     */
    public Deadline(String newName, String by, boolean isDone){
        super(newName, isDone);
        this.by = by;
    }

    /**
     * Gets the 'by' date.
     *
     * @return the date
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the 'by' date.
     *
     * @param by the date
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Converts the task into string form for listing.
     */
    @Override
    public String toString() {
        return super.toString("[D]") + " (by: " + by + ")";
    }

    /**
     * Converts the task back into command form for storage.
     */
    @Override
    public String toCommand() {
        return "deadline " + super.toCommand() + " /by " + by + (isDone ? " /done" : "");
    }
}
