package MedicalClinicDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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
 * enter an healthcareProvider for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class PatientHCPInputGUI extends PatientMiscInputGUI
{
    // JLabel variables
    protected JLabel healthcareProviderL;
    protected JLabel providerTypeL;
        
    // String and ArrayList of string variables for healthcareProviders
    protected String healthcareProviderStr = "Healthcare Provider:";
    private String providerTypeStr = "Type:";
    private String[] providerTypesStr = new String[]{"Primary", "Secondary"};
    protected ArrayList<String> healthcareProviderList;
    
    // JTextField varables
    protected JComboBox healthcareProviderCB;                                    
    private JComboBox providerTypeCB;                                    
    
    // JButton variables
    private JButton addHealthcareProviderB;
    
    // Button handler
    private addHealthcareProviderButtonHandler addHealthcareProviderH;
                                
    // JPanel variables
    protected JPanel healthcareProviderP;
    private JPanel providerTypeP;
    private JPanel addHCPButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the healthcareProvider input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public PatientHCPInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an healthcareProvider 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing healthcareProvider components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign healthcareProvider label for field
        healthcareProviderL = new JLabel(healthcareProviderStr, 
                SwingConstants.LEFT);
        providerTypeL = new JLabel(providerTypeStr, SwingConstants.LEFT);

        // Assign healthcareProvider list from database
        healthcareProviderList = medicalClinicDB.getHCPList();
        
        // Assign healthcareProvider combo box information and adds list 
        // information
        healthcareProviderCB = new JComboBox(healthcareProviderList.toArray());
        healthcareProviderCB.setEditable(true);
        AutoCompleteDecorator.decorate(healthcareProviderCB);
        
        providerTypeCB = new JComboBox(providerTypesStr);
        providerTypeCB.setEditable(false);
        
        // Assign add healthcareProvider button and button handler
        addHealthcareProviderB = new JButton("Add Patient Healthcare Provider");
        addHealthcareProviderH = new addHealthcareProviderButtonHandler();
        addHealthcareProviderB.addActionListener(addHealthcareProviderH);
        
        // Assign panel for add button
        addHCPButtonP = new JPanel();
        addHCPButtonP.add(addHealthcareProviderB);
        
        // Assign healthcareProvider panel components
        healthcareProviderP = new JPanel();
        healthcareProviderP.add(healthcareProviderL);
        healthcareProviderP.add(healthcareProviderCB);
        providerTypeP = new JPanel();
        providerTypeP.add(providerTypeL);
        providerTypeP.add(providerTypeCB);
        
        // Add healthcareProvider panels to base panel and return final panel
        basePanel.add(healthcareProviderP);
        basePanel.add(providerTypeP);
        basePanel.add(addHCPButtonP);
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
        healthcareProviderCB.setSelectedItem("");
        providerTypeCB.setSelectedItem("");
    }
        
    /** 
     * This class performs the action of adding an healthcareProvider by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addHealthcareProviderButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String clinicNumber = clinicNumberCB.getSelectedItem().toString();
            String healthcareProvider = healthcareProviderCB.getSelectedItem().
                    toString();
            String providerType = providerTypeCB.getSelectedItem().toString();
            try 
            {
                medicalClinicDB.isFieldEmpty(clinicNumber);
                medicalClinicDB.isFieldEmpty(healthcareProvider);
                String patientID = medicalClinicDB.determinePatientID(
                        clinicNumber);
                String healthcareProviderID = medicalClinicDB.
                        determineHCPID(healthcareProvider);
                medicalClinicDB.addPatientHealthcareProvider(null, 
                        patientID, healthcareProviderID, providerType);
                clearFields();
                validEntryStr0 = validEntryStr1 + healthcareProvider + 
                        validEntryStr2;
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (IllegalArgumentException | NoSuchElementException ex)
            {
                JOptionPane.showMessageDialog(null, invalidEntryStr0, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, invalidEntryStr0, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
                Logger.getLogger(PatientHCPInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}