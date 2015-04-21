package MedicalClinicDB;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
public class MedicalDBUI 
{
    //Window size
    private static final int WIDTH = 450;                               
    private static final int HEIGHT = 425;                             
        
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
    private String clinicNumberStr = "Clinic Number:";
    private String firstNameStr = "First Name:";
    private String middleNameStr = "Middle Name:";
    private String lastNameStr = "Last Name:";                                    
    private String genderStr = "Gender:";
    private String birthDateStr = "Birth Date:";
    private String heightStr = "Height:";
    private String weightStr = "Weight:";
    
    private String patientInputStr = "Add a Patient";
    private String patientADInputStr = "Add a Patient Assistive Device";
    
//    private String patientConditionStr = "Patient "
    
    private String[] addPatientStrings = {"Add Patient", "Add Patient Condition"
            , "Add Patient Healthcare Provider", "Add Patient Assistive Device"
            };
    
    //JTextField varables
    private JTextField patientIDTF, patientIDTF2;
    private JTextField clinicNumberTF, clinicNumberTF2;
    private JTextField firstNameTF, firstNameTF2, middleNameTF, middleNameTF2, 
            lastNameTF, lastNameTF2;                                    
    private JTextField genderTF;
    private JTextField birthDateTF;
    private JTextField heightTF;
    private JTextField weightTF;
    
    //JButton variables
    private JButton addPatientB;
    private JButton searchPatientB;
    
    private JComboBox addPatientList = new JComboBox(addPatientStrings);
    
    private searchPatientButtonHandler searchPatientH;
//    private changePatientInputPanelHandler changePatientPanelH;
                        
    //JPanel variables
    private JPanel patientIDP, patientIDP2;
    private JPanel clinicNumberP, clinicNumberP2;
    private JPanel firstNameP, firstNameP2, middleNameP, middleNameP2, 
            lastNameP, lastNameP2;                                    
    private JPanel genderP;
    private JPanel birthDateP;
    private JPanel heightP;
    private JPanel weightP;
    private JPanel addPatientButtonP;
    private JPanel searchPatientButtonP;
    private JPanel patientInputComboP;
    private JPanel patientInputPanels;
    private JPanel patientInputTabP;
    
    private JFrame medicalDBUIFrame;
    
//    //Action variables
//    private Action submitAnswerA, submitWagerA;  
    
    private MedicalClinicDB medicalClinicDB;
    private PatientInputGUI patientInputGUI;
    private PatientADInputGUI patientADInputGUI;
    private VisitInputGUI visitInputGUI;
    
    private JComboBox patientInputCombo = new JComboBox();
    private String patientComboBoxItems[] = {patientInputStr, patientADInputStr};
//    JComboBox cb = new JComboBox(patientComboBoxItems);
    
    public JPanel createPatientSearchPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1));   
        
        patientIDL = new JLabel(patientIDStr, SwingConstants.LEFT);
        clinicNumberL = new JLabel(clinicNumberStr, SwingConstants.LEFT);
        firstNameL = new JLabel(firstNameStr, SwingConstants.LEFT);
        middleNameL = new JLabel(middleNameStr, SwingConstants.LEFT); 
        lastNameL = new JLabel(lastNameStr, SwingConstants.LEFT);                               
//        genderL = new JLabel(genderStr, SwingConstants.LEFT);
//        birthDateL = new JLabel(birthDateStr, SwingConstants.LEFT);
//        heightL = new JLabel(heightStr, SwingConstants.LEFT);
//        weightL = new JLabel(weightStr, SwingConstants.LEFT);

        patientIDTF2 = new JTextField(20);
        clinicNumberTF2 = new JTextField(20);
        firstNameTF2 = new JTextField(20);
        middleNameTF2 = new JTextField(20);
        lastNameTF2 = new JTextField(20);                                    
//        genderTF = new JTextField(20);
//        birthDateTF = new JTextField(20);
//        heightTF = new JTextField(20);
//        weightTF = new JTextField(20);
        
        searchPatientB = new JButton("Search for Patient");
        searchPatientH = new searchPatientButtonHandler();
        searchPatientB.addActionListener(searchPatientH);
        searchPatientButtonP = new JPanel();
        searchPatientButtonP.add(searchPatientB);
        
        int rows = 1;
        int columns = 2;
        GridLayout gridLayout = new GridLayout(rows, columns);
        patientIDP2 = new JPanel();
//        patientIDP.setLayout(gridLayout);
        patientIDP2.add(patientIDL);
        patientIDP2.add(patientIDTF2);
        clinicNumberP2 = new JPanel();
//        clinicNumberP.setLayout(gridLayout);
        clinicNumberP2.add(clinicNumberL);
        clinicNumberP2.add(clinicNumberTF2);
        firstNameP2 = new JPanel();
//        firstNameP.setLayout(gridLayout);
        firstNameP2.add(firstNameL);
        firstNameP2.add(firstNameTF2);
        middleNameP2 = new JPanel(); 
//        middleNameP.setLayout(gridLayout);
        middleNameP2.add(middleNameL);
        middleNameP2.add(middleNameTF2);
        lastNameP2 = new JPanel();                               
//        lastNameP.setLayout(gridLayout);
        lastNameP2.add(lastNameL);
        lastNameP2.add(lastNameTF2);
//        genderP = new JPanel();
////        genderP.setLayout(gridLayout);
//        genderP.add(genderL);
//        genderP.add(genderTF);
//        birthDateP = new JPanel();
////        birthDateP.setLayout(gridLayout);
//        birthDateP.add(birthDateL);
//        birthDateP.add(birthDateTF);
//        heightP = new JPanel();
////        heightP.setLayout(gridLayout);
//        heightP.add(heightL);
//        heightP.add(heightTF);
//        weightP = new JPanel();
////        weightP.setLayout(gridLayout);
//        weightP.add(weightL);
//        weightP.add(weightTF);
        
        panel.add(patientIDP2);
        panel.add(clinicNumberP2);
        panel.add(firstNameP2);
        panel.add(middleNameP2);
        panel.add(lastNameP2);
//        panel.add(genderP);
//        panel.add(birthDateP);
//        panel.add(heightP);
//        panel.add(weightP);
        panel.add(searchPatientButtonP);
        return panel;
    }
    
    public void clearSearchPatientTF()
    {
        patientIDTF2.setText("");
        clinicNumberTF2.setText("");
        firstNameTF2.setText("");
        middleNameTF2.setText("");
        lastNameTF2.setText("");                                    
//        genderTF.setText("");
//        birthDateTF.setText("");
//        heightTF.setText("");
//        weightTF.setText("");
    }
        
    private class searchPatientButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String patientID = patientIDTF2.getText();
            String clinicNumber = clinicNumberTF2.getText();
            String firstName = firstNameTF2.getText();
            String middleName = middleNameTF2.getText();
            String lastName = lastNameTF2.getText();
//            String gender = genderTF.getText();
//            String birthDate = birthDateTF.getText();
//            String height = heightTF.getText();
//            String weight = weightTF.getText();
            medicalClinicDB.searchPatient();
            if(!patientID.equals(""))
            {
               medicalClinicDB.searchPatientID(patientID);
            }
            if(!clinicNumber.equals(""))
            {
               medicalClinicDB.searchClinicNumber(clinicNumber);
            }
            if(!firstName.equals(""))
            {
               medicalClinicDB.searchFirstName(firstName);
            }
            if(!middleName.equals(""))
            {
               medicalClinicDB.searchMiddleName(middleName);
            }
            if(!lastName.equals(""))
            {
               medicalClinicDB.searchLastName(lastName);
            }
            try 
            {
                medicalClinicDB.search();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(MedicalDBUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
            clearSearchPatientTF();
        }
    }
    
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
        
        patientInputGUI = new PatientInputGUI(medicalClinicDB);
        patientADInputGUI = new PatientADInputGUI(medicalClinicDB);
        visitInputGUI = new VisitInputGUI(medicalClinicDB);
        
        JFrame medicalDBUIFrame = new JFrame();
        JPanel patientInputPanel = patientInputGUI.createPatientInputPanel();
        JPanel patientADInputPanel = patientADInputGUI.
                createInputPanel();
        JPanel patientSearchPanel = createPatientSearchPanel();
        JPanel visitInputPanel = visitInputGUI.createVisitInputPanel();

        //Create the panel that contains the "patientInputPanels".
        patientInputPanels = new JPanel(new CardLayout());
        patientInputPanels.add(patientInputPanel, patientInputStr);
        patientInputPanels.add(patientADInputPanel, patientADInputStr);
               
        patientInputTabP.add(patientInputPanels);
        patientInputTabP.add(patientInputComboP,BorderLayout.PAGE_END);

        medicalDBUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Patient Input", patientInputTabP);
        tabbedPane.addTab("Visit Input", visitInputPanel);
        tabbedPane.addTab("Patient Search", patientSearchPanel);
        
        medicalDBUIFrame.add(tabbedPane);
        medicalDBUIFrame.pack();
        medicalDBUIFrame.setVisible(true);
        // Sets size of window, close operation, and displays it
//        medicalDBUIFrame.setSize (WIDTH, HEIGHT);
        medicalDBUIFrame.setLocationRelativeTo(null);
    }
}