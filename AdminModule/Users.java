package AdminModule;
public class Users {
    private String name;
    private long ID;
    private String password;
    private String role;
    private static long counter = 0;
    public static void setCounter(long c) {
        counter = c;
    }
    public Users(String name, String password, String role) {
        this.name = name;
        this.ID = counter;
        this.password = password;
        this.role = role;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}