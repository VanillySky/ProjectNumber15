package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import client.ChatClient;
import entities.Teacher;
import entities.User;
import entities.Exam;
import entities.Question;
import entities.Student;
import entities.StudentExamanation;

public class SQLConnection {
	private static Connection conn = null;

	public static void connecttoDB() throws ParseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex2) {
			System.out.println("Driver definition failed");
		}
		try {

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root",
					"Ahmf1144");
			// conn =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST","root","IbraPro1234");

			// conn =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST",
			// "root","Ahmf1144");
			// conn =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST","root","IbraPro1234");
		//	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root",
			//		"Shaden#2034");
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/*
	 * public static void saveUserToDB(final ArrayList<String> list) { if (conn !=
	 * null) { try { PreparedStatement stmt =
	 * conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?);");
	 * stmt.setString(1, list.get(0)); stmt.setString(2, list.get(1));
	 * stmt.setString(3, list.get(2)); stmt.setString(4, list.get(3));
	 * stmt.setString(5, list.get(4)); stmt.setString(6, list.get(5));
	 * stmt.setString(7, list.get(6)); stmt.executeUpdate(); } catch (SQLException
	 * e) { e.printStackTrace(); } } }
	 * 
	 * public static void UpdateOnDB(String id, String Field2, String newValue) { if
	 * (conn != null) { try { String str = "UPDATE Test set "; str =
	 * String.valueOf(str) + Field2; str = String.valueOf(str) + "= '"; str =
	 * String.valueOf(str) + newValue; str = String.valueOf(str) +
	 * "' Where userName = '"; str = String.valueOf(str) + id; str =
	 * String.valueOf(str) + "'"; final Statement stmt = conn.createStatement();
	 * stmt.executeUpdate(str); System.out.println("Update Successfuly "); } catch
	 * (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * public static String ShowExamInformation(String Id) { String str = ""; if
	 * (conn != null) { try { String query = "Select * FROM Test WHERE TestID = '";
	 * query = String.valueOf(query) + Id; query = String.valueOf(query) + "' ;";
	 * final Statement st = conn.createStatement(); final ResultSet rs =
	 * st.executeQuery(query); while (rs.next()) { str = String.valueOf(str) +
	 * rs.getString("TestOD"); str = String.valueOf(str) + " "; str =
	 * String.valueOf(str) + rs.getString("Subject"); str = String.valueOf(str) +
	 * " "; str = String.valueOf(str) + rs.getString("Course"); str =
	 * String.valueOf(str) + " "; str = String.valueOf(str) +
	 * rs.getString("ExamTime"); str = String.valueOf(str) + " "; str =
	 * String.valueOf(str) + rs.getString("QuestionsPoints"); } st.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } return str; }
	 */
	public static Connection getConn() {
		return conn;
	}

	public static User checkUser(ArrayList<Object> arr) {
		String username = (String) arr.get(0);
		String password = (String) arr.get(1);
		User user = null;

		if (conn != null)
			try {

				String query = "Select * FROM users WHERE userName = '" + username + "'AND password = '" + password
						+ "'";
				Statement st = conn.createStatement();

				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String userId = rs.getString("userId");
					String email = rs.getString("email");
					String role = rs.getString("role");

					switch (role) {
					case "Teacher":
						return new Teacher(username, password, firstName, lastName, userId, email);

					case "Student":
						return new Student(username, password, firstName, lastName, userId, email);
					case "Manager":
					default:
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
	}

	public static ArrayList<Exam> getAllexams() {
		ArrayList<Exam> array = new ArrayList<Exam>();
		if (conn != null) {
			try {
				String query = "Select * FROM exams";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Exam ex = new Exam(rs.getString("ExamCode"), rs.getString("ExamNumber"),
							rs.getString("ExamSubject"), rs.getString("ExamCourse"), rs.getString("ExamTime"),
							rs.getString("TeacherName"), rs.getString("ChosenQuestion"), rs.getString("QuestionPoint"),
							rs.getString("StudentInstructions"), rs.getString("TeacherInstructions"));
					array.add(ex);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	public static ArrayList<Question> getAllquestions() {
		ArrayList<Question> array = new ArrayList<Question>();
		if (conn != null) {
			try {
				String query = "Select * FROM questions";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Question qu = new Question(rs.getString("QuestionCode"), rs.getString("QuestionNumber"),
							rs.getString("Subject"), rs.getString("Question"), rs.getString("QuestionInstruction"),
							rs.getString("Answer1"), rs.getString("Answer2"), rs.getString("Answer3"),
							rs.getString("Answer4"), rs.getString("RightAnswer"), rs.getString("Author"),
							rs.getString("point"));
					array.add(qu);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}


	public static ArrayList<StudentExamanation> getTime() {
		ArrayList<StudentExamanation> array = new ArrayList<StudentExamanation>();
		if (conn != null) {
			try {
				String query = "Select * FROM studentexam WHERE StudenUserName + '"
						+ ChatClient.currentUser.getUserName() + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentExamanation SE = new StudentExamanation(rs.getString("StudneUserName"), rs.getString("ExamCode"),
							rs.getString("ExamHours"),rs.getString("ExamMinutes"));
					array.add(SE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	public static void DeleteExam(ArrayList<Object> arr) {

		String ExamCode = (String) arr.get(0);
		if (conn != null)
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM exams  WHERE ExamCode = ?");
				ps.setString(1, ExamCode);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void DeleteQuestion(ArrayList<Object> arr) {

		String questioncode = (String) arr.get(0);
		if (conn != null)
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM questions  WHERE QuestionCode = ?");
				ps.setString(1, questioncode);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static boolean AddNewQuestion(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn
						.prepareStatement("INSERT INTO questions VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
				stmt.setString(1, ((Question) list.get(0)).getQuestionCode());
				stmt.setString(2, ((Question) list.get(0)).getQuestionNumber());
				stmt.setString(3, ((Question) list.get(0)).getSubject());

				stmt.setString(4, ((Question) list.get(0)).getQuestion());

				stmt.setString(5, ((Question) list.get(0)).getQuestionInstruction());

				stmt.setString(6, ((Question) list.get(0)).getAnswer1());

				stmt.setString(7, ((Question) list.get(0)).getAnswer2());

				stmt.setString(8, ((Question) list.get(0)).getAnswer3());

				stmt.setString(9, ((Question) list.get(0)).getAnswer4());

				stmt.setString(10, ((Question) list.get(0)).getRightAnswer());

				stmt.setString(11, ((Question) list.get(0)).getAuthor());

				stmt.setString(12, ((Question) list.get(0)).getPoint());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean AddNewExam(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("INSERT INTO exams VALUES (?,?,?,?,?,?,?,?,?,?);");
				stmt.setString(1, ((Exam) list.get(0)).getExamCode());
				stmt.setString(2, ((Exam) list.get(0)).getExamNumber());
				stmt.setString(3, ((Exam) list.get(0)).getExamSubject());

				stmt.setString(4, ((Exam) list.get(0)).getExamCourse());

				stmt.setString(5, ((Exam) list.get(0)).getExamTime());

				stmt.setString(6, ((Exam) list.get(0)).getTeacherName());

				stmt.setString(7, ((Exam) list.get(0)).getChosenQuestion());

				stmt.setString(8, ((Exam) list.get(0)).getQuestionPoint());

				stmt.setString(9, ((Exam) list.get(0)).getStudentInstructions());

				stmt.setString(10, ((Exam) list.get(0)).getTeacherInstructions());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean UpgradeExam(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement(
						"UPDATE exams Set ExamTime = ?  , ChosenQuestion=? , QuestionPoint = ? , StudentInstructions = ? , TeacherInstructions=? WHERE ExamCode = ? ");
				
				
				stmt.setString(1, ((Exam) list.get(0)).getExamTime());

				stmt.setString(2, ((Exam) list.get(0)).getChosenQuestion());

				stmt.setString(3, ((Exam) list.get(0)).getQuestionPoint());

				stmt.setString(4, ((Exam) list.get(0)).getStudentInstructions());

				stmt.setString(5, ((Exam) list.get(0)).getTeacherInstructions());
				stmt.setString(6, ((Exam) list.get(0)).getExamCode());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	
	public static boolean UpgradeQuestion(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement(
						"UPDATE questions Set Question = ?  , QuestionInstruction=? , Answer1 = ? , Answer2 = ? , Answer3 = ? , Answer4 = ? , RightAnswer=? , point=?  WHERE QuestionCode = ? ");
				
				
				stmt.setString(1, ((Question) list.get(0)).getQuestion());

				stmt.setString(2, ((Question) list.get(0)).getQuestionInstruction());

				stmt.setString(3, ((Question) list.get(0)).getAnswer1());

				stmt.setString(4, ((Question) list.get(0)).getAnswer2());

				stmt.setString(5, ((Question) list.get(0)).getAnswer3());
				
				stmt.setString(6, ((Question) list.get(0)).getAnswer4());
				
				stmt.setString(7, ((Question) list.get(0)).getRightAnswer());

				stmt.setString(8, ((Question) list.get(0)).getPoint());
				
				stmt.setString(9, ((Question) list.get(0)).getQuestionCode());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
}
