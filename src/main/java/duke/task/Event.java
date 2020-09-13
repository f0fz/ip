package duke.task;

public class Event extends Task {
    protected String token = "[E]";
    protected String at;

    public Event(String newName, String at, boolean isDone){
        super(newName, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString("[E]") + " (at: " + at + ")";
    }

    @Override
    public String toCommand() {
        return "event " + super.toCommand() + " /at " + at + (isDone ? " /done" : "");
    }
}
