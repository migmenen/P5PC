package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente 
{	
	
	public static void main(String[] args)
	{
		BufferedReader cout = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Username: ");
		String user;
		try {
			user = cout.readLine();
			//LOGIN SHITT
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		//you are logged in from now on		
		
		String host = args[0];
		int puerto = Integer.parseInt(args[1]);
		String frame = args[2];
		
		try {
			Socket s = new Socket(host, puerto);
			BufferedReader fin = new BufferedReader(
					new InputStreamReader(s.getInputStream()));
			
			PrintWriter fout = new PrintWriter(s.getOutputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
