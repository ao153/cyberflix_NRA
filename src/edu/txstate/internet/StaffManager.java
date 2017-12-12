package edu.txstate.internet;
import edu.txstate.internet.cyberflix.data.staff.Staff;
public class StaffManager {
    private Staff staff;
    private static StaffManager instance = null;
    public static StaffManager getInstance() {
        if (instance == null) {
            instance = new StaffManager();
        }
        return instance;
    }
    private StaffManager () {
    staff = new Staff();
    }
    
    public Staff getUser() {
        return staff;
    }
    
    public void logInAs(Staff staff) {
        this.staff = staff;
    }
}