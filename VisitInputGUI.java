package MedicalClinicDB;


import .*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class VisitInputGUI 
{
    //Window size
    private static final int WIDTH = 450;                               
    private static final int HEIGHT = 425;                             
        
    //JLabel variables
    private JLabel visitIDL;
    private JLabel visitNumberL;
    private JLabel visitDateL;                                    
    private JLabel patientIDL;
    private JLabel healthcareProviderIDL;
    private JLabel dateAnalysisCompleteL;
    private JLabel dateProcessingCompleteL;
    
    private JLabel patientConditionIDL;
    private JLabel conditionL;
    
    private String visitIDStr = "Visit ID:";
    private String visitNumberStr = "Visit Number:";
    private String visitDateStr = "Visit Date:";                                    
    private String patientIDStr = "Patient ID:";
    private String healthcareProviderIDStr = "Healthcare Provider ID:";
    private String dateAnalysisCompleteStr = "Date Analysis Complete:";
    private String dateProcessingCompleteStr = "Date Processing Complete:";
        
    private String[] addPatientStrings = {"Add Patient", "Add Patient Condition"
            , "Add Patient Healthcare Provider", "Add Patient Assistive Device"
            };
    
    //JTextField varables
    private JTextField visitIDTF;
    private JTextField visitNumberTF;
    private JTextField visitDateTF;                                    
    private JTextField patientIDTF;
    private JTextField healthcareProviderIDTF;
    private JTextField dateAnalysisCompleteTF;
    private JTextField dateProcessingCompleteTF;
    
    
    //JButton variables
    private JButton addVisitB;
    private JButton searchPatientB;
    
    private JComboBox addPatientList = new JComboBox(addPatientStrings);
    
    private addVisitButtonHandler addVisitH;
                        
    //JPanel variables
    private JPanel visitIDP;
    private JPanel visitNumberP;
    private JPanel visitDateP;                                    
    private JPanel patientIDP;
    private JPanel healthcareProviderIDP;
    private JPanel dateAnalysisCompleteP;
    private JPanel dateProcessingCompleteP;
    private JPanel addVisitButtonP;
    
//    //Action variables
//    private Action submitAnswerA, submitWagerA;  
    
    private MedicalClinicDB medicalClinicDB;
    
    public JPanel createVisitInputPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1)); 
        
        visitIDL = new JLabel(visitIDStr, SwingConstants.LEFT);
        visitNumberL = new JLabel(visitNumberStr, SwingConstants.LEFT);
        visitDateL = new JLabel(visitDateStr, SwingConstants.LEFT);
        patientIDL = new JLabel(patientIDStr, SwingConstants.LEFT); 
        healthcareProviderIDL = new JLabel(healthcareProviderIDStr, SwingConstants.LEFT);                               
        dateAnalysisCompleteL = new JLabel(dateAnalysisCompleteStr, SwingConstants.LEFT);
        dateProcessingCompleteL = new JLabel(dateProcessingCompleteStr, SwingConstants.LEFT);

        visitIDTF = new JTextField(20);
        visitNumberTF = new JTextField(20);
        visitDateTF = new JTextField(20);
        patientIDTF = new JTextField(20);
        healthcareProviderIDTF = new JTextField(20);                                    
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
        visitIDP = new JPanel();
//        visitIDP.setLayout(gridLayout);
        visitIDP.add(visitIDL);
        visitIDP.add(visitIDTF);
        visitNumberP = new JPanel();
//        visitNumberP.setLayout(gridLayout);
        visitNumberP.add(visitNumberL);
        visitNumberP.add(visitNumberTF);
        visitDateP = new JPanel();
//        visitDateP.setLayout(gridLayout);
        visitDateP.add(visitDateL);
        visitDateP.add(visitDateTF);
        patientIDP = new JPanel(); 
//        patientIDP.setLayout(gridLayout);
        patientIDP.add(patientIDL);
        patientIDP.add(patientIDTF);
        healthcareProviderIDP = new JPanel();                               
//        healthcareProviderIDP.setLayout(gridLayout);
        healthcareProviderIDP.add(healthcareProviderIDL);
        healthcareProviderIDP.add(healthcareProviderIDTF);
        dateAnalysisCompleteP = new JPanel();
//        dateAnalysisCompleteP.setLayout(gridLayout);
        dateAnalysisCompleteP.add(dateAnalysisCompleteL);
        dateAnalysisCompleteP.add(dateAnalysisCompleteTF);
        dateProcessingCompleteP = new JPanel();
//        dateProcessingCompleteP.setLayout(gridLayout);
        dateProcessingCompleteP.add(dateProcessingCompleteL);
        dateProcessingCompleteP.add(dateProcessingCompleteTF);
        
        panel.add(visitIDP);
        panel.add(visitNumberP);
        panel.add(visitDateP);
        panel.add(patientIDP);
        panel.add(healthcareProviderIDP);
        panel.add(dateAnalysisCompleteP);
        panel.add(dateProcessingCompleteP);
        panel.add(addVisitButtonP);
        return panel;
    }
        
    public void clearAddVisitTF()
    {
        visitIDTF.setText("");
        visitNumberTF.setText("");
        visitDateTF.setText("");
        patientIDTF.setText("");
        healthcareProviderIDTF.setText("");                                    
        dateAnalysisCompleteTF.setText("");
        dateProcessingCompleteTF.setText("");
    }
        
    private class addVisitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String visitID = visitIDTF.getText();
            String visitNumber = visitNumberTF.getText();
            String visitDate = visitDateTF.getText();
            String patientID = patientIDTF.getText();
            String healthcareProviderID = healthcareProviderIDTF.getText();
            String dateAnalysisComplete = dateAnalysisCompleteTF.getText();
            String dateProcessingComplete = dateProcessingCompleteTF.getText();
            try 
            {
                medicalClinicDB.addVisit(visitID, visitNumber, visitDate, 
                        patientID, healthcareProviderID, dateAnalysisComplete, 
                        dateProcessingComplete);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(MedicalDBUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
            clearAddVisitTF();
        }
    }
    
    public VisitInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
}