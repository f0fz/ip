public class Task {
    private String name;
    private boolean isDone;

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
}
