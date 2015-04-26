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
 * This class allows a user to enter an diagnosis for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class ICD9DiagnosisInputGUI
{
    // JLabel variables
    private JLabel icd9CodeL;
    private JLabel icd9DescriptionL;
        
    // String variable
    private String icd9CodeStr = "ICD9 Code:";
    private String icd9DescriptionStr = "ICD9 Description:";
    
    // JTextField varables
    private JTextField icd9CodeTF;                                    
    private JTextField icd9DescriptionTF;                                    
    
    // JButton variables
    private JButton addICD9DiagnosisB;
    
    // Button handler
    private addICD9DiagnosisButtonHandler addICD9DiagnosisH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel icd9CodeP;
    private JPanel icd9DescriptionP;
    private JPanel addICD9DiagnosisButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the diagnosis input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public ICD9DiagnosisInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an diagnosis 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing diagnosis components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign diagnosis label for field
        icd9CodeL = new JLabel(icd9CodeStr, SwingConstants.LEFT);
        icd9DescriptionL = new JLabel(icd9DescriptionStr, SwingConstants.LEFT);
        
        // Assign diagnosis text field
        icd9CodeTF = new JTextField(20);
        icd9DescriptionTF = new JTextField(20);
        
        // Assign add diagnosis button and button handler
        addICD9DiagnosisB = new JButton("Add ICD9Diagnosis");
        addICD9DiagnosisH = new addICD9DiagnosisButtonHandler();
        addICD9DiagnosisB.addActionListener(addICD9DiagnosisH);
        
        // Assign panel for add button
        addICD9DiagnosisButtonP = new JPanel();
        addICD9DiagnosisButtonP.add(addICD9DiagnosisB);
        
        // Assign diagnosis panel components
        icd9CodeP = new JPanel();
        icd9CodeP.add(icd9CodeL);
        icd9CodeP.add(icd9CodeTF);
        icd9DescriptionP = new JPanel();
        icd9DescriptionP.add(icd9DescriptionL);
        icd9DescriptionP.add(icd9DescriptionTF);
        
        // Add diagnosis panels to base panel and return final panel
        basePanel.add(icd9CodeP);
        basePanel.add(icd9DescriptionP);
        basePanel.add(addICD9DiagnosisButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        icd9CodeTF.setText("");
        icd9DescriptionTF.setText("");
    }
        
    /** 
     * This class performs the action of adding an diagnosis by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addICD9DiagnosisButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String icd9Code = icd9CodeTF.getText();
            String icd9Description = icd9DescriptionTF.getText();
            try 
            {
                medicalClinicDB.addICD9Diagnosis(null, icd9Code, 
                        icd9Description);
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