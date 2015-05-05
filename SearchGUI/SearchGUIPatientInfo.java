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

	private Object[][] data = { { "", "", "", "" } };

	private Map<String, String> terms = new HashMap<String, String>();

	private DefaultTableModel tableModel = new DefaultTableModel(data,
			columnNames) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	private String[] columnNamesTreatment = { "ID", "Treatment"};

	private Object[][] dataTreatment = { { "", "" } };
	
	private DefaultTableModel tableModelTreatment = new DefaultTableModel(dataTreatment,
			columnNamesTreatment) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	
	private JTable tblTreatment;
	private JLabel lblVisitNum = new JLabel();
	private JLabel lblVisitProvider = new JLabel();
	private JLabel lblAnalysis = new JLabel();
	private JLabel lblProcessing = new JLabel();
	JLabel lblVisitTestResults = new JLabel("");
	private JTextArea txtDiagnosis = new JTextArea();
	JTextArea txtStudy = new JTextArea();
	JTextArea txtSystem = new JTextArea();
	JTextArea txtProcedures = new JTextArea();
	JTextArea txtFiles = new JTextArea();
	
	SearchGUIPatientInfoDB pinfo = new SearchGUIPatientInfoDB();
	SearchGUIVisitInfoDB vinfo = new SearchGUIVisitInfoDB();
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
		setBounds(100, 100, 700, 825);
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
		
		JLabel lblConditions = new JLabel("Files");
		lblConditions.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblConditions.setBounds(191, 90, 89, 16);
		contentPane.add(lblConditions);
		
		JTextArea txtConditions = new JTextArea();
		txtConditions.setEditable(false);
		String tmp = "";
		for (int i = 0; i < pinfo.conditions.size(); i++) {
			tmp += pinfo.conditions.get(i) + "\n";
		}
		txtConditions.setText(tmp);
		txtConditions.setBounds(6, 118, 170, 80);
		contentPane.add(txtConditions);
		
		JLabel lblAssitiveDevices = new JLabel("Assitive Devices");
		lblAssitiveDevices.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAssitiveDevices.setBounds(6, 210, 135, 16);
		contentPane.add(lblAssitiveDevices);
		
		JTextArea txtDevices = new JTextArea();
		txtDevices.setEditable(false);
		tmp = "";
		for (int i = 0; i < pinfo.devices.size(); i++) {
			tmp += pinfo.devices.get(i) + "\n";
		}
		txtDevices.setText(tmp);
		txtDevices.setBounds(6, 238, 170, 80);
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
							Integer visitId = Integer.parseInt((String) tableVisits.getModel().getValueAt(row, 0));
							
							//empty field first
							lblVisitNum.setText(" ");
							lblVisitProvider.setText(" ");
							lblAnalysis.setText(" ");
							lblProcessing.setText(" ");
							lblVisitTestResults.setText(" ");
							txtDiagnosis.setText(" ");
							txtStudy.setText(" ");
							txtSystem.setText(" ");
							txtFiles.setText(" ");
							
							try {
								vinfo.getVisit(visitId);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							lblVisitNum.setText(vinfo.visitNumber);
							lblVisitProvider.setText(vinfo.provider);
							lblAnalysis.setText(vinfo.analysisDate);
							lblProcessing.setText(vinfo.processingDate);
							lblVisitTestResults.setText(vinfo.test);
							
							String tmp = "";
							for (int i = 0; i < vinfo.diagnosis.size(); i++) {
								tmp += vinfo.diagnosis.get(i) + "\n";
							}
							txtDiagnosis.setText(tmp);
							tmp = "";
							
							
							for (int i = 0; i < vinfo.study.size(); i++) {
								tmp += vinfo.study.get(i) + "\n";
							}
							txtStudy.setText(tmp);
							tmp = "";
							
							for (int i = 0; i < vinfo.system.size(); i++) {
								tmp += vinfo.system.get(i) + "\n";
							}
							txtSystem.setText(tmp);
							tmp = "";
							
							tableModelTreatment.setRowCount(0);

							List<Vector<String>> procedureData = null;

							procedureData = vinfo.treatment;

							for (int i = 0; i < procedureData.size(); ++i) {
								tableModelTreatment.addRow(procedureData.get(i));
							}
						}
					}
				});
		
				
				tableModel.setRowCount(0);

				List<Vector<String>> visitData = null;

				visitData = pinfo.visits;
				
				for (int i = 0; i < visitData.size(); ++i) {
					tableModel.addRow(visitData.get(i));
				}
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 330, 663, 6);
		contentPane.add(separator_1);
		
		JLabel lblVisitDetails = new JLabel("Visit Details");
		lblVisitDetails.setFont(new Font("Lucida Grande", Font.ITALIC, 18));
		lblVisitDetails.setBounds(6, 348, 111, 27);
		contentPane.add(lblVisitDetails);
		
		
		lblVisitNum.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVisitNum.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblVisitNum.setBounds(558, 355, 111, 16);
		contentPane.add(lblVisitNum);
		
		
		lblVisitProvider.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVisitProvider.setBounds(514, 377, 155, 16);
		contentPane.add(lblVisitProvider);
		
		JLabel lblAnalysisComplete = new JLabel("Analysis Complete:");
		lblAnalysisComplete.setBounds(6, 377, 121, 16);
		contentPane.add(lblAnalysisComplete);
		
		JLabel lblProcessingComplete = new JLabel("Processing Complete:");
		lblProcessingComplete.setBounds(6, 396, 136, 16);
		contentPane.add(lblProcessingComplete);
		
		
		lblAnalysis.setBounds(151, 377, 80, 16);
		contentPane.add(lblAnalysis);
		
		
		lblProcessing.setBounds(151, 396, 80, 16);
		contentPane.add(lblProcessing);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 457, 335, 95);
		contentPane.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(txtDiagnosis);
		txtDiagnosis.setEditable(false);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(353, 457, 155, 95);
		contentPane.add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(txtStudy);
		txtStudy.setEditable(false);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(514, 457, 155, 95);
		contentPane.add(scrollPane_3);
		
		
		scrollPane_3.setViewportView(txtSystem);
		txtSystem.setEditable(false);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDiagnosis.setBounds(6, 434, 89, 22);
		contentPane.add(lblDiagnosis);
		
		JLabel lblTreatment = new JLabel("Treatment");
		lblTreatment.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTreatment.setBounds(6, 564, 89, 16);
		contentPane.add(lblTreatment);
		
		JLabel lblStudy = new JLabel("Study\n");
		lblStudy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblStudy.setBounds(350, 437, 51, 16);
		contentPane.add(lblStudy);
		
		JLabel lblSystem = new JLabel("System\n");
		lblSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSystem.setBounds(511, 437, 61, 16);
		contentPane.add(lblSystem);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_5.setBounds(353, 586, 316, 97);
		contentPane.add(scrollPane_5);
		
		
		scrollPane_5.setViewportView(txtProcedures);
		txtProcedures.setEditable(false);
		
		JLabel lblTreatmentDetails = new JLabel("Treatment Procedures");
		lblTreatmentDetails.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTreatmentDetails.setBounds(353, 565, 180, 16);
		contentPane.add(lblTreatmentDetails);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_4.setBounds(6, 585, 335, 98);
		contentPane.add(scrollPane_4);
		
		tblTreatment = new JTable(tableModelTreatment) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblTreatment.setRowSelectionAllowed(true);
		tblTreatment.setShowVerticalLines(false);
		tblTreatment.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblTreatment.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							JTable target = (JTable) e.getSource();
							int row = target.getSelectedRow();
							Integer treatmentId = Integer.parseInt((String) tblTreatment.getModel().getValueAt(row, 0));
							
							txtProcedures.setText("");
							
							try {
								vinfo.getProcedure(treatmentId);
								
								String tmp = "";
								for (int i = 0; i < vinfo.treatment.size(); i++) {
									tmp += vinfo.treatment.get(i) + "\n";
								}
								txtProcedures.setText(tmp);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		
						}
					}
				});
		
		scrollPane_4.setViewportView(tblTreatment);		
		
		JLabel label_2 = new JLabel("Conditions");
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		label_2.setBounds(6, 90, 89, 16);
		contentPane.add(label_2);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_6.setBounds(191, 118, 150, 200);
		contentPane.add(scrollPane_6);
		
		tmp = "";
		for (int i = 0; i < pinfo.files.size(); i++) {
			tmp += pinfo.files.get(i) + "\n";
		}
		scrollPane_6.setViewportView(txtFiles);
		txtFiles.setText(tmp);
		txtFiles.setEditable(false);
		
		JLabel lblTestResults = new JLabel("Test Results:");
		lblTestResults.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTestResults.setBounds(6, 695, 111, 16);
		contentPane.add(lblTestResults);
		
		
		lblVisitTestResults.setBounds(110, 695, 559, 16);
		contentPane.add(lblVisitTestResults);
		
	}
}
