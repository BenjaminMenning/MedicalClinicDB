package MedicalClinicDB;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
 * enter an diagnosis for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class VisitDiagnosisInputGUI extends VisitMiscInputGUI
{
    // JLabel variables
    private JLabel diagnosisL;
        
    // String and ArrayList of string variables for studies
    private String diagnosisStr = "Diagnosis:";
    private ArrayList<String> diagnosisList;
    
    // JTextField varables
    private JComboBox diagnosisCB;                                    
    
    // JButton variables
    private JButton addDiagnosisB;
    
    // Button handler
    private addDiagnosisButtonHandler addDiagnosisH;
                                
    // JPanel variables
    private JPanel diagnosisP;
    private JPanel addDiagnosisButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the diagnosis input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitDiagnosisInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an diagnosis 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing diagnosis components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign diagnosis label for field
        diagnosisL = new JLabel(diagnosisStr, SwingConstants.LEFT);

        // Assign diagnosis list from database
        diagnosisList = medicalClinicDB.getDiagnosisList();
        
        // Assign diagnosis combo box information and adds list 
        // information
        diagnosisCB = new JComboBox(diagnosisList.toArray());
        diagnosisCB.setEditable(true);
        diagnosisCB.setPreferredSize(new Dimension(350, 25));
        AutoCompleteDecorator.decorate(diagnosisCB);
        
        // Assign add diagnosis button and button handler
        addDiagnosisB = new JButton("Add Visit Diagnosis");
        addDiagnosisH = new addDiagnosisButtonHandler();
        addDiagnosisB.addActionListener(addDiagnosisH);
        
        // Assign panel for add button
        addDiagnosisButtonP = new JPanel();
        addDiagnosisButtonP.add(addDiagnosisB);
        
        // Assign diagnosis panel components
        diagnosisP = new JPanel();
        diagnosisP.add(diagnosisL);
        diagnosisP.add(diagnosisCB);
        
        // Add diagnosis panels to base panel and return final panel
        basePanel.add(diagnosisP);
        basePanel.add(addDiagnosisButtonP);
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
        diagnosisCB.setSelectedItem("");
    }
        
    /** 
     * This class performs the action of adding an diagnosis by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addDiagnosisButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String visitNumber = visitNumberCB.getSelectedItem().toString();
            String diagnosis = diagnosisCB.getSelectedItem().toString();
            try 
            {
                String visitID = medicalClinicDB.determineVisitID(
                        visitNumber);
                String diagnosisID = medicalClinicDB.
                        determineDiagnosisID(diagnosis);
                medicalClinicDB.addVisitDiagnosis(null, 
                        visitID, diagnosisID);
                clearFields();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(VisitDiagnosisInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}