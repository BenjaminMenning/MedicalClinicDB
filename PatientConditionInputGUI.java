package MedicalClinicDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
 *                  things, including patient conditions and conditions,
 *                  visit diagnoses and studies, as well as information about
 *                  healthcare providers or systems used. It also allows a user
 *                  to search and lookup information about patients based on a
 *                  wide variety of criteria like name, diagnoses, date of birth
 *                  and more. It also allows users to see more detailed 
 *                  information about patients and their visits.
 */

/** 
 * This class extends the PatientMiscInputGUI class and allows a user to 
 * enter an condition for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class PatientConditionInputGUI extends PatientMiscInputGUI
{
    // JLabel variables
    private JLabel conditionL;
        
    // String and ArrayList of string variables for conditions
    private String conditionStr = "Condition:";
    private ArrayList<String> conditionList;
    
    // JTextField varables
    private JComboBox conditionCB;                                    
    
    // JButton variables
    private JButton addConditionB;
    
    // Button handler
    private addConditionButtonHandler addConditionH;
                                
    // JPanel variables
    private JPanel conditionP;
    private JPanel addCondButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the condition input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public PatientConditionInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an condition 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing condition components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign condition label for field
        conditionL = new JLabel(conditionStr, SwingConstants.LEFT);

        // Assign condition list from database
        conditionList = medicalClinicDB.getConditionList();
        
        // Assign condition combo box information and adds list 
        // information
        conditionCB = new JComboBox(conditionList.toArray());
        conditionCB.setEditable(true);
        AutoCompleteDecorator.decorate(conditionCB);
        
        // Assign add condition button and button handler
        addConditionB = new JButton("Add Patient Condition");
        addConditionH = new addConditionButtonHandler();
        addConditionB.addActionListener(addConditionH);
        
        // Assign panel for add button
        addCondButtonP = new JPanel();
        addCondButtonP.add(addConditionB);
        
        // Assign condition panel components
        conditionP = new JPanel();
        conditionP.add(conditionL);
        conditionP.add(conditionCB);
        
        // Add condition panels to base panel and return final panel
        basePanel.add(conditionP);
        basePanel.add(addCondButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    @Override
    public void clearFields()
    {
        super.clearFields();
        conditionCB.setSelectedItem("");
    }
        
    /** 
     * This class performs the action of adding an condition by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addConditionButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String clinicNumber = clinicNumberCB.getSelectedItem().toString();
            String condition = conditionCB.getSelectedItem().
                    toString();
            try 
            {
                String patientID = medicalClinicDB.determinePatientID(
                        clinicNumber);
                String conditionID = medicalClinicDB.
                        determineConditionID(condition);
                medicalClinicDB.addPatientCondition(null, 
                        patientID, conditionID);
                clearFields();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(PatientConditionInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}