package duke.task;

import static duke.util.UI.tick;
import static duke.util.UI.cross;


/**
 * The practically abstract type Task.
 * Holds all the base attributes and methods for its child classes To-do, Event, and Deadline.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param newName the name (description) of the task
     * @param isDone  whether the task is complete
     */
    public Task(String newName, boolean isDone){
        this.name = newName;
        this.isDone = isDone;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the boolean completion of the task.
     *
     * @return the done
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Sets name.
     *
     * @param newName the new name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Set the boolean completion of the task.
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * Gets the check/cross string for the toString method based on isDone.
     *
     * @return the check
     */
    public String getCheck() {
        return isDone ? "["+ tick +"]" : "["+ cross +"]";
    }

    /**
     * Converts the task into string for listing.
     *
     * @param token the token, e.g. [T] for to-do, [D] for deadline, [E] for event
     * @return the task in string form
     */
    public String toString(String token) {
        return token + getCheck() + " " + name;
    }

    /**
     * Converts the task back into command form for storage.
     * e.g. "Event, do ABC, tomorrow" into "event do ABC /by tomorrow"
     *
     * @return the command-form string of the task
     */
    public String toCommand() {
        return getName();
    }
}
