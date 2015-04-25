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
 * This class allows a user to enter an assistive device for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class ADInputGUI
{
    // JLabel variables
    private JLabel assisDevL;
        
    // String variable
    private String assisDevStr = "Assistive Device:";
    
    // JTextField varables
    private JTextField assisDevTF;                                    
    
    // JButton variables
    private JButton addAssisDevB;
    
    // Button handler
    private addAssisDevButtonHandler addAssisDevH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel assisDevP;
    private JPanel addAssisDevButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the assistive device input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public ADInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an assistive device 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing assistive device components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign assistive device label for field
        assisDevL = new JLabel(assisDevStr, SwingConstants.LEFT);
        
        // Assign assistive device text field
        assisDevTF = new JTextField(20);
        
        // Assign add assistive device button and button handler
        addAssisDevB = new JButton("Add Assistive Device");
        addAssisDevH = new addAssisDevButtonHandler();
        addAssisDevB.addActionListener(addAssisDevH);
        
        // Assign panel for add button
        addAssisDevButtonP = new JPanel();
        addAssisDevButtonP.add(addAssisDevB);
        
        // Assign assistive device panel components
        assisDevP = new JPanel();
        assisDevP.add(assisDevL);
        assisDevP.add(assisDevTF);
        
        // Add assistive device panels to base panel and return final panel
        basePanel.add(assisDevP);
        basePanel.add(addAssisDevButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        assisDevTF.setText("");
    }
        
    /** 
     * This class performs the action of adding an assistive device by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addAssisDevButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String assisDev = assisDevTF.getText();
            try 
            {
                medicalClinicDB.addAssistiveDevice(null, assisDev);
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