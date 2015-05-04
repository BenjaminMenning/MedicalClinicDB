package MedicalClinicDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/** 
 * Author:          Benjamin Menning, Dan Johnson, Holly Schreader
 * 
 * Date:            05/05/2015 
 *                
 * Course:          CS 485 - 01, Spring 2015
 * 
 * Assignment:      Database Project
 * 
 * Description:     This program is a medical database program that utilizes a
 *                  MySQL relational database management system to allow users
 *                  to input and view information about patients and visits.
 *                  It allows users to input information on a wide variety of 
 *                  things, including patient conditions and assistive devices,
 *                  visit diagnoses and studies, as well as information about
 *                  healthcare providers or systems used. It also allows a user
 *                  to search and lookup information about patients based on a
 *                  wide variety of criteria like name, diagnoses, date of birth
 *                  and more. It also allows users to see more detailed 
 *                  information about patients and their visits.
 */

/** 
 * This class implements the MedicalDBInterface and defines its methods. These
 * methods allow certain types of functionality, such as connecting to a 
 * database, adding various items to the database, determining the IDs of 
 * entities within the database, and compiling lists of various data within the
 * database. These methods all provide functionality that the GUI components 
 * are allowed to use.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class MedicalClinicDB implements MedicalClinicDBInterface 
{
    // String variables for database connectivity
    private String className = "com.mysql.jdbc.Driver";
    private String databaseURL = 
            "jdbc:mysql://cs485project.cloudapp.net:3307/cs485_user";
    private String databaseUsername = "cs485_user";
    private String databasePassword = "Databas3";

    // String variables for querying data and IDs from the database
    private String basePatientQuery = "SELECT * \nFROM Patient";
    private String baseVisitQuery = "SELECT * \nFROM Visit";
    private String patientIDQuery = "SELECT patientID \nFROM Patient";
    private String visitIDQuery = "SELECT visitID \nFROM Visit";
    private String assisDevIDQuery = "SELECT assistiveDeviceID "
            + "\nFROM AssistiveDevice";
    private String conditionIDQuery = "SELECT conditionID \nFROM `Condition`";
    private String providerIDQuery = "SELECT healthcareProviderID "
            + "\nFROM HealthcareProvider";
    private String procedureIDQuery = "SELECT icd9ProcedureID \nFROM "
            + "ICD9Procedure";
    private String diagnosisIDQuery = "SELECT icd9DiagnosisID \nFROM "
            + "ICD9Diagnosis";
    private String fileIDQuery = "SELECT fileID \nFROM File";
    private String studyIDQuery = "SELECT studyID \nFROM Study";
    private String systemIDQuery = "SELECT systemID \nFROM System";
    private String treatmentIDQuery = "SELECT treatmentID \nFROM Treatment";

    // Connection variable for connecting to the database
    private Connection connection = null;
    
    @Override
    public void connectToDatabase()
    {
        int timeout = 0;
        try {
            if(connection == null || connection.isValid(timeout) == false) 
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
        } catch (SQLException ex) {
            Logger.getLogger(MedicalClinicDB.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    @Override
    public void addPatient(String patientID, String clinicNumber, String 
            firstName, String middleName, String lastName, String gender, 
            String birthDate, String height, String weight) throws SQLException
    {
        connectToDatabase();
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
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    

        System.out.println(query);
    }
    
    @Override
    public void addPatientAssistiveDevice(String patientAssistiveDeviceID, 
            String patientID, String assistiveDeviceID) throws SQLException
    {
        connectToDatabase();
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
        catch (SQLException e) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addPatientHealthcareProvider(String patientHealthcareProviderID,
            String patientID, String healthcareProviderID, String 
                    healthcareProviderType) throws SQLException
    {
        connectToDatabase();
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
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addPatientCondition(String patientConditionID, String patientID,
            String conditionID) throws SQLException
    {
        connectToDatabase();
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
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addVisit(String visitID, String visitNumber, String 
            visitDate, String patientID, String healthcareProviderID, String dateAnalysisComplete, 
            String dateProcessingComplete) throws SQLException
    {
        connectToDatabase();
        String visitIDStr = visitID;
        String visitNumberStr = "'" + visitNumber + "'";
        String visitDateStr = "'" + visitDate + "'";
        String patientIDStr = "'" + patientID + "'";
        String healthcareProviderIDStr = "'" + healthcareProviderID + "'";
        String dateAnalysisCompleteStr = "'" + dateAnalysisComplete + "'";
        String dateProcessingCompleteStr = "'" + dateProcessingComplete + "'";
        Statement stmt = null;
        String query = "CALL addVisit(" + visitIDStr + ", " + 
                visitNumberStr + ", " +
                visitDateStr + ", " +
                patientIDStr + ", " +
                healthcareProviderIDStr + ", " +
                dateAnalysisCompleteStr + ", " +
                dateProcessingCompleteStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    

        System.out.println(query);
    }
    
    @Override
    public void addVisitDiagnosis(String visitDiagnosisID, String visitID,
            String diagnosisID) throws SQLException
    {
        connectToDatabase();
        String visitDiagnosisIDStr = visitDiagnosisID;
        String visitIDStr = visitID;
        String diagnosisIDStr = diagnosisID;
        Statement stmt = null;
        String query = "CALL addVisitDiagnosis(" + 
                visitDiagnosisIDStr + ", " + 
                visitIDStr + ", " +
                diagnosisIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addVisitFile(String visitFileID, String visitID, String fileID) 
            throws SQLException
    {
        connectToDatabase();
        String visitFileIDStr = visitFileID;
        String visitIDStr = visitID;
        String fileIDStr = fileID;
        Statement stmt = null;
        String query = "CALL addVisitFile(" + 
                visitFileIDStr + ", " +
                visitIDStr + ", " +
                fileIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addVisitStudy(String visitStudyID, String visitID,
            String studyID) throws SQLException
    {
        connectToDatabase();
        String visitStudyIDStr = visitStudyID;
        String visitIDStr = visitID;
        String studyIDStr = studyID;
        Statement stmt = null;
        String query = "CALL addVisitStudy(" + 
                visitStudyIDStr + ", " + 
                visitIDStr + ", " +
                studyIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
        
    @Override
    public void addVisitSystem(String visitSystemID, String visitID,
            String systemID) throws SQLException
    {
        connectToDatabase();
        String visitSystemIDStr = visitSystemID;
        String visitIDStr = visitID;
        String systemIDStr = systemID;
        Statement stmt = null;
        String query = "CALL addVisitSystem(" + 
                visitSystemIDStr + ", " + 
                visitIDStr + ", " +
                systemIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }

    @Override
    public void addVisitTestResults(String visitID, String testResults1,
            String testResults2, String testResults3, String testResults4) 
            throws SQLException
    {
        connectToDatabase();
        String visitIDStr = visitID;
        String testResults1Str = "'" + testResults1 + "'";
        String testResults2Str = "'" + testResults2 + "'";
        String testResults3Str = "'" + testResults3 + "'";
        String testResults4Str = "'" + testResults4 + "'";
        Statement stmt = null;
        String query = "CALL addTestResults(" + 
                null + ", " + 
                visitIDStr + ", " +
                testResults1Str + ", " +
                testResults2Str + ", " +
                testResults3Str + ", " +
                testResults4Str + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addVisitTreatment(String visitTreatmentID, String visitID,
            String treatmentID) throws SQLException
    {
        connectToDatabase();
        String visitTreatmentIDStr = visitTreatmentID;
        String visitIDStr = visitID;
        String treatmentIDStr = treatmentID;
        Statement stmt = null;
        String query = "CALL addVisitTreatment(" + 
                visitTreatmentIDStr + ", " + 
                visitIDStr + ", " +
                treatmentIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }

    @Override
    public void addAssistiveDevice(String assistiveDeviceID, String 
            assistiveDeviceName) 
            throws SQLException
    {
        connectToDatabase();
        String assistiveDeviceIDStr = assistiveDeviceID;
        String assistiveDeviceNameStr = "'" + assistiveDeviceName + "'";
        Statement stmt = null;
        String query = "CALL addAssistiveDevice(" + 
                assistiveDeviceIDStr + ", " +
                assistiveDeviceNameStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addCondition(String conditionID, String conditionName) 
            throws SQLException
    {
        connectToDatabase();
        String conditionIDStr = conditionID;
        String conditionNameStr = "'" + conditionName + "'";
        Statement stmt = null;
        String query = "CALL addCondition(" + 
                conditionIDStr + ", " +
                conditionNameStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addFile(String fileID, String fileName, String patientID) 
            throws SQLException
    {
        connectToDatabase();
        String fileIDStr = fileID;
        String fileNameStr = "'" + fileName + "'";
        String patientIDStr = patientID;
        Statement stmt = null;
        String query = "CALL addFile(" + 
                fileIDStr + ", " +
                fileNameStr + ", " +
                patientIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addHealthcareProvider(String healthcareProviderID, String 
            firstName, String middleName, String lastName) 
            throws SQLException
    {
        connectToDatabase();
        String healthcareProviderIDStr = healthcareProviderID;
        String firstNameStr = "'" + firstName + "'";
        String middleNameStr = "'" + middleName + "'";
        String lastNameStr = "'" + lastName + "'";
        Statement stmt = null;
        String query = "CALL addHealthcareProvider(" + 
                healthcareProviderIDStr + ", " +
                firstNameStr + ", " +
                middleNameStr + ", " +
                lastNameStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addICD9Diagnosis(String icd9DiagnosisID, String icd9Code, String 
            icd9Description) throws SQLException
    {
        connectToDatabase();
        String icd9DiagnosisIDStr = icd9DiagnosisID;
        String icd9CodeStr = "'" + icd9Code + "'";
        String icd9DescriptionStr = "'" + icd9Description + "'";
        Statement stmt = null;
        String query = "CALL addICD9Diagnosis(" + 
                icd9DiagnosisIDStr + ", " +
                icd9CodeStr + ", " + 
                icd9DescriptionStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addICD9Procedure(String icd9ProcedureID, String icd9Code, String 
            icd9Description) throws SQLException
    {
        connectToDatabase();
        String icd9ProcedureIDStr = icd9ProcedureID;
        String icd9CodeStr = "'" + icd9Code + "'";
        String icd9DescriptionStr = "'" + icd9Description + "'";
        Statement stmt = null;
        String query = "CALL addICD9Procedure(" + 
                icd9ProcedureIDStr + ", " +
                icd9CodeStr + ", " + 
                icd9DescriptionStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addStudy(String studyID, String typeOfStudy) 
            throws SQLException
    {
        connectToDatabase();
        String studyIDStr = studyID;
        String typeOfStudyStr = "'" + typeOfStudy + "'";
        Statement stmt = null;
        String query = "CALL addStudy(" + 
                studyIDStr + ", " +
                typeOfStudyStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addSystem(String systemID, String systemUsed) 
            throws SQLException
    {
        connectToDatabase();
        String systemIDStr = systemID;
        String systemUsedStr = "'" + systemUsed + "'";
        Statement stmt = null;
        String query = "CALL addSystem(" + 
                systemIDStr + ", " +
                systemUsedStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addTreatment(String treatmentID, String treatmentName) 
            throws SQLException
    {
        connectToDatabase();
        String treatmentIDStr = treatmentID;
        String treatmentNameStr = "'" + treatmentName + "'";
        Statement stmt = null;
        String query = "CALL addTreatment(" + 
                treatmentIDStr + ", " +
                treatmentNameStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public void addTreatmentICD9Procedure(String treatmentICD9ProcID, String 
            treatmentID, String icd9ProcedureID) throws SQLException
    {
        connectToDatabase();
        String treatmentICD9ProcIDStr = treatmentICD9ProcID;
        String treatmentIDStr = treatmentID;
        String icd9ProcedureIDStr = icd9ProcedureID;
        Statement stmt = null;
        String query = "CALL addTreatmentICD9Procedure(" + 
                treatmentICD9ProcIDStr + ", " +
                treatmentIDStr + ", " + 
                icd9ProcedureIDStr + ")";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    @Override
    public String determinePatientID(String clinicNum) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String patientID = "";
        String clinicNumStr = "'" + clinicNum + "'";
        String query = patientIDQuery + "\nWHERE clinicNumber = " + clinicNumStr;
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          patientID = rs.getString("patientID");
        }    
        return patientID;
    }

    @Override
    public String determinePatientIDVisit(String visitNum) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String patientID = "";
        String visitNumStr = "'" + visitNum + "'";
        String query = patientIDQuery + "\nRIGHT JOIN Visit\nUSING (patientID)"
                + "\nWHERE visitNumber = " + visitNumStr;
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          patientID = rs.getString("patientID");
        }    
        return patientID;
    }
    
    @Override
    public String determineVisitID(String visitNum) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String visitID = "";
        String visitNumStr = "'" + visitNum + "'";
        String query = visitIDQuery + "\nWHERE visitNumber = " + visitNumStr;
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          visitID = rs.getString("visitID");
        }    
        return visitID;
    }
    
    @Override
    public String determineAssistiveDeviceID(String assisDevName) throws 
            SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String assistiveDeviceID = "";
        String assisDevNameStr = "'" + assisDevName + "'";
        String query = assisDevIDQuery + "\nWHERE assistiveDeviceName = " + 
                assisDevNameStr;
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          assistiveDeviceID = rs.getString("assistiveDeviceID");
        }    
        return assistiveDeviceID;
    }
    
    @Override
    public String determineConditionID(String conditionName) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String conditionID = "";
        String conditionNameStr = "'" + conditionName + "'";
        String query = conditionIDQuery + "\nWHERE conditionName = " + 
                conditionNameStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          conditionID = rs.getString("conditionID");
        }    
        return conditionID;
    }

    @Override
    public String determineHCPID(String providerName) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String providerID = "";
        String providerNameStr = "'" + providerName + "'";
        Scanner sc = new Scanner(providerName);
        String firstNameStr = "'" + sc.next() + "'";
        String middleNameStr = "'" + sc.next() + "'";
        String lastNameStr = "'" + sc.next() + "'";
        String query = providerIDQuery + "\nWHERE firstName = " + firstNameStr +
                "\nAND middleName = " + middleNameStr + "\nAND lastName = " + 
                lastNameStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          providerID = rs.getString("healthcareProviderID");
        }    
        return providerID;
    }
    
    @Override
    public String determineFileID(String fileName) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String fileID = "";
        String fileNameStr = "'" + fileName + "'";
        String query = fileIDQuery + "\nWHERE fileName = " + 
                fileNameStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          fileID = rs.getString("fileID");
        }    
        return fileID;
    }
    
    @Override
    public String determineStudyID(String typeOfStudy) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String studyID = "";
        String typeOfStudyStr = "'" + typeOfStudy + "'";
        String query = studyIDQuery + "\nWHERE typeOfStudy = " + 
                typeOfStudyStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          studyID = rs.getString("studyID");
        }    
        return studyID;
    }
    
    @Override
    public String determineDiagnosisID(String icd9Description) throws 
            SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String diagnosisID = "";
        String icd9DescriptionStr = "'" + icd9Description + "'";
        String query = diagnosisIDQuery + "\nWHERE icd9Description = " + 
                icd9DescriptionStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          diagnosisID = rs.getString("icd9DiagnosisID");
        }    
        return diagnosisID;
    }
    
    @Override
    public String determineProcedureID(String icd9Description) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String procedureID = "";
        String icd9DescriptionStr = "'" + icd9Description + "'";
        String query = procedureIDQuery + "\nWHERE icd9Description = " + 
                icd9DescriptionStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          procedureID = rs.getString("icd9ProcedureID");
        }    
//        if(procedureID.equals(""))
//        {
//            throw new IllegalArgumentException();
//        }
        return procedureID;
    }
    
    @Override
    public String determineSystemID(String systemUsed) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String systemID = "";
        String systemUsedStr = "'" + systemUsed + "'";
        String query = systemIDQuery + "\nWHERE systemUsed = " + 
                systemUsedStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          systemID = rs.getString("systemID");
        }    
        return systemID;
    }
    
    @Override
    public String determineTreatmentID(String treatmentName) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        stmt = connection.createStatement();
        String treatmentID = "";
        String treatmentNameStr = "'" + treatmentName + "'";
        String query = treatmentIDQuery + "\nWHERE treatmentName = " + 
                treatmentNameStr;
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
          rs.first();
          treatmentID = rs.getString("treatmentID");
        }    
        return treatmentID;
    }
    
    @Override
    public ArrayList<String> getClinicNumberList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> clinicNumberList = new ArrayList<String>();
        clinicNumberList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Patient";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String clinicNumber = rs.getString("clinicNumber");
                clinicNumberList.add(clinicNumber);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return clinicNumberList;
    }
    
    @Override
    public ArrayList<String> getADList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> assisDevList = new ArrayList<String>();
        assisDevList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM AssistiveDevice";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String assistiveDevice = rs.getString("assistiveDeviceName");
                assisDevList.add(assistiveDevice);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return assisDevList;
    }
    
    @Override
    public ArrayList<String> getConditionList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> conditionList = new ArrayList<String>();
        conditionList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM `Condition`";
        System.out.println(query);
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String condition = rs.getString("conditionName");
                conditionList.add(condition);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return conditionList;
    }
    
    @Override
    public ArrayList<String> getProcedureList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> procedureList = new ArrayList<String>();
        procedureList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM ICD9Procedure";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String procedure = rs.getString("icd9Description");
                procedureList.add(procedure);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return procedureList;
    }
    
    @Override
    public ArrayList<String> getHCPList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> healthcareProviderList = new ArrayList<String>();
        healthcareProviderList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM `HealthcareProvider`";
        System.out.println(query);
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                String fullName = firstName + " " + middleName + " " + lastName;
                healthcareProviderList.add(fullName);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return healthcareProviderList;
    }
    
    @Override
    public ArrayList<String> getVisitNumberList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> visitNumberList = new ArrayList<String>();
        visitNumberList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Visit";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String visitNumber = rs.getString("visitNumber");
                visitNumberList.add(visitNumber);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return visitNumberList;
    }
    
    @Override
    public ArrayList<String> getStudyList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> studyList = new ArrayList<String>();
        studyList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Study";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String study = rs.getString("typeOfStudy");
                studyList.add(study);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return studyList;
    }
    
    @Override
    public ArrayList<String> getDiagnosisList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> diagnosisList = new ArrayList<String>();
        diagnosisList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM ICD9Diagnosis";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String diagnosis = rs.getString("icd9Description");
                diagnosisList.add(diagnosis);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return diagnosisList;
    }
    
    @Override
    public ArrayList<String> getSystemList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> systemList = new ArrayList<String>();
        systemList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM System";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String system = rs.getString("systemUsed");
                systemList.add(system);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return systemList;
    }
    
    @Override
    public ArrayList<String> getTreatmentList() throws SQLException
    {
//        connectToDatabase();
        ArrayList<String> treatmentList = new ArrayList<String>();
        treatmentList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Treatment";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String treatment = rs.getString("treatmentName");
                treatmentList.add(treatment);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return treatmentList;
    }
    
    @Override
    public void isFieldEmpty(String field)
    {
        boolean isEmpty = false;
        String fieldStr = field;
        isEmpty = fieldStr.equals("");
        if(isEmpty == true)
        {
            throw new IllegalArgumentException();
        }
//        return isEmpty;
    }
}