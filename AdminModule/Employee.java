package AdminModule;
public class Employee extends Users {
    private String hireDate;
    private String type;
    private long id;
    private long typeid;
    private static long empCounter = 0;
    public static void setEmpCounter(long c) {
        empCounter = c;
    }
    public Employee(String name, String password, String role, String hireDate, long typeid) {
        super(name, password, role);
        this.id = empCounter;
        this.hireDate = hireDate;
        this.type = DataBase.Query.getTypeNameById(typeid);
        this.typeid = typeid;
    }
    public long getTypeID() {
        return typeid;
    }
    public void setType(long idtype) {
        this.type = DataBase.Query.getTypeNameById(idtype);
    }

    public String getType() {
        return this.type;
    }
    public void setTypeID(long id) {
        this.typeid = id;
    }
    public long getID() {
        return this.id;
    }
    public String getHireDate() {
        return hireDate;
    }
}