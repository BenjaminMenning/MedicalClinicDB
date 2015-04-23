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
 * This class is an abstract class that extends various visit input panels. It
 * contains common components across all visit input panels including visit
 * number input components.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public abstract class VisitMiscInputGUI 
{
    // JLabel variables
    protected JLabel visitNumberL;
        
    // String and ArrayList of string variables for visit numbers
    protected String visitNumberStr = "Visit Number:";
    protected ArrayList<String> visitNumberList;
        
    // JTextField varables
    protected JComboBox visitNumberCB;
                                        
    // JPanel variables
    protected JPanel visitNumberP;
    protected JPanel basePanel;
    
    // Declares medical visit database object
    protected MedicalClinicDB medicalClinicDB;
    
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the visit input GUI panels.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitMiscInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for a visit input 
     * panel.
     * 
     * @return JPanel   returns the JPanel containing visit number components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 

        // Initialize and assign visit number list and labels
        visitNumberList = medicalClinicDB.getVisitNumberList();
        visitNumberL = new JLabel(visitNumberStr, SwingConstants.LEFT);

        // Assign visit number combo box information and adds list information
        visitNumberCB = new JComboBox(visitNumberList.toArray());
        visitNumberCB.setEditable(true);
        AutoCompleteDecorator.decorate(visitNumberCB);
        
        // Assign visit number panel components and add to base panel
        visitNumberP = new JPanel();
        visitNumberP.add(visitNumberL);
        visitNumberP.add(visitNumberCB);
        basePanel.add(visitNumberP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        visitNumberCB.setSelectedItem("");
    }
}