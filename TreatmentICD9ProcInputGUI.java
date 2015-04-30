package MedicalClinicDB;

import java.awt.Dimension;
import java.awt.GridLayout;
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
 * This class allows a user to enter an procedure for a treatment.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class TreatmentICD9ProcInputGUI
{
    // JLabel variables
    private JLabel treatmentL;
    private JLabel procedureL;
        
    // String variable
    private String treatmentStr = "Treatment:";
    private String procedureStr = "ICD9 Procedure:";
    private String invalidEntryStr =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty and "
            + "values entered must match those that are listed. Please "
            + "try again.</p></body></html>";
    private String validEntryStr0;
    private String validEntryStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    private String validEntryStr2
            = "' has been added successfully.</p></body></html>";

    private ArrayList<String> treatmentList;
    private ArrayList<String> procedureList;
    
    // JComboBox varables
    private JComboBox treatmentCB;                                    
    private JComboBox procedureCB;                                    
    
    // JButton variables
    private JButton addTreatmentICD9ProcB;
    
    // Button handler
    private addTreatmentICD9ProcButtonHandler addTreatmentICD9ProcH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel treatmentP;
    private JPanel procedureP;
    private JPanel addTreatmentICD9ProcButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the procedure input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public TreatmentICD9ProcInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
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
        treatmentL = new JLabel(treatmentStr, SwingConstants.LEFT);
        procedureL = new JLabel(procedureStr, SwingConstants.LEFT);
        
        // Assign treatment and procedure lists from database
        treatmentList = medicalClinicDB.getTreatmentList();
        procedureList = medicalClinicDB.getProcedureList();
        
        // Assign procedure text field
        treatmentCB = new JComboBox(treatmentList.toArray());
        procedureCB = new JComboBox(procedureList.toArray());
        treatmentCB.setEditable(true);
        procedureCB.setEditable(true);
        procedureCB.setPreferredSize(new Dimension(350, 25));
        AutoCompleteDecorator.decorate(treatmentCB);
        AutoCompleteDecorator.decorate(procedureCB);
        
        // Assign add procedure button and button handler
        addTreatmentICD9ProcB = new JButton("Add Procedure to Treatment");
        addTreatmentICD9ProcH = new addTreatmentICD9ProcButtonHandler();
        addTreatmentICD9ProcB.addActionListener(addTreatmentICD9ProcH);
        
        // Assign panel for add button
        addTreatmentICD9ProcButtonP = new JPanel();
        addTreatmentICD9ProcButtonP.add(addTreatmentICD9ProcB);
        
        // Assign procedure panel components
        treatmentP = new JPanel();
        treatmentP.add(treatmentL);
        treatmentP.add(treatmentCB);
        procedureP = new JPanel();
        procedureP.add(procedureL);
        procedureP.add(procedureCB);
        
        // Add procedure panels to base panel and return final panel
        basePanel.add(treatmentP);
        basePanel.add(procedureP);
        basePanel.add(addTreatmentICD9ProcButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        treatmentCB.setSelectedItem("");
        procedureCB.setSelectedItem("");
    }
        
    /** 
     * This class performs the action of adding an procedure by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addTreatmentICD9ProcButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String treatment = treatmentCB.getSelectedItem().toString();
            String procedure = procedureCB.getSelectedItem().toString();
            try 
            {
                medicalClinicDB.isFieldEmpty(treatment);
                medicalClinicDB.isFieldEmpty(procedure);
                String treatmentID = medicalClinicDB.determineTreatmentID
                    (treatment);
                String procedureID = medicalClinicDB.determineProcedureID
                    (procedure);
                medicalClinicDB.addTreatmentICD9Procedure(null, treatmentID, 
                        procedureID);
                clearFields();
                validEntryStr0 = validEntryStr1 + treatment + 
                        validEntryStr2;
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, invalidEntryStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, invalidEntryStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
                Logger.getLogger(TreatmentICD9ProcInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}