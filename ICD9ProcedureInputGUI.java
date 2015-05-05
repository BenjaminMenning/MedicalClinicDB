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
 * This class allows a user to enter an procedure for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class ICD9ProcedureInputGUI
{
    // JLabel variables
    private JLabel icd9CodeL;
    private JLabel icd9DescriptionL;
        
    // String variable
    private String icd9CodeStr = "ICD9 Code:";
    private String icd9DescriptionStr = "ICD9 Description:";
    private String invalidProcedureStr0 =  "<html><body><p style='width: "
            + "200px;'>Invalid procedure entered. Procedure fields cannot be"
            + " empty. Please enter a valid procedure code and/or description."
            + "</p></body></html>";
    private String invalidProcedureStr1 = "<html><body><p style='width: "
            + "200px;'>Invalid procedure entered. Procedure code must be "
            + "between 3 and 5 characters. Please enter a valid procedure code."
            + "</p></body></html>";
    private String validProcedureStr0;
    private String validProcedureStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    private String validProcedureStr2
            = "' has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField icd9CodeTF;                                    
    private JTextField icd9DescriptionTF;                                    
    
    // JButton variables
    private JButton addICD9ProcedureB;
    
    // Button handler
    private addICD9ProcedureButtonHandler addICD9ProcedureH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel icd9CodeP;
    private JPanel icd9DescriptionP;
    private JPanel addICD9ProcedureButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
    private TreatmentICD9ProcInputGUI treatmentICD9ProcInputGUI;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the procedure input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public ICD9ProcedureInputGUI(MedicalClinicDB medicalClinicObj, 
            TreatmentICD9ProcInputGUI treatmentICD9ProcInputGUIObj)
    {
        medicalClinicDB = medicalClinicObj;
        treatmentICD9ProcInputGUI = treatmentICD9ProcInputGUIObj;
    }
    
    /**
     * This method creates and retrieves the components for an procedure 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing procedure components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign procedure label for field
        icd9CodeL = new JLabel(icd9CodeStr, SwingConstants.LEFT);
        icd9DescriptionL = new JLabel(icd9DescriptionStr, SwingConstants.LEFT);
        
        // Assign procedure text field
        icd9CodeTF = new JTextField(20);
        icd9DescriptionTF = new JTextField(20);
        
        // Assign add procedure button and button handler
        addICD9ProcedureB = new JButton("Add ICD9 Procedure");
        addICD9ProcedureH = new addICD9ProcedureButtonHandler();
        addICD9ProcedureB.addActionListener(addICD9ProcedureH);
        
        // Assign panel for add button
        addICD9ProcedureButtonP = new JPanel();
        addICD9ProcedureButtonP.add(addICD9ProcedureB);
        
        // Assign procedure panel components
        icd9CodeP = new JPanel();
        icd9CodeP.add(icd9CodeL);
        icd9CodeP.add(icd9CodeTF);
        icd9DescriptionP = new JPanel();
        icd9DescriptionP.add(icd9DescriptionL);
        icd9DescriptionP.add(icd9DescriptionTF);
        
        // Add procedure panels to base panel and return final panel
        basePanel.add(icd9CodeP);
        basePanel.add(icd9DescriptionP);
        basePanel.add(addICD9ProcedureButtonP);
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
     * This class performs the action of adding an procedure by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addICD9ProcedureButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String icd9Code = icd9CodeTF.getText();
            String icd9Description = icd9DescriptionTF.getText();
            try 
            {
                medicalClinicDB.isFieldEmpty(icd9Code);
                medicalClinicDB.isFieldEmpty(icd9Description);
                medicalClinicDB.addICD9Procedure(null, icd9Code, 
                        icd9Description);
                clearFields();
                validProcedureStr0 = validProcedureStr1 + icd9Description + 
                        validProcedureStr2;
                JOptionPane.showMessageDialog(null, validProcedureStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE); 
                treatmentICD9ProcInputGUI.updateProcedureList(icd9Description);
            } 
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, invalidProcedureStr0, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
//                int errorCodeNum = ex.getErrorCode();
                JOptionPane.showMessageDialog(null, invalidProcedureStr1, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
//                System.out.println(errorCodeNum);
//                Logger.getLogger(ICD9ProcedureInputGUI.class.getName()).
//                        log(Level.SEVERE, null, ex);
            }
        }
    }
}