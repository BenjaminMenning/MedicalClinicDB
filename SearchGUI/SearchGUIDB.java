package MedicalClinicDB.SearchGUI;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SearchGUIDB {
	private Map<String, String> terms;
	private List<Vector<String>> result = new ArrayList<>();

	private String className = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://cs485project.cloudapp.net:3307/cs485_user";
	private String databaseUsername = "cs485_user";
	private String databasePassword = "Databas3";
	private String basePatientQuery = "SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate, hp.firstName AS providerFirstName, hp.middleName AS providerMiddleName, hp.lastName AS providerLastName \n"
			+ "FROM Patient AS p \n"
			+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
			+ "ON p.patientID = phpx.patientID \n"
			+ "JOIN HealthcareProvider AS hp \n"
			+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n";
	private Connection connection = null;
	private PreparedStatement ps = null;


	public void connectToDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(databaseURL,
					databaseUsername, databasePassword);
			System.out.println("Connection Successful");
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
	}

	protected void patientSearch(Map<String, String> terms) throws SQLException {
		this.terms = terms;
		
		connectToDatabase();

		if (!terms.get("ICD9 Diagnosis").isEmpty()) {
			try {
				ps = connection
						.prepareStatement("SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate, hp.firstName AS providerFirstName, hp.middleName AS providerMiddleName, hp.lastName AS providerLastName \n"
								+ "FROM Patient AS p \n"
								+ "JOIN Visit AS v \n"
								+ "ON p.patientID = v.patientID \n"
								+ "JOIN VisitDiagnosis_xref AS vdx \n"
								+ "ON v.visitID = vdx.visitID \n"
								+ "JOIN Diagnosis AS d \n"
								+ "ON d.diagnosisID = vdx.diagnosisID \n"
								+ "JOIN ICD9Diagnosis AS icdd \n"
								+ "ON d.icd9DiagnosisID = icdd.icd9DiagnosisID \n"
								+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
								+ "ON p.patientID = phpx.patientID \n"
								+ "JOIN HealthcareProvider AS hp \n"
								+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n"
								+ "WHERE icdd.icd9Code LIKE(?) OR icdd.icd9Description LIKE(?)"
								+ "GROUP BY p.patientID");

				ps.setString(1, "%" + terms.get("ICD9 Diagnosis") + "%");
				ps.setString(2, "%" + terms.get("ICD9 Diagnosis") + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (!terms.get("ICD9 Procedure").isEmpty()) {
			try {
				ps = connection
						.prepareStatement("SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate, hp.firstName AS providerFirstName, hp.middleName AS providerMiddleName, hp.lastName AS providerLastName \n"
								+ "FROM Patient AS p \n"
								+ "JOIN Visit AS v \n"
								+ "ON p.patientID = v.patientID \n"
								+ "JOIN VisitTreatment_xref AS vtx \n"
								+ "ON v.visitID = vtx.visitID \n"
								+ "JOIN Treatment AS t \n"
								+ "ON t.treatmentID = vtx.treatmentID \n"
								+ "JOIN TreatmentICD9Procedure_xref AS ticdpx \n"
								+ "ON t.treatmentID = ticdpx.treatmentID \n"
								+ "JOIN ICD9Procedure AS icdp \n"
								+ "ON ticdpx.icd9ProcedureID = icdp.icd9ProcedureID \n"
								+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
								+ "ON p.patientID = phpx.patientID \n"
								+ "JOIN HealthcareProvider AS hp \n"
								+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n"
								+ "WHERE icdp.icd9Code LIKE(?) OR icdp.icd9Description LIKE(?)"
								+ "GROUP BY p.patientID");

				ps.setString(1, "%" + terms.get("ICD9 Procedure") + "%");
				ps.setString(2, "%" + terms.get("ICD9 Procedure") + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (!terms.get("Condition").isEmpty()) {
			try {
				ps = connection
						.prepareStatement("SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate, hp.firstName AS providerFirstName, hp.middleName AS providerMiddleName, hp.lastName AS providerLastName \n"
								+ "FROM Patient AS p \n"
								+ "JOIN PatientCondition_xref AS pcx \n"
								+ "ON p.patientID = pcx.patientID \n"
								+ "JOIN `Condition` AS c \n"
								+ "ON c.conditionID = pcx.conditionID \n"
								+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
								+ "ON p.patientID = phpx.patientID \n"
								+ "JOIN HealthcareProvider AS hp \n"
								+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n"
								+ "WHERE c.conditionName LIKE(?)"
								+ "GROUP BY p.patientID");

				ps.setString(1, "%" + terms.get("Condition") + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!terms.get("Study").isEmpty()) {
			try {
				ps = connection
						.prepareStatement("SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate, hp.firstName AS providerFirstName, hp.middleName AS providerMiddleName, hp.lastName AS providerLastName \n"
								+ "FROM Patient AS p \n"
								+ "JOIN Visit AS v \n"
								+ "ON p.patientID = v.patientID \n"
								+ "JOIN VisitStudy_xref AS vsx \n"
								+ "ON v.visitID = vsx.visitID \n"
								+ "JOIN Study AS s \n"
								+ "ON s.studyID = vsx.studyID \n"
								+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
								+ "ON p.patientID = phpx.patientID \n"
								+ "JOIN HealthcareProvider AS hp \n"
								+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n"
								+ "WHERE s.studyID LIKE(?) OR s.typeOfStudy LIKE(?)"
								+ "GROUP BY p.patientID");

				ps.setString(1, "%" + terms.get("Study") + "%");
				ps.setString(2, "%" + terms.get("Study") + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector<String> row = new Vector<>(9);
				int i = 1;
				while (i <= 6) {
					row.add(rs.getString(i++));
				}
				row.add(rs.getString(7) + " " + rs.getString(8) + " "
						+ rs.getString(9));
				result.add(row);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	public List<Vector<String>> getData() {
		return result;
	}

}
