package MedicalClinicDB.SearchGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SearchGUIPatientInfo extends JFrame {

	private JPanel contentPane;
	private JTable tableVisits;
	
	private String[] columnNames = { "ID", "Visit Number",
			"Date", "Provider"};

	private Object[][] data = { { "1", "8AR5GY94", "1900-01-01", "Jennifer H Catflisch" } };

	private Map<String, String> terms = new HashMap<String, String>();

	private DefaultTableModel tableModel = new DefaultTableModel(data,
			columnNames) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	SearchGUIPatientInfoDB pinfo = new SearchGUIPatientInfoDB();

	/**
	 * Create the frame.
	 */
	public SearchGUIPatientInfo(int patientId) {
		
		try {
			pinfo.getPatient(patientId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatientName = new JLabel(pinfo.name);
		lblPatientName.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblPatientName.setBounds(6, 6, 295, 22);
		contentPane.add(lblPatientName);
		
		JLabel lblClinicNum = new JLabel(pinfo.clinicNumber);
		lblClinicNum.setBounds(6, 31, 80, 16);
		contentPane.add(lblClinicNum);
		
		JLabel lblNewLabel = new JLabel(pinfo.birthdate);
		lblNewLabel.setBounds(497, 12, 80, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblBirthdate = new JLabel("Birthdate:");
		lblBirthdate.setBounds(424, 12, 61, 16);
		contentPane.add(lblBirthdate);
		
		JLabel lblM = new JLabel(pinfo.gender);
		lblM.setHorizontalAlignment(SwingConstants.TRAILING);
		lblM.setBounds(652, 12, 17, 16);
		contentPane.add(lblM);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(589, 12, 51, 16);
		contentPane.add(lblGender);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(6, 51, 51, 16);
		contentPane.add(lblHeight);
		
		JLabel label = new JLabel(pinfo.height);
		label.setBounds(69, 51, 29, 16);
		contentPane.add(label);
		
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(110, 51, 51, 16);
		contentPane.add(lblWeight);
		
		JLabel label_1 = new JLabel(pinfo.weight);
		label_1.setBounds(173, 51, 29, 16);
		contentPane.add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 72, 663, 6);
		contentPane.add(separator);
		
		JLabel lblPrimary = new JLabel("Primary:");
		lblPrimary.setBounds(453, 31, 51, 16);
		contentPane.add(lblPrimary);
		
		JLabel lblJenniferHChantigan = new JLabel(pinfo.primaryProvider);
		lblJenniferHChantigan.setHorizontalAlignment(SwingConstants.TRAILING);
		lblJenniferHChantigan.setBounds(514, 31, 155, 16);
		contentPane.add(lblJenniferHChantigan);
		
		JLabel lblSecondary = new JLabel("Secondary:");
		lblSecondary.setBounds(434, 51, 70, 16);
		contentPane.add(lblSecondary);
		
		JLabel lblJenniferHChatigan = new JLabel(pinfo.secondaryProvider);
		lblJenniferHChatigan.setHorizontalAlignment(SwingConstants.TRAILING);
		lblJenniferHChatigan.setBounds(514, 51, 155, 16);
		contentPane.add(lblJenniferHChatigan);
		
		JLabel lblConditions = new JLabel("Conditions");
		lblConditions.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblConditions.setBounds(6, 90, 89, 16);
		contentPane.add(lblConditions);
		
		JTextArea txtConditions = new JTextArea();
		txtConditions.setEditable(false);
		String tmp = "";
		for (int i = 0; i < pinfo.conditions.size(); i++) {
			tmp += pinfo.conditions.get(i) + "\n";
		}
		txtConditions.setText(tmp);
		txtConditions.setBounds(6, 118, 155, 200);
		contentPane.add(txtConditions);
		
		JLabel lblAssitiveDevices = new JLabel("Assitive Devices");
		lblAssitiveDevices.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAssitiveDevices.setBounds(171, 90, 135, 16);
		contentPane.add(lblAssitiveDevices);
		
		JTextArea txtDevices = new JTextArea();
		txtDevices.setEditable(false);
		tmp = "";
		for (int i = 0; i < pinfo.devices.size(); i++) {
			tmp += pinfo.devices.get(i) + "\n";
		}
		txtDevices.setText(tmp);
		txtDevices.setBounds(171, 118, 170, 200);
		contentPane.add(txtDevices);
		
		JLabel lblVisits = new JLabel("Visits");
		lblVisits.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblVisits.setBounds(353, 90, 180, 16);
		contentPane.add(lblVisits);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(353, 118, 316, 200);
		contentPane.add(scrollPane);
		
		tableVisits = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(tableVisits);
		tableVisits.setRowSelectionAllowed(true);
		tableVisits.setShowVerticalLines(false);
		tableVisits.getColumnModel().getColumn(0).setPreferredWidth(10);
				tableVisits.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							JTable target = (JTable) e.getSource();
							int row = target.getSelectedRow();
							Integer patientId = Integer.parseInt((String) tableVisits.getModel().getValueAt(row, 0));
		
							SearchGUIPatientInfo frame = new SearchGUIPatientInfo(patientId);
							frame.setVisible(true);
		
						}
					}
				});
		
				
				tableModel.setRowCount(0);

				List<Vector<String>> visitData = null;

				visitData = pinfo.visits;

				System.out.println("Number of data tiems:"
						+ visitData.size());

				for (int i = 0; i < visitData.size(); ++i) {
					tableModel.addRow(visitData.get(i));
				}
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 330, 663, 6);
		contentPane.add(separator_1);
	}
}
