package duke.task;

/**
 * The type To-do, which extends Task.
 * Characterised by not having extra attributes.
 */
public class Todo extends Task {
    protected String token = "[T]";

    /**
     * Instantiates a new To-do.
     *
     * @param newName the name (description) of the to-do task
     * @param isDone  whether the task is complete
     */
    public Todo(String newName, boolean isDone){
        super(newName, isDone);
    }

    /**
     * Converts the task into string form for listing.
     */
    @Override
    public String toString() {
        return super.toString("[T]");
    }

    /**
     * Converts the task back into command form for storage.
     */
    @Override
    public String toCommand() {
        return "todo " + super.toCommand() + (isDone ? " /done" : "");
    }
}
