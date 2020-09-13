package duke.task;

public class Todo extends Task {
    protected String token = "[T]";

    public Todo(String newName, boolean isDone){
        super(newName, isDone);
    }

    @Override
    public String toString() {
        return super.toString("[T]");
    }

    @Override
    public String toCommand() {
        return "todo " + super.toCommand() + (isDone ? " /done" : "");
    }
}
