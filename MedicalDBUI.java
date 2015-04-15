
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    private String patientConditionStr = "Patient "
    
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
    
    private addPatientButtonHandler addPatientH;
    private searchPatientButtonHandler searchPatientH;
                        
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
    
//    //Action variables
//    private Action submitAnswerA, submitWagerA;  
    
    private MedicalClinicDB medicalClinicDB;
    
    public JPanel createPatientInputPanel()
    {
        JPanel panel = basePanel();
        
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
        genderP = new JPanel();
//        genderP.setLayout(gridLayout);
        genderP.add(genderL);
        genderP.add(genderTF);
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
        
        panel.add(patientIDP);
        panel.add(clinicNumberP);
        panel.add(firstNameP);
        panel.add(middleNameP);
        panel.add(lastNameP);
        panel.add(genderP);
        panel.add(birthDateP);
        panel.add(heightP);
        panel.add(weightP);
        panel.add(addPatientButtonP);
        return panel;
    }
    
    public JPanel createConditionInputPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1)); 
        
        
    }
    
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
    
    public JPanel basePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1));   
        return panel;
    }
    
    public void clearAddPatientTF()
    {
        patientIDTF.setText("");
        clinicNumberTF.setText("");
        firstNameTF.setText("");
        middleNameTF.setText("");
        lastNameTF.setText("");                                    
        genderTF.setText("");
        birthDateTF.setText("");
        heightTF.setText("");
        weightTF.setText("");
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
    
    private class addPatientButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String patientID = patientIDTF.getText();
            String clinicNumber = clinicNumberTF.getText();
            String firstName = firstNameTF.getText();
            String middleName = middleNameTF.getText();
            String lastName = lastNameTF.getText();
            String gender = genderTF.getText();
            String birthDate = birthDateTF.getText();
            String height = heightTF.getText();
            String weight = weightTF.getText();
            try 
            {
                medicalClinicDB.addPatient(patientID, clinicNumber, firstName, 
                        middleName, lastName, gender, birthDate, height, 
                        weight);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(MedicalDBUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
            clearAddPatientTF();
        }
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
    
    public MedicalDBUI(MedicalClinicDB medicalClinicObj)
    {
        //Where instance variables are declared:
//        JPanel cards;
//        JPanel card1 = new JPanel();
//        ...
//        JPanel card2 = new JPanel();
//        ...
//
//        //Create the panel that contains the "cards".
//        cards = new JPanel(new CardLayout());
//        cards.add(card1, BUTTONPANEL);
//        cards.add(card2, TEXTPANEL);

        medicalClinicDB = medicalClinicObj;
        medicalClinicDB.connectToDatabase();
        JFrame frame = new JFrame();
        JPanel patientInputPanel = createPatientInputPanel();
        JPanel patientSearchPanel = createPatientSearchPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();
//        ImageIcon icon = createImageIcon("images/middle.gif");

//        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Patient Input", patientInputPanel);
        tabbedPane.addTab("Patient Search", patientSearchPanel);
//        tabbedPane.addTab("Tab 1", icon, panel1,
//                          "Does nothing");
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

//        JComponent panel2 = makeTextPanel("Panel #2");
//        tabbedPane.addTab("Tab 2", icon, panel2,
//                          "Does twice as much nothing");
//        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        frame.add(tabbedPane);
        frame.pack();
        frame.setVisible(true);
        // Sets size of window, close operation, and displays it
//        frame.setSize (WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);	
    }
}
