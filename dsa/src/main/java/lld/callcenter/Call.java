package lld.callcenter;

public class Call {

    private Caller caller;
    private Employee employee;

    public Caller getCaller() {
        return caller;
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
