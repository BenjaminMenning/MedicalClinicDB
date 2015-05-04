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

public class SearchGUIVisitInfoDB {
	
	protected List<Vector<String>> treatment = new ArrayList<>();
	protected List<String> procedure = new ArrayList<>();
	protected List<String> diagnosis = new ArrayList<>();
	protected List<String> study = new ArrayList<>();
	protected List<String> system = new ArrayList<>();
	protected String visitNumber = null, visitDate = null, provider = null, analysisDate = null,
					processingDate = null, test = null;
	
	private String className = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://cs485project.cloudapp.net:3307/cs485_user";
	private String databaseUsername = "cs485_user";
	private String databasePassword = "Databas3";
	private Connection connection = null;
	private PreparedStatement ps = null;
	
	
	protected void getVisit(int id) throws SQLException {
		String visitQuery = "SELECT v.visitNumber, v.visitDate, v.dateAnalysisComplete, v.dateProcessingComplete, hp.firstName, hp.MiddleName, hp.LastName \n"
				+ "FROM Visit AS v \n" 
				+ "JOIN HealthcareProvider AS hp \n"
				+ "ON hp.healthcareProviderID = v.healthcareProviderID \n"
				+ "WHERE v.visitID = "
				+ id;
		
		String diagnosisQuery = "SELECT icdd.icd9Code, icdd.icd9Description \n"
				+ "FROM ICD9Diagnosis AS icdd \n"
				+ "JOIN VisitDiagnosis_xref AS vdx \n"
				+ "ON icdd.icd9DiagnosisID = vdx.icd9DiagnosisID \n"
				+ "WHERE vdx.visitID = "
				+ id;
		
		String studyQuery = "SELECT s.studyID, s.typeOfStudy \n"
				+ "FROM Study AS s \n"
				+ "JOIN VisitStudy_xref AS vsx \n"
				+ "ON s.studyID = vsx.studyID \n"
				+ "WHERE vsx.visitID = "
				+ id;
		
		String SystemQuery = "SELECT s.systemID, s.systemUsed \n"
				+ "FROM System AS s \n"
				+ "JOIN VisitSystem_xref AS vsx \n"
				+ "ON s.systemID = vsx.systemID \n"
				+ "WHERE vsx.systemID = "
				+ id;
		
		String treatmentQuery = "SELECT t.treatmentID, t.treatmentName \n"
				+ "FROM Treatment AS t \n"
				+ "JOIN VisitTreatment_xref AS vtx \n"
				+ "ON t.treatmentID = vtx.treatmentID \n"
				+ "WHERE vtx.visitID = "
				+ id;
		
		String testQuery = "SELECT testResults1, testResults2, testResults3, testResults4 \n"
				+ "FROM TestResults \n"
				+ "WHERE testResultsID = "
				+ id;
		
		connectToDatabase();
		
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(visitQuery);
			
			if(rs.next()) {
				visitNumber = rs.getString("visitNumber");
				provider = rs.getString("lastName") + ", " + rs.getString("firstName") + " " + rs.getString("middleName");
				visitDate = rs.getString("visitDate");
				analysisDate = rs.getString("dateAnalysisComplete");
				processingDate = rs.getString("dateProcessingComplete");
			}
			
			rs = s.executeQuery(diagnosisQuery);
			diagnosis.clear();
			while(rs.next()) {
				diagnosis.add(rs.getString("icd9Code") + " - " + rs.getString(("icd9Description")));
			}
			
			rs = s.executeQuery(studyQuery);
			study.clear();
			while(rs.next()) {
				study.add(rs.getString("studyID") + " - " + rs.getString(("typeOfStudy")));
			}
			
			rs = s.executeQuery(SystemQuery);
			system.clear();
			while(rs.next()) {
				system.add(rs.getString("systemID") + " - " + rs.getString(("systemUsed")));
			}
			
			rs = s.executeQuery(testQuery);
			test = null;
			while(rs.next()) {
				test = rs.getString("testResults1");
				
				if (!rs.getString("testResults2").isEmpty()) {
					test += ", " + rs.getString("testResults2");
				}
				
				if (!rs.getString("testResults3").isEmpty()) {
					test += ", " + rs.getString("testResults3");
				}
				
				if (!rs.getString("testResults4").isEmpty()) {
					test += ", " + rs.getString("testResults4");
				}
			}
			
			rs = s.executeQuery(treatmentQuery);
			treatment.clear();
			while(rs.next()) {
				Vector<String> row = new Vector<>(4);
				row.add(rs.getString("treatmentID"));
				row.add(rs.getString("treatmentName"));
				treatment.add(row);
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}
	
	protected void getProcedure(int id) throws SQLException {
		
		String procedureQuery = "SELECT icdp.icd9Code, icdp.icd9Description \n"
				+ "FROM ICD9Procedure AS icdp \n"
				+ "JOIN TreatmentICD9Procedure_xref AS icdp \n"
				+ "ON icdp.icd9ProcedureID = icdp.icd9ProcedureID \n"
				+ "WHERE icdp.treatmentID = "
				+ id;
		
		connectToDatabase();
		
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(procedureQuery);
			procedure.clear();
			while(rs.next()) {
				procedure.add(rs.getString("icd9Code") + " - " + rs.getString(("icd9Description")));
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
