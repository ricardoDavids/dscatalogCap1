package com.devricardo.dscatalog.services.exceptions;

public class DatabaseException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg) {
		super(msg);
	}
}

/*Aqui criei um constructor que recebe uma mensagem e depois repassa essa mensagem para o
 constructor do RuntimeException usando o super.  
 
 Então, quando vc faz um constructor e quer repassar o argumento do constructor para constructor da superClasse, usamos dessa forma aqui. */

 