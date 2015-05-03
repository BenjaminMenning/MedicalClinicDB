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
 * This class extends the PatientMiscInputGUI class and allows a user to 
 * enter an assistive device for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class PatientADInputGUI extends PatientMiscInputGUI
{
    // JLabel variables
    private JLabel assistiveDeviceL;
        
    // String and ArrayList of string variables for assistive devices
    private String assistiveDeviceStr = "Assistive Device:";
    private ArrayList<String> assistiveDeviceList;
    
    // JTextField varables
    private JComboBox assistiveDeviceCB;                                    
    
    // JButton variables
    private JButton addAssistiveDeviceB;
    
    // Button handler
    private addAssistiveDeviceButtonHandler addAssistiveDeviceH;
                                
    // JPanel variables
    private JPanel assistiveDeviceP;
    private JPanel addADButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the assistive device input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public PatientADInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an assistive device 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing assis. device components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign assistive device label for field
        assistiveDeviceL = new JLabel(assistiveDeviceStr, SwingConstants.LEFT);

        // Assign assistive device list from database
        assistiveDeviceList = medicalClinicDB.getADList();
        
        // Assign assistive device combo box information and adds list 
        // information
        assistiveDeviceCB = new JComboBox(assistiveDeviceList.toArray());
        assistiveDeviceCB.setEditable(true);
        AutoCompleteDecorator.decorate(assistiveDeviceCB);
        
        // Assign add asssitive button and button handler
        addAssistiveDeviceB = new JButton("Add Patient Assistive Device");
        addAssistiveDeviceH = new addAssistiveDeviceButtonHandler();
        addAssistiveDeviceB.addActionListener(addAssistiveDeviceH);
        
        // Assign panel for add button
        addADButtonP = new JPanel();
        addADButtonP.add(addAssistiveDeviceB);
        
        // Assign assistive device panel components
        assistiveDeviceP = new JPanel();
        assistiveDeviceP.add(assistiveDeviceL);
        assistiveDeviceP.add(assistiveDeviceCB);
        
        // Add assistive device panels to base panel and return final panel
        basePanel.add(assistiveDeviceP);
        basePanel.add(addADButtonP);
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
        assistiveDeviceCB.setSelectedItem("");
    }
    
    /**
     * This method updates the list of assistive devices.
     * 
     * @param newAssisDev   the assistive device to be assigned
     * @throws SQLException if SQL database encounters an error
     */
    public void updateADList(String newAssisDev) throws SQLException
    {
        assistiveDeviceCB.addItem(newAssisDev);
    }
        
    /** 
     * This class performs the action of adding an assistive device by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addAssistiveDeviceButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String clinicNumber = clinicNumberCB.getSelectedItem().toString();
            String assistiveDevice = assistiveDeviceCB.getSelectedItem().
                    toString();
            try 
            {
                medicalClinicDB.isFieldEmpty(clinicNumber);
                medicalClinicDB.isFieldEmpty(assistiveDevice);
                String patientID = medicalClinicDB.determinePatientID(
                        clinicNumber);
                String assistiveDeviceID = medicalClinicDB.
                        determineAssistiveDeviceID(assistiveDevice);
                medicalClinicDB.addPatientAssistiveDevice(null, 
                        patientID, assistiveDeviceID);
                clearFields();
                validEntryStr0 = validEntryStr1 + assistiveDevice + 
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
                Logger.getLogger(PatientADInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}