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
public abstract class PatientMiscInputGUI 
{
    //JLabel variables
    protected JLabel clinicNumberL;
        
    protected String clinicNumberStr = "Clinic Number:";
    protected ArrayList<String> clinicNumberList;
    
    //JTextField varables
    protected JComboBox clinicNumberCB;
                                        
    //JPanel variables
    protected JPanel clinicNumberP;
    protected JPanel basePanel;
    
    protected MedicalClinicDB medicalClinicDB;
    
    public PatientMiscInputGUI(MedicalClinicDB medicalClinicObj)
    {
        medicalClinicDB = medicalClinicObj;
    }
    
    public JPanel createInputPanel() throws SQLException
    {
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 

        clinicNumberList = medicalClinicDB.getClinicNumberList();
        clinicNumberL = new JLabel(clinicNumberStr, SwingConstants.LEFT);

        clinicNumberCB = new JComboBox(clinicNumberList.toArray());
        clinicNumberCB.setEditable(true);
        AutoCompleteDecorator.decorate(clinicNumberCB);
        clinicNumberP = new JPanel();
        clinicNumberP.add(clinicNumberL);
        clinicNumberP.add(clinicNumberCB);
        basePanel.add(clinicNumberP);
        return basePanel;
    }
    
    public void clearFields()
    {
        clinicNumberCB.setSelectedItem("");
    }
}