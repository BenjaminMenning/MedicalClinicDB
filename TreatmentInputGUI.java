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
 * This class allows a user to enter an treatment for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class TreatmentInputGUI
{
    // JLabel variables
    private JLabel treatmentL;
        
    // String variable
    private String treatmentStr = "Treatment:";
    private String invalidTreatmentStr =  "<html><body><p style='width: "
            + "200px;'>Invalid treatment entered. Treatment field cannot be"
            + " empty. Please enter a valid treatment.</p></body></html>";
    private String validTreatmentStr0;
    private String validTreatmentStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    private String validTreatmentStr2
            = "' has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField treatmentTF;                                    
    
    // JButton variables
    private JButton addTreatmentB;
    
    // Button handler
    private addTreatmentButtonHandler addTreatmentH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel treatmentP;
    private JPanel addTreatmentButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the treatment input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public TreatmentInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an treatment 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing treatment components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign treatment label for field
        treatmentL = new JLabel(treatmentStr, SwingConstants.LEFT);
        
        // Assign treatment text field
        treatmentTF = new JTextField(20);
        
        // Assign add treatment button and button handler
        addTreatmentB = new JButton("Add Treatment");
        addTreatmentH = new addTreatmentButtonHandler();
        addTreatmentB.addActionListener(addTreatmentH);
        
        // Assign panel for add button
        addTreatmentButtonP = new JPanel();
        addTreatmentButtonP.add(addTreatmentB);
        
        // Assign treatment panel components
        treatmentP = new JPanel();
        treatmentP.add(treatmentL);
        treatmentP.add(treatmentTF);
        
        // Add treatment panels to base panel and return final panel
        basePanel.add(treatmentP);
        basePanel.add(addTreatmentButtonP);
        return basePanel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        treatmentTF.setText("");
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
            String treatment = treatmentTF.getText();
            try 
            {
                medicalClinicDB.isFieldEmpty(treatment);
                medicalClinicDB.addTreatment(null, treatment);
                clearFields();
                validTreatmentStr0 = validTreatmentStr1 + treatment + 
                        validTreatmentStr2;
                JOptionPane.showMessageDialog(null, validTreatmentStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, invalidTreatmentStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(TreatmentInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}