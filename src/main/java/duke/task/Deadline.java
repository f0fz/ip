package duke.task;

public class Deadline extends Task {
    protected String token = "[D]";
    protected String by;

    public Deadline(String newName, String by, boolean isDone){
        super(newName, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString("[D]") + " (by: " + by + ")";
    }

    @Override
    public String toCommand() {
        return "deadline " + super.toCommand() + " /by " + by + (isDone ? " /done" : "");
    }
}
