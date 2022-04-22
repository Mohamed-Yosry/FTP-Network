package project_package;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Server {
	
	private static ServerSocket serversocket;
	private static Socket socket;
	
	public static void main(String[] args)
	{
		try {
			System.out.println("Starting To create serversocket...");
			serversocket=new ServerSocket(5000);
			System.out.println("Serversocket created successfully Waiting for Connection with Client...");
			while (true) {
			socket=serversocket.accept();
			System.out.println("Socket Successfully connected");
			
			Thread client = new MultipleClients(socket);

			client.start();}
			
		} catch (IOException e) {
			System.out.println("Failed To connect");
		}
		
		
	}

	static class MultipleClients extends Thread {
		
		
		final private Socket socket;
		
		public MultipleClients(Socket socket) 
		{
			this.socket = socket;
		}
		public void run() {
			
			try {
				DataInputStream input = new DataInputStream(socket.getInputStream());
				
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				
				
				boolean condition=true;
				while(condition) {
					output.writeUTF("Server: Enter Username: ");
					String username=input.readUTF();
					String pass=null;
					String n=null,p=null;
					boolean name_checking=true;
					
					File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\a.txt");
					FileInputStream  fileinput=new FileInputStream (file);
					Scanner file_entery=new Scanner(fileinput);
					int c=0;
					while(file_entery.hasNext())
					{
					
						n=file_entery.nextLine();
						p=file_entery.nextLine();
						if(username.equalsIgnoreCase(n))
						{
							name_checking=true;
							output.writeBoolean(name_checking);
							output.writeUTF("Server: Enter Password: ");
							pass=input.readUTF();
			
							if(pass.equalsIgnoreCase(p))
							{
								c++;
								output.writeBoolean(true);
								output.writeUTF("Server: Login Successfully");
								
								ServerSocket serversocket2=new ServerSocket(4000);
								while(true) {
								Socket socket2=serversocket2.accept();
								Thread client2 = new MultipleClients2(socket2);

								client2.start();
								
								}
							}
							else 
								{
								    output.writeBoolean(false);
									output.writeUTF("You enterd wrong password, Closing Connection.. ");
									
								}
						}
						
						
						}
					if(c==0) {
						name_checking=false;
						output.writeBoolean(name_checking);
						output.writeUTF("You Enterd unvalid username :( ");
						condition=false;
						
					
					}
				}
				
			} catch (IOException e) {
				
			}

				
		}
		

	}

	static class MultipleClients2 extends Thread {
		
		
		final private Socket socket2;
		
		public MultipleClients2(Socket socket2) 
		{
			this.socket2 = socket2;
		}
		
		public void run() {
			try {
				System.out.println("Connected Succesfully with socket2..");
			DataInputStream input = new DataInputStream(socket2.getInputStream());
			
			DataOutputStream output = new DataOutputStream(socket2.getOutputStream());
			while(true) {
			output.writeUTF("Server: Enter your command");
			String user=input.readUTF();
			
			if(!user.equalsIgnoreCase("close"))
			{
				File dirs=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\dirs.txt");
				FileInputStream  fileinput2=new FileInputStream (dirs);
				Scanner file_entery2=new Scanner(fileinput2);
				while(file_entery2.hasNext())
				{
				  
					output.writeUTF(file_entery2.nextLine());
				}
				output.writeUTF("Your Choice: ");
				String choice;
				choice=input.readUTF();
			
				
				
				int sending_bytes;
				if(choice.equalsIgnoreCase("movies") || choice.equalsIgnoreCase("1"))
				{
					output.writeUTF("Your files are : \n\t[1]Joker\n\t[2]la la land\n\t[3]War\n Your choice:");
					choice=input.readUTF();
					if(choice.equalsIgnoreCase("Joker") || choice.equalsIgnoreCase("1")) {
						output.writeUTF("Joker.mp4");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Movies\\jo.mp4");
						 FileInputStream fis=new FileInputStream(file);
				
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
						
					}
					else if(choice.equalsIgnoreCase("la la land") || choice.equalsIgnoreCase("2"))
					{
						output.writeUTF("La La Land.mp4");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Movies\\la.mp4");
						 FileInputStream fis=new FileInputStream(file);
					    
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
						
					}
					else if(choice.equalsIgnoreCase("War") || choice.equalsIgnoreCase("3"))
					{
						output.writeUTF("War.mp4");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Movies\\wa.mp4");
						 FileInputStream fis=new FileInputStream(file);
					 
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
						
					}
				}
				else if(choice.equalsIgnoreCase("music") || choice.equalsIgnoreCase("2"))
				{
					output.writeUTF("Your files are : \n\t[1]Hello\n\t[2]Beliver\n\t[3]Dynasty\nYour choice:");
					choice=input.readUTF();
					if(choice.equalsIgnoreCase("Hello") || choice.equalsIgnoreCase("1")) {
						output.writeUTF("Hello.mp3");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Music\\a.mp3");
						 FileInputStream fis=new FileInputStream(file);
			
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
					}
					else if(choice.equalsIgnoreCase("Beliver") || choice.equalsIgnoreCase("2"))
					{
						output.writeUTF("Belivier.mp3");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Music\\b.mp3");
						 FileInputStream fis=new FileInputStream(file);
					   
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
					}
					else if(choice.equalsIgnoreCase("Dynasty") || choice.equalsIgnoreCase("3"))
					{
						output.writeUTF("Dynasty.mp3");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Music\\d.mp3");
						 FileInputStream fis=new FileInputStream(file);
					
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
					}
				}
				else if(choice.equalsIgnoreCase("docs") || choice.equalsIgnoreCase("3"))
				{
					output.writeUTF("Your files are : \n\t[1]Network\n\t[2]Math \n\t[3]Logic\nYour choice:");
					choice=input.readUTF();
					if(choice.equalsIgnoreCase("Network") || choice.equalsIgnoreCase("1")) {
						output.writeUTF("Network.pdf");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Docs\\N.pdf");
						 FileInputStream fis=new FileInputStream(file);
					
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
					}
					else if(choice.equalsIgnoreCase("Math") || choice.equalsIgnoreCase("2"))
					{
						output.writeUTF("Math.pdf");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Docs\\M.pdf");
						 FileInputStream fis=new FileInputStream(file);
					     
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
						
					}
					else if(choice.equalsIgnoreCase("OOP") || choice.equalsIgnoreCase("3"))
					{
						output.writeUTF("OOP.pdf");
						File file=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Docs\\O.pdf");
						 FileInputStream fis=new FileInputStream(file);
					 
					      OutputStream os=socket2.getOutputStream();
					      int x;
					      output.writeInt((int)file.length());
					      output.writeUTF("Sending file....");
					      while((x=fis.read()) !=-1)
					      {
					    	  os.write(x);
					    	  System.out.println(x);
					      }
					      output.writeUTF("File has been recieved :)");
					}
				}
				
			}
			
			
			}
			}
			catch (IOException e) {
				
			}
			
			
		
		}
	}
	
	
}
