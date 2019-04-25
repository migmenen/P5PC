package main;

import java.util.List;

public class Usuario 
{

	private String _user;
	private String _ip;
	private List<String> _files;
	private boolean _isOnline;
	
	public Usuario(String user, String ip, List<String> files, boolean online)
	{
		_user = user;
		_ip = ip;
		_files = files;
		_isOnline = online;
	}
	

	public String get_user() { return _user; }
	public String get_ip() { return _ip; }
	public void set_ip(String _ip) { this._ip = _ip; }
	public List<String> get_files() { return _files; }
	public void add_file(String filename) { _files.add(filename); }
	public boolean is_isOnline() { return _isOnline; }
	public void set_isOnline(boolean _isOnline) { this._isOnline = _isOnline; }
}

