package controllers;

import java.util.ArrayList;
import java.util.Collection;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.Question;
import entities.StudentGrade;

public class DisplayController {
	@SuppressWarnings("unchecked")
	////function to add exams to exams building in teacher

	public static ArrayList<Exam> ShowExams() {
		ArrayList<Exam> list = new ArrayList<Exam>();
		ClientMessage msgFromClient = new ClientMessage("getAllexams", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<Exam>) msgFromServer.getData();
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
	public static ArrayList<StudentGrade> ShowStudentGrade() {
		ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
		ClientMessage msgFromClient = new ClientMessage("getAllgrades", null, 0);
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list = (ArrayList<StudentGrade>) msgFromServer.getData();
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
}
