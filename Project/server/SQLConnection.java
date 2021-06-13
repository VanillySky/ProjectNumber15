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
import entities.commonmistake;
import entities.Exam;
import entities.ExamResponse;
import entities.InExam;
import entities.Manager;
import entities.ManagerMessage;
import entities.Question;
import entities.StatusExam;
import entities.Student;
import entities.StudentExamanation;
import entities.StudentGrade;

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
//			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root","anitad31");

			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root",
					"Ahmf1144");
			// conn =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST","root","IbraPro1234");

			// conn =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST",
			// "root",
			// "Shaden#2034");

			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

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
						return new Manager(username, password, firstName, lastName, userId, email);
					default:
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return user;
	}

	public static String checkManualCode(ArrayList<Object> arr) {
		String ExamManCode = (String) arr.get(0);
		String ExamCode = "";
		if (conn != null)
			try {

				String query = "Select ExamCode FROM studentexamcode WHERE ExamManCode = '" + ExamManCode + "'";
				Statement st = conn.createStatement();

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					ExamCode = rs.getString("ExamCode");

					if (ExamCode != "")
						return ExamCode;

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return ExamCode;
	}
	
	public static String checkAutoCode(ArrayList<Object> arr) {
		String ExamAutoCode = (String) arr.get(0);
		String ExamCode = "";
		if (conn != null)
			try {

				String query = "Select ExamCode FROM studentexamcode WHERE ExamAutoCode = '" + ExamAutoCode + "'";
				Statement st = conn.createStatement();

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					ExamCode = rs.getString("ExamCode");

					if (ExamCode != "")
						return ExamCode;

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return ExamCode;
	}
	
	
	public static String getexamtime(ArrayList<Object> arr) {
		String ExamCode1 = (String) arr.get(0);
		String ExamTime = "";
		if (conn != null)
			try {

				String query = "Select ExamTime FROM exams WHERE ExamCode = '" + ExamCode1 + "'";
				Statement st = conn.createStatement();

				ResultSet rs = st.executeQuery(query);

				if (rs.next()) {
					ExamTime = rs.getString("ExamTime");

					if (ExamTime != "")
						return ExamTime;

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return ExamTime;
	}
	
	
	
	public static ArrayList<commonmistake> getAllCommonMistake() {
		ArrayList<commonmistake> array = new ArrayList<commonmistake>();
		if (conn != null) {
			try {
				String query = "Select * FROM commonmistakes";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					commonmistake CM = new commonmistake(rs.getString("ExamCode"), rs.getString("QuestionCode"),
							rs.getString("UserName1"), rs.getString("UserName2"));
					array.add(CM);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
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

	public static ArrayList<ManagerMessage> getManagerMessages() {
		ArrayList<ManagerMessage> array = new ArrayList<ManagerMessage>();
		if (conn != null) {
			try {
				String query = "Select * FROM managermessage";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ManagerMessage MM = new ManagerMessage(rs.getString("Examcode"), rs.getString("TeacherName"),
							rs.getString("addtime"), rs.getString("instruction"));
					array.add(MM);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	public static ArrayList<Exam> getOneExams(ArrayList<Object> arr) {
		ArrayList<Exam> array = new ArrayList<Exam>();
		String ExamCode = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM exams WHERE ExamCode = '" + ExamCode + "'";
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
	public static ArrayList<StatusExam> oneStatusExam(ArrayList<Object> arr) {
		ArrayList<StatusExam> array = new ArrayList<StatusExam>();
		String ExamCode = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM statusexam WHERE ExamCode = '" + ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StatusExam ex = new StatusExam(rs.getString("ExamCode"), rs.getString("NumberStartExam"),
							rs.getString("NumberEndExam"), rs.getString("time"), rs.getString("Date"));
					array.add(ex);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	

	public static ArrayList<Exam> getTeacherexams(ArrayList<Object> arr) {
		ArrayList<Exam> array = new ArrayList<Exam>();
		String TeacherExam = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM exams WHERE TeacherName = '" + TeacherExam + "'";
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

	public static ArrayList<Question> getOneQuestion(ArrayList<Object> arr) {
		ArrayList<Question> array = new ArrayList<Question>();
		String QuestionCode = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM questions WHERE QuestionCode = '" + QuestionCode + "'";
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

	public static ArrayList<StudentGrade> getAllgrades() {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		if (conn != null) {
			try {
				String query = "Select * FROM studentgrade";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {

					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("examCode"),
							rs.getString("examCourse"), rs.getString("examGrade"), rs.getString("TeacherName"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	public static ArrayList<StudentGrade> getAllApprovalegrades() {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		if (conn != null) {
			try {
				String query = "Select * FROM approvedstudentgrade";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {

					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("ExamCode"),
							rs.getString("ExamCourse"), rs.getString("ExamGrade"), rs.getString("TeacherName"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	

	public static ArrayList<StudentGrade> getAllApprovedgrades(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String StudentName = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM approvedstudentgrade WHERE studentUserName = '" + StudentName + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("ExamCode"),
							rs.getString("ExamCourse"), rs.getString("ExamGrade"), rs.getString("TeacherName"),
							rs.getString("instr"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	public static ArrayList<StudentGrade> getAllApprovedgradesTeacher(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String TeacherName = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM approvedstudentgrade WHERE TeacherName = '" + TeacherName + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("ExamCode"),
							rs.getString("ExamCourse"), rs.getString("ExamGrade"), rs.getString("TeacherName"),
							rs.getString("instr"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	public static ArrayList<StudentGrade> getAllApprovedgradesStudent(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String StudentName = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM approvedstudentgrade WHERE studentUserName = '" + StudentName + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("ExamCode"),
							rs.getString("ExamCourse"), rs.getString("ExamGrade"), rs.getString("TeacherName"),
							rs.getString("instr"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	
	
	public static ArrayList<StudentGrade> getAllApprovedgradesCourse(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String CourseName = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM approvedstudentgrade WHERE ExamCourse = '" + CourseName + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("ExamCode"),
							rs.getString("ExamCourse"), rs.getString("ExamGrade"), rs.getString("TeacherName"),
							rs.getString("instr"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	

	public static ArrayList<StudentGrade> TeachergetAllgrades(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String TeacherName = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM studentgrade WHERE TeacherName = '" + TeacherName + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade SG = new StudentGrade(rs.getString("studentUserName"), rs.getString("ExamCode"),
							rs.getString("ExamCourse"), rs.getString("ExamGrade"), rs.getString("TeacherName"));
					array.add(SG);
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
					StudentExamanation SE = new StudentExamanation(rs.getString("StudneUserName"),
							rs.getString("ExamCode"), rs.getString("ExamHours"), rs.getString("ExamMinutes"));
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
	
	public static void DeleteInExam(ArrayList<Object> arr) {

		String ExamCode = (String) arr.get(0);
		String UserName= (String) arr.get(1);
		if (conn != null)
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM inexam  WHERE ExamCode = ? and userName = ?");
				ps.setString(1, ExamCode);
				ps.setString(2, UserName);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	
		public static ArrayList<ExamResponse> getStudentsAnswer(ArrayList<Object> arr) {
			ArrayList<ExamResponse> array = new ArrayList<ExamResponse>();
			String ExamCode = (String) arr.get(0);
			String UserName= (String) arr.get(1);
			if (conn != null) {
				try {
					String query = "Select * FROM examresponse WHERE ExamCode = '" + ExamCode + "' AND UserName = '"+ UserName + "'";
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						ExamResponse ER = new ExamResponse(rs.getString("ExamCode"), rs.getString("UserName"), rs.getString("QuestionCode"), rs.getString("StudentAnswer"));
						array.add(ER);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return array;
		}
	

		public static ArrayList<ExamResponse> getSameAnswer(ArrayList<Object> arr) {
			ArrayList<ExamResponse> array = new ArrayList<ExamResponse>();
			String ExamCode = (String) arr.get(0);
			String QuestionCode= (String) arr.get(1);
			String StudentAnswer = (String)arr.get(2);
			if (conn != null) {
				try {
					String query = "Select * FROM examresponse WHERE ExamCode = '" + ExamCode + "' AND QuestionCode = '"+ QuestionCode + "' AND StudentAnswer = '" +StudentAnswer+"'" ;
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						ExamResponse ER = new ExamResponse(rs.getString("ExamCode"), rs.getString("UserName"), rs.getString("QuestionCode"), rs.getString("StudentAnswer"));
						array.add(ER);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return array;
		}
		


	public static void DeleteManagerMessage(ArrayList<Object> arr) {

		String ExamCode = (String) arr.get(0);
		if (conn != null)
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM managermessage  WHERE Examcode = ?");
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

	public static void DeleteApprovalStudentGrade(ArrayList<Object> arr) {
		String username = (String) arr.get(0);
		String ExamCode = (String) arr.get(1);

		if (conn != null)
			try {
				PreparedStatement ps = conn
						.prepareStatement("DELETE FROM studentgrade  WHERE studentUserName = ? And ExamCode = ?");
				ps.setString(1, username);
				ps.setString(2, ExamCode);
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
 
	public static boolean AddNewExamResponse(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("INSERT INTO examresponse VALUES (?,?,?,?);");
				stmt.setString(1, ((ExamResponse) list.get(0)).getExamCode());
				stmt.setString(2, ((ExamResponse) list.get(0)).getUserName());
				stmt.setString(3, ((ExamResponse) list.get(0)).getQuestionCode());
				stmt.setString(4, ((ExamResponse) list.get(0)).getStudentAnswer());
			

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}



	public static boolean AddCommonMistake(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("INSERT INTO commonmistakes VALUES (?,?,?,?);");
				stmt.setString(1, ((commonmistake) list.get(0)).getExamCode());
				stmt.setString(2, ((commonmistake) list.get(0)).getQuestionCode());
				stmt.setString(3, ((commonmistake) list.get(0)).getUserName1());
				stmt.setString(4, ((commonmistake) list.get(0)).getUserName2());
			

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	
	public static boolean AddNewStudentGrade(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("INSERT INTO studentgrade VALUES (?,?,?,?,?);");
				stmt.setString(1, ((StudentGrade) list.get(0)).getStudentUserName());
				stmt.setString(2, ((StudentGrade) list.get(0)).getExamCode());
				stmt.setString(3, ((StudentGrade) list.get(0)).getExamCourse());

				stmt.setString(4, ((StudentGrade) list.get(0)).getExamGrade());

				stmt.setString(5, ((StudentGrade) list.get(0)).getTeacherName());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean AddInExam(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("INSERT INTO inexam VALUES (?,?,?);");
				stmt.setString(1, ((InExam) list.get(0)).getExamCode());
				stmt.setString(2, ((InExam) list.get(0)).getUserName());
				stmt.setString(3, ((InExam) list.get(0)).getUserId());
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean AddNewApprovalStudentGrade(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn
						.prepareStatement("INSERT INTO approvedstudentgrade VALUES (?,?,?,?,?,?);");
				stmt.setString(1, ((StudentGrade) list.get(0)).getStudentUserName());
				stmt.setString(2, ((StudentGrade) list.get(0)).getExamCode());
				stmt.setString(3, ((StudentGrade) list.get(0)).getExamCourse());

				stmt.setString(4, ((StudentGrade) list.get(0)).getExamGrade());

				stmt.setString(5, ((StudentGrade) list.get(0)).getTeacherName());
				stmt.setString(6, ((StudentGrade) list.get(0)).getInstr());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean AddMessagetoManager(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("INSERT INTO managermessage VALUES (?,?,?,?);");
				stmt.setString(1, ((ManagerMessage) list.get(0)).getExamcode());
				stmt.setString(2, ((ManagerMessage) list.get(0)).getTeacherName());
				stmt.setString(3, ((ManagerMessage) list.get(0)).getAddtime());
				stmt.setString(4, ((ManagerMessage) list.get(0)).getInstruction());

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean AddNewExamStatus(ArrayList<Object> list) {
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO statusexam VALUES (?,?,?,?,?);");
				stmt.setString(1, ((StatusExam) list.get(0)).getExamCode());
				stmt.setString(2, ((StatusExam) list.get(0)).getNumberStartExam());
				stmt.setString(3, ((StatusExam) list.get(0)).getNumberEndExam());
				stmt.setString(4, ((StatusExam) list.get(0)).getTime());
				stmt.setString(5, ((StatusExam) list.get(0)).getDate());
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
	
	public static boolean UpgradeStart(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement(
						"UPDATE statusexam Set NumberStartExam = ?  WHERE ExamCode = ? ");

				stmt.setString(1, ((StatusExam) list.get(0)).getNumberStartExam());

				stmt.setString(2, ((StatusExam) list.get(0)).getExamCode());


				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean UpgradeEnd(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement(
						"UPDATE statusexam Set NumberEndExam = ?  WHERE ExamCode = ? ");

				stmt.setString(1, ((StatusExam) list.get(0)).getNumberEndExam());

				stmt.setString(2, ((StatusExam) list.get(0)).getExamCode());


				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	

	public static boolean ChangeLockedExCode(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn
						.prepareStatement("UPDATE studentexamcode Set isLocked = ?   WHERE ExamCode = ? ");

				stmt.setString(1, ((String) list.get(0)));

				stmt.setString(2, ((String) list.get(1)));

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public static boolean ChangeOnline(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("UPDATE users Set online = ?   WHERE userName = ? ");

				stmt.setString(1, ((String) list.get(0)));

				stmt.setString(2, ((String) list.get(1)));

				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	
	

	public static boolean RestOnline(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("UPDATE users Set online = ? ");

				stmt.setString(1, ((String)list.get(0)));
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
	

	public static ArrayList<String> checkOnline(ArrayList<Object> arr) {
		ArrayList<String> array = new ArrayList<String>();
		String username = (String) arr.get(0);
		if (conn != null)
			try {

				String query = "Select online FROM users WHERE userName = '" + username + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					String online = new String(rs.getString("online"));
					array.add(online);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return array;
	}

	public static ArrayList<String> checkLockedMExam(ArrayList<Object> arr) {
		ArrayList<String> array = new ArrayList<String>();
		String AMCode = (String) arr.get(0);
		if (conn != null)
			try {

				String query = "Select isLocked FROM studentexamcode WHERE ExamManCode = '" + AMCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					String locked = new String(rs.getString("isLocked"));
					array.add(locked);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return array;
	}

	public static ArrayList<String> checkLockedAExam(ArrayList<Object> arr) {
		ArrayList<String> array = new ArrayList<String>();
		String AMCode = (String) arr.get(0);
		if (conn != null)
			try {

				String query = "Select isLocked FROM studentexamcode WHERE ExamAutoCode = '" + AMCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					String locked = new String(rs.getString("isLocked"));
					array.add(locked);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		return array;
	}

	public static ArrayList<String> checkLockedEXCODE(ArrayList<Object> arr) {
		ArrayList<String> array = new ArrayList<String>();
		String ExamCode = (String) arr.get(0);
		if (conn != null)
			try {

				String query = "Select isLocked FROM studentexamcode WHERE ExamCode = '" + ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					String locked = new String(rs.getString("isLocked"));
					array.add(locked);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		return array;
	}
	
	public static ArrayList<String> checkDoneExamBefore(ArrayList<Object> arr) {
		ArrayList<String> array = new ArrayList<String>();
		String ExamCode = (String) arr.get(0);
		if (conn != null)
			try {

				String query = "Select studentUserName FROM studentgrade WHERE ExamCode = '" + ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					String locked = new String(rs.getString("studentUserName"));
					array.add(locked);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		return array;
	}
	
	
	
	

	public static ArrayList<StudentGrade> CheckRepeatExam(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String ExamCode = (String) arr.get(0);
		String username = (String) arr.get(1);

		if (conn != null)
			try {

				String query = "Select * FROM studentgrade WHERE studentUserName = '" + username + "'AND ExamCode = '"
						+ ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					StudentGrade SG = new StudentGrade(username, ExamCode, rs.getString("ExamCourse"),
							rs.getString("ExamGrade"), rs.getString("TeacherName"));
					array.add(SG);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return array;
	}

	public static ArrayList<InExam> ShowStudentsInExam(ArrayList<Object> arr) {
		ArrayList<InExam> array = new ArrayList<InExam>();
		String ExamCode = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM inexam WHERE ExamCode = '" + ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					InExam IE = new InExam(ExamCode, rs.getString("userName"), rs.getString("userId"));
					array.add(IE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	public static ArrayList<StatusExam> ShowStatusExam(ArrayList<Object> arr) {
		ArrayList<StatusExam> array = new ArrayList<StatusExam>();
		String ExamCode = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM statusexam WHERE ExamCode = '" + ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StatusExam SE = new StatusExam(ExamCode, rs.getString("NumberStartExam"),
							rs.getString("NumberEndExam"), rs.getString("time"), rs.getString("Date"));
					array.add(SE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

}
