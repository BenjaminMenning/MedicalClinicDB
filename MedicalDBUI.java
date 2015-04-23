package MedicalClinicDB;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class MedicalDBUI 
{
    //Window size
    private static final int WIDTH = 450;                               
    private static final int HEIGHT = 425;                             
            
    private String patientInputStr = "Add a Patient";
    private String patientADInputStr = "Add a Patient Assistive Device";
    private String patientConditionInputStr = "Add a Patient Condition";
    private String patientHCPInputStr = "Add a Patient Healthcare Provider";
    
    private String visitInputStr = "Add a Visit";
    private String visitDiagnosisInputStr = "Add a Visit Diagnosis";
    private String visitFileInputStr = "Add a Visit File";
    private String visitStudyInputStr = "Add a Visit Study";
    private String visitSystemInputStr = "Add a Visit System";
    private String visitTreatmentInputStr = "Add a Visit Treatment";
        
//    private changePatientInputPanelHandler changePatientPanelH;
                        
    //JPanel variables
    private JPanel patientInputComboP;
    private JPanel patientInputPanel;
    private JPanel patientADInputPanel;
    private JPanel patientConditionInputPanel;
    private JPanel patientSearchPanel;
    private JPanel patientInputPanels;
    private JPanel patientInputTabP;
    private JPanel visitInputComboP;
    private JPanel visitInputPanel;
    private JPanel visitStudyInputPanel;
    private JPanel visitSystemInputPanel;
    private JPanel visitTreatmentInputPanel;
    private JPanel visitInputPanels;
    private JPanel visitInputTabP;
    
    private JFrame medicalDBUIFrame;
        
    private MedicalClinicDB medicalClinicDB;
    private PatientInputGUI patientInputGUI;
    private PatientADInputGUI patientADInputGUI;
    private PatientConditionInputGUI patientConditionInputGUI;
    private VisitInputGUI visitInputGUI;
    private VisitStudyInputGUI visitStudyInputGUI;
    private VisitSystemInputGUI visitSystemInputGUI;
    private VisitTreatmentInputGUI visitTreatmentInputGUI;
    
    private JComboBox patientInputCombo = new JComboBox();
    private String patientComboBoxItems[] = {patientInputStr, patientADInputStr, 
        patientConditionInputStr};
    
    private JComboBox visitInputCombo = new JComboBox();
    private String visitComboBoxItems[] = {visitInputStr, visitStudyInputStr, 
        visitSystemInputStr, visitTreatmentInputStr};
    
//    private class changePatientInputPanelHandler implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e) 
//        {
//            JComboBox jcb = (JComboBox) e.getSource();
//            CardLayout cl = (CardLayout) patientInputPanels.getLayout();
//            cl.show(patientInputPanels, jcb.getSelectedItem().toString());
//        }
//    }
    
    public MedicalDBUI(MedicalClinicDB medicalClinicObj) throws SQLException
    {
        medicalClinicDB = medicalClinicObj;
        medicalClinicDB.connectToDatabase();
        medicalDBUIFrame = new JFrame();
        patientInputTabP = new JPanel();
        patientInputTabP.setLayout(new BorderLayout());   
        visitInputTabP = new JPanel();
        visitInputTabP.setLayout(new BorderLayout());
        
        patientInputCombo = new JComboBox(patientComboBoxItems);
        patientInputCombo.setEditable(false);
//        patientInputCombo.addActionListener(changePatientPanelH);
        patientInputCombo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) patientInputPanels.getLayout();
                cl.show(patientInputPanels, jcb.getSelectedItem().toString());
            }
        });
        patientInputComboP = new JPanel();
        patientInputComboP.add(patientInputCombo);   
        
        visitInputCombo = new JComboBox(visitComboBoxItems);
        visitInputCombo.setEditable(false);
//        visitInputCombo.addActionListener(changePatientPanelH);
        visitInputCombo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) visitInputPanels.getLayout();
                cl.show(visitInputPanels, jcb.getSelectedItem().toString());
            }
        });
        visitInputComboP = new JPanel();
        visitInputComboP.add(visitInputCombo);   
        
        patientInputGUI = new PatientInputGUI(medicalClinicDB);
        patientADInputGUI = new PatientADInputGUI(medicalClinicDB);
        patientConditionInputGUI = new PatientConditionInputGUI
        (medicalClinicDB);
        visitInputGUI = new VisitInputGUI(medicalClinicDB);
        visitStudyInputGUI = new VisitStudyInputGUI(medicalClinicDB);
        visitSystemInputGUI = new VisitSystemInputGUI(medicalClinicDB);
        visitTreatmentInputGUI = new VisitTreatmentInputGUI(medicalClinicDB);
        
        JFrame medicalDBUIFrame = new JFrame();
        patientInputPanel = patientInputGUI.createPatientInputPanel();
        patientADInputPanel = patientADInputGUI.
                createInputPanel();
        patientConditionInputPanel = patientConditionInputGUI.
                createInputPanel();
        visitInputPanel = visitInputGUI.createVisitInputPanel();
        visitStudyInputPanel = visitStudyInputGUI.createInputPanel();
        visitSystemInputPanel = visitSystemInputGUI.createInputPanel();
        visitTreatmentInputPanel = visitTreatmentInputGUI.createInputPanel();

        // Creates and adds panels for patient inputs
        patientInputPanels = new JPanel(new CardLayout());
        patientInputPanels.add(patientInputPanel, patientInputStr);
        patientInputPanels.add(patientADInputPanel, patientADInputStr);
        patientInputPanels.add(patientConditionInputPanel, 
                patientConditionInputStr);
               
        patientInputTabP.add(patientInputPanels);
        patientInputTabP.add(patientInputComboP,BorderLayout.PAGE_END);
        
        // Creates and adds panels for visit inputs
        visitInputPanels = new JPanel(new CardLayout());
        visitInputPanels.add(visitInputPanel, visitInputStr);
        visitInputPanels.add(visitStudyInputPanel, visitStudyInputStr);
        visitInputPanels.add(visitSystemInputPanel, visitSystemInputStr);
        visitInputPanels.add(visitTreatmentInputPanel, visitTreatmentInputStr);
               
        visitInputTabP.add(visitInputPanels);
        visitInputTabP.add(visitInputComboP,BorderLayout.PAGE_END);

        medicalDBUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Patient Input", patientInputTabP);
        tabbedPane.addTab("Visit Input", visitInputTabP);
        tabbedPane.addTab("Patient Search", patientSearchPanel);
        
        medicalDBUIFrame.add(tabbedPane);
        medicalDBUIFrame.pack();
        medicalDBUIFrame.setVisible(true);
        
        // Sets size of window, close operation, and displays it
//        medicalDBUIFrame.setSize (WIDTH, HEIGHT);
        medicalDBUIFrame.setLocationRelativeTo(null);
    }
}