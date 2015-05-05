package MedicalClinicDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
 * This class extends the VisitMiscInputGUI class and allows a user to 
 * enter an test results for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class VisitTRInputGUI extends VisitMiscInputGUI
{
    // JLabel variables
    private JLabel testResults1L;
    private JLabel testResults2L;
    private JLabel testResults3L;
    private JLabel testResults4L;
        
    // String and ArrayList of string variables for studies
    private String testResults1Str = "Test Results 1:";
    private String testResults2Str = "Test Results 2:";
    private String testResults3Str = "Test Results 3:";
    private String testResults4Str = "Test Results 4:";
    private String invalidEntryStr0 =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. At least one test result field "
            + "must not be empty and values entered for the clinic number must "
            + "match those that are listed. Please try again."
            + "</p></body></html>";
    private String validEntryStr0 =  "<html><body><p style='width: "
            + "200px;'>Test results have been successfully added to the visit."
            + "</p></body></html>";
    
    // JTextField varables
    private JTextField testResults1TF;                                    
    private JTextField testResults2TF;                                    
    private JTextField testResults3TF;                                    
    private JTextField testResults4TF;                                    
    
    // JButton variables
    private JButton addTestResultsB;
    
    // Button handler
    private addTestResultsButtonHandler addTestResultsH;
                                
    // JPanel variables
    private JPanel testResults1P;
    private JPanel testResults2P;
    private JPanel testResults3P;
    private JPanel testResults4P;
    private JPanel addTestResultsButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the test results input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitTRInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an test results 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing test results components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign test results label for field
        testResults1L = new JLabel(testResults1Str, SwingConstants.LEFT);
        testResults2L = new JLabel(testResults2Str, SwingConstants.LEFT);
        testResults3L = new JLabel(testResults3Str, SwingConstants.LEFT);
        testResults4L = new JLabel(testResults4Str, SwingConstants.LEFT);
        
        // Assign test results combo box information and adds list 
        // information
        testResults1TF = new JTextField(20);
        testResults2TF = new JTextField(20);
        testResults3TF = new JTextField(20);
        testResults4TF = new JTextField(20);
        
        // Assign add test results button and button handler
        addTestResultsB = new JButton("Add Visit Test Results");
        addTestResultsH = new addTestResultsButtonHandler();
        addTestResultsB.addActionListener(addTestResultsH);
        
        // Assign panel for add button
        addTestResultsButtonP = new JPanel();
        addTestResultsButtonP.add(addTestResultsB);
        
        // Assign test results panel components
        testResults1P = new JPanel();
        testResults2P = new JPanel();
        testResults3P = new JPanel();
        testResults4P = new JPanel();
        testResults1P.add(testResults1L);
        testResults1P.add(testResults1TF);
        testResults2P.add(testResults2L);
        testResults2P.add(testResults2TF);
        testResults3P.add(testResults3L);
        testResults3P.add(testResults3TF);
        testResults4P.add(testResults4L);
        testResults4P.add(testResults4TF);
        
        // Add test results panels to base panel and return final panel
        basePanel.add(testResults1P);
        basePanel.add(testResults2P);
        basePanel.add(testResults3P);
        basePanel.add(testResults4P);
        basePanel.add(addTestResultsButtonP);
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
        testResults1TF.setText("");
        testResults2TF.setText("");
        testResults3TF.setText("");
        testResults4TF.setText("");
    }
    
    public boolean areFieldsEmpty()
    {
        int count = 0;
        if(testResults1TF.getText().equals("") == false)
        {
            count++;
        }
        if(testResults2TF.getText().equals("") == false)
        {
            count++;
        }
        if(testResults3TF.getText().equals("") == false)
        {
            count++;
        }
        if(testResults4TF.getText().equals("") == false)
        {
            count++;
        }
        if(count == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
        
    /** 
     * This class performs the action of adding an test results by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addTestResultsButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String visitNumber = visitNumberCB.getSelectedItem().toString();
            String testResults1 = testResults1TF.getText();
            String testResults2 = testResults2TF.getText();
            String testResults3 = testResults3TF.getText();
            String testResults4 = testResults4TF.getText();
            try 
            {
                if(areFieldsEmpty())
                {
                    throw new IllegalArgumentException();
                }
                String visitID = medicalClinicDB.determineVisitID(visitNumber);
                medicalClinicDB.addVisitTestResults(visitID, testResults1, 
                        testResults2, testResults3, testResults4);
                clearFields();
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, invalidEntryStr0, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, invalidEntryStr0, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
//                Logger.getLogger(VisitTRInputGUI.class.getName()).
//                        log(Level.SEVERE, null, ex);
            }
        }
    }
}