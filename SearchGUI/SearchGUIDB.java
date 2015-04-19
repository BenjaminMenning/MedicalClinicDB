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

	public SearchGUIDB(Map<String, String> terms) {
		this.terms = terms;
		connectToDatabase();
		
		if (terms.containsKey("ICD9 Diagnosis")) {
			try {			
				ps = connection.prepareStatement("SELECT p.patientID, p.clinicNumber, p.firstName, p.lastName, p.gender, p.birthDate, hp.firstName AS providerFirstName, hp.middleName AS providerMiddleName, hp.lastName AS providerLastName \n" + 
						"FROM Patient AS p \n" +
						"JOIN Visit AS v \n" +
						"ON p.patientID = v.patientID \n" +
						"JOIN VisitDiagnosis_xref AS vdx \n" +
						"ON v.visitID = vdx.visitID \n" +
						"JOIN Diagnosis AS d \n" +
						"ON d.diagnosisID = vdx.diagnosisID \n" +
						"JOIN ICD9Diagnosis AS icdd \n" +
						"ON d.icd9DiagnosisID = icdd.icd9DiagnosisID \n"+ "" +
						"JOIN PatientHealthcareProvider_xref AS phpx \n"
						+ "ON p.patientID = phpx.patientID \n"
						+ "JOIN HealthcareProvider AS hp \n"
						+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n" +
						"WHERE icdd.icd9Code LIKE(?) OR icdd.icd9Description LIKE(?)" +
						"GROUP BY p.patientID");
				
				ps.setString(1, "%" + terms.get("ICD9 Diagnosis") + "%");
				ps.setString(2, "%" + terms.get("ICD9 Diagnosis") + "%");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			System.out.println(basePatientQuery);
		}

		if (terms.containsKey("ICD9 Procedure")) {

		}

		if (terms.containsKey("Condition")) {

		}

		if (terms.containsKey("Study")) {

		}

	}

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

	protected void patientSearch() throws SQLException {
		

		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector<String> row = new Vector<>(9);
				int i = 1;
				while (i <= 6) {
						row.add(rs.getString(i++));
				}
				row.add(rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9));
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
