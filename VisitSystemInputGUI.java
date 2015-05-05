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
import javax.swing.JOptionPane;
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
 * This class extends the VisitMiscInputGUI class and allows a user to 
 * enter an system for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class VisitSystemInputGUI extends VisitMiscInputGUI
{
    // JLabel variables
    private JLabel systemL;
        
    // String and ArrayList of string variables for studies
    private String systemStr = "System:";
    private ArrayList<String> systemList;
    
    // JTextField varables
    private JComboBox systemCB;                                    
    
    // JButton variables
    private JButton addSystemB;
    
    // Button handler
    private addSystemButtonHandler addSystemH;
                                
    // JPanel variables
    private JPanel systemP;
    private JPanel addSystemButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the system input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitSystemInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an system 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing system components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign system label for field
        systemL = new JLabel(systemStr, SwingConstants.LEFT);

        // Assign system list from database
        systemList = medicalClinicDB.getSystemList();
        
        // Assign system combo box information and adds list 
        // information
        systemCB = new JComboBox(systemList.toArray());
        systemCB.setEditable(true);
        AutoCompleteDecorator.decorate(systemCB);
        
        // Assign add system button and button handler
        addSystemB = new JButton("Add Visit System");
        addSystemH = new addSystemButtonHandler();
        addSystemB.addActionListener(addSystemH);
        
        // Assign panel for add button
        addSystemButtonP = new JPanel();
        addSystemButtonP.add(addSystemB);
        
        // Assign system panel components
        systemP = new JPanel();
        systemP.add(systemL);
        systemP.add(systemCB);
        
        // Add system panels to base panel and return final panel
        basePanel.add(systemP);
        basePanel.add(addSystemButtonP);
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
        systemCB.setSelectedItem("");
    }
    
    /**
     * This method updates the list of systems.
     * 
     * @param newSystem   the system to be assigned
     * @throws SQLException if SQL database encounters an error
     */
    public void updateSystemList(String newSystem) throws SQLException
    {
        systemCB.addItem(newSystem);
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
            String visitNumber = visitNumberCB.getSelectedItem().toString();
            String system = systemCB.getSelectedItem().toString();
            try 
            {
                medicalClinicDB.isFieldEmpty(visitNumber);
                medicalClinicDB.isFieldEmpty(system);
                String visitID = medicalClinicDB.determineVisitID(
                        visitNumber);
                String systemID = medicalClinicDB.
                        determineSystemID(system);
                medicalClinicDB.addVisitSystem(null, 
                        visitID, systemID);
                clearFields();
                validEntryStr0 = validEntryStr1 + system + 
                        validEntryStr2;
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
//                Logger.getLogger(VisitSystemInputGUI.class.getName()).
//                        log(Level.SEVERE, null, ex);
            }
        }
    }
}