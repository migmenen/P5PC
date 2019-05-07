package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import util.Usuario;
import util.Mensaje;
import util.Mensaje.MensajeType;

public class Servidor 
{
	//puerto : 8888
	
	private static List<Usuario> readUsers(String filename)
	{
		//READS USERNAME AND FILES OWNED FROM USERS TXT FILE
		// \/ formato: \/
		//NAME ONLINE
		//FILES .. . . .
		List<Usuario> _users = new LinkedList<Usuario>();

		File file = new File("C:\\Users\\migue\\git\\P5PC\\P5-PC\\src\\" + filename); 

		try 
		{			  
			BufferedReader br = new BufferedReader(new FileReader(file));
			String sline;
			
			boolean usr = true;
			StringBuilder user = new StringBuilder();
			while((sline = br.readLine()) != null)
			{
				char[] line = sline.toCharArray();
				
				if(usr)
				{
					int i = 0;
					while(line[i] != ' ')
					{
						user.append(line[i]);
						i++;
					}
				}
				else
				{
					List<String> files = new LinkedList<String>();
					StringBuilder sb = new StringBuilder();
					
					int i = 0;
					while(i < line.length)
					{
						if(line[i] == ' ')
						{
							files.add(sb.toString());
							sb = new StringBuilder();
						}
						else
						{
							sb.append(line[i]);
						}
						i++;
					}
					
					_users.add(new Usuario(user.toString(), "", files, false));
					user = new StringBuilder();
				}
				usr = !usr;
			}
			
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return _users;
	}
	
	private static boolean loginUser(String name, List<Usuario> users)
	{
		for (Usuario usuario : users) {
			if(usuario.get_user().equals(name))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		//Init data
		List<Usuario> _users = readUsers("usuarios.txt");
		Usuario servidor;
		
		ServerSocket listen = null;
		try 
		{			  		
			listen = new ServerSocket(Integer.parseInt(args[0]));
			
			servidor = new Usuario("Server", listen.getInetAddress().getHostName(), null, false);
			boolean run = true;
			while(run)
			{
				System.out.println("Server runing ... Waiting for client response ...");
				Socket s = listen.accept();
				
				//READ INPUT FROM CLIENT
				ObjectInputStream sin = new ObjectInputStream(s.getInputStream());
				//SEND OUTPUT TO CLIENT
				ObjectOutputStream sout = new ObjectOutputStream(s.getOutputStream());
				
				
				//Show message
				Mensaje msg = (Mensaje)sin.readObject();
				//String msg = (String)sin.readObject();
				
				System.out.println(">> Incoming: " + msg.get_content() + " " + msg.get_type() + " " + msg.get_user().get_user());
								
				switch(msg.get_type())
				{
					case ST_CONECT:
						String outmsg="";
						if(loginUser(msg.get_content(), _users))
						{
							outmsg= "Welcome back mai broder ";
						}
						else
						{
							outmsg= "Unknown user, we'll register u ";
						}

						sout.writeObject(new Mensaje(MensajeType.CM_CONECT, outmsg + msg.get_content(), servidor));
						//new oyente cliente
						break;
					case LIST_USERS:
						System.out.println("...procesando petisionsita bakana");

						StringBuilder ulist = new StringBuilder();
						for(Usuario u : _users)
						{
							ulist.append(u.toString() + "\n");
						}
						
						sout.writeObject(new Mensaje(MensajeType.CM_CONECT, ulist.toString(), servidor));
						System.out.println("lista mandose correta wiebb");
						break;
					case SHUTDOWN:
						System.out.println(">> Server shutting down");

						run = false;
						break;
					case CM_CONECT:
						
						break;
					case DATA:
						
						break;
					case FILE_DEMAND:
						
						break;
					case FILE_READY:
						
						break;
				}
				
				//Check user list n update status
				

				//sout.writeObject("Welcome " + msg);
				//sout.writeObject(new Mensaje(MensajeType.CM_CONECT, "Welcome " + msg.get_content()));

				//NOW A CLIENT LOGGED IN
					
	            //CLOSE EM ALL
	            sin.close();
	            sout.close();
	            s.close();
	            
	            	
				//new oyentecliente()
			}	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("Server shutdown ...");

	}
}

