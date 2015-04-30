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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class PatientInputGUI 
{
    //JLabel variables
    private JLabel patientIDL;
    private JLabel clinicNumberL;
    private JLabel firstNameL, middleNameL, lastNameL;                                    
    private JLabel genderL;
    private JLabel birthDateL;
    private JLabel heightL;
    private JLabel weightL;
    
    private JLabel patientConditionIDL;
    private JLabel conditionL;
    
    private String patientIDStr = "Patient ID:";
    private String clinicNumberStr = "Clinic Number(X-XXX-XXX):";
    private String firstNameStr = "First Name:";
    private String middleNameStr = "Middle Name:";
    private String lastNameStr = "Last Name:";                                    
    private String genderStr = "Gender:";
    private String birthDateStr = "Birth Date(YYYY-MM-DD):";
    private String heightStr = "Height(kg):";
    private String weightStr = "Weight(cm):";
    
    private String[] genderStrings = {"M", "F", "T", "O"};
    private String[] addPatientStrings = {"Add Patient", "Add Patient Condition"
            , "Add Patient Healthcare Provider", "Add Patient Assistive Device"
            };
    
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
    
    //JTextField varables
    private JTextField patientIDTF;
    private JTextField clinicNumberTF;
    private JTextField firstNameTF, middleNameTF, lastNameTF;                                    
    private JTextField genderTF;
    private JTextField birthDateTF;
    private JTextField heightTF;
    private JTextField weightTF;
    
    //JButton variables
    private JButton addPatientB;
    private JButton searchPatientB;
    
    private JComboBox genderCB = new JComboBox(genderStrings);
    private JComboBox addPatientList = new JComboBox(addPatientStrings);
    
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
        
    private MedicalClinicDB medicalClinicDB;
    
    public JPanel createPatientInputPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1)); 
        
        patientIDL = new JLabel(patientIDStr, SwingConstants.LEFT);
        clinicNumberL = new JLabel(clinicNumberStr, SwingConstants.LEFT);
        firstNameL = new JLabel(firstNameStr, SwingConstants.LEFT);
        middleNameL = new JLabel(middleNameStr, SwingConstants.LEFT); 
        lastNameL = new JLabel(lastNameStr, SwingConstants.LEFT);                               
        genderL = new JLabel(genderStr, SwingConstants.LEFT);
        birthDateL = new JLabel(birthDateStr, SwingConstants.LEFT);
        heightL = new JLabel(heightStr, SwingConstants.LEFT);
        weightL = new JLabel(weightStr, SwingConstants.LEFT);

        patientIDTF = new JTextField(20);
        clinicNumberTF = new JTextField(20);
        firstNameTF = new JTextField(20);
        middleNameTF = new JTextField(20);
        lastNameTF = new JTextField(20);                                    
        genderTF = new JTextField(20);
        birthDateTF = new JTextField(20);
        heightTF = new JTextField(20);
        weightTF = new JTextField(20);
        
        addPatientB = new JButton("Add Patient");
        addPatientH = new addPatientButtonHandler();
        addPatientB.addActionListener(addPatientH);
        addPatientList.setSelectedIndex(0);
//        addPatientList.addActionListener();        
        addPatientButtonP = new JPanel();
        addPatientButtonP.add(addPatientB);
        
        int rows = 1;
        int columns = 2;
        GridLayout gridLayout = new GridLayout(rows, columns);
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
//            catch (IllegalArgumentException ex)
//            {
//                
//            }
        }
    }
            
    public PatientInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    public static void main(String[] argv) throws SQLException, ParseException 
    {
        MedicalClinicDB medicalClinicDB = new MedicalClinicDB();
        PatientInputGUI patientInputGUI = new PatientInputGUI(medicalClinicDB);
        patientInputGUI.validateClinicNum("4-123-456");
        patientInputGUI.validateBirthDate("1920-11-12");
        patientInputGUI.validateSize("4000");
    }
}