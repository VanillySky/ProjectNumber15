package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import Protocol.ClientMessage;
import Protocol.ServerMessage;
import gui.ServerPortController;
import entities.Connection;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;


public class EchoServer extends AbstractServer {
    public static final int DEFAULT_PORT = 5555;
    
	public static ArrayList<Connection> clientTypes=new ArrayList<Connection>();
	public static HashMap<ConnectionToClient, Connection> clientsMap=new HashMap<ConnectionToClient, Connection>();
    public static ServerPortController serverController;
    
    public EchoServer(int port) {
        super(port);
        try {
           SQLConnection.connecttoDB();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void handleMessageFromClient(final Object msg,final ConnectionToClient client) {
		Object result = null;

		if (msg instanceof ClientMessage) {

			java.lang.reflect.Method method = null;

			try {
				if(((ClientMessage) msg).getMethodName().equals(""))
				{
					ServerMessage sr = new ServerMessage(((ClientMessage) msg).getMethodName(), true);
					if(!clientsMap.containsValue((Connection)((ClientMessage) msg).getParameters().get(0)))
					{
						clientTypes.add((Connection)((ClientMessage) msg).getParameters().get(0));
						serverController.addConnection();
						clientsMap.put(client, (Connection)((ClientMessage) msg).getParameters().get(0));	
					}
					else {
						sr.setData(false);
					}
					try {
						client.sendToClient(sr);
					} catch (IOException e) {e.printStackTrace();}
					serverController.refresh();
					return;
				}
				else 
				if (((ClientMessage) msg).getNumParameters() == 0)
					method = SQLConnection.class.getMethod(((ClientMessage) msg).getMethodName());
				else
					method = SQLConnection.class.getMethod(((ClientMessage) msg).getMethodName(),
							((ClientMessage) msg).getParameters().getClass());

			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (((ClientMessage) msg).getNumParameters() == 0)
					result = method.invoke(null);
				else
					result = method.invoke(null, ((ClientMessage) msg).getParameters());
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				
			}
			
			try {
				ServerMessage sr = new ServerMessage(((ClientMessage)msg).getMethodName(), result);
				client.sendToClient(sr);
				
			}catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Message received: " + ((ClientMessage) msg).getMethodName() + " from " + client);

		}
    }

    protected void serverStarted() {
        System.out.println("Server listening for connections on port " + getPort());
    }

    protected void serverStopped() {
        System.out.println("Server has stopped listening for connections.");
    }

    public static void main(final String[] args) {
        int port = 0;
        try {
            port = Integer.parseInt(args[0]);
        }
        catch (Throwable t) {
            port = 5555;
        }
        EchoServer sv = new EchoServer(port);
        try {
            sv.listen();
        }
        catch (Exception ex) {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }

	public int checkDisconnectedClients() {
		ArrayList<ConnectionToClient> connections=new ArrayList<ConnectionToClient>(clientsMap.keySet());
		int cnt=0;
		for(ConnectionToClient c:connections)
		{
			synchronized (c) {
				if(c!=null)
				{
					if(c.toString()==null)
					{
					clientDisconnected(c);
					cnt++;
					}
				}
			}	
		}
		return cnt;
	}
	
	protected  void	clientDisconnected(ConnectionToClient client)
	{
		Connection connection=clientsMap.remove(client);
		clientTypes.remove(connection);
		serverController.refresh();
	}
	
}