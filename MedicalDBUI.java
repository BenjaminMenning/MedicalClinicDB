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
    private String visitTRInputStr = "Add Visit Test Results";
    private String visitTreatmentInputStr = "Add a Visit Treatment";
    
    private String assisDevInputStr = "Add a Assistive Device";
    private String conditionInputStr = "Add a Condition";
    private String icd9DiagnosisInputStr = "Add a ICD9 Diagnosis";
    private String icd9ProcedureInputStr = "Add a ICD9 Procedure";
    private String providerInputStr = "Add a Healthcare Provider";
    private String studyInputStr = "Add a Study";
    private String systemInputStr = "Add a System";
    private String treatmentInputStr = "Add a Treatment";
    private String treatmentICD9ProcInputStr = "Add a ICD9 Procedure to a "
            + "Treatment";
                                
    //JPanel variables
    private JPanel patientInputComboP;
    private JPanel patientInputPanel;
    private JPanel patientADInputPanel;
    private JPanel patientConditionInputPanel;
    private JPanel patientHCPInputPanel;
    private JPanel patientSearchPanel;
    private JPanel patientInputPanels;
    private JPanel patientInputTabP;
    
    private JPanel visitInputComboP;
    private JPanel visitInputPanel;
    private JPanel visitDiagnosisInputPanel;
    private JPanel visitFileInputPanel;
    private JPanel visitStudyInputPanel;
    private JPanel visitSystemInputPanel;
    private JPanel visitTRInputPanel;
    private JPanel visitTreatmentInputPanel;
    private JPanel visitInputPanels;
    private JPanel visitInputTabP;
    
    private JPanel miscInputComboP;
    private JPanel assisDevInputPanel;
    private JPanel conditionInputPanel;
    private JPanel icd9DiagnosisInputPanel;
    private JPanel icd9ProcedureInputPanel;
    private JPanel providerInputPanel;
    private JPanel studyInputPanel;
    private JPanel systemInputPanel;
    private JPanel treatmentInputPanel;
    private JPanel treatmentICD9ProcInputPanel;
    private JPanel miscInputPanels;
    private JPanel miscInputTabP;
    
    private JFrame medicalDBUIFrame;
        
    private MedicalClinicDB medicalClinicDB;
    private PatientInputGUI patientInputGUI;
    private PatientADInputGUI patientADInputGUI;
    private PatientConditionInputGUI patientConditionInputGUI;
    private PatientHCPInputGUI patientHCPInputGUI;
    
    private VisitInputGUI visitInputGUI;
    private VisitFileInputGUI visitFileInputGUI;
    private VisitDiagnosisInputGUI visitDiagnosisInputGUI;
    private VisitStudyInputGUI visitStudyInputGUI;
    private VisitSystemInputGUI visitSystemInputGUI;
    private VisitTRInputGUI visitTRInputGUI;
    private VisitTreatmentInputGUI visitTreatmentInputGUI;
    
    private ADInputGUI assisDevInputGUI;
    private ConditionInputGUI conditionInputGUI;
    private ICD9DiagnosisInputGUI icd9DiagnosisInputGUI;
    private ICD9ProcedureInputGUI icd9ProcedureInputGUI;
    private ProviderInputGUI providerInputGUI;
    private StudyInputGUI studyInputGUI;
    private SystemInputGUI systemInputGUI;
    private TreatmentInputGUI treatmentInputGUI;
    private TreatmentICD9ProcInputGUI treatmentICD9ProcInputGUI;
    
    private JComboBox patientInputCombo = new JComboBox();
    private String patientComboBoxItems[] = {patientInputStr, patientADInputStr, 
        patientConditionInputStr, patientHCPInputStr};
    
    private JComboBox visitInputCombo = new JComboBox();
    private String visitComboBoxItems[] = {visitInputStr, visitFileInputStr,
        visitDiagnosisInputStr, visitStudyInputStr, visitSystemInputStr, 
        visitTRInputStr, visitTreatmentInputStr};
    
    private JComboBox miscInputCombo = new JComboBox();
    private String miscComboBoxItems[] = {assisDevInputStr, 
        conditionInputStr, icd9DiagnosisInputStr, icd9ProcedureInputStr, 
        providerInputStr, studyInputStr, systemInputStr, treatmentInputStr, 
        treatmentICD9ProcInputStr};

    public MedicalDBUI(MedicalClinicDB medicalClinicObj) throws SQLException
    {
        medicalClinicDB = medicalClinicObj;
        medicalClinicDB.connectToDatabase();
        medicalDBUIFrame = new JFrame();
        patientInputTabP = new JPanel();
        patientInputTabP.setLayout(new BorderLayout());   
        visitInputTabP = new JPanel();
        visitInputTabP.setLayout(new BorderLayout());
        miscInputTabP = new JPanel();
        miscInputTabP.setLayout(new BorderLayout());
        
        patientInputCombo = new JComboBox(patientComboBoxItems);
        patientInputCombo.setEditable(false);
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

        miscInputCombo = new JComboBox(miscComboBoxItems);
        miscInputCombo.setEditable(false);
        miscInputCombo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) miscInputPanels.getLayout();
                cl.show(miscInputPanels, jcb.getSelectedItem().toString());
            }
        });
        miscInputComboP = new JPanel();
        miscInputComboP.add(miscInputCombo);   
        
        patientInputGUI = new PatientInputGUI(medicalClinicDB);
        patientADInputGUI = new PatientADInputGUI(medicalClinicDB);
        patientConditionInputGUI = new PatientConditionInputGUI
        (medicalClinicDB);
        patientHCPInputGUI = new PatientHCPInputGUI(medicalClinicDB);
        
        visitInputGUI = new VisitInputGUI(medicalClinicDB);
        visitFileInputGUI = new VisitFileInputGUI(medicalClinicDB);
        visitDiagnosisInputGUI = new VisitDiagnosisInputGUI(medicalClinicDB);
        visitStudyInputGUI = new VisitStudyInputGUI(medicalClinicDB);
        visitSystemInputGUI = new VisitSystemInputGUI(medicalClinicDB);
        visitTRInputGUI = new VisitTRInputGUI(medicalClinicDB);
        visitTreatmentInputGUI = new VisitTreatmentInputGUI(medicalClinicDB);
        
        assisDevInputGUI = new ADInputGUI(medicalClinicDB);
        conditionInputGUI = new ConditionInputGUI(medicalClinicDB);
        icd9DiagnosisInputGUI = new ICD9DiagnosisInputGUI(medicalClinicDB);
        icd9ProcedureInputGUI = new ICD9ProcedureInputGUI(medicalClinicDB);
        providerInputGUI = new ProviderInputGUI(medicalClinicDB);
        studyInputGUI = new StudyInputGUI(medicalClinicDB);
        systemInputGUI = new SystemInputGUI(medicalClinicDB);
        treatmentInputGUI = new TreatmentInputGUI(medicalClinicDB);
        treatmentICD9ProcInputGUI = new TreatmentICD9ProcInputGUI
            (medicalClinicDB);
        
        JFrame medicalDBUIFrame = new JFrame();
        patientInputPanel = patientInputGUI.createPatientInputPanel();
        patientADInputPanel = patientADInputGUI.
                createInputPanel();
        patientConditionInputPanel = patientConditionInputGUI.
                createInputPanel();
        patientHCPInputPanel = patientHCPInputGUI.createInputPanel();
        
        visitInputPanel = visitInputGUI.createInputPanel();
        visitFileInputPanel = visitFileInputGUI.createInputPanel();
        visitDiagnosisInputPanel = visitDiagnosisInputGUI.createInputPanel();
        visitStudyInputPanel = visitStudyInputGUI.createInputPanel();
        visitSystemInputPanel = visitSystemInputGUI.createInputPanel();
        visitTRInputPanel = visitTRInputGUI.createInputPanel();
        visitTreatmentInputPanel = visitTreatmentInputGUI.createInputPanel();
        
        assisDevInputPanel = assisDevInputGUI.createInputPanel();
        conditionInputPanel = conditionInputGUI.createInputPanel();
        icd9DiagnosisInputPanel = icd9DiagnosisInputGUI.createInputPanel();
        icd9ProcedureInputPanel = icd9ProcedureInputGUI.createInputPanel();
        providerInputPanel = providerInputGUI.createInputPanel();
        studyInputPanel = studyInputGUI.createInputPanel();
        systemInputPanel = systemInputGUI.createInputPanel();
        treatmentInputPanel = treatmentInputGUI.createInputPanel();
        treatmentICD9ProcInputPanel = treatmentICD9ProcInputGUI.
                createInputPanel();

        // Creates and adds panels for patient inputs
        patientInputPanels = new JPanel(new CardLayout());
        patientInputPanels.add(patientInputPanel, patientInputStr);
        patientInputPanels.add(patientADInputPanel, patientADInputStr);
        patientInputPanels.add(patientConditionInputPanel, 
                patientConditionInputStr);
        patientInputPanels.add(patientHCPInputPanel, patientHCPInputStr);
               
        patientInputTabP.add(patientInputPanels);
        patientInputTabP.add(patientInputComboP,BorderLayout.PAGE_END);
        
        // Creates and adds panels for visit inputs
        visitInputPanels = new JPanel(new CardLayout());
        visitInputPanels.add(visitInputPanel, visitInputStr);
        visitInputPanels.add(visitFileInputPanel, visitFileInputStr);
        visitInputPanels.add(visitDiagnosisInputPanel, visitDiagnosisInputStr);
        visitInputPanels.add(visitStudyInputPanel, visitStudyInputStr);
        visitInputPanels.add(visitSystemInputPanel, visitSystemInputStr);
        visitInputPanels.add(visitTRInputPanel, visitTRInputStr);
        visitInputPanels.add(visitTreatmentInputPanel, visitTreatmentInputStr);
        
        visitInputTabP.add(visitInputPanels);
        visitInputTabP.add(visitInputComboP,BorderLayout.PAGE_END);

        miscInputPanels = new JPanel(new CardLayout());
        miscInputPanels.add(assisDevInputPanel, assisDevInputStr);
        miscInputPanels.add(conditionInputPanel, conditionInputStr);
        miscInputPanels.add(icd9DiagnosisInputPanel, icd9DiagnosisInputStr);
        miscInputPanels.add(icd9ProcedureInputPanel, icd9ProcedureInputStr);
        miscInputPanels.add(providerInputPanel, providerInputStr);
        miscInputPanels.add(studyInputPanel, studyInputStr);
        miscInputPanels.add(systemInputPanel, systemInputStr);
        miscInputPanels.add(treatmentInputPanel, treatmentInputStr);
        miscInputPanels.add(treatmentICD9ProcInputPanel, 
                treatmentICD9ProcInputStr);
        
        miscInputTabP.add(miscInputPanels);
        miscInputTabP.add(miscInputComboP,BorderLayout.PAGE_END);
               
        medicalDBUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Patient Input", patientInputTabP);
        tabbedPane.addTab("Visit Input", visitInputTabP);
        tabbedPane.addTab("Miscellaneous Input", miscInputTabP);
        tabbedPane.addTab("Patient Search", patientSearchPanel);
        
        medicalDBUIFrame.add(tabbedPane);
        medicalDBUIFrame.pack();
        medicalDBUIFrame.setVisible(true);
        
        // Sets size of window, close operation, and displays it
//        medicalDBUIFrame.setSize (WIDTH, HEIGHT);
        medicalDBUIFrame.setLocationRelativeTo(null);
    }
}