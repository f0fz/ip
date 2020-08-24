public class Task {
    private String name;
    private boolean isDone;

    Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void complete(){
        this.isDone = true;
    }
}
