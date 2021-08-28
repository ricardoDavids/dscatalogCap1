package com.devricardo.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devricardo.dscatalog.entities.Client;
import com.devricardo.dscatalog.repositories.ClientRepository;


/*Esta annotation vai registrar esta minha classe como um componente que vai participar do sistema de injecção de dependencias automatizada aqui do Spring.
 
  O que isto quer dizer? Quer dizer que quem vai gerir as instancias das dependencias dos objectos do tipo ClientService vai ser o spring */
@Service
public class ClientService {
	
	//Para injectar o repository aqui dentro desta classe eu vou ter que ir no ClientRepository
	@Autowired
	private ClientRepository repository; //Agora aqui já tenho o meu objecto injectado
	
	@Transactional(readOnly = true) //aqui sera só para ler alguns dados
	public List<Client> findAll(){
		return repository.findAll();
	}
}
