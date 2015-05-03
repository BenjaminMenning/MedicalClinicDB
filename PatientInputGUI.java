package MedicalClinicDB;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
 * This class allows a user to enter information about a patient.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class PatientInputGUI 
{
    // JLabel variables
    private JLabel patientIDL;
    private JLabel clinicNumberL;
    private JLabel firstNameL, middleNameL, lastNameL;                                    
    private JLabel genderL;
    private JLabel birthDateL;
    private JLabel heightL;
    private JLabel weightL;
    
    // String variables for JLabels
    private String patientIDStr = "Patient ID:";
    private String clinicNumberStr = "Clinic Number(X-XXX-XXX):";
    private String firstNameStr = "First Name:";
    private String middleNameStr = "Middle Name:";
    private String lastNameStr = "Last Name:";                                    
    private String genderStr = "Gender:";
    private String birthDateStr = "Birth Date(YYYY-MM-DD):";
    private String heightStr = "Height(kg):";
    private String weightStr = "Weight(cm):";
    
    // Arrays of string variables for JComboBoxes
    private String[] genderStrings = {"M", "F", "T", "O"};
    private String[] addPatientStrings = {"Add Patient", "Add Patient Condition"
            , "Add Patient Healthcare Provider", "Add Patient Assistive Device"
            };
    
    // String variables for error messages
    private String errorStr = "";
    private String emptyFieldsStr =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty. "
            + "Please try again.</p></body></html>";
    private String invalidClinNumStr =  "<html><body><p style='width: "
            + "200px;'>Invalid clinic number entered. Clinic number must "
            + "follow the format 'X-XXX-XXX'. Please try again."
            + "</p></body></html>";
    private String invalidBirthDateStr =  "<html><body><p style='width: "
            + "200px;'>Invalid birth date entered. Birth date must "
            + "follow the format 'YYYY-MM-DD'. Please try again."
            + "</p></body></html>";
    private String invalidSizeStr =  "<html><body><p style='width: "
            + "200px;'>Invalid height and/or weight entered. Height and weight "
            + "must be whole numbers and only three digits long. Please try "
            + "again.</p></body></html>";
    private String validEntryStr0 = "<html><body><p style='width: "
            + "200px;'>Patient has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField patientIDTF;
    private JTextField clinicNumberTF;
    private JTextField firstNameTF, middleNameTF, lastNameTF;                                    
    private JTextField genderTF;
    private JTextField birthDateTF;
    private JTextField heightTF;
    private JTextField weightTF;
    
    // JButton variables
    private JButton addPatientB;
    private JButton searchPatientB;
    
    // JComboBox variables
    private JComboBox genderCB = new JComboBox(genderStrings);
    private JComboBox addPatientList = new JComboBox(addPatientStrings);
    
    // JButton Handlers
    private addPatientButtonHandler addPatientH;
                        
    //JPanel variables
    private JPanel patientIDP;
    private JPanel clinicNumberP;
    private JPanel firstNameP, middleNameP, lastNameP;                                    
    private JPanel genderP;
    private JPanel birthDateP;
    private JPanel heightP;
    private JPanel weightP;
    private JPanel addPatientButtonP;
        
    // Medical Clinic database object
    private MedicalClinicDB medicalClinicDB;
    
    /**
     * This method creates and retrieves the components for a patient 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing patient components
     */
    public JPanel createPatientInputPanel()
    {
        // Declare overall JPanel and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1)); 
        
        // Initialize JLabels
        patientIDL = new JLabel(patientIDStr, SwingConstants.LEFT);
        clinicNumberL = new JLabel(clinicNumberStr, SwingConstants.LEFT);
        firstNameL = new JLabel(firstNameStr, SwingConstants.LEFT);
        middleNameL = new JLabel(middleNameStr, SwingConstants.LEFT); 
        lastNameL = new JLabel(lastNameStr, SwingConstants.LEFT);                               
        genderL = new JLabel(genderStr, SwingConstants.LEFT);
        birthDateL = new JLabel(birthDateStr, SwingConstants.LEFT);
        heightL = new JLabel(heightStr, SwingConstants.LEFT);
        weightL = new JLabel(weightStr, SwingConstants.LEFT);

        // Initialize JTextFields
        patientIDTF = new JTextField(20);
        clinicNumberTF = new JTextField(20);
        firstNameTF = new JTextField(20);
        middleNameTF = new JTextField(20);
        lastNameTF = new JTextField(20);                                    
        genderTF = new JTextField(20);
        birthDateTF = new JTextField(20);
        heightTF = new JTextField(20);
        weightTF = new JTextField(20);
        
        // Initialize JButton and JButton handler
        addPatientB = new JButton("Add Patient");
        addPatientH = new addPatientButtonHandler();
        addPatientB.addActionListener(addPatientH);
        addPatientList.setSelectedIndex(0);
//        addPatientList.addActionListener();        
        addPatientButtonP = new JPanel();
        addPatientButtonP.add(addPatientB);
        
        // Set optional layout
        int rows = 1;
        int columns = 2;
        GridLayout gridLayout = new GridLayout(rows, columns);
        
        // Initialize JPanels and add JPanel components
        patientIDP = new JPanel();
//        patientIDP.setLayout(gridLayout);
        patientIDP.add(patientIDL);
        patientIDP.add(patientIDTF);
        clinicNumberP = new JPanel();
//        clinicNumberP.setLayout(gridLayout);
        clinicNumberP.add(clinicNumberL);
        clinicNumberP.add(clinicNumberTF);
        firstNameP = new JPanel();
//        firstNameP.setLayout(gridLayout);
        firstNameP.add(firstNameL);
        firstNameP.add(firstNameTF);
        middleNameP = new JPanel(); 
//        middleNameP.setLayout(gridLayout);
        middleNameP.add(middleNameL);
        middleNameP.add(middleNameTF);
        lastNameP = new JPanel();                               
//        lastNameP.setLayout(gridLayout);
        lastNameP.add(lastNameL);
        lastNameP.add(lastNameTF);
        lastNameP.add(genderL);
        lastNameP.add(genderCB);
        genderP = new JPanel();
//        genderP.setLayout(gridLayout);
//        genderP.add(genderL);
//        genderP.add(genderCB);
//        lastNameP.add(genderP);
        birthDateP = new JPanel();
//        birthDateP.setLayout(gridLayout);
        birthDateP.add(birthDateL);
        birthDateP.add(birthDateTF);
        heightP = new JPanel();
//        heightP.setLayout(gridLayout);
        heightP.add(heightL);
        heightP.add(heightTF);
        weightP = new JPanel();
//        weightP.setLayout(gridLayout);
        weightP.add(weightL);
        weightP.add(weightTF);
        
        // Nest JPanels within base JPanel
        panel.add(clinicNumberP);
        panel.add(firstNameP);
        panel.add(middleNameP);
        panel.add(lastNameP);
//        panel.add(genderP);
        panel.add(birthDateP);
        panel.add(heightP);
        panel.add(weightP);
        panel.add(addPatientButtonP);
        return panel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearAddPatientTF()
    {
        patientIDTF.setText("");
        clinicNumberTF.setText("");
        firstNameTF.setText("");
        middleNameTF.setText("");
        lastNameTF.setText("");                                    
//        genderCB.setSelectedItem("");
        birthDateTF.setText("");
        heightTF.setText("");
        weightTF.setText("");
    }
    
    /**
     * This method determines whether or not a field is a empty and throws an
     * IllegalArgumentException if it is.
     * 
     * @param field the String to be evaluated as empty or not
     */
    public void isFieldEmpty(String field)
    {
        boolean isEmpty = false;
        String fieldStr = field;
        isEmpty = fieldStr.equals("");
        if(isEmpty == true)
        {
            errorStr = emptyFieldsStr;
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * This method determines whether or not a clinic number is valid and throws
     * an IllegalArgumentException if it isn't.
     * 
     * @param clinicNum the String of the clinic number to be validated
     */
    public void validateClinicNum(String clinicNum)
    {
        String clinicNumStr = clinicNum;
        Pattern clinicNumPtn = Pattern.compile("\\d{1}-\\d{3}-\\d{3}");
        Matcher matcher = clinicNumPtn.matcher(clinicNumStr);  
        if(!matcher.matches())
        {
            errorStr = invalidClinNumStr;
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * This method determines whether or not a birth date is valid and throws
     * an ParseException if it isn't.
     * 
     * @param birthDate the String of the birth date to be validated
     * @throws java.text.ParseException if birth date is invalid
     */
    public void validateBirthDate(String birthDate) throws ParseException
    {
        String birthDateStr = birthDate;
        String dateFmtStr = "yyyy-MM-dd";
        SimpleDateFormat simpDateFmt = new SimpleDateFormat(dateFmtStr);
        try 
        {
            simpDateFmt.parse(birthDateStr);
        } 
        catch (ParseException e) 
        {
            errorStr = invalidBirthDateStr;
            throw e;
        }
    }
    
    /**
     * This method determines whether or not a height/weight is valid and throws
     * an NumberFormatException if it isn't.
     * 
     * @param size the String of the height or weight to be validated
     */
    public void validateSize(String size)
    {
        String sizeStr = size;
        try 
        {
            int sizeInt = Integer.parseInt(sizeStr);
            if(sizeStr.length() > 3)
            {
                throw new NumberFormatException();
            }
        } 
        catch (NumberFormatException e) 
        {
            errorStr = invalidSizeStr;
            throw e;
        }
    }

    /** 
     * This class performs the action of adding a patient by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class addPatientButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            errorStr = "";
            String clinicNumber = clinicNumberTF.getText();
            String firstName = firstNameTF.getText();
            String middleName = middleNameTF.getText();
            String lastName = lastNameTF.getText();
            String gender = genderCB.getSelectedItem().toString();
            String birthDate = birthDateTF.getText();
            String height = heightTF.getText();
            String weight = weightTF.getText();
            try 
            {
                isFieldEmpty(clinicNumber);
                isFieldEmpty(firstName);
                isFieldEmpty(middleName);
                isFieldEmpty(lastName);
                isFieldEmpty(birthDate);
                isFieldEmpty(height);
                isFieldEmpty(weight);
                validateClinicNum(clinicNumber);
                validateBirthDate(birthDate);
                validateSize(height);
                validateSize(weight);
                medicalClinicDB.addPatient(null, clinicNumber, firstName, 
                        middleName, lastName, gender, birthDate, height, 
                        weight);
                clearAddPatientTF();
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException | ParseException | IllegalArgumentException ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
                Logger.getLogger(PatientInputGUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
            
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database for the patient input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public PatientInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
}