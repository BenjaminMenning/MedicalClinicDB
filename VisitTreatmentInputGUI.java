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
 * enter an treatment for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class VisitTreatmentInputGUI extends VisitMiscInputGUI
{
    // JLabel variables
    private JLabel treatmentL;
        
    // String and ArrayList of string variables for studies
    private String treatmentStr = "Treatment:";
    private ArrayList<String> treatmentList;
    
    // JTextField varables
    private JComboBox treatmentCB;                                    
    
    // JButton variables
    private JButton addTreatmentB;
    
    // Button handler
    private addTreatmentButtonHandler addTreatmentH;
                                
    // JPanel variables
    private JPanel treatmentP;
    private JPanel addTreatmentButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the treatment input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitTreatmentInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an treatment 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing treatment components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign treatment label for field
        treatmentL = new JLabel(treatmentStr, SwingConstants.LEFT);

        // Assign treatment list from database
        treatmentList = medicalClinicDB.getTreatmentList();
        
        // Assign treatment combo box information and adds list 
        // information
        treatmentCB = new JComboBox(treatmentList.toArray());
        treatmentCB.setEditable(true);
        AutoCompleteDecorator.decorate(treatmentCB);
        
        // Assign add treatment button and button handler
        addTreatmentB = new JButton("Add Visit Treatment");
        addTreatmentH = new addTreatmentButtonHandler();
        addTreatmentB.addActionListener(addTreatmentH);
        
        // Assign panel for add button
        addTreatmentButtonP = new JPanel();
        addTreatmentButtonP.add(addTreatmentB);
        
        // Assign treatment panel components
        treatmentP = new JPanel();
        treatmentP.add(treatmentL);
        treatmentP.add(treatmentCB);
        
        // Add treatment panels to base panel and return final panel
        basePanel.add(treatmentP);
        basePanel.add(addTreatmentButtonP);
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
        treatmentCB.setSelectedItem("");
    }
        
    /** 
     * This class performs the action of adding an treatment by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addTreatmentButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String visitNumber = visitNumberCB.getSelectedItem().toString();
            String treatment = treatmentCB.getSelectedItem().toString();
            try 
            {
                medicalClinicDB.isFieldEmpty(visitNumber);
                medicalClinicDB.isFieldEmpty(treatment);
                String visitID = medicalClinicDB.determineVisitID(
                        visitNumber);
                String treatmentID = medicalClinicDB.
                        determineTreatmentID(treatment);
                medicalClinicDB.addVisitTreatment(null, 
                        visitID, treatmentID);
                clearFields();
                validEntryStr0 = validEntryStr1 + treatment + 
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
                Logger.getLogger(VisitTreatmentInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}