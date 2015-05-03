package MedicalClinicDB;

import MedicalClinicDB.SearchGUI.SearchGUIMain;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
 * This class constructs the overall user interface for the medical clinic 
 * database and implements a wide array of different components into a single
 * frame.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class MedicalDBUI 
{
    // String variables for Patient JComboBox        
    private String patientInputStr = "Add a Patient";
    private String patientADInputStr = "Add a Patient Assistive Device";
    private String patientConditionInputStr = "Add a Patient Condition";
    private String patientHCPInputStr = "Add a Patient Healthcare Provider";
    
    // String variables for Visit JComboBox
    private String visitInputStr = "Add a Visit";
    private String visitDiagnosisInputStr = "Add a Visit Diagnosis";
    private String visitFileInputStr = "Add a Visit File";
    private String visitStudyInputStr = "Add a Visit Study";
    private String visitSystemInputStr = "Add a Visit System";
    private String visitTRInputStr = "Add Visit Test Results";
    private String visitTreatmentInputStr = "Add a Visit Treatment";
    
    // String variables for Miscellaneous JComboBox
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
                                
    // String variable for JFrame title
    private String inputTitleStr = "Medical Clinic Database Input";
    
    // Patient JPanel variables
    private JPanel patientInputComboP;
    private JPanel patientInputPanel;
    private JPanel patientADInputPanel;
    private JPanel patientConditionInputPanel;
    private JPanel patientHCPInputPanel;
    private JPanel patientInputPanels;
    private JPanel patientInputTabP;
    
    // Visit JPanel variables
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
    
    // Miscellaneous JPanel variables
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
    
    // Frame that contains all components
    private JFrame medicalDBUIFrame;
        
    // Medical clinic database object
    private MedicalClinicDB medicalClinicDB;
    
    // Patient input GUI objects
    private PatientInputGUI patientInputGUI;
    private PatientADInputGUI patientADInputGUI;
    private PatientConditionInputGUI patientConditionInputGUI;
    private PatientHCPInputGUI patientHCPInputGUI;
    
    // Visit input GUI objects
    private VisitInputGUI visitInputGUI;
    private VisitFileInputGUI visitFileInputGUI;
    private VisitDiagnosisInputGUI visitDiagnosisInputGUI;
    private VisitStudyInputGUI visitStudyInputGUI;
    private VisitSystemInputGUI visitSystemInputGUI;
    private VisitTRInputGUI visitTRInputGUI;
    private VisitTreatmentInputGUI visitTreatmentInputGUI;
    
    // Miscellaneous input GUI objects
    private ADInputGUI assisDevInputGUI;
    private ConditionInputGUI conditionInputGUI;
    private ICD9DiagnosisInputGUI icd9DiagnosisInputGUI;
    private ICD9ProcedureInputGUI icd9ProcedureInputGUI;
    private ProviderInputGUI providerInputGUI;
    private StudyInputGUI studyInputGUI;
    private SystemInputGUI systemInputGUI;
    private TreatmentInputGUI treatmentInputGUI;
    private TreatmentICD9ProcInputGUI treatmentICD9ProcInputGUI;
    
    // JButton variables
    private JButton searchPatientB;
    
    // JButton Handlers
    private searchPatientButtonHandler searchPatientH;
    
    // Patient Input JComboBox and String variables
    private JComboBox patientInputCombo = new JComboBox();
    private String patientComboBoxItems[] = {patientInputStr, patientADInputStr, 
        patientConditionInputStr, patientHCPInputStr};
    
    // Visit Input JComboBox and String variables
    private JComboBox visitInputCombo = new JComboBox();
    private String visitComboBoxItems[] = {visitInputStr, visitFileInputStr,
        visitDiagnosisInputStr, visitStudyInputStr, visitSystemInputStr, 
        visitTRInputStr, visitTreatmentInputStr};
    
    // Miscellaneous Input JComboBox and String variables
    private JComboBox miscInputCombo = new JComboBox();
    private String miscComboBoxItems[] = {assisDevInputStr, 
        conditionInputStr, icd9DiagnosisInputStr, icd9ProcedureInputStr, 
        providerInputStr, studyInputStr, systemInputStr, treatmentInputStr, 
        treatmentICD9ProcInputStr};

    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database and create the main frame that utilizes all of the GUI 
     * components.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public MedicalDBUI(MedicalClinicDB medicalClinicObj) throws SQLException
    {
        // Initializes medical clinic database object and connects to database
        medicalClinicDB = medicalClinicObj;
        medicalClinicDB.connectToDatabase();
        
        // Creates JFrame
        medicalDBUIFrame = new JFrame();
        
        // Creates input tab panels and sets layout
        patientInputTabP = new JPanel();
        patientInputTabP.setLayout(new BorderLayout());   
        visitInputTabP = new JPanel();
        visitInputTabP.setLayout(new BorderLayout());
        miscInputTabP = new JPanel();
        miscInputTabP.setLayout(new BorderLayout());
        
        // Creates patient JComboBox, initializes it, and adds it to panel
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
        
        // Initialize search patient JButton and JButton handler
        searchPatientB = new JButton("Search for a Patient");
        searchPatientH = new searchPatientButtonHandler();
        searchPatientB.addActionListener(searchPatientH);
        patientInputComboP.add(searchPatientB);
                
        // Creates visit JComboBox, initializes it, and adds it to panel
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

        // Creates misc. JComboBox, initializes it, and adds it to panel
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
        
        // Initializes patient input GUI components
        patientInputGUI = new PatientInputGUI(medicalClinicDB);
        patientADInputGUI = new PatientADInputGUI(medicalClinicDB);
        patientConditionInputGUI = new PatientConditionInputGUI
        (medicalClinicDB);
        patientHCPInputGUI = new PatientHCPInputGUI(medicalClinicDB);
        
        // Initializes visit input GUI components
        visitInputGUI = new VisitInputGUI(medicalClinicDB);
        visitFileInputGUI = new VisitFileInputGUI(medicalClinicDB);
        visitDiagnosisInputGUI = new VisitDiagnosisInputGUI(medicalClinicDB);
        visitStudyInputGUI = new VisitStudyInputGUI(medicalClinicDB);
        visitSystemInputGUI = new VisitSystemInputGUI(medicalClinicDB);
        visitTRInputGUI = new VisitTRInputGUI(medicalClinicDB);
        visitTreatmentInputGUI = new VisitTreatmentInputGUI(medicalClinicDB);
        
        // Iniitializes miscellaneous input GUI components
        assisDevInputGUI = new ADInputGUI(medicalClinicDB, patientADInputGUI);
        conditionInputGUI = new ConditionInputGUI(medicalClinicDB, 
                patientConditionInputGUI);
        icd9DiagnosisInputGUI = new ICD9DiagnosisInputGUI(medicalClinicDB, 
                visitDiagnosisInputGUI);
        providerInputGUI = new ProviderInputGUI(medicalClinicDB, 
                patientHCPInputGUI);
        studyInputGUI = new StudyInputGUI(medicalClinicDB, visitStudyInputGUI);
        systemInputGUI = new SystemInputGUI(medicalClinicDB, 
                visitSystemInputGUI);
        treatmentICD9ProcInputGUI = new TreatmentICD9ProcInputGUI
            (medicalClinicDB);
        treatmentInputGUI = new TreatmentInputGUI(medicalClinicDB, 
                visitTreatmentInputGUI, treatmentICD9ProcInputGUI);
        icd9ProcedureInputGUI = new ICD9ProcedureInputGUI(medicalClinicDB, 
                treatmentICD9ProcInputGUI);
        
//        JFrame medicalDBUIFrame = new JFrame();
        
        // Initializes patient input panels
        patientInputPanel = patientInputGUI.createPatientInputPanel();
        patientADInputPanel = patientADInputGUI.
                createInputPanel();
        patientConditionInputPanel = patientConditionInputGUI.
                createInputPanel();
        patientHCPInputPanel = patientHCPInputGUI.createInputPanel();
        
        // Initializes visit input panels
        visitInputPanel = visitInputGUI.createInputPanel();
        visitFileInputPanel = visitFileInputGUI.createInputPanel();
        visitDiagnosisInputPanel = visitDiagnosisInputGUI.createInputPanel();
        visitStudyInputPanel = visitStudyInputGUI.createInputPanel();
        visitSystemInputPanel = visitSystemInputGUI.createInputPanel();
        visitTRInputPanel = visitTRInputGUI.createInputPanel();
        visitTreatmentInputPanel = visitTreatmentInputGUI.createInputPanel();
        
        // Initializes miscellaneous input panels
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
               
        // Adds patient input panels to tab and sets layout
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
        
        // Adds visit input panels to tab and sets layout
        visitInputTabP.add(visitInputPanels);
        visitInputTabP.add(visitInputComboP,BorderLayout.PAGE_END);

        // Creates and add panels for miscellaneous inputs
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
        
        // Adds miscellaneous input panels to tab and sets layout
        miscInputTabP.add(miscInputPanels);
        miscInputTabP.add(miscInputComboP,BorderLayout.PAGE_END);
               
        // Create JTabbedPane and add tab panels to pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patient Input", patientInputTabP);
        tabbedPane.addTab("Visit Input", visitInputTabP);
        tabbedPane.addTab("Miscellaneous Input", miscInputTabP);
        
        // Initialize JFrame properties
        medicalDBUIFrame.setTitle(inputTitleStr);
        medicalDBUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        medicalDBUIFrame.add(tabbedPane);
        medicalDBUIFrame.pack();
        medicalDBUIFrame.setVisible(true);
        medicalDBUIFrame.setLocationRelativeTo(null);
    }
    
    /** 
     * This class performs the action of opening a new window to search for a
     * patient.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class searchPatientButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            SearchGUIMain window = new SearchGUIMain();
            window.prepareFrame();
        }
    }
}