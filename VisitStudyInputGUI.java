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
 * enter an study for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class VisitStudyInputGUI extends VisitMiscInputGUI
{
    // JLabel variables
    private JLabel studyL;
        
    // String and ArrayList of string variables for studies
    private String studyStr = "Assistive Device:";
    private ArrayList<String> studyList;
    
    // JTextField varables
    private JComboBox studyCB;                                    
    
    // JButton variables
    private JButton addStudyB;
    
    // Button handler
    private addStudyButtonHandler addStudyH;
                                
    // JPanel variables
    private JPanel studyP;
    private JPanel addADButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the study input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitStudyInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an study 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing study components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign study label for field
        studyL = new JLabel(studyStr, SwingConstants.LEFT);

        // Assign study list from database
        studyList = medicalClinicDB.getStudyList();
        
        // Assign study combo box information and adds list 
        // information
        studyCB = new JComboBox(studyList.toArray());
        studyCB.setEditable(true);
        AutoCompleteDecorator.decorate(studyCB);
        
        // Assign add asssitive button and button handler
        addStudyB = new JButton("Add Patient Assistive Device");
        addStudyH = new addStudyButtonHandler();
        addStudyB.addActionListener(addStudyH);
        
        // Assign panel for add button
        addADButtonP = new JPanel();
        addADButtonP.add(addStudyB);
        
        // Assign study panel components
        studyP = new JPanel();
        studyP.add(studyL);
        studyP.add(studyCB);
        
        // Add study panels to base panel and return final panel
        basePanel.add(studyP);
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
        studyCB.setSelectedItem("");
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
            String visitNumber = visitNumberCB.getSelectedItem().toString();
            String study = studyCB.getSelectedItem().toString();
            try 
            {
                String visitID = medicalClinicDB.determineVisitID(
                        visitNumber);
                String studyID = medicalClinicDB.
                        determineStudyID(study);
                medicalClinicDB.addVisitStudy(null, 
                        visitID, studyID);
                clearFields();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(VisitStudyInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}