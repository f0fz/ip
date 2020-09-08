package duke.task;

import static duke.util.UI.tick;
import static duke.util.UI.cross;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String newName){
        name = newName;
        isDone = false;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setDone(){
        isDone = true;
    }

    public String getCheck() {
        return isDone ? "["+ tick +"]" : "["+ cross +"]";
    }

    public String toString(String token) {
        return token + getCheck() + " " + name;
    }
}