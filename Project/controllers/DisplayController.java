package controllers;

import java.util.ArrayList;
import java.util.Collection;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.ExamResponse;
import entities.InExam;
import entities.ManagerMessage;
import entities.Question;
import entities.Statistics;
import entities.StudentGrade;
import entities.Teacher;
import entities.commonmistake;

/**This class is made to call the SQLConnection methods which has the sql method (SELECT)
 * @author Ibrahim Qassem
 *
 */
public class DisplayController {
	@SuppressWarnings("unchecked")
	////function to add exams to exams building in teacher
	
	public static ArrayList<Statistics> ShowStatistics() {
		ArrayList<Statistics> list = new ArrayList<Statistics>();
		ClientMessage msgFromClient = new ClientMessage("getAllgrades", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Statistics>) msgFromServer.getData();
		return list;
	}
	
	
	
	
	/**function that show exam*/
	public static ArrayList<Exam> ShowExams() {
		ArrayList<Exam> list = new ArrayList<Exam>();
		ClientMessage msgFromClient = new ClientMessage("getAllexams", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Exam>) msgFromServer.getData();
		return list;
	}
	 
	public static ArrayList<commonmistake> ShowCommonMistake() {
		ArrayList<commonmistake> list = new ArrayList<commonmistake>();
		ClientMessage msgFromClient = new ClientMessage("getAllCommonMistake", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<commonmistake>) msgFromServer.getData();
		return list;
	}
	
	
	/**show manager message*/
	public static ArrayList<ManagerMessage> showManagerMessage() {
		
		ArrayList<ManagerMessage> list = new ArrayList<ManagerMessage>();
		ClientMessage msgFromClient = new ClientMessage("getManagerMessages", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<ManagerMessage>) msgFromServer.getData();
		return list;
	}
	
	
	
	/** show one exam */
	public static  ArrayList<Object> ShowOneExam(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("getOneExams", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	public static  ArrayList<Object> GetAllStudentAnswer(String ExamCode , String username) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add(username);
		ClientMessage msgFromClient = new ClientMessage("getStudentsAnswer", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	

	public static  ArrayList<Object> GetAllSameAnswer(String ExamCode , String QuestionCode , String StudentAnswer) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add(QuestionCode);
		list.add(StudentAnswer);
		ClientMessage msgFromClient = new ClientMessage("getSameAnswer", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	
	/**show teacher exams  */
	public static Collection<Object> ShowTeacherExams(String TeacherName)  {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TeacherName);
		ClientMessage msgFromClient = new ClientMessage("getTeacherexams", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
/**function to add questions to Questions building in teacher*/
	public static ArrayList<Question> ShowQuestions() {
		// TODO Auto-generated method stub
		ArrayList<Question> list = new ArrayList<Question>();
		ClientMessage msgFromClient = new ClientMessage("getAllquestions", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Question>) msgFromServer.getData();
		return list;	
		}
	
	
	/**show one question  */

	public static  Collection<Object> ShowOneQuestions(String QuestionCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(QuestionCode);
		ClientMessage msgFromClient = new ClientMessage("getOneQuestion", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	
	
	/**check if the exam repeat  */

	public static Collection<Object> CheckRepeatExam(String ExamCode , String userName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add(userName);
		ClientMessage msgFromClient = new ClientMessage("CheckRepeatExam", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list= (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	
	/** show student grades */

	public static ArrayList<StudentGrade> ShowStudentGrade() {
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		ClientMessage msgFromClient = new ClientMessage("getAllgrades", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<StudentGrade>) msgFromServer.getData();
		return list;	
	}
	
	
	/**show approval student grade  */

	public static ArrayList<StudentGrade> ShowApprovaleStudentGrade() {
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		ClientMessage msgFromClient = new ClientMessage("getAllApprovalegrades", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<StudentGrade>) msgFromServer.getData();
		return list;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**show students grade of the same teacher  */

	public static  Collection<Object> ShowStudentGradeTeacher(String TeacherName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TeacherName);
		ClientMessage msgFromClient = new ClientMessage("TeachergetAllgrades", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	
	/**show approval of studens of the same teacher  */

	public static Collection<Object> ShowApprovedStudentTeacher(String TeacherName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TeacherName);
		ClientMessage msgFromClient = new ClientMessage("getAllApprovedgradesTeacher", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	
	
	public static Collection<Object> ShowApprovedStudentStudent(String StudentName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(StudentName);
		ClientMessage msgFromClient = new ClientMessage("getAllApprovedgradesStudent", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	
	/**show approval of students of the course  */

	public static Collection<Object> ShowApprovedStudentCourse(String CourseName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(CourseName);
		ClientMessage msgFromClient = new ClientMessage("getAllApprovedgradesCourse", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	

	
	/**show approval grade of the student  */

	public static Collection<Object> ShowApprovedStudentGrade(String studentName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(studentName);
		ClientMessage msgFromClient = new ClientMessage("getAllApprovedgrades", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	
	
	/**show exam time  */

	public static ArrayList<StudentGrade> ShowExamTime() {
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		ClientMessage msgFromClient = new ClientMessage("getTime", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<StudentGrade>) msgFromServer.getData();
		return list;	
	}
	
	
	
	
	/**show status of the exam  */

	public static Collection<Object> ShowStatusExam(String ExamCode) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("ShowStatusExam", list	, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;		
	}
	/////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Object> checkLockedEXCODE(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		
		ClientMessage msgFromClient = new ClientMessage("checkLockedEXCODE", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list= (ArrayList<Object>) msgFromServer.getData();

		return list;
	}
	
	
	
	/**function that get exam time  */

	public static String GetExamTime(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		
		ClientMessage msgFromClient = new ClientMessage("getexamtime", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		String time = (String) msgFromServer.getData();
		if(time!="")
		return time;
		else
		{
		return "";
		}
	}
	
	

	public static  ArrayList<Object> GetoneStatusExam(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("oneStatusExam", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	public static  ArrayList<Object> ApprovedChangeTime(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("oneApprovedChangeTime", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	public static  ArrayList<Object> checkGradeExist(String ExamCode,String username) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add(username);
		ClientMessage msgFromClient = new ClientMessage("checkOneGradeExist", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	public static  ArrayList<Object> checkapprovalgradeExist(String ExamCode,String username) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add(username);
		ClientMessage msgFromClient = new ClientMessage("checkOneApprovalGradeExist", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	
	
	
	
	


}

