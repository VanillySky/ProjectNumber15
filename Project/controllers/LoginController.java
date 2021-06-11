package controllers;

import java.util.ArrayList;
import java.util.Collection;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import client.ChatClient;
import client.ClientUI;
import entities.Exam;
import entities.User;


public class LoginController {
	
	public static User checkUser(String userName, String password) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(userName);
		list.add(password);
		
		ClientMessage msgFromClient = new ClientMessage("checkUser", list, list.size());
		
		ClientUI.chat.accept(msgFromClient);
	
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		User user = (User) msgFromServer.getData();
		return user;
	}
	
	public static String checkManual(String ExamManualCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamManualCode);
		
		ClientMessage msgFromClient = new ClientMessage("checkManualCode", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		String code = (String) msgFromServer.getData();
		if(code!="")
		return code;
		else
		{
		return "";
		}
	}
	
	public static String checkAuto(String ExamManualCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamManualCode);
		
		ClientMessage msgFromClient = new ClientMessage("checkAutoCode", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		String code = (String) msgFromServer.getData();
		if(code!="")
		return code;
		else
		{
		return "";
		}
	}
	
	public static ArrayList<Object> checkLockedM(String AMCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(AMCode);
		
		ClientMessage msgFromClient = new ClientMessage("checkLockedMExam", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list= (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	public static ArrayList<Object> checkLockedA(String AMCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(AMCode);
		
		ClientMessage msgFromClient = new ClientMessage("checkLockedAExam", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list= (ArrayList<Object>) msgFromServer.getData();
		return list;
	}
	
	
	
	public static ArrayList<Object> checkOnline(String username) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(username);
		
		ClientMessage msgFromClient = new ClientMessage("checkOnline", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list= (ArrayList<Object>) msgFromServer.getData();

		return list;
	}
	public static ArrayList<Object> checkLockedEXCODE(String ExamCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(ExamCode);
		
		ClientMessage msgFromClient = new ClientMessage("checkLockedEXCODE", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		list= (ArrayList<Object>) msgFromServer.getData();

		return list;
	}
	
	public static boolean ChangeLockedEXCODE(String ExamCode,String IsLocked) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(IsLocked);
		list.add(ExamCode);
		ClientMessage msgFromClient = new ClientMessage("ChangeLockedExCode", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	public static boolean ChangeOnline(String username,String online) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(online);
		list.add(username);
		ClientMessage msgFromClient = new ClientMessage("ChangeOnline", list, list.size());
		ClientUI.chat.accept(msgFromClient);
		ServerMessage msgFromServer = ChatClient.messageRecievedFromServerEvents.get(msgFromClient.getMethodName());
		return (boolean) msgFromServer.getData();
	}
	

}
