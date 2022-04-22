package project_package;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Project_Client {
	
	private static Socket socket;
	private static Socket socket2;
	static FileOutputStream fos;
	static BufferedOutputStream bos;

	public static void main(String[] args)
	{
		InputStream is;
		try 
		{
			
			socket=new Socket("localhost",5000);
			
			DataInputStream input=new DataInputStream(socket.getInputStream());
			DataOutputStream output=new DataOutputStream(socket.getOutputStream());
			
			Scanner uesr_enter=new Scanner(System.in);
			
			String username=null;
			String pass=null,user=null;
			boolean namechecker=false,passchecker=false;
			System.out.println(input.readUTF());
			username=uesr_enter.nextLine();
			output.writeUTF(username);
			namechecker=input.readBoolean();
			
			if(namechecker)
			{
				
				System.out.println(input.readUTF());
				pass=uesr_enter.nextLine();
				output.writeUTF(pass);
				passchecker=input.readBoolean();
				System.out.println(input.readUTF());
			}
			
		
		 if(passchecker)
			{
				socket2=new Socket("localhost",4000);
				DataInputStream input2=new DataInputStream(socket2.getInputStream());
				DataOutputStream output2=new DataOutputStream(socket2.getOutputStream());
				user="s";
			
				while(true) {
				
				System.out.println(input2.readUTF());
				user=uesr_enter.nextLine();
				if(user.equalsIgnoreCase("close")) 
					break;
				output2.writeUTF(user);
			    for(int i=0;i<4;i++)
				{
					System.out.println(input2.readUTF());
				}
			
				System.out.println(input2.readUTF());
				String choice;
				choice=uesr_enter.nextLine();
				output2.writeUTF(choice);
				System.out.println(input2.readUTF());
				choice=uesr_enter.nextLine();
				output2.writeUTF(choice);
				boolean condition=true;
				String filename2=input2.readUTF();
				File myfile=new File("E:\\PROJECTS\\Project Network V1\\src\\project_package\\Client\\"+filename2);
				FileOutputStream fos=new FileOutputStream(myfile);
			
				InputStream is2=socket2.getInputStream();
				int recive=0;
				int filesize=input2.readInt();
				System.out.println(filesize);
				String message=input2.readUTF();
				System.out.println(message);
				
				for(int i=0;i<filesize;i++){
					recive=is2.read();
					fos.write(recive);
				}
				
				message=input2.readUTF();
				System.out.println(message);
				}
				
				
				
			}
			else if(!namechecker) {
				System.out.println(input.readUTF());
			}
			else if(!passchecker) {
				System.out.println(input.readUTF());
			}
		 	
		// System.out.println(input.readUTF());
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
