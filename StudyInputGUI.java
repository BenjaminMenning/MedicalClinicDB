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
 * This class allows a user to enter an study for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class StudyInputGUI
{
    // JLabel variables
    private JLabel studyL;
        
    // String variable
    private String studyStr = "Study:";
    private String invalidStudyStr =  "<html><body><p style='width: "
            + "200px;'>Invalid study entered. Study field cannot be"
            + " empty. Please enter a valid study.</p></body></html>";
    private String validStudyStr0;
    private String validStudyStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    private String validStudyStr2
            = "' has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField studyTF;                                    
    
    // JButton variables
    private JButton addStudyB;
    
    // Button handler
    private addStudyButtonHandler addStudyH;
                                
    // JPanel variables
    private JPanel basePanel;
    private JPanel studyP;
    private JPanel addStudyButtonP;
    
    // Declares medical clinic database object
    protected MedicalClinicDB medicalClinicDB;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the study input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public StudyInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    /**
     * This method creates and retrieves the components for an study 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing study components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 
        
        // Assign study label for field
        studyL = new JLabel(studyStr, SwingConstants.LEFT);
        
        // Assign study text field
        studyTF = new JTextField(20);
        
        // Assign add study button and button handler
        addStudyB = new JButton("Add Study");
        addStudyH = new addStudyButtonHandler();
        addStudyB.addActionListener(addStudyH);
        
        // Assign panel for add button
        addStudyButtonP = new JPanel();
        addStudyButtonP.add(addStudyB);
        
        // Assign study panel components
        studyP = new JPanel();
        studyP.add(studyL);
        studyP.add(studyTF);
        
        // Add study panels to base panel and return final panel
        basePanel.add(studyP);
        basePanel.add(addStudyButtonP);
        return basePanel;
    }

    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        studyTF.setText("");
    }
    
    /** 
     * This class performs the action of adding an study by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addStudyButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String study = studyTF.getText();
            try 
            {
                medicalClinicDB.isFieldEmpty(study);
                medicalClinicDB.addStudy(null, study);
                clearFields();
                validStudyStr0 = validStudyStr1 + study + 
                        validStudyStr2;
                JOptionPane.showMessageDialog(null, validStudyStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showMessageDialog(null, invalidStudyStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(StudyInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}