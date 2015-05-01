package MedicalClinicDB.SearchGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SearchGUIMain {

	private JFrame frame;
	private JTextField txtFldDiagnosis;
	private JTable table;
	private JTextField txtFldFirstName;
	private JTextField txtFldProvider;
	private JTextField txtFldProcedure;
	private JTextField txtFldLastName;
	private JTextField txtFldSecondProvider;
	private JTextField txtFldStudy;
	private JTextField txtFldClinicNumber;
	private JTextField txtFldDOBStart;
	private JTextField txtFldCondition;
	private JTextField txtFldGender;
	private JLabel lblProcedure;
	private JLabel lblClinicNumber;
	private JLabel lblPatientName;
	private JLabel lblDateOfBirth;
	private JTextField txtFldDOBEnd;
	private JLabel lblGender;
	private JLabel lblCondition;
	private JLabel lblProvider;
	private JLabel lblSecondaryProvider;
	private JLabel lblResearchStudy;
	private JScrollPane scrollPane;

	private String[] columnNames = { "Patient ID", "Clinic Number",
			"First Name", "Last Name", "Gender", "DOB", "Provider" };

	private Object[][] data = { { "1", "0-000-000", "Testy", "McTester", "T",
			"William Williamson", "01/01/1978" } };

	private Map<String, String> terms = new HashMap<String, String>();

	private DefaultTableModel tableModel = new DefaultTableModel(data,
			columnNames) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchGUIMain window = new SearchGUIMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchGUIMain() {
		initialize();

		terms.put("First Name", "");
		terms.put("Last Name", "");
		terms.put("ICD9 Diagnosis", "");
		terms.put("ICD9 Procedure", "");
		terms.put("Condition", "");
		terms.put("Study", "");
		terms.put("DOB Start", "");
		terms.put("DOB End", "");
		terms.put("Clinic Number", "");
		terms.put("Provider", "");
		terms.put("Secondary Provider", "");
		terms.put("Gender", "");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtFldDiagnosis = new JTextField();
		txtFldDiagnosis.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldDiagnosis.setText(null);
				txtFldDiagnosis.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("ICD9 Diagnosis", txtFldDiagnosis.getText());
			}
		});
		txtFldDiagnosis.setText("ICD9 or Name");
		txtFldDiagnosis.setForeground(Color.LIGHT_GRAY);
		txtFldDiagnosis.setBounds(6, 76, 134, 28);
		frame.getContentPane().add(txtFldDiagnosis);
		txtFldDiagnosis.setColumns(10);

		JLabel lblNewLabel = new JLabel("Diagnosis");
		lblNewLabel.setBounds(6, 59, 63, 16);
		frame.getContentPane().add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 226, 688, 346);
		frame.getContentPane().add(scrollPane);

		table = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowSelectionAllowed(true);
		table.setShowVerticalLines(false);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					Integer patientId = Integer.parseInt((String) table.getModel().getValueAt(row, 0));
					
					SearchGUIPatientInfo frame = new SearchGUIPatientInfo(patientId);
					frame.setVisible(true);

				}
			}
		});
		scrollPane.setViewportView(table);

		txtFldFirstName = new JTextField();
		txtFldFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldFirstName.setText(null);
				txtFldFirstName.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("First Name", txtFldFirstName.getText());
			}
		});
		txtFldFirstName.setForeground(Color.LIGHT_GRAY);
		txtFldFirstName.setText("First Name");
		txtFldFirstName.setColumns(10);
		txtFldFirstName.setBounds(6, 132, 150, 28);
		frame.getContentPane().add(txtFldFirstName);

		txtFldProvider = new JTextField();
		txtFldProvider.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldProvider.setText(null);
				txtFldProvider.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Provider", txtFldProvider.getText());
			}
		});
		txtFldProvider.setText("First or Last Name");
		txtFldProvider.setForeground(Color.LIGHT_GRAY);
		txtFldProvider.setColumns(10);
		txtFldProvider.setBounds(6, 186, 134, 28);
		frame.getContentPane().add(txtFldProvider);

		txtFldProcedure = new JTextField();
		txtFldProcedure.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldProcedure.setText(null);
				txtFldProcedure.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("ICD9 Procedure", txtFldProcedure.getText());
			}
		});
		txtFldProcedure.setForeground(Color.LIGHT_GRAY);
		txtFldProcedure.setText("ICD9 or Name");
		txtFldProcedure.setColumns(10);
		txtFldProcedure.setBounds(152, 76, 134, 28);
		frame.getContentPane().add(txtFldProcedure);

		txtFldLastName = new JTextField();
		txtFldLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldLastName.setText(null);
				txtFldLastName.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Last Name", txtFldLastName.getText());
			}
		});
		txtFldLastName.setForeground(Color.LIGHT_GRAY);
		txtFldLastName.setText("Last Name");
		txtFldLastName.setColumns(10);
		txtFldLastName.setBounds(152, 132, 134, 28);
		frame.getContentPane().add(txtFldLastName);

		txtFldSecondProvider = new JTextField();
		txtFldSecondProvider.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldSecondProvider.setText(null);
				txtFldSecondProvider.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Secondary Provider", txtFldSecondProvider.getText());
			}
		});
		txtFldSecondProvider.setText("First or Last Name");
		txtFldSecondProvider.setForeground(Color.LIGHT_GRAY);
		txtFldSecondProvider.setColumns(10);
		txtFldSecondProvider.setBounds(152, 186, 134, 28);
		frame.getContentPane().add(txtFldSecondProvider);

		txtFldStudy = new JTextField();
		txtFldStudy.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldStudy.setText(null);
				txtFldStudy.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Study", txtFldStudy.getText());
			}
		});
		txtFldStudy.setText("Study Name");
		txtFldStudy.setForeground(Color.LIGHT_GRAY);
		txtFldStudy.setColumns(10);
		txtFldStudy.setBounds(298, 186, 134, 28);
		frame.getContentPane().add(txtFldStudy);

		txtFldClinicNumber = new JTextField();
		txtFldClinicNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldClinicNumber.setText(null);
				txtFldClinicNumber.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Clinic Number", txtFldClinicNumber.getText());
			}
		});
		txtFldClinicNumber.setForeground(Color.LIGHT_GRAY);
		txtFldClinicNumber.setText("4-234-123");
		txtFldClinicNumber.setColumns(10);
		txtFldClinicNumber.setBounds(444, 76, 90, 28);
		frame.getContentPane().add(txtFldClinicNumber);

		txtFldDOBStart = new JTextField();
		txtFldDOBStart.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldDOBStart.setText(null);
				txtFldDOBStart.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("DOB Start", txtFldDOBStart.getText());
			}
		});
		txtFldDOBStart.setForeground(Color.LIGHT_GRAY);
		txtFldDOBStart.setText("Start");
		txtFldDOBStart.setToolTipText("MM/DD/YYYY");
		txtFldDOBStart.setColumns(10);
		txtFldDOBStart.setBounds(298, 132, 110, 28);
		frame.getContentPane().add(txtFldDOBStart);

		txtFldCondition = new JTextField();
		txtFldCondition.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldCondition.setText(null);
				txtFldCondition.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Condition", txtFldCondition.getText());
			}
		});
		txtFldCondition.setText("Condition Name");
		txtFldCondition.setForeground(Color.LIGHT_GRAY);
		txtFldCondition.setColumns(10);
		txtFldCondition.setBounds(298, 76, 134, 28);
		frame.getContentPane().add(txtFldCondition);

		txtFldGender = new JTextField();
		txtFldGender.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldGender.setText(null);
				txtFldGender.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("Gender", txtFldGender.getText());
			}
		});
		txtFldGender.setForeground(Color.LIGHT_GRAY);
		txtFldGender.setToolTipText("M, F, T, O");
		txtFldGender.setText("M");
		txtFldGender.setColumns(10);
		txtFldGender.setBounds(533, 132, 28, 28);
		frame.getContentPane().add(txtFldGender);

		lblProcedure = new JLabel("Procedure");
		lblProcedure.setBounds(152, 59, 63, 16);
		frame.getContentPane().add(lblProcedure);

		lblClinicNumber = new JLabel("Clinic Number");
		lblClinicNumber.setBounds(444, 59, 90, 16);
		frame.getContentPane().add(lblClinicNumber);

		lblPatientName = new JLabel("Patient Name");
		lblPatientName.setBounds(6, 116, 90, 16);
		frame.getContentPane().add(lblPatientName);

		lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(298, 116, 90, 16);
		frame.getContentPane().add(lblDateOfBirth);

		txtFldDOBEnd = new JTextField();
		txtFldDOBEnd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFldDOBEnd.setText(null);
				txtFldDOBEnd.setForeground(null);
			}

			@Override
			public void focusLost(FocusEvent e) {
				terms.put("DOB End", txtFldDOBEnd.getText());
			}
		});
		txtFldDOBEnd.setForeground(Color.LIGHT_GRAY);
		txtFldDOBEnd.setToolTipText("MM/DD/YYYY");
		txtFldDOBEnd.setText("End");
		txtFldDOBEnd.setColumns(10);
		txtFldDOBEnd.setBounds(407, 132, 110, 28);
		frame.getContentPane().add(txtFldDOBEnd);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(533, 116, 55, 16);
		frame.getContentPane().add(lblGender);

		lblCondition = new JLabel("Condition");
		lblCondition.setBounds(298, 59, 63, 16);
		frame.getContentPane().add(lblCondition);

		lblProvider = new JLabel("Provider");
		lblProvider.setBounds(6, 172, 90, 16);
		frame.getContentPane().add(lblProvider);

		lblSecondaryProvider = new JLabel("Secondary Provider");
		lblSecondaryProvider.setBounds(152, 172, 119, 16);
		frame.getContentPane().add(lblSecondaryProvider);

		lblResearchStudy = new JLabel("Research Study");
		lblResearchStudy.setBounds(298, 172, 110, 16);
		frame.getContentPane().add(lblResearchStudy);

		JButton btnSearchPatients = new JButton("Search Patients");
		btnSearchPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SearchGUIDB search = new SearchGUIDB();

				try {

					search.patientSearch(terms);

					tableModel.setRowCount(0);

					List<Vector<String>> searchData = null;

					searchData = search.getData();

					System.out.println("Number of data tiems:"
							+ searchData.size());

					for (int i = 0; i < searchData.size(); ++i) {
						tableModel.addRow(searchData.get(i));
					}

					// tableModel.fireTableDataChanged();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearchPatients.setBounds(560, 185, 134, 29);
		frame.getContentPane().add(btnSearchPatients);

		JLabel lblPatientSearch = new JLabel("Patient Search");
		lblPatientSearch.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblPatientSearch.setBounds(6, 17, 150, 30);
		frame.getContentPane().add(lblPatientSearch);
	}
}
