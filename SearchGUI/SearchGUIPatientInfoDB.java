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

public class SearchGUIPatientInfoDB {
	protected List<Vector<String>> visits = new ArrayList<>();
	protected List<String> conditions = new ArrayList<>();
	protected List<String> devices = new ArrayList<>();
	protected List<String> files = new ArrayList<>();
	protected String patientID = null, clinicNumber = null, name = null,
			gender = null, birthdate = null, height = null, weight = null,
			primaryProvider = null, secondaryProvider = null;

	private String className = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://cs485project.cloudapp.net:3307/cs485_user";
	private String databaseUsername = "cs485_user";
	private String databasePassword = "Databas3";
	private Connection connection = null;
	private PreparedStatement ps = null;

	protected void getPatient(int id) throws SQLException {
		String patientQuery = "SELECT patientID, clinicNumber, firstName, lastName, middleName, gender, birthDate, height, weight\n"
				+ "FROM Patient\n" + "WHERE patientID = " + id;
		String primaryProviderQuery = "SELECT hp.firstName, hp.middleName, hp.lastName \n"
				+ "FROM HealthcareProvider as hp \n"
				+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
				+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n"
				+ "WHERE phpx.patientID = "
				+ id
				+ "\n AND healthcareProviderType = 'Primary'";
		String secondaryProviderQuery = "SELECT hp.firstName, hp.middleName, hp.lastName \n"
				+ "FROM HealthcareProvider as hp \n"
				+ "JOIN PatientHealthcareProvider_xref AS phpx \n"
				+ "ON hp.healthcareProviderID = phpx.healthcareProviderID \n"
				+ "WHERE phpx.patientID = "
				+ id
				+ "\n AND healthcareProviderType = 'Secondary'";
		String conditionsQuery = "SELECT c.conditionName \n"
				+ "FROM `Condition` as c \n"
				+ "JOIN PatientCondition_xref AS pc \n"
				+ "ON c.conditionID = pc.conditionID \n"
				+ "WHERE pc.patientID = " + id;
		String devicesQuery = "SELECT d.assistiveDeviceName\n"
				+ "FROM AssistiveDevice as d\n"
				+ "JOIN PatientAssistiveDevice_xref AS pd \n"
				+ "ON d.assistiveDeviceID = pd.assistiveDeviceID \n"
				+ "WHERE pd.patientID = " + id;
		String visitsQuery = "SELECT v.visitID, v.visitNumber, v.visitDate, v.dateAnalysisComplete, v.dateProcessingComplete, hp.firstName, hp.middleName, hp.lastName \n"
				+ "FROM Visit as v \n"
				+ "JOIN HealthcareProvider as hp \n"
				+ "ON v.healthcareProviderID = hp.healthcareProviderID \n"
				+ "WHERE v.patientID = " + id;
		String filesQuery = "SELECT f.fileName \n"
				+ "FROM File as f \n"
				+ "WHERE f.patientID = " + id;

		connectToDatabase();
		
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(patientQuery);
			
			if(rs.next()) {
				patientID = rs.getString("patientID");
				clinicNumber = rs.getString("clinicNumber");
				name = rs.getString("lastName") + ", " + rs.getString("firstName") + " " + rs.getString("middleName");
				gender = rs.getString("gender");
				birthdate = rs.getString("birthDate");
				height = rs.getString("height");
				weight = rs.getString("weight");
			}
			
			System.out.println(primaryProviderQuery);
			rs = s.executeQuery(primaryProviderQuery);
			
			if(rs.next()) {
				primaryProvider = rs.getString("lastName") + ", " + rs.getString("firstName") + " " + rs.getString("middleName");
			}
			
			rs = s.executeQuery(secondaryProviderQuery);
			
			if(rs.next()) {
				secondaryProvider = rs.getString("lastName") + ", " + rs.getString("firstName") + " " + rs.getString("middleName");
			}
			
			rs = s.executeQuery(conditionsQuery);
			
			while(rs.next()) {
				conditions.add(rs.getString("conditionName"));
			}
			
			rs = s.executeQuery(devicesQuery);
			
			while(rs.next()) {
				devices.add(rs.getString("assistiveDeviceName"));
			}
			
			rs = s.executeQuery(filesQuery);
			
			while(rs.next()) {
				files.add(rs.getString("fileName"));
			}
			
			rs = s.executeQuery(visitsQuery);
			
			while(rs.next()) {
				Vector<String> row = new Vector<>(4);
				row.add(rs.getString("visitID"));
				row.add(rs.getString("visitNumber"));
				row.add(rs.getString("visitDate"));
				row.add(rs.getString("lastName") + ", " + rs.getString("firstName") + " " + rs.getString("middleName"));
				visits.add(row);
			}
			
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
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

}
