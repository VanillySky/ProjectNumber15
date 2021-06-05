package server;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import gui.ServerPortController;



public class ServerUI extends Application {
    public static final int DEFAULT_PORT = 5555;
	public static EchoServer sv;

    public static void main(final String[] args) throws Exception {
       launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final ServerPortController server = new ServerPortController();
        server.start(primaryStage);
    }

    public static boolean runServer(final String p) {
        int port = 0;
        try {
            port = Integer.parseInt(p);
            if (port != 5555) {
                return false;
            }
        }
        catch (Throwable t) {
            System.out.println("ERROR - Could not connect!");
        }
       sv = new EchoServer(port);
        try {
            sv.listen();
        }
        catch (Exception ex) {
            System.out.println("ERROR - Could not listen for clients!");
        }
        return true;
    }
    
    public static boolean closeServer()
	{
		try {
			if(sv!=null)
				sv.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
    
	@Override
	public void stop()
	{
		closeServer();
		System.exit(0);
	}
}