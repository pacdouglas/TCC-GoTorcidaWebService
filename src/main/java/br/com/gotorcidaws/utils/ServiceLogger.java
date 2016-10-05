package br.com.gotorcidaws.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceLogger {

	private static SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
	
	public static void received(String message){
		System.out.println("["+format.format(new Date())+"]" + " Received : " + message);
	}
	
	public static void sent(String message){
		System.out.println("["+format.format(new Date())+"]" + " Sent : " + message);
	}
}
