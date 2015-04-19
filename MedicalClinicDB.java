package MedicalClinicDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MedicalClinicDB 
{
    private String className = "com.mysql.jdbc.Driver";
    private String databaseURL = 
            "jdbc:mysql://cs485project.cloudapp.net:3307/cs485_user";
    private String databaseUsername = "cs485_user";
    private String databasePassword = "Databas3";
    private String completeSearchTerm;
    private String basePatientQuery = "SELECT * \nFROM Patient";
    private String baseVisitQuery = "SELECT * \nFROM Visit";
    private int queryCriteriaCount = 0;
    private Connection connection = null;
    
    public void connectToDatabase()
    {
	try 
        {
            Class.forName("com.mysql.jdbc.Driver");
	} 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
	}
        
	try 
        {
            connection = DriverManager.getConnection(databaseURL, 
                    databaseUsername, databasePassword);
            System.out.println("Connection Successful");
	} 
        catch (SQLException e) 
        {
            System.out.println("Connection Failed");
            e.printStackTrace();
	}
    }
    
    public void addPatient(String patientID, String clinicNumber, String 
            firstName, String middleName, String lastName, String gender, 
            String birthDate, String height, String weight) throws SQLException
    {
        String patientIDStr = patientID;
        String clinicNumberStr = "'" + clinicNumber + "'";
        String firstNameStr = "'" + firstName + "'";
        String middleNameStr = "'" + middleName + "'";
        String lastNameStr = "'" + lastName + "'";
        String genderStr = "'" + gender + "'";
        String birthDateStr = "'" + birthDate + "'";
        String heightStr = height;
        String weightStr = weight;
        Statement stmt = null;
        String query = "CALL addPatient(" + patientIDStr + ", " + 
                clinicNumberStr + ", " +
                firstNameStr + ", " +
                middleNameStr + ", " +
                lastNameStr + ", " +
                genderStr + ", " +
                birthDateStr + ", " +
                heightStr + ", " +
                weightStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    

        System.out.println(query);
    }
    
    public void addPatientAssistiveDevice(String patientAssistiveDeviceID, 
            String patientID, String assistiveDeviceID) throws SQLException
    {
        String patientAssistiveDeviceIDStr = patientAssistiveDeviceID;
        String patientIDStr = patientID;
        String assistiveDeviceIDStr = assistiveDeviceID;
        Statement stmt = null;
        String query = "CALL addPatientAssistiveDevice(" + 
                patientAssistiveDeviceIDStr + ", " + 
                patientIDStr + ", " +
                assistiveDeviceIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    public void addPatientHealthcareProvider(String patientHealthcareProviderID,
            String patientID, String healthcareProviderID, String 
                    healthcareProviderType) throws SQLException
    {
        String patientHealthcareProviderIDStr = patientHealthcareProviderID;
        String patientIDStr = patientID;
        String healthcareProviderIDStr = healthcareProviderID;
        String healthcareProviderTypeStr = "'" + healthcareProviderType + "'";
        Statement stmt = null;
        String query = "CALL addPatientHealthcareProvider(" + 
                patientHealthcareProviderIDStr + ", " + 
                patientIDStr + ", " +
                healthcareProviderIDStr + ", " +
                healthcareProviderTypeStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    public void addPatientCondition(String patientConditionID, String patientID,
            String conditionID) throws SQLException
    {
        String patientConditionIDStr = patientConditionID;
        String patientIDStr = patientID;
        String conditionIDStr = conditionID;
        Statement stmt = null;
        String query = "CALL addPatientCondition(" + 
                patientConditionIDStr + ", " + 
                patientIDStr + ", " +
                conditionIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    public void search() throws SQLException
    {
        Statement stmt = null;
        String query = completeSearchTerm;
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String patientID = rs.getString("patientID");
                String clinicNumber = rs.getString("clinicNumber");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                String birthDate = rs.getString("birthDate");
                String height = rs.getString("height");
                String weight = rs.getString("weight");
                System.out.println(patientID + " " + clinicNumber + " " + 
                        firstName + " " + middleName + " " + lastName
                         + " " + gender + " " + birthDate + " " + 
                        height + " " + weight);
            }
        } 
        catch (SQLException e ) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(completeSearchTerm);
        completeSearchTerm = "";
        queryCriteriaCount = 0;
    }
    
    public void searchPatient()
    {
        completeSearchTerm = basePatientQuery;
    }
    
    public void queryModifier(String searchTerm)
    {
        String queryCondition;
        if(queryCriteriaCount < 2)
        {
            queryCondition = "\nWHERE " + searchTerm;
            completeSearchTerm += queryCondition;
        }
        else
        {
            queryCondition = "\nAND " + searchTerm;
            completeSearchTerm += queryCondition;
        }
    }
    
    public void searchFirstName(String firstName)
    {
        String firstNameStr = "firstName = '" + firstName + "'";
        queryCriteriaCount++;
        queryModifier(firstNameStr);
    }
    
    public void searchMiddleName(String middleName)
    {
        String middleNameStr = "middleName = '" + middleName + "'";
        queryCriteriaCount++;
        queryModifier(middleNameStr);
    }
    
    public void searchLastName(String lastName)
    {
        String lastNameStr = "lastName = '" + lastName + "'";
        queryCriteriaCount++;
        queryModifier(lastNameStr);
    }
    
    public void searchPatientID(String patientID)
    {
        String patientIDStr = "patientID = " + patientID;
        queryCriteriaCount++;
        queryModifier(patientIDStr);
    }
    
    public void searchClinicNumber(String clinicNumber)
    {
        String clinicNumberStr = "clinicNumber = " + clinicNumber;
        queryCriteriaCount++;
        queryModifier(clinicNumberStr);
    }
    
    public void testQuery() throws SQLException
    {
        Statement stmt = null;
        String query = "SELECT * FROM Patient";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String patientID = rs.getString("patientID");
                String clinicNumber = rs.getString("clinicNumber");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                String birthDate = rs.getString("birthDate");
                String height = rs.getString("height");
                String weight = rs.getString("weight");
                System.out.println(patientID + " " + clinicNumber + " " + 
                        firstName + " " + middleName + " " + lastName
                         + " " + gender + " " + birthDate + " " + 
                        height + " " + weight);
            }
        } 
        catch (SQLException e ) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
    }
 
    public static void main(String[] argv) throws SQLException 
    {
      MedicalClinicDB medicalClinicDB = new MedicalClinicDB();
      medicalClinicDB.connectToDatabase();
      medicalClinicDB.searchPatient();
      medicalClinicDB.searchFirstName("John");
      medicalClinicDB.searchMiddleName("William");
      medicalClinicDB.searchLastName("Smith");
      medicalClinicDB.searchPatientID("1");
      medicalClinicDB.search();
//      medicalClinicDB.addPatient("53", "2-333-333", "john", "john", "john", "M", "1990-07-27", "120", "120");
//      medicalClinicDB.addPatientAssistiveDevice("500", "500", "500");
//      medicalClinicDB.addPatientCondition("500", "500", "500");
//      medicalClinicDB.addPatientHealthcareProvider("500", "500", "500", "Primary");
//      medicalClinicDB.testQuery();
    }
}