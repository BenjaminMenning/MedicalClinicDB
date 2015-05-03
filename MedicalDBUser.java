package MedicalClinicDB;

import java.sql.SQLException;

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
public class MedicalDBUser 
{
    
    /** 
     * This method is a main method that allows a user to run the medical clinic
     * database program
     * 
     * @throws SQLException if SQL database encounters an error
     */   
   public static void main(String[] args) throws SQLException
   {
        MedicalClinicDB medicalClinicDB = new MedicalClinicDB();
        MedicalDBUI medicalDBUI = new MedicalDBUI(medicalClinicDB);
//        medicalClinicDB.connectToDatabase();
//        String diagnosisID = medicalClinicDB.determineDiagnosisID("Typhoid fever");
//        System.out.println(diagnosisID);
   }
}