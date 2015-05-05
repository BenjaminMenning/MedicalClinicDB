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
     * @param assistiveDeviceID the String to be assigned as the assistive 
     *      device ID
     * @param assistiveDeviceName the String to be assigned as the assistive
     *      device name
     * @throws SQLException if SQL database encounters an error
     */
    public void addAssistiveDevice(String assistiveDeviceID, String 
            assistiveDeviceName) throws SQLException;

    /**
     * This methods adds a condition to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param conditionID the String to be assigned as the condition ID
     * @param conditionName the String to be assigned as the condition name
     * @throws SQLException if SQL database encounters an error
     */
    public void addCondition(String conditionID, String conditionName) 
            throws SQLException;

    /**
     * This methods adds a file to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param fileID the String to be assigned as the file ID
     * @param fileName the String to be assigned as the file name
     * @param patientID the String to be assigned as the patient ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addFile(String fileID, String fileName, String patientID) 
            throws SQLException;

    /**
     * This methods adds a provider to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param healthcareProviderID the String to be assigned as the provider ID
     * @param firstName the String to be assigned as the first name
     * @param middleName the String to be assigned as the middle name
     * @param lastName the String to be assigned as the last name
     * @throws SQLException if SQL database encounters an error
     */
    public void addHealthcareProvider(String healthcareProviderID, String 
            firstName, String middleName, String lastName) throws SQLException;

    /**
     * This methods adds a diagnosis to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param icd9DiagnosisID the String to be assigned as the diagnosis ID
     * @param icd9Code the String to be assigned as the ICD9 code
     * @param icd9Description the String to be assigned as the description
     * @throws SQLException if SQL database encounters an error
     */
    public void addICD9Diagnosis(String icd9DiagnosisID, String icd9Code, String 
            icd9Description) throws SQLException;

    /**
     * This methods adds a procedure to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param icd9ProcedureID the String to be assigned as the procedure ID
     * @param icd9Code the String to be assigned as the ICD9 code
     * @param icd9Description the String to be assigned as the description
     * @throws SQLException if SQL database encounters an error
     */
    public void addICD9Procedure(String icd9ProcedureID, String icd9Code, String 
            icd9Description) throws SQLException;

    /**
     * This methods adds a patient to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param patientID the String to be assigned as the patient ID
     * @param clinicNumber the String to be assigned as the clinic number
     * @param firstName the String to be assigned as the first name
     * @param middleName the String to be assigned as the middle name
     * @param lastName the String to be assigned as the last name
     * @param gender the String to be assigned as the gender
     * @param birthDate the String to be assigned as the birth date
     * @param height the String to be assigned as the height
     * @param weight the String to be assigned as the weight
     * @throws SQLException if SQL database encounters an error
     */
    public void addPatient(String patientID, String clinicNumber, String 
            firstName, String middleName, String lastName, String gender, String 
                    birthDate, String height, String weight) 
            throws SQLException;

    /**
     * This methods adds a patient assistive device to the database by calling a 
     * stored procedure from within the database and passing the assigned 
     * values.
     * 
     * @param patientAssistiveDeviceID the String to be assigned as the patient
     *      assistive device ID
     * @param patientID the String to be assigned as the patient ID
     * @param assistiveDeviceID the String to be assigned as the assistive
     *      device ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addPatientAssistiveDevice(String patientAssistiveDeviceID, 
            String patientID, String assistiveDeviceID) throws SQLException;

    /**
     * This methods adds a patient condition to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param patientConditionID the String to be assigned as the patient 
     *      condition ID
     * @param patientID the String to be assigned as the patient ID
     * @param conditionID the String to be assigned as the condition ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addPatientCondition(String patientConditionID, String patientID, 
            String conditionID) throws SQLException;

    /**
     * This methods adds a patient provider to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param patientHealthcareProviderID the String to be assigned as the 
     *      patient provider ID
     * @param patientID the String to be assigned as the patient ID
     * @param healthcareProviderID the String to be assigned as the provider ID
     * @param healthcareProviderType the String to be assigned as the provider
     *      type
     * @throws SQLException if SQL database encounters an error
     */
    public void addPatientHealthcareProvider(String patientHealthcareProviderID, 
            String patientID, String healthcareProviderID, String 
                    healthcareProviderType) throws SQLException;

    /**
     * This methods adds a study to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param studyID the String to be assigned as the study ID
     * @param typeOfStudy the String to be assigned as the type of study
     * @throws SQLException if SQL database encounters an error
     */
    public void addStudy(String studyID, String typeOfStudy) throws 
            SQLException;

    /**
     * This methods adds a system to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param systemID the String to be assigned as the system ID
     * @param systemUsed the String to be assigned as the system used
     * @throws SQLException if SQL database encounters an error
     */
    public void addSystem(String systemID, String systemUsed) throws 
            SQLException;

    /**
     * This methods adds a treatment to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param treatmentID the String to be assigned as the treatment ID
     * @param treatmentName the String to be assigned as the treatment name
     * @throws SQLException if SQL database encounters an error
     */
    public void addTreatment(String treatmentID, String treatmentName) throws 
            SQLException;

    /**
     * This methods adds a procedure to a treatment in the database by calling a 
     * stored procedure from within the database and passing the assigned 
     * values.
     * 
     * @param treatmentICD9ProcID the String to be assigned as the treatment 
     *      ICD9 procedure ID
     * @param treatmentID the String to be assigned as the treatment ID
     * @param icd9ProcedureID the String to be assigned as the procedure ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addTreatmentICD9Procedure(String treatmentICD9ProcID, String 
            treatmentID, String icd9ProcedureID) throws SQLException;

    /**
     * This methods adds a visit to the database by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param visitID the String to be assigned as the visit ID
     * @param visitNumber the String to be assigned as the visit number
     * @param visitDate the String to be assigned as the visit date
     * @param patientID the String to be assigned as the patient ID
     * @param healthcareProviderID the String to be assigned as the provider ID
     * @param dateAnalysisComplete the String to be assigned as the analysis 
     *      date
     * @param dateProcessingComplete the String to be assigned as the processing
     *      date
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisit(String visitID, String visitNumber, String visitDate, 
            String patientID, String healthcareProviderID, String 
                    dateAnalysisComplete, String dateProcessingComplete) 
            throws SQLException;

    /**
     * This methods adds diagnosis to a visit in the database by calling a 
     * stored procedure from within the database and passing the assigned values.
     * 
     * @param visitDiagnosisID the String to be assigned as the visit diagnosis
     *      ID
     * @param visitID the String to be assigned as the visit ID
     * @param diagnosisID the String to be assigned as the diagnosis ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisitDiagnosis(String visitDiagnosisID, String visitID, 
            String diagnosisID) throws SQLException;

    /**
     * This methods adds a file to a visit by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param visitFileID the String to be assigned as the visit file ID
     * @param visitID the String to be assigned as the visit ID
     * @param fileID the String to be assigned as the file ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisitFile(String visitFileID, String visitID, String fileID) 
            throws SQLException;

    /**
     * This methods adds a study to a visit by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param visitStudyID the String to be assigned as the visit study ID
     * @param visitID the String to be assigned as the visit ID
     * @param studyID the String to be assigned as the study ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisitStudy(String visitStudyID, String visitID, String 
            studyID) throws SQLException;

    /**
     * This methods adds a system to a visit by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param visitSystemID the String to be assigned as the visit system ID
     * @param visitID the String to be assigned as the visit ID
     * @param systemID the String to be assigned as the system ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisitSystem(String visitSystemID, String visitID, String 
            systemID) throws SQLException;

    /**
     * This methods adds test results to a visit by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param visitID the String to be assigned as the visit ID
     * @param testResults1 the String to be assigned as the test results
     * @param testResults2 the String to be assigned as the test results
     * @param testResults3 the String to be assigned as the test results
     * @param testResults4 the String to be assigned as the test results
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisitTestResults(String visitID, String testResults1, String 
            testResults2, String testResults3, String testResults4) 
            throws SQLException;

    /**
     * This methods adds a treatment to a visit by calling a stored
     * procedure from within the database and passing the assigned values.
     * 
     * @param visitTreatmentID the String to be assigned as the visit treatment 
     *      ID
     * @param visitID the String to be assigned as the visit ID
     * @param treatmentID the String to be assigned as the treatment ID
     * @throws SQLException if SQL database encounters an error
     */
    public void addVisitTreatment(String visitTreatmentID, String visitID, 
            String treatmentID) throws SQLException;

    /**
     * This method connects to the medical database.
     * 
     */
    public void connectToDatabase();

    /**
     * This method determines the ID of an assistive device using the assistive
     * device name.
     * 
     * @param assisDevName the String to be assigned as the assistive device 
     *      name
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineAssistiveDeviceID(String assisDevName) 
            throws SQLException;

    /**
     * This method determines the ID of a condition using the condition name.
     * 
     * @param conditionName the String to be assigned as the condition name
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineConditionID(String conditionName) 
            throws SQLException;

    /**
     * This method determines the ID of a diagnosis using the description.
     * 
     * @param icd9Description the String to be assigned as the diagnosis 
     *      description
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineDiagnosisID(String icd9Description) 
            throws SQLException;

    /**
     * This method determines the ID of a file using the file name.
     * 
     * @param fileName the String to be assigned as the file name
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineFileID(String fileName) throws SQLException;

    /**
     * This method determines the ID of a provider using the provider name.
     * 
     * @param providerName the String to be assigned as the provider name
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineHCPID(String providerName) throws SQLException;

    /**
     * This method determines the ID of a patient using the clinic number.
     * 
     * @param clinicNum the String to be assigned as the clinic number
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determinePatientID(String clinicNum) throws SQLException;

    /**
     * This method determines the ID of a patient using the visit number.
     * 
     * @param visitNum the String to be assigned as the visit number
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determinePatientIDVisit(String visitNum) throws SQLException;

    /**
     * This method determines the ID of a procedure using the description.
     * 
     * @param icd9Description the String to be assigned as the description
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineProcedureID(String icd9Description) 
            throws SQLException;

    /**
     * This method determines the ID of a study using the type of study.
     * 
     * @param typeOfStudy the String to be assigned as the type of study
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineStudyID(String typeOfStudy) throws SQLException;

    /**
     * This method determines the ID of a system using the system used.
     * 
     * @param systemUsed the String to be assigned as the system used
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineSystemID(String systemUsed) throws SQLException;

    /**
     * This method determines the ID of a treatment using the treatment name.
     * 
     * @param treatmentName the String to be assigned as the treatment name
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineTreatmentID(String treatmentName) 
            throws SQLException;

    /**
     * This method determines the ID of a visit using the visit number.
     * 
     * @param visitNum the String to be assigned as the visit number
     * @return String returns a String containing the ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineVisitID(String visitNum) throws SQLException;

    /**
     * This method retrieves a list of assistive devices from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getADList() throws SQLException;

    /**
     * This method retrieves a list of clinic numbers from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getClinicNumberList() throws SQLException;

    /**
     * This method retrieves a list of conditions from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getConditionList() throws SQLException;

    /**
     * This method retrieves a list of diagnoses from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getDiagnosisList() throws SQLException;

    /**
     * This method retrieves a list of providers from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getHCPList() throws SQLException;

    /**
     * This method retrieves a list of procedures from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getProcedureList() throws SQLException;

    /**
     * This method retrieves a list of studies from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getStudyList() throws SQLException;

    /**
     * This method retrieves a list of systems from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getSystemList() throws SQLException;

    /**
     * This method retrieves a list of treatments from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getTreatmentList() throws SQLException;

    /**
     * This method retrieves a list of visit numbers from the database.
     * 
     * @return ArrayList returns an ArrayList containing the list
     * @throws SQLException if SQL database encounters an error
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