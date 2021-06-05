package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import entities.Teacher;
import entities.User;
import entities.Exam;


public class SQLConnection {
    private static Connection conn= null;
    


    public static void connecttoDB() throws ParseException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        }
        catch (Exception ex2) {
            System.out.println("Driver definition failed");
        }
        try {
            //SQLConnection.conn = DriverManager.getConnection("jdbc:mysql://localhost/test/world?serverTimezone=IST","root","Ahmf1144");
        	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/projectass3?serverTimezone=IST","root","IbraPro1234");

            System.out.println("SQL connection succeed");
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
/*
    public static void saveUserToDB(final ArrayList<String> list) {
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?);");
                stmt.setString(1, list.get(0));
                stmt.setString(2, list.get(1));
                stmt.setString(3, list.get(2));
                stmt.setString(4, list.get(3));
                stmt.setString(5, list.get(4));
                stmt.setString(6, list.get(5));
                stmt.setString(7, list.get(6));
                stmt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void UpdateOnDB(String id, String Field2, String newValue) {
        if (conn != null) {
            try {
                String str = "UPDATE Test set ";
                str = String.valueOf(str) + Field2;
                str = String.valueOf(str) + "= '";
                str = String.valueOf(str) + newValue;
                str = String.valueOf(str) + "' Where userName = '";
                str = String.valueOf(str) + id;
                str = String.valueOf(str) + "'";
                final Statement stmt = conn.createStatement();
                stmt.executeUpdate(str);
                System.out.println("Update Successfuly ");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String ShowExamInformation(String Id) {
        String str = "";
        if (conn != null) {
            try {
                String query = "Select * FROM Test WHERE TestID = '";
                query = String.valueOf(query) + Id;
                query = String.valueOf(query) + "' ;";
                final Statement st = conn.createStatement();
                final ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    str = String.valueOf(str) + rs.getString("TestOD");
                    str = String.valueOf(str) + " ";
                    str = String.valueOf(str) + rs.getString("Subject");
                    str = String.valueOf(str) + " ";
                    str = String.valueOf(str) + rs.getString("Course");
                    str = String.valueOf(str) + " ";
                    str = String.valueOf(str) + rs.getString("ExamTime");
                    str = String.valueOf(str) + " ";
                    str = String.valueOf(str) + rs.getString("QuestionsPoints");
                }
                st.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return str;
    }*/
    public static Connection getConn() {
		return conn;
	}
 
    public static User checkUser(ArrayList<Object> arr) {
		String username = (String) arr.get(0);
		String password = (String) arr.get(1);
		User user = null;

		if (conn != null)
			try {
				
				String query = "Select * FROM users WHERE userName = '" + username + "'AND password = '" + password +"'";
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
						return new Teacher(username, password, firstName, lastName, userId,email);
						

					case "Student":

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
                    Exam ex = new Exam( rs.getString("ExamCode"),rs.getString("ExamNumber"), rs.getString("ExamSubject"), rs.getString("ExamCourse"), rs.getString("ExamTime"),rs.getString("TeacherName"),rs.getString("ChosenQuestion"),rs.getString("QuestionPoint"),rs.getString("StudentInstructions"),rs.getString("TeacherInstructions"));
                    array.add(ex);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}