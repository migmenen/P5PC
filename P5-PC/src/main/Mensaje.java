package util;

import java.io.Serializable;

public class Mensaje implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum MensajeType {ST_CONECT, CM_CONECT, LIST_USERS, FILE_DEMAND, FILE_READY, DATA, SHUTDOWN};

	private MensajeType _type;
	private String _content;
	private Usuario _user;
	

	public Mensaje(MensajeType type, String content, Usuario usr)
	{
		_type = type;
		_content = content;
		_user = usr;
	}
	
	public MensajeType get_type() {
		return _type;
	}

	public void set_type(MensajeType _type) {
		this._type = _type;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}
	
	public Usuario get_user() {
		return _user;
	}

	public void set_user(Usuario _user) {
		this._user = _user;
	}
}
