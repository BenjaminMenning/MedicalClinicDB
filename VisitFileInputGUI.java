package MedicalClinicDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
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
 * enter an file for a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class VisitFileInputGUI extends VisitMiscInputGUI
{
    // JLabel variables
    private JLabel fileL;
        
    // String and ArrayList of string variables for studies
    private String fileStr = "File Name:";
    private ArrayList<String> fileList;
    
    // JTextField varables
    private JTextField fileTF;                                    
    
    // JButton variables
    private JButton addFileB;
    
    // Button handler
    private addFileButtonHandler addFileH;
                                
    // JPanel variables
    private JPanel fileP;
    private JPanel addFileButtonP;
        
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the file input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public VisitFileInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    /**
     * This method creates and retrieves the components for an file 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing file components
     * @throws SQLException if SQL database encounters an error
     */
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        // Call superclass
        super.createInputPanel();

        // Assign file label for field
        fileL = new JLabel(fileStr, SwingConstants.LEFT);
        
        // Assign file combo box information and adds list 
        // information
        fileTF = new JTextField(30);
        fileTF.setEditable(true);
        
        // Assign add file button and button handler
        addFileB = new JButton("Add Visit File");
        addFileH = new addFileButtonHandler();
        addFileB.addActionListener(addFileH);
        
        // Assign panel for add button
        addFileButtonP = new JPanel();
        addFileButtonP.add(addFileB);
        
        // Assign file panel components
        fileP = new JPanel();
        fileP.add(fileL);
        fileP.add(fileTF);
        
        // Add file panels to base panel and return final panel
        basePanel.add(fileP);
        basePanel.add(addFileButtonP);
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
        fileTF.setText("");
    }
        
    /** 
     * This class performs the action of adding an file by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addFileButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String visitNumber = visitNumberCB.getSelectedItem().toString();
            String file = fileTF.getText().toString();
            try 
            {
                String visitID = medicalClinicDB.determineVisitID(visitNumber);
                String patientID = medicalClinicDB.determinePatientIDVisit
                        (visitNumber);
                medicalClinicDB.addFile(null, file, patientID);
                String fileID = medicalClinicDB.determineFileID(file);
                medicalClinicDB.addVisitFile(null, 
                        visitID, fileID);
                clearFields();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(VisitFileInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}