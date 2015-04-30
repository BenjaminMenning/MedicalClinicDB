package MedicalClinicDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
 
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
            throw e;
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
            throw e;
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
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        System.out.println(query);
    }
    
    public void addVisit(String visitID, String visitNumber, String 
            visitDate, String patientID, String healthcareProviderID, String dateAnalysisComplete, 
            String dateProcessingComplete) throws SQLException
    {
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
    
    public void addVisitDiagnosis(String visitDiagnosisID, String visitID,
            String diagnosisID) throws SQLException
    {
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
    
    public void addVisitFile(String visitFileID, String visitID, String fileID) 
            throws SQLException
    {
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
    
    public void addVisitStudy(String visitStudyID, String visitID,
            String studyID) throws SQLException
    {
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
        
    public void addVisitSystem(String visitSystemID, String visitID,
            String systemID) throws SQLException
    {
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

    public void addVisitTestResults(String visitID, String testResults1,
            String testResults2, String testResults3, String testResults4) 
            throws SQLException
    {
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
    
    public void addVisitTreatment(String visitTreatmentID, String visitID,
            String treatmentID) throws SQLException
    {
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

    public void addAssistiveDevice(String assistiveDeviceID, String 
            assistiveDeviceName) 
            throws SQLException
    {
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
    
    public void addCondition(String conditionID, String conditionName) 
            throws SQLException
    {
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
    
    public void addFile(String fileID, String fileName, String patientID) 
            throws SQLException
    {
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
    
    public void addHealthcareProvider(String healthcareProviderID, String 
            firstName, String middleName, String lastName) 
            throws SQLException
    {
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
    
    public void addICD9Diagnosis(String icd9DiagnosisID, String icd9Code, String 
            icd9Description) throws SQLException
    {
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
    
    public void addICD9Procedure(String icd9ProcedureID, String icd9Code, String 
            icd9Description) throws SQLException
    {
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
    
    public void addStudy(String studyID, String typeOfStudy) 
            throws SQLException
    {
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
    
    public void addSystem(String systemID, String systemUsed) 
            throws SQLException
    {
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
    
    public void addTreatment(String treatmentID, String treatmentName) 
            throws SQLException
    {
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
    
    public void addTreatmentICD9Procedure(String treatmentICD9ProcID, String 
            treatmentID, String icd9ProcedureID) throws SQLException
    {
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
    
    public String determinePatientID(String clinicNum) throws SQLException
    {
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

    public String determinePatientIDVisit(String visitNum) throws SQLException
    {
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
    
    public String determineVisitID(String visitNum) throws SQLException
    {
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
    
    public String determineAssistiveDeviceID(String assisDevName) throws 
            SQLException
    {
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
    
    public String determineConditionID(String conditionName) throws SQLException
    {
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

    public String determineHCPID(String providerName) throws SQLException
    {
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
    
    public String determineFileID(String fileName) throws SQLException
    {
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
    
    public String determineStudyID(String typeOfStudy) throws SQLException
    {
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
    
    public String determineDiagnosisID(String icd9Description) throws 
            SQLException
    {
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
    
    public String determineProcedureID(String icd9Description) throws SQLException
    {
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
    
    public String determineSystemID(String systemUsed) throws SQLException
    {
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
    
    public String determineTreatmentID(String treatmentName) throws SQLException
    {
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
    
    public ArrayList<String> getClinicNumberList() throws SQLException
    {
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
    
    public ArrayList<String> getADList() throws SQLException
    {
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
    
    public ArrayList<String> getConditionList() throws SQLException
    {
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
    
    public ArrayList<String> getProcedureList() throws SQLException
    {
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
    
    public ArrayList<String> getHCPList() throws SQLException
    {
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
    
    public ArrayList<String> getVisitNumberList() throws SQLException
    {
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
    
    public ArrayList<String> getStudyList() throws SQLException
    {
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
    
    public ArrayList<String> getDiagnosisList() throws SQLException
    {
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
    
    public ArrayList<String> getSystemList() throws SQLException
    {
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
    
    public ArrayList<String> getTreatmentList() throws SQLException
    {
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
    
    /**
     * This method determines whether or not a field is a empty and throws an
     * IllegalArgumentException if it is.
     * 
     * @param field the String to be evaluated as empty or not
     */
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
        
//    /**
//     * This method determines whether or not a field is a empty and throws an
//     * IllegalArgumentException if it is.
//     * 
//     * @param field the String to be evaluated as empty or not
//     */
//    public boolean isFieldEmpty2(String field)
//    {
//        boolean isEmpty = false;
//        String fieldStr = field;
//        isEmpty = fieldStr.equals("");
//        if(isEmpty == true)
//        {
//            throw new IllegalArgumentException();
//        }
//        return isEmpty;
//    }
 
//    public static void main(String[] argv) throws SQLException 
//    {
//      MedicalClinicDB medicalClinicDB = new MedicalClinicDB();
//      medicalClinicDB.connectToDatabase();
//      medicalClinicDB.determinePatientID("8-765-452");
//      ArrayList<String> poopList = new ArrayList<String>();
//      poopList = medicalClinicDB.getClinicNumberList();
//      
//      medicalClinicDB.searchPatient();
//      medicalClinicDB.searchFirstName("John");
//      medicalClinicDB.searchMiddleName("William");
//      medicalClinicDB.searchLastName("Smith");
//      medicalClinicDB.searchPatientID("1");
//      medicalClinicDB.search();
      
//      medicalClinicDB.addPatient("53", "2-333-333", "john", "john", "john", "M", "1990-07-27", "120", "120");
//      medicalClinicDB.addPatientAssistiveDevice("500", "500", "500");
//      medicalClinicDB.addPatientCondition("500", "500", "500");
//      medicalClinicDB.addPatientHealthcareProvider("500", "500", "500", "Primary");
//      medicalClinicDB.testQuery();
//    }
}