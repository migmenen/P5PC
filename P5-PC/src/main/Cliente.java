package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import util.Mensaje;
import util.Mensaje.MensajeType;
import util.Usuario;

public class Cliente 
{		
	//puerto : 9862

	public static void main(String[] args)
	{
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		String user;
		InetAddress host = null;
		Usuario _usuario = null;
		
		try 
		{

			
			//LOGIN SHITT
			System.out.println("Username: ");
			user = cin.readLine();
					
			//host = InetAddress.getLocalHost();
			//host = InetAddress.getByName("192.168.56.1");
			//System.out.println(host);
			host = InetAddress.getByName(args[0]);
			int puerto = Integer.parseInt(args[1]);
			String frame = args[2];	
			
			_usuario = new Usuario(user, host.getHostName(), null, false);
			
			Socket s = new Socket(host.getHostName(), puerto);
			
			
			//WRITE TO SERVER
			ObjectOutputStream sout = new ObjectOutputStream(s.getOutputStream());
			sout.writeObject(new Mensaje(MensajeType.ST_CONECT, "", _usuario));
			//sout.writeObject(user);
			System.out.println("Le abro el direct al SERVER");
			
			
			//READ SERVER RESPONSE
			ObjectInputStream sin = new ObjectInputStream(s.getInputStream());
			Mensaje msg = (Mensaje)sin.readObject();
			//String msg = (String)sin.readObject();

			System.out.println(">> Server said: " + msg.get_content());
			if(msg.get_type() != MensajeType.CM_CONECT) 
			{
				System.out.println("....Error while taking with the server.....");
				return;
			}
			
			int option=-1;
			
			while(true)
			{		
				s = new Socket(host.getHostName(), puerto);
				sout = new ObjectOutputStream(s.getOutputStream());
				sin = new ObjectInputStream(s.getInputStream());
				
				System.out.println("0. Desconectar\n1. Pedir archivo\n2. Lista Usuarios");
				option = Integer.parseInt(cin.readLine());
			
				if(option == 0) 
				{
					sout.writeObject(new Mensaje(MensajeType.SHUTDOWN, "", _usuario));
					break;
				}
				else if(option == 1)
				{
					System.out.println("Input filename u ask for: ");
					String fname = cin.readLine();
					sout.writeObject(new Mensaje(MensajeType.FILE_DEMAND, fname, _usuario));
				}
				else if(option == 2)
				{
					sout.writeObject(new Mensaje(MensajeType.LIST_USERS, "deme la lista wei", _usuario));
				}
				
				msg = (Mensaje)sin.readObject();
				System.out.println(">> Server says: " + msg.get_content());
			}
			
			//CLOSE ALL STUFF
			sout.close();
			sin.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}				
	}
}
