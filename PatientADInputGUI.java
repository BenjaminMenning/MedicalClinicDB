package MedicalClinicDB;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class PatientADInputGUI extends PatientMiscInputGUI
{
    //JLabel variables
//    private JLabel clinicNumberL;
    private JLabel assistiveDeviceL;
        
//    private String clinicNumberStr = "Clinic Number:";
    private String assistiveDeviceStr = "Assistive Device:";
//    private ArrayList<String> clinicNumberList;
    private ArrayList<String> assistiveDeviceList;
    //JTextField varables
//    private JComboBox clinicNumberCB;
    private JComboBox assistiveDeviceCB;                                    
    
    //JButton variables
    private JButton addAssistiveDeviceB;
    
    private addAssistiveDeviceButtonHandler addAssistiveDeviceH;
                                
    //JPanel variables
//    private JPanel clinicNumberP;
    private JPanel assistiveDeviceP;
    private JPanel addPatientButtonP;
        
//    private MedicalClinicDB medicalClinicDB;
    
    public PatientADInputGUI(MedicalClinicDB medicalClinicObj)
    {
        super(medicalClinicObj);
    }
    
    @Override
    public JPanel createInputPanel() throws SQLException
    {
        super.createInputPanel();
        assistiveDeviceL = new JLabel(assistiveDeviceStr, SwingConstants.LEFT);
        assistiveDeviceCB = new JComboBox();
        assistiveDeviceCB.setEditable(true);
        
        addAssistiveDeviceB = new JButton("Add Patient Assistive Device");
        addAssistiveDeviceH = new addAssistiveDeviceButtonHandler();
        addAssistiveDeviceB.addActionListener(addAssistiveDeviceH);
        addPatientButtonP = new JPanel();
        addPatientButtonP.add(addAssistiveDeviceB);
        
        assistiveDeviceP = new JPanel();
        assistiveDeviceP.add(assistiveDeviceL);
        assistiveDeviceP.add(assistiveDeviceCB);
        
        basePanel.add(assistiveDeviceP);
        basePanel.add(addPatientButtonP);
        return basePanel;
    }
    
    public void clearFields()
    {
        super.clearFields();
        assistiveDeviceCB.setSelectedItem("");
    }
        
    private class addAssistiveDeviceButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String clinicNumber = clinicNumberCB.getSelectedItem().toString();
            String assistiveDevice = assistiveDeviceCB.getSelectedItem().
                    toString();
            try 
            {
                String patientID = medicalClinicDB.determinePatientID(
                        clinicNumber);
                String assistiveDeviceID = medicalClinicDB.
                        determineAssistiveDeviceID(assistiveDevice);
                medicalClinicDB.addPatientAssistiveDevice(null, 
                        patientID, assistiveDeviceID);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(PatientADInputGUI.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            clearFields();
        }
    }
}