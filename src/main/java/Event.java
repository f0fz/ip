public class Event extends Task {
    protected String token = "[E]";
    protected String at;

    public Event(String newName, String at){
        super(newName);
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
}
