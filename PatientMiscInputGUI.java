package MedicalClinicDB;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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
 * This class is an abstract class that extends various patient input panels. It
 * contains common components across all patient input panels including clinic
 * number input components.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public abstract class PatientMiscInputGUI 
{
    // JLabel variables
    protected JLabel clinicNumberL;
        
    // String and ArrayList of string variables for clinic numbers
    protected String clinicNumberStr = "Clinic Number:";
    protected String invalidEntryStr0 =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty and "
            + "values entered must match those that are listed. Please "
            + "try again.</p></body></html>";
    protected String validEntryStr0;
    protected String validEntryStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    protected String validEntryStr2
            = "' has been added successfully to the patient.</p></body></html>";
    protected ArrayList<String> clinicNumberList;
    
        
    // JTextField varables
    protected JComboBox clinicNumberCB;
                                        
    // JPanel variables
    protected JPanel clinicNumberP;
    protected JPanel basePanel;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
    
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the patient input GUI panels.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public PatientMiscInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for a patient input 
     * panel.
     * 
     * @return JPanel   returns the JPanel containing clinic number components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 

        // Initialize and assign clinic number list and labels
        clinicNumberList = medicalClinicDB.getClinicNumberList();
        clinicNumberL = new JLabel(clinicNumberStr, SwingConstants.LEFT);

        // Assign clinic number combo box information and adds list information
        clinicNumberCB = new JComboBox(clinicNumberList.toArray());
        clinicNumberCB.setEditable(true);
        AutoCompleteDecorator.decorate(clinicNumberCB);
        
        // Assign clinic number panel components and add to base panel
        clinicNumberP = new JPanel();
        clinicNumberP.add(clinicNumberL);
        clinicNumberP.add(clinicNumberCB);
        basePanel.add(clinicNumberP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        clinicNumberCB.setSelectedItem("");
    }
}