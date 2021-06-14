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

/**This class is SQL Connection is made to connect to sql and to call all the sql methods 
 * @author Group number 15
 *
 */
/**
 * @author Ibrahim Qassem
 *
 */
/**
 * @author axwara1
 *
 */
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
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root","anitad31");
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root","1vikylja1");
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root","Ahmf1144");
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST","root","IbraPro1234");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST", "root", "Shaden#2034");

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

	/**Checks the loginframecontroller role and checks if he is in the system or not
	 * @param arr an array list the has the input
	 * @return user that is in the Database
	 */
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

	/**This method checks the code of the exam in the manual method to do an auto exam
	 * @param arr
	 * @return
	 */
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
	
	/**Checks if the auto code that the student entered is in the database and returns the code of the exam
	 * @param arr
	 * @return
	 */
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
	
	
	/**This function is made to return the timem of the exam if the input is arraylist with the code (primary key in database )
	 * @param arr
	 * @return
	 */
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
	
	
	
	/**This method is made to check if display all the student that copied from each other
	 * @return
	 */
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
	

	/**
	 * @return This function is written to display all the exams
	 */
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

	/**This method is written to show all the messages that the manager got from teacher
	 * @return
	 */
	public static ArrayList<ManagerMessage> getManagerMessages() {
		ArrayList<ManagerMessage> array = new ArrayList<ManagerMessage>();
		if (conn != null) {
			try {
				String query = "Select * FROM managermessage";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ManagerMessage MM = new ManagerMessage(rs.getString("Examcode"), rs.getString("TeacherName"),
							rs.getString("addtime"), rs.getString("instruction"), rs.getString("Approved"));
					array.add(MM);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	/**This function is written to find an specific exam or one exam according to it's code
	 * @param arr
	 * @return
	 */
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
	/** The method is made to take one status of exam from the database and table status exam
	 * @param arr
	 * @return
	 */
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
	
	

	/**This method returns the exams of the teacher according to his name 
	 * @param arr input it has his name
	 * @return
	 */
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

	/**
	 * @return akk the question in the table Questions in database
	 */
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

	/** returns one specific question from database
	 * @param arr
	 * @return
	 */
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

	/**
	 * @return displays all the studentgrade exams that he has done
	 */
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
	
	/**
	 * @return it displays all the approved student grades that has been approved by the teacher
	 */
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
	
	

	/** it displays all the approved grades according to the student username
	 * @param arr
	 * @return
	 */
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

	/** it returns all the approved grades by the teacher using her name
	 * @param arr
	 * @return
	 */
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
	
	/** method returns all the approved grade of student according to his username
	 * @param arr
	 * @return
	 */
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
	
	
	
	/** it returns all the approved grades of student according to the course name
	 * @param arr
	 * @return
	 */
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
	
	

	/** returns all the grades from student grade according to the teacher name
	 * @param arr
	 * @return
	 */
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

	/** returns the time of the exam according to students username
	 * @return
	 */
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

	/** this method deletes an exam from database exams
	 * @param arr
	 */
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
	
	/** deletes user that is in the database who is doing an exam
	 * @param arr
	 */
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
	
	
	
		/** it returns the information about the student answers in the exam according to the code and username
		 * @param arr
		 * @return
		 */
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
	

		/**this method returns what the student answered according to the table examresponse
		 * @param arr
		 * @return
		 */
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
		


	/**this function deletes manager message from database according to exam code 
	 * @param arr
	 */
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

	/** delete question from questions according to question code
	 * @param arr
	 */
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

	/** delete approvalstudentgrade according the username and examcode from the database
	 * @param arr
	 */
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

	/** Addnew question to the questions table in sql
	 * @param list
	 * @return
	 */
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

	/** add new exam to the table exams in database
	 * @param list
	 * @return
	 */
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
 
	/**this method adds the answer of student to database examresponse 
	 * @param list
	 * @return
	 */
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



	/** adds a common mistake for 2students to the database
	 * @param list
	 * @return
	 */
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

	
	
	/** adds the new student grade to the database to show it to the teacher
	 * @param list
	 * @return
	 */
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

	/** adds the student to inexam table in database that he is 
	 * @param list
	 * @return
	 */
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

	/** add the new approval student grade by the teacher to database
	 * @param list
	 * @return
	 */
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

	/** add new message to the managaer about changing time from teacher
	 * @param list
	 * @return
	 */
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

	/**add new status of exam with startexam and endexam etc..
	 * @param list
	 * @return
	 */
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

	/**updates time of exam in database exams
	 * @param list
	 * @return
	 */
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

	/** this method updates the question in the database questions
	 * @param list
	 * @return
	 */
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
	
	/**This method updates status exam and changes the number of student who started the exam 
	 * @param list
	 * @return
	 */
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
	
	/** updates the number of student who finished the exam
	 * @param list
	 * @return
	 */
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
	

	/**updates the exam situation locked/unlocked
	 * @param list
	 * @return
	 */
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

	/** it changes the connection of the client if he is oline/offline
	 * @param list
	 * @return
	 */
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

	/** updates approved field in the table managermessage when the manager approves to changing time
	 * @param list
	 * @return
	 */
	public static boolean UpgradeApprove(ArrayList<Object> list) {
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement("UPDATE managermessage Set Approved = ?   WHERE Examcode = ? ");

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
	

	/**change online/offline situation for users
	 * @param list
	 * @return
	 */
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
	

	/**display the online/offline situation of the user
	 * @param arr
	 * @return
	 */
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

	/** check if the exam is locked by checking the table studentexamcode
	 * @param arr
	 * @return
	 */
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
   //the same but auto examination
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
	
	/**returns the student who did the exam 
	 * @param arr
	 * @return
	 */
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
	
	
	
	

	/** check if he did exam more than 1 time
	 * @param arr
	 * @return
	 */
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

	
	/**show the situation of student while doing an exam
	 * @param arr
	 * @return
	 */
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

	/**display status of an exam according to status exam table from database
	 * @param arr
	 * @return
	 */
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
	
	
	/**returns the exam that we should change it's time
	 * @param arr
	 * @return
	 */
	public static ArrayList<ManagerMessage> oneApprovedChangeTime(ArrayList<Object> arr) {
		ArrayList<ManagerMessage> array = new ArrayList<ManagerMessage>();
		String ExamCode = (String) arr.get(0);
		if (conn != null) {
			try {
				String query = "Select * FROM managermessage WHERE ExamCode = '" + ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ManagerMessage MS = new ManagerMessage(ExamCode, rs.getString("TeacherName"),
							rs.getString("addtime"), rs.getString("instruction"), rs.getString("Approved"));
					array.add(MS);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	
	/** returns the exam that the student has done it
	 * @param arr
	 * @return
	 */
	public static ArrayList<StudentGrade> checkOneGradeExist(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String ExamCode = (String) arr.get(0);
		String username = (String) arr.get(1);
		if (conn != null) {
			try {
				String query = "Select * FROM approvedstudentgrade WHERE studentUserName = '" + username + "'AND ExamCode = '"
						+ ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade ST = new StudentGrade( rs.getString("studentUserName"), ExamCode,  rs.getString("ExamCourse"),  rs.getString("ExamGrade"),  rs.getString("TeacherName"),  rs.getString("instr"));
					array.add(ST);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
	
	
	/**returns the exam that the student has done it but not approved by teacher
	 * @param arr
	 * @return
	 */
	public static ArrayList<StudentGrade> checkOneApprovalGradeExist(ArrayList<Object> arr) {
		ArrayList<StudentGrade> array = new ArrayList<StudentGrade>();
		String ExamCode = (String) arr.get(0);
		String username = (String) arr.get(1);
		if (conn != null) {
			try {
				String query = "Select * FROM studentgrade WHERE studentUserName = '" + username + "'AND ExamCode = '"
						+ ExamCode + "'";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					StudentGrade ST = new StudentGrade( rs.getString("studentUserName"), ExamCode,  rs.getString("ExamCourse"),  rs.getString("ExamGrade"),  rs.getString("TeacherName"));
					array.add(ST);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return array;
	}
}
