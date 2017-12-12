package edu.txstate.internet.cyberflix.data.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import edu.txstate.internet.cyberflix.data.staff.Staff;
public class StaffDAO extends DAO {
    private final static Logger LOGGER = Logger.getLogger(StaffDAO.class.getName());
    public Staff findStaffByEmail(String emailAddress) {
        final String STAFF_SELECT = "SELECT * FROM staff ";
        final String EMAIL_CLAUSE = "WHERE email = ";
        final String selectString = STAFF_SELECT + EMAIL_CLAUSE + '"' + emailAddress + '"'; 
        
        List<Staff> staff = new ArrayList<>();
        Connection dbConnection = null;
        try {
            dbConnection = DAO.getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(selectString);
            while (results.next()) {
                staff.add(new Staff(
                        results.getInt("staff_id"),
                        results.getString("first_name"),
                        results.getString("last_name"),
                        results.getString("email"),
                        results.getString("password")
                ));
            }
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println("StaffDAO.findByEmail: " + e.toString());
            LOGGER.severe(e.toString());
            DAO.closeQuietly(dbConnection);
        }
        if (staff.isEmpty())
            return null;
        else    
            return staff.get(0);
    }
    @Override
    public void save(Object anObject) throws SQLException {
        // TODO Auto-generated method stub
    }
}
