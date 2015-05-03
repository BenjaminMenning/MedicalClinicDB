package MedicalClinicDB;

import java.sql.SQLException;
import java.util.ArrayList;

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
 * This class contains a main method that allows a user to run the medical
 * clinic database program.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public interface MedicalClinicDBInterface 
{

    /**
     * This methods adds an assistive device to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param assistiveDeviceID
     * @param assistiveDeviceName
     * @throws SQLException
     */
    public void addAssistiveDevice(String assistiveDeviceID, String 
            assistiveDeviceName) throws SQLException;

    /**
     * This methods adds a condition to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param conditionID
     * @param conditionName
     * @throws SQLException
     */
    public void addCondition(String conditionID, String conditionName) 
            throws SQLException;

    /**
     *
     * @param fileID
     * @param fileName
     * @param patientID
     * @throws SQLException
     */
    public void addFile(String fileID, String fileName, String patientID) 
            throws SQLException;

    /**
     *
     * @param healthcareProviderID
     * @param firstName
     * @param middleName
     * @param lastName
     * @throws SQLException
     */
    public void addHealthcareProvider(String healthcareProviderID, String 
            firstName, String middleName, String lastName) throws SQLException;

    /**
     *
     * @param icd9DiagnosisID
     * @param icd9Code
     * @param icd9Description
     * @throws SQLException
     */
    public void addICD9Diagnosis(String icd9DiagnosisID, String icd9Code, String 
            icd9Description) throws SQLException;

    /**
     *
     * @param icd9ProcedureID
     * @param icd9Code
     * @param icd9Description
     * @throws SQLException
     */
    public void addICD9Procedure(String icd9ProcedureID, String icd9Code, String 
            icd9Description) throws SQLException;

    /**
     *
     * @param patientID
     * @param clinicNumber
     * @param firstName
     * @param middleName
     * @param lastName
     * @param gender
     * @param birthDate
     * @param height
     * @param weight
     * @throws SQLException
     */
    public void addPatient(String patientID, String clinicNumber, String 
            firstName, String middleName, String lastName, String gender, String 
                    birthDate, String height, String weight) 
            throws SQLException;

    /**
     *
     * @param patientAssistiveDeviceID
     * @param patientID
     * @param assistiveDeviceID
     * @throws SQLException
     */
    public void addPatientAssistiveDevice(String patientAssistiveDeviceID, 
            String patientID, String assistiveDeviceID) throws SQLException;

    /**
     *
     * @param patientConditionID
     * @param patientID
     * @param conditionID
     * @throws SQLException
     */
    public void addPatientCondition(String patientConditionID, String patientID, 
            String conditionID) throws SQLException;

    /**
     *
     * @param patientHealthcareProviderID
     * @param patientID
     * @param healthcareProviderID
     * @param healthcareProviderType
     * @throws SQLException
     */
    public void addPatientHealthcareProvider(String patientHealthcareProviderID, 
            String patientID, String healthcareProviderID, String 
                    healthcareProviderType) throws SQLException;

    /**
     *
     * @param studyID
     * @param typeOfStudy
     * @throws SQLException
     */
    public void addStudy(String studyID, String typeOfStudy) throws 
            SQLException;

    /**
     *
     * @param systemID
     * @param systemUsed
     * @throws SQLException
     */
    public void addSystem(String systemID, String systemUsed) throws 
            SQLException;

    /**
     *
     * @param treatmentID
     * @param treatmentName
     * @throws SQLException
     */
    public void addTreatment(String treatmentID, String treatmentName) throws 
            SQLException;

    /**
     *
     * @param treatmentICD9ProcID
     * @param treatmentID
     * @param icd9ProcedureID
     * @throws SQLException
     */
    public void addTreatmentICD9Procedure(String treatmentICD9ProcID, String 
            treatmentID, String icd9ProcedureID) throws SQLException;

    /**
     *
     * @param visitID
     * @param visitNumber
     * @param visitDate
     * @param patientID
     * @param healthcareProviderID
     * @param dateAnalysisComplete
     * @param dateProcessingComplete
     * @throws SQLException
     */
    public void addVisit(String visitID, String visitNumber, String visitDate, 
            String patientID, String healthcareProviderID, String 
                    dateAnalysisComplete, String dateProcessingComplete) 
            throws SQLException;

    /**
     *
     * @param visitDiagnosisID
     * @param visitID
     * @param diagnosisID
     * @throws SQLException
     */
    public void addVisitDiagnosis(String visitDiagnosisID, String visitID, 
            String diagnosisID) throws SQLException;

    /**
     *
     * @param visitFileID
     * @param visitID
     * @param fileID
     * @throws SQLException
     */
    public void addVisitFile(String visitFileID, String visitID, String fileID) 
            throws SQLException;

    /**
     *
     * @param visitStudyID
     * @param visitID
     * @param studyID
     * @throws SQLException
     */
    public void addVisitStudy(String visitStudyID, String visitID, String 
            studyID) throws SQLException;

    /**
     *
     * @param visitSystemID
     * @param visitID
     * @param systemID
     * @throws SQLException
     */
    public void addVisitSystem(String visitSystemID, String visitID, String 
            systemID) throws SQLException;

    /**
     *
     * @param visitID
     * @param testResults1
     * @param testResults2
     * @param testResults3
     * @param testResults4
     * @throws SQLException
     */
    public void addVisitTestResults(String visitID, String testResults1, String 
            testResults2, String testResults3, String testResults4) 
            throws SQLException;

    /**
     *
     * @param visitTreatmentID
     * @param visitID
     * @param treatmentID
     * @throws SQLException
     */
    public void addVisitTreatment(String visitTreatmentID, String visitID, 
            String treatmentID) throws SQLException;

    /**
     *
     */
    public void connectToDatabase();

    /**
     *
     * @param assisDevName
     * @return
     * @throws SQLException
     */
    public String determineAssistiveDeviceID(String assisDevName) 
            throws SQLException;

    /**
     *
     * @param conditionName
     * @return
     * @throws SQLException
     */
    public String determineConditionID(String conditionName) 
            throws SQLException;

    /**
     *
     * @param icd9Description
     * @return
     * @throws SQLException
     */
    public String determineDiagnosisID(String icd9Description) 
            throws SQLException;

    /**
     *
     * @param fileName
     * @return
     * @throws SQLException
     */
    public String determineFileID(String fileName) throws SQLException;

    /**
     *
     * @param providerName
     * @return
     * @throws SQLException
     */
    public String determineHCPID(String providerName) throws SQLException;

    /**
     *
     * @param clinicNum
     * @return
     * @throws SQLException
     */
    public String determinePatientID(String clinicNum) throws SQLException;

    /**
     *
     * @param visitNum
     * @return
     * @throws SQLException
     */
    public String determinePatientIDVisit(String visitNum) throws SQLException;

    /**
     *
     * @param icd9Description
     * @return
     * @throws SQLException
     */
    public String determineProcedureID(String icd9Description) 
            throws SQLException;

    /**
     *
     * @param typeOfStudy
     * @return
     * @throws SQLException
     */
    public String determineStudyID(String typeOfStudy) throws SQLException;

    /**
     *
     * @param systemUsed
     * @return
     * @throws SQLException
     */
    public String determineSystemID(String systemUsed) throws SQLException;

    /**
     *
     * @param treatmentName
     * @return
     * @throws SQLException
     */
    public String determineTreatmentID(String treatmentName) 
            throws SQLException;

    /**
     *
     * @param visitNum
     * @return
     * @throws SQLException
     */
    public String determineVisitID(String visitNum) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getADList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getClinicNumberList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getConditionList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getDiagnosisList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getHCPList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getProcedureList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getStudyList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getSystemList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getTreatmentList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getVisitNumberList() throws SQLException;

    /**
     * This method determines whether or not a field is a empty and throws an
     * IllegalArgumentException if it is.
     *
     * @param field the String to be evaluated as empty or not
     */
    public void isFieldEmpty(String field);
}