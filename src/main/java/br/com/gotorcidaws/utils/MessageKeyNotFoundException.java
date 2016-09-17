package br.com.gotorcidaws.utils;

public class MessageKeyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MessageKeyNotFoundException(String message){
		super(message);
	}
}
