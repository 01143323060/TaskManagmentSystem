package AdminModule;
public class Projects {
    private String title;
    private long ID;
    private String phase;
    private static long counter = 0;
    private long phaseid;
    public static void setCounter(long c) {
        counter = c;
    }
    public Projects(String title, long phaseid) {
        this.title = title;
        this.ID = counter;
        this.phase = DataBase.Query.getPhaseNameById(phaseid);
        this.phaseid = phaseid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long  getID() {
        return ID;
    }
    public String getPhase() {
        return phase;
    }
    public void setPhaseID(long phaseid) {
        this.phaseid = phaseid;
    }
    public void setPhase(long phaseid) {
        this.phase = DataBase.Query.getPhaseNameById(phaseid);
    }
    public long getPhaseID() {
        return phaseid;
    }
}