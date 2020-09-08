package duke.task;

public class Todo extends Task {
    protected String token = "[T]";

    public Todo(String newName){
        super(newName);
    }

    @Override
    public String toString() {
        return super.toString("[T]");
    }
}
