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
 * This class allows a user to enter an provider for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class ProviderInputGUI
{
    // JLabel variables
    private JLabel firstNameL;
    private JLabel middleNameL;
    private JLabel lastNameL;
        
    // String variable
    private String firstNameStr = "First Name:";
    private String middleNameStr = "Middle Name:";
    private String lastNameStr = "Last Name:";
    
    // JTextField varables
    private JTextField firstNameTF;                                    
    private JTextField middleNameTF;                                    
    private JTextField lastNameTF;                                    
    
    // JButton variables
    private JButton addProviderB;
    
    // Button handler
    private addProviderButtonHandler addProviderH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel firstNameP;
    private JPanel middleNameP;
    private JPanel lastNameP;
    private JPanel addProviderButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the provider input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public ProviderInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an provider 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing provider components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign name label for field
        firstNameL = new JLabel(firstNameStr, SwingConstants.LEFT);
        middleNameL = new JLabel(middleNameStr, SwingConstants.LEFT);
        lastNameL = new JLabel(lastNameStr, SwingConstants.LEFT);
        
        // Assign name text field
        firstNameTF = new JTextField(20);
        middleNameTF = new JTextField(20);
        lastNameTF = new JTextField(20);
        
        // Assign add name button and button handler
        addProviderB = new JButton("Add Provider");
        addProviderH = new addProviderButtonHandler();
        addProviderB.addActionListener(addProviderH);
        
        // Assign panel for add button
        addProviderButtonP = new JPanel();
        addProviderButtonP.add(addProviderB);
        
        // Assign name panel components
        firstNameP = new JPanel();
        firstNameP.add(firstNameL);
        firstNameP.add(firstNameTF);
        middleNameP = new JPanel();
        middleNameP.add(middleNameL);
        middleNameP.add(middleNameTF);
        lastNameP = new JPanel();
        lastNameP.add(lastNameL);
        lastNameP.add(lastNameTF);
        
        // Add name panels to base panel and return final panel
        basePanel.add(firstNameP);
        basePanel.add(middleNameP);
        basePanel.add(lastNameP);
        basePanel.add(addProviderButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        firstNameTF.setText("");
        middleNameTF.setText("");
        lastNameTF.setText("");
    }
        
    /** 
     * This class performs the action of adding an provider by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addProviderButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String firstName = firstNameTF.getText();
            String middleName = middleNameTF.getText();
            String lastName = lastNameTF.getText();
            try 
            {
                medicalClinicDB.addHealthcareProvider(null, firstName, 
                        middleName, lastName);
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