package controllers;

import java.util.ArrayList;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;

public class AddController {
	public void AddQuestion(String QuestionNumber,String QuestionCode,String Question,String Subject,String QuestionInstruction,
			String Answer1,String Answer2, String Answer3,String Answer4,String RightAnswer,String Author,String point) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(QuestionNumber);
		list.add( QuestionCode);
		list.add( Question);
		list.add(  Subject);
		list.add(QuestionInstruction);
		list.add( Answer1);
		list.add( Answer2);
		list.add( Answer3);
		list.add( Answer4);
		list.add( RightAnswer);
		list.add( Author);
		list.add( point);
		ClientMessage msgFromClient = new ClientMessage("AddNewQuestion", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	//	boolean bool=(boolean) msgFromServer.getData();
		//return bool;
	}
	
	
	
	public void AddExam(String ExamCode,String ExamNumber,String ExamSubject,String ExamCourse,String  ExamTime,
			String TeacherName,String ChosenQuestion,String QuestionPoint ,String StudentInstructions,String TeacherInstructions) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		list.add( ExamNumber);
		list.add( ExamSubject);
		list.add(  ExamCourse);
		list.add(ExamTime);
		list.add( TeacherName);
		list.add( ChosenQuestion);
		list.add( QuestionPoint);
		list.add( StudentInstructions);
		list.add( TeacherInstructions);
		
		System.out.println(list.toString());
		
		ClientMessage msgFromClient = new ClientMessage("AddNewExam", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
	//	boolean bool=(boolean) msgFromServer.getData();
		//return bool;
	}
	
}
