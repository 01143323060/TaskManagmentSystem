package AdminModule;
public class TaskPhases {
    private String phase;
    private long id;
    private static long counter = 0;
    public static void setCounter(long c) {
        counter = c;
    }
    public TaskPhases(String phase) {
        this.phase = phase;
        this.id = counter;
    }
    public String getphase() {
        return phase;
    }
    public static long getID() {
        return counter;
    }
    public long getid() {
        return id;
    }
    public  void setPhase(String phase) {
        this.phase = phase;
    }
}