module Peoject_v1 {
}



/*Threads*/

/*

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {
	private static String name[]= {"Mohamed","Ahmed","Yosry"};
	private static int grade[]= {10,12,13};
	
	final private Socket socket;

	public ClientConnection(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {

			DataInputStream input = new DataInputStream(socket.getInputStream());

			DataOutputStream output = new DataOutputStream(socket.getOutputStream());

		  


String message="message";
int nummessage=0;
while(!message.equalsIgnoreCase("close"))
{
	message=input.readUTF();
	boolean found =false;
	for(int i=0;i<3;i++)
	{
		if (message.equalsIgnoreCase(name[i]))
			{
				found=true;
				message=(name[i]+"'s score is: "+grade[i]+"/20");
				output.writeUTF(message);
				break;
			}
	}
	if (!found)
		{
			message=("Name not found, Enter other name..");
			output.writeUTF(message);
		}
}


			
			
			
			input.close();
			output.close();

		} catch (IOException e) {
			System.out.println("Connection with this client [" + socket + "] is terminated.");
		}
	}




}

	

*/




/*server*/

/*

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server_test2 {
	
	
	
	private static ServerSocket serversocket;
	private static Socket socket;
	public static void main(String[] args)
	{
		System.out.println("Trying to Conneect...");
		handle_client();
	}
		
	private static void handle_client() {
		socket=null;
		try 
		{
			System.out.println("Connetct successfully :)");
			serversocket=new ServerSocket(4000);
			System.out.println("Server is ready and is waiting for clients to connect.");

			while (true) 
			{
				socket = serversocket.accept();
				System.out.println(" new client [" + socket + "] is connected .");

				Thread client = new ClientConnection(socket);

				client.start();
			
			}
		}
		catch (IOException e) {
		}
	}
}

*/


/*client*/

/*



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import tcplaptask.Server.ClientConnection;

public class client_test2 {
	private static Socket socket;
	public static void main(String[] args)
	{
		try
		{
			socket=new Socket("localhost",4000);
			
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			Scanner uesrenter=new Scanner(System.in);
			
			int num=0;
			
			String message=null;
			do
			{
				System.out.println("Enter the Student name..");
				message=uesrenter.nextLine();
				output.writeUTF(message);
				message=input.readUTF();
				System.out.println(message);
			}while(message.equalsIgnoreCase("close"));
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
		finally {
			try {
				System.out.println("Closing Connection..");
				socket.close();
			} catch (IOException e) {
				System.out.println("Faliled to close connection..");
			}
		}
	}

}



*/