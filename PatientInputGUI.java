package MedicalClinicDB;


import .*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
    private String clinicNumberStr = "Clinic Number:";
    private String firstNameStr = "First Name:";
    private String middleNameStr = "Middle Name:";
    private String lastNameStr = "Last Name:";                                    
    private String genderStr = "Gender:";
    private String birthDateStr = "Birth Date:";
    private String heightStr = "Height:";
    private String weightStr = "Weight:";
    
    
    private String[] addPatientStrings = {"Add Patient", "Add Patient Condition"
            , "Add Patient Healthcare Provider", "Add Patient Assistive Device"
            };
    
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
    
    public class PatientTable
    {
        private Connection connection = null;
	private Object[][] data = {{null, null, null, null, null}};
	private String[] columnNames = {"Clinic Number", 
                        "First Name",
                        "Middle Name",
			"Last Name",
			"DOB"
			};
        private JTable patientTable;
        private JScrollPane scrollPane;
	private DefaultTableModel tableModel = new DefaultTableModel(data, columnNames){
		   public boolean isCellEditable(int row, int column){
		        return false;
		   }
		};
        
        public void allPatientsQuery() throws SQLException
        {
        patientTable = new JTable(tableModel){
                   public boolean isCellEditable(int row, int column){
                        return false;
                   }
                };
            Statement stmt = null;
            String query = "SELECT * FROM Patient";
            try 
            {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) 
                {
    //                String patientID = rs.getString("patientID");
                    String clinicNumber = rs.getString("clinicNumber");
                    String firstName = rs.getString("firstName");
                    String middleName = rs.getString("middleName");
                    String lastName = rs.getString("lastName");
    //                String gender = rs.getString("gender");
                    String birthDate = rs.getString("birthDate");
    //                String height = rs.getString("height");
    //                String weight = rs.getString("weight");
                    tableModel.addRow(new Object[]{clinicNumber, firstName, middleName, lastName, birthDate});
                }
            } 
            catch (SQLException e ) 
            {
                System.out.println(e);
            } 
            finally 
            {
                if (stmt != null) { stmt.close(); }
            }    
        }
        
        public JScrollPane createPatientTable()
        {
            patientTable = new JTable(tableModel)
            {
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            };
            patientTable.setRowSelectionAllowed(true);
            patientTable.setShowVerticalLines(false);
            scrollPane.setViewportView(patientTable);

            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setViewportBorder(new LineBorder(Color.RED));
            scrollPane.getViewport().add(patientTable);  
            return scrollPane;
        }
    }
    
    
    
    public class PartialPatient
    {
        private String clinicNumber;
        private String firstName;
        private String middleName;
        private String lastName;
        private String birthDate;
        
        public PartialPatient()
        {
            clinicNumber = "";
            firstName = "";
            middleName = "";
            lastName = "";
            birthDate = "";
        }
        
        
    }
    
    public PatientInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
}