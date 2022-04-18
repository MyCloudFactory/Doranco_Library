package com.doranco.multitiers.context;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteContext {


	private InitialContext initialContext;
	private static RemoteContext instance;
	
	private RemoteContext() {
		
		try {
			initialContext =  new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public InitialContext getInitialContext() {
		return initialContext;
	}
	// synchronized gere les acces concurents
	synchronized public static RemoteContext getInstance() {
		
		if (instance == null) 
			return new RemoteContext();			
		return instance;
	}
	
	

}
