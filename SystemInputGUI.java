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
 * This class allows a user to enter an system for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class SystemInputGUI
{
    // JLabel variables
    private JLabel systemL;
        
    // String variable
    private String systemStr = "System:";
    private String invalidSystemStr =  "<html><body><p style='width: "
            + "200px;'>Invalid system entered. System field cannot be"
            + " empty. Please enter a valid system.</p></body></html>";
    private String validSystemStr0;
    private String validSystemStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    private String validSystemStr2
            = "' has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField systemTF;                                    
    
    // JButton variables
    private JButton addSystemB;
    
    // Button handler
    private addSystemButtonHandler addSystemH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel systemP;
    private JPanel addSystemButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the system input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public SystemInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an system 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing system components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign system label for field
        systemL = new JLabel(systemStr, SwingConstants.LEFT);
        
        // Assign system text field
        systemTF = new JTextField(20);
        
        // Assign add system button and button handler
        addSystemB = new JButton("Add System");
        addSystemH = new addSystemButtonHandler();
        addSystemB.addActionListener(addSystemH);
        
        // Assign panel for add button
        addSystemButtonP = new JPanel();
        addSystemButtonP.add(addSystemB);
        
        // Assign system panel components
        systemP = new JPanel();
        systemP.add(systemL);
        systemP.add(systemTF);
        
        // Add system panels to base panel and return final panel
        basePanel.add(systemP);
        basePanel.add(addSystemButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        systemTF.setText("");
    }
    
    /** 
     * This class performs the action of adding an system by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addSystemButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String system = systemTF.getText();
            try 
            {
                medicalClinicDB.isFieldEmpty(system);
                medicalClinicDB.addSystem(null, system);
                clearFields();
                validSystemStr0 = validSystemStr1 + system + 
                        validSystemStr2;
                JOptionPane.showMessageDialog(null, validSystemStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, invalidSystemStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(SystemInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}