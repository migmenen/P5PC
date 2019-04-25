package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{
	public Servidor()
	{
		
		
	}
	
	public static void run()
	{
		try {
			ServerSocket listen = new ServerSocket(500);
			
			while(true)
			{
				Socket s = listen.accept();
				
				
				//new oyentecliente()
				BufferedReader fin = new BufferedReader(
						new InputStreamReader(s.getInputStream()));
				
				PrintWriter fout = new PrintWriter(s.getOutputStream());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

