package MedicalClinicDB;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
 * This class allows a user to enter an condition for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class ConditionInputGUI
{
    // JLabel variables
    private JLabel conditionL;
        
    // String variable
    private String conditionStr = "Condition:";
    
    // JTextField varables
    private JTextField conditionTF;                                    
    
    // JButton variables
    private JButton addConditionB;
    
    // Button handler
    private addConditionButtonHandler addConditionH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel conditionP;
    private JPanel addConditionButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the condition input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public ConditionInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an condition 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing condition components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign condition label for field
        conditionL = new JLabel(conditionStr, SwingConstants.LEFT);
        
        // Assign condition text field
        conditionTF = new JTextField(20);
        
        // Assign add condition button and button handler
        addConditionB = new JButton("Add Condition");
        addConditionH = new addConditionButtonHandler();
        addConditionB.addActionListener(addConditionH);
        
        // Assign panel for add button
        addConditionButtonP = new JPanel();
        addConditionButtonP.add(addConditionB);
        
        // Assign condition panel components
        conditionP = new JPanel();
        conditionP.add(conditionL);
        conditionP.add(conditionTF);
        
        // Add condition panels to base panel and return final panel
        basePanel.add(conditionP);
        basePanel.add(addConditionButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        conditionTF.setText("");
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
            String condition = conditionTF.getText();
            try 
            {
                medicalClinicDB.addCondition(null, condition);
                clearFields();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(VisitTRInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}