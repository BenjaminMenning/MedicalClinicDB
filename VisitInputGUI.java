package MedicalClinicDB;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
public class VisitInputGUI extends PatientHCPInputGUI
{
    //Window size
    private static final int WIDTH = 450;                               
    private static final int HEIGHT = 425;                             
        
    //JLabel variables
    private JLabel visitNumberL;
    private JLabel visitDateL;                                    
    private JLabel dateAnalysisCompleteL;
    private JLabel dateProcessingCompleteL;
    
    private String visitNumberStr = "Visit Number(XXXXXXXX):";
    private String visitDateStr = "Visit Date(YYYY-MM-DD):";                                    
    private String dateAnalysisCompleteStr = "Date Analysis Complete:";
    private String dateProcessingCompleteStr = "Date Processing Complete:";
    
    private String errorStr = "";
    private String emptyFieldsStr =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty. "
            + "Please try again.</p></body></html>";
    private String invalidVisitNumStr =  "<html><body><p style='width: "
            + "200px;'>Invalid visit number entered. Visit number must "
            + "be alphanumeric and only 8 digits long. Please try again."
            + "</p></body></html>";
    private String invalidDateStr =  "<html><body><p style='width: "
            + "200px;'>Invalid visit date(s) entered. Date(s) must "
            + "follow the format 'YYYY-MM-DD'. Please try again."
            + "</p></body></html>";
    private String invalidClinicNumStr =  "<html><body><p style='width: "
            + "200px;'>Invalid clinic number entered. Clinic number entered "
            + "must match those listed. Please try again.</p></body></html>";
    private String invalidProviderStr =  "<html><body><p style='width: "
            + "200px;'>Invalid provider entered. Provider entered must match "
            + "those listed. Please try again.</p></body></html>";
    private String invalidAnlysDateStr =  "<html><body><p style='width: "
            + "200px;'>Invalid analysis date entered. Analysis date must be on "
            + "or after date of the visit. Please try again.</p></body></html>";
    private String invalidPrcsDateStr =  "<html><body><p style='width: "
            + "200px;'>Invalid processing date entered. Processing date must "
            + "be on or after date of the analysis date. Please try again."
            + "</p></body></html>";
    private String validEntryStr0 = "<html><body><p style='width: "
            + "200px;'>Visit has been added successfully.</p></body></html>";
            
    //JTextField varables
    private JTextField visitNumberTF;
    private JTextField visitDateTF;                                    
    private JTextField dateAnalysisCompleteTF;
    private JTextField dateProcessingCompleteTF;
    
    
    //JButton variables
    private JButton addVisitB;
        
    private addVisitButtonHandler addVisitH;
                        
    //JPanel variables
    private JPanel visitNumberP;
    private JPanel visitDateP;                                    
    private JPanel dateAnalysisCompleteP;
    private JPanel dateProcessingCompleteP;
    private JPanel addVisitButtonP;
        
    public JPanel createInputPanel() throws SQLException
    {
        super.createInputPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1)); 
        
        visitNumberL = new JLabel(visitNumberStr, SwingConstants.LEFT);
        visitDateL = new JLabel(visitDateStr, SwingConstants.LEFT);
        dateAnalysisCompleteL = new JLabel(dateAnalysisCompleteStr, SwingConstants.LEFT);
        dateProcessingCompleteL = new JLabel(dateProcessingCompleteStr, SwingConstants.LEFT);

        visitNumberTF = new JTextField(20);
        visitDateTF = new JTextField(20);
        dateAnalysisCompleteTF = new JTextField(20);
        dateProcessingCompleteTF = new JTextField(20);
        
        addVisitB = new JButton("Add Visit");
        addVisitH = new addVisitButtonHandler();
        addVisitB.addActionListener(addVisitH);
        addVisitButtonP = new JPanel();
        addVisitButtonP.add(addVisitB);
        
        int rows = 1;
        int columns = 2;
        GridLayout gridLayout = new GridLayout(rows, columns);
        visitNumberP = new JPanel();
//        visitNumberP.setLayout(gridLayout);
        visitNumberP.add(visitNumberL);
        visitNumberP.add(visitNumberTF);
        visitDateP = new JPanel();
//        visitDateP.setLayout(gridLayout);
        visitDateP.add(visitDateL);
        visitDateP.add(visitDateTF);
        dateAnalysisCompleteP = new JPanel();
//        dateAnalysisCompleteP.setLayout(gridLayout);
        dateAnalysisCompleteP.add(dateAnalysisCompleteL);
        dateAnalysisCompleteP.add(dateAnalysisCompleteTF);
        dateProcessingCompleteP = new JPanel();
//        dateProcessingCompleteP.setLayout(gridLayout);
        dateProcessingCompleteP.add(dateProcessingCompleteL);
        dateProcessingCompleteP.add(dateProcessingCompleteTF);
        
        panel.add(visitNumberP);
        panel.add(visitDateP);
        panel.add(clinicNumberP);
        panel.add(healthcareProviderP);
        panel.add(dateAnalysisCompleteP);
        panel.add(dateProcessingCompleteP);
        panel.add(addVisitButtonP);
        return panel;
    }
        
    public void clearFields()
    {
        super.clearFields();
        visitNumberTF.setText("");
        visitDateTF.setText("");
        dateAnalysisCompleteTF.setText("");
        dateProcessingCompleteTF.setText("");
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
    
    public void validateVisitNum(String visitNum)
    {
        String visitNumStr = visitNum;
        if(!visitNumStr.matches("[A-Za-z0-9]+") || visitNumStr.length() != 8)
        {
            errorStr = invalidVisitNumStr;
            throw new IllegalArgumentException();
        }
    }
    
    public void validateClinicNum(String clinicNum) throws SQLException
    {
        String clinicNumStr = clinicNum;
        String patientID;
        patientID = medicalClinicDB.determinePatientID(clinicNumStr);
        if(patientID.equals(""))
        {
            errorStr = invalidClinicNumStr;
            throw new IllegalArgumentException();
        }
    }
    
    public void validateProvider(String providerName) throws SQLException
    {
        String providerNameStr = providerName;
        String providerID;
        providerID = medicalClinicDB.determineHCPID(providerNameStr);
        if(providerID.equals(""))
        {
            errorStr = invalidProviderStr;
            throw new IllegalArgumentException();
        }
    }
    
    public void validateDate(String date) throws ParseException
    {
        String dateStr = date;
        String dateFmtStr = "yyyy-MM-dd";
        SimpleDateFormat simpDateFmt = new SimpleDateFormat(dateFmtStr);
        try 
        {
            simpDateFmt.parse(dateStr);
        } 
        catch (ParseException e) 
        {
            errorStr = invalidDateStr;
            throw e;
        }
    }
    
    public void validateAnalysisDate(String anlysDate) throws ParseException
    {
        String analysisDateStr = anlysDate;
        validateDate(analysisDateStr);
        String visitDateStr = visitDateTF.getText();
        String dateFmtStr = "yyyy-MM-dd";
        SimpleDateFormat simpDateFmt = new SimpleDateFormat(dateFmtStr);
        Date analysisDate = simpDateFmt.parse(analysisDateStr);
        Date visitDate = simpDateFmt.parse(visitDateStr);
        if(analysisDate.before(visitDate))
        {
            errorStr = invalidAnlysDateStr;
            throw new IllegalArgumentException();
        }
    }
    
    public void validateProcessingDate(String prcsDate) throws ParseException
    {
        String processingDateStr = prcsDate;
        validateDate(processingDateStr);
        String analysisDateStr = dateAnalysisCompleteTF.getText();
        String dateFmtStr = "yyyy-MM-dd";
        SimpleDateFormat simpDateFmt = new SimpleDateFormat(dateFmtStr);
        Date processingDate = simpDateFmt.parse(processingDateStr);
        Date analysisDate = simpDateFmt.parse(analysisDateStr);
        if(processingDate.before(analysisDate))
        {
            errorStr = invalidPrcsDateStr;
            throw new IllegalArgumentException();
        }
    }
        
    private class addVisitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String visitNumber = visitNumberTF.getText();
            String visitDate = visitDateTF.getText();
            String clinicNumber = clinicNumberCB.getSelectedItem().toString();
            String healthcareProvider = healthcareProviderCB.getSelectedItem().toString();
            String dateAnalysisComplete = dateAnalysisCompleteTF.getText();
            String dateProcessingComplete = dateProcessingCompleteTF.getText();
            try 
            {
                isFieldEmpty(visitNumber);
                isFieldEmpty(visitDate);
                isFieldEmpty(clinicNumber);
                isFieldEmpty(healthcareProvider);
                isFieldEmpty(dateAnalysisComplete);
                isFieldEmpty(dateProcessingComplete);                
                validateVisitNum(visitNumber);
                validateDate(visitDate);
                validateClinicNum(clinicNumber);
                validateProvider(healthcareProvider);
                validateAnalysisDate(dateAnalysisComplete);
                validateProcessingDate(dateProcessingComplete);
                String patientID = medicalClinicDB.determinePatientID(
                        clinicNumber);
                String healthcareProviderID = medicalClinicDB.
                        determineHCPID(healthcareProvider);
                medicalClinicDB.addVisit(null, visitNumber, visitDate, 
                        patientID, healthcareProviderID, dateAnalysisComplete, 
                        dateProcessingComplete);
                clearFields();
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException | ParseException | IllegalArgumentException ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
                Logger.getLogger(VisitInputGUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            } 
        }
    }
    
    public VisitInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    public static void main(String[] argv) throws SQLException, ParseException 
    {
        MedicalClinicDB medicalClinicDB = new MedicalClinicDB();
        medicalClinicDB.connectToDatabase();
        VisitInputGUI visitInputGUI = new VisitInputGUI(medicalClinicDB);
        visitInputGUI.validateVisitNum("ABC4EFG8");
        visitInputGUI.validateClinicNum("4-234-123");
        visitInputGUI.validateProvider("Dave WW Boldt");
    }
}