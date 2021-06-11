package controllers;

import java.util.ArrayList;
import java.util.Collection;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.InExam;
import entities.ManagerMessage;
import entities.Question;
import entities.Statistics;
import entities.StudentGrade;
import entities.Teacher;

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
	public static ArrayList<Exam> ShowExams() {
		ArrayList<Exam> list = new ArrayList<Exam>();
		ClientMessage msgFromClient = new ClientMessage("getAllexams", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Exam>) msgFromServer.getData();
		return list;
	}
	
	
	public static ArrayList<ManagerMessage> showManagerMessage() {
		
		ArrayList<ManagerMessage> list = new ArrayList<ManagerMessage>();
		ClientMessage msgFromClient = new ClientMessage("getManagerMessages", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<ManagerMessage>) msgFromServer.getData();
		return list;
	}
	
	
	
	public static  Collection<Object> ShowOneExam(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("getOneExams", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	public static Collection<Object> ShowTeacherExams(String TeacherName)  {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TeacherName);
		ClientMessage msgFromClient = new ClientMessage("getTeacherexams", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
//function to add questions to Questions building in teacher
	public static ArrayList<Question> ShowQuestions() {
		// TODO Auto-generated method stub
		ArrayList<Question> list = new ArrayList<Question>();
		ClientMessage msgFromClient = new ClientMessage("getAllquestions", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Question>) msgFromServer.getData();
		return list;	
		}
	
	public static  Collection<Object> ShowOneQuestions(String QuestionCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(QuestionCode);
		ClientMessage msgFromClient = new ClientMessage("getOneQuestion", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	
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
	
	
	
	public static ArrayList<StudentGrade> ShowStudentGrade() {
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		ClientMessage msgFromClient = new ClientMessage("getAllgrades", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<StudentGrade>) msgFromServer.getData();
		return list;	
	}
	
	
	public static  Collection<Object> ShowStudentGradeTeacher(String TeacherName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TeacherName);
		ClientMessage msgFromClient = new ClientMessage("TeachergetAllgrades", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	public static Collection<Object> ShowApprovedStudentTeacher(String TeacherName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TeacherName);
		ClientMessage msgFromClient = new ClientMessage("getAllApprovedgradesTeacher", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	

	
	
	public static Collection<Object> ShowApprovedStudentGrade(String studentName) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(studentName);
		ClientMessage msgFromClient = new ClientMessage("getAllApprovedgrades", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
	}
	
	
	public static ArrayList<StudentGrade> ShowExamTime() {
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		ClientMessage msgFromClient = new ClientMessage("getTime", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<StudentGrade>) msgFromServer.getData();
		return list;	
	}
	
	public static Collection<InExam> ShowStudentsInExam(String ExamCode) {
		Collection<InExam> list = new ArrayList<InExam>();
		ArrayList<Object> list2 = new ArrayList<Object>();
		list2.add(ExamCode);

		ClientMessage msgFromClient = new ClientMessage("ShowStudentsInExam", list2	, list2.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<InExam>) msgFromServer.getData();
		return list;	
	}
	
	
	public static Collection<Object> ShowStatusExam(String ExamCode) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("ShowStatusExam", list	, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Object>) msgFromServer.getData();
		return list;	
		
		
	}


}

