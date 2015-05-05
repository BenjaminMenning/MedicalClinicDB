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
	private String baseQuerySelect = "SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate \n"
			+ "FROM Patient AS p \n";
	private String baseQueryJoin = "";
	// private String baseQueryLastJoin =
	// "JOIN PatientHealthcareProvider_xref AS phpx \n"
	// + "ON p.patientID = phpx.patientID \n"
	// + "JOIN HealthcareProvider AS hp \n"
	// + "ON hp.healthcareProviderID = phpx.healthcareProviderID \n";
	private String baseQueryWhere = "WHERE 1 \n";
	private String baseQueryGroup = "GROUP BY p.patientID";
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

		// Check if a Visit join is needed
		if (!terms.get("ICD9 Diagnosis").isEmpty()
				|| !terms.get("ICD9 Procedure").isEmpty()
				|| !terms.get("Study").isEmpty()) {

			// Join Visit table
			baseQueryJoin += "JOIN Visit AS v \n"
					+ "ON p.patientID = v.patientID \n";

			// Check if ICD9 Diagnosis is needed
			if (!terms.get("ICD9 Diagnosis").isEmpty()) {
				baseQueryJoin += "JOIN VisitDiagnosis_xref AS vdx \n"
						+ "ON v.visitID = vdx.visitID \n"
//						+ "JOIN Diagnosis AS d \n"
//						+ "ON d.diagnosisID = vdx.diagnosisID \n"
						+ "JOIN ICD9Diagnosis AS icdd \n"
						+ "ON icdd.icd9DiagnosisID = vdx.icd9DiagnosisID \n";

				baseQueryWhere += "AND (icdd.icd9Code LIKE('%"
						+ terms.get("ICD9 Diagnosis")
						+ "%') OR icdd.icd9Description LIKE('%"
						+ terms.get("ICD9 Diagnosis") + "%')) \n";
			}

			// Check if ICD9 Procedure is needed
			if (!terms.get("ICD9 Procedure").isEmpty()) {

				baseQueryJoin += "JOIN VisitTreatment_xref AS vtx \n"
						+ "ON v.visitID = vtx.visitID \n"
						+ "JOIN Treatment AS t \n"
						+ "ON t.treatmentID = vtx.treatmentID \n"
						+ "JOIN TreatmentICD9Procedure_xref AS ticdpx \n"
						+ "ON t.treatmentID = ticdpx.treatmentID \n"
						+ "JOIN ICD9Procedure AS icdp \n"
						+ "ON ticdpx.icd9ProcedureID = icdp.icd9ProcedureID \n";

				baseQueryWhere += "AND (icdp.icd9Code LIKE('%"
						+ terms.get("ICD9 Procedure")
						+ "%') OR icdp.icd9Description LIKE('%"
						+ terms.get("ICD9 Procedure") + "%')) \n";
			}

			// Check if Study is needed
			if (!terms.get("Study").isEmpty()) {

				baseQueryJoin += "JOIN VisitStudy_xref AS vsx \n"
						+ "ON v.visitID = vsx.visitID \n"
						+ "JOIN Study AS s \n"
						+ "ON s.studyID = vsx.studyID \n";

				baseQueryWhere += "AND (s.studyID LIKE('%" + terms.get("Study")
						+ "%') OR s.typeOfStudy LIKE('%" + terms.get("Study")
						+ "%')) \n";
			}
		}

		// Check if Condition is needed
		if (!terms.get("Condition").isEmpty()) {

			baseQueryJoin += "JOIN PatientCondition_xref AS pcx \n"
					+ "ON p.patientID = pcx.patientID \n"
					+ "JOIN `Condition` AS c \n"
					+ "ON c.conditionID = pcx.conditionID \n";

			baseQueryWhere += "AND c.conditionName LIKE('%"
					+ terms.get("Condition") + "%') \n";
		}

		// Check for First Name
		if (!terms.get("First Name").isEmpty()) {
			baseQueryWhere += "AND p.firstName LIKE('%"
					+ terms.get("First Name") + "%') \n";
		}

		// Check for Last Name
		if (!terms.get("Last Name").isEmpty()) {
			baseQueryWhere += "AND p.lastName LIKE('%" + terms.get("Last Name")
					+ "%') \n";
		}

		// Check for Clinic Number
		if (!terms.get("Clinic Number").isEmpty()) {
			baseQueryWhere += "AND p.clinicNumber LIKE('%"
					+ terms.get("Clinic Number") + "%') \n";
		}

		// Check for Provider
		if (!terms.get("Provider").isEmpty()
				|| !terms.get("Secondary Provider").isEmpty()) {
			baseQueryJoin += "JOIN PatientHealthcareProvider_xref AS phpx \n"
					+ "ON p.patientID = phpx.patientID \n"
					+ "JOIN HealthcareProvider AS hp \n"
					+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n";
			
			//Check for Primary Provider
			if (!terms.get("Provider").isEmpty()) {
				baseQueryWhere += "AND (hp.firstName LIKE('"
						+ terms.get("Provider") + "%') OR hp.lastName LIKE('"
						+ terms.get("Provider") + "%')) \n";
				baseQueryWhere += "AND phpx.healthcareProviderType = 'Primary'";
			}

			// Check for Secondary Provider
			if (!terms.get("Secondary Provider").isEmpty()) {
				baseQueryWhere += "AND (hp.firstName LIKE('"
						+ terms.get("Secondary Provider")
						+ "%') OR hp.lastName LIKE('"
						+ terms.get("Secondary Provider") + "%')) \n";
				baseQueryWhere += "AND phpx.healthcareProviderType = 'Secondary'";
			}
		}
		// Check for Gender
		if (!terms.get("Gender").isEmpty()) {
			baseQueryWhere += "AND p.gender LIKE('%" + terms.get("Gender")
					+ "%') \n";
		}

		// Check for DOB Start
		if (!terms.get("DOB Start").isEmpty()) {
			baseQueryWhere += "AND p.birthdate >= '" + terms.get("DOB Start")
					+ "' \n";
		}

		// Check for DOB End
		if (!terms.get("DOB End").isEmpty()) {
			baseQueryWhere += "AND p.birthdate <= '" + terms.get("DOB End")
					+ "' \n";
		}

		try {
			// String query = baseQuerySelect + baseQueryJoin + baseQueryLastJoin
			//		+ baseQueryWhere + baseQueryGroup;
			String query = baseQuerySelect + baseQueryJoin + baseQueryWhere + baseQueryGroup;
			System.out.println(query);
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				Vector<String> row = new Vector<>(6);
				int i = 1;
				while (i <= 6) {
					row.add(rs.getString(i++));
				}
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
		if (result.size() == 0) {
			Vector<String> row = new Vector<>(1);
			row.add("No Results.");
			result.add(row);
		}
		return result;
	}

}
