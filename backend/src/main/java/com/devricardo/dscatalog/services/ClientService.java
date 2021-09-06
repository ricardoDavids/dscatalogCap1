package com.devricardo.dscatalog.services;

import java.util.Optional;


import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devricardo.dscatalog.dto.ClientDTO;
import com.devricardo.dscatalog.entities.Client;
import com.devricardo.dscatalog.repositories.ClientRepository;
import com.devricardo.dscatalog.services.exceptions.DatabaseException;
import com.devricardo.dscatalog.services.exceptions.ResourceNotFoundException;

/*Esta annotation vai registrar esta minha classe como um componente que vai participar do sistema de injecção de dependencias automatizada aqui do Spring.
 
  O que isto quer dizer? Quer dizer que quem vai gerir as instancias das dependencias dos objectos do tipo ClientService vai ser o spring */
@Service
public class ClientService {

	// Para injectar o repository aqui dentro desta classe eu vou ter que ir no
	// ClientRepository
	@Autowired
	private ClientRepository repository; // Agora aqui já tenho o meu objecto injectado

	@Transactional(readOnly = true) // aqui sera só para ler alguns dados
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);

		/*
		 * 1ª Hipotese: Agora vamos ter que converter uma lista de client para uma lista
		 * de ClientDTO, e para isso podemos utilizar um for, instanciamdo uma lista de
		 * ClientDTO, ou seja, instanciei uma lista vazia, e vou percorrer uma lista de
		 * Client, por exemplo para cada Client cl na minha lista list, o que é que eu
		 * vou fazer , eu irei no meu listDTO para adicionar na lista um novo ClientDTO
		 * PASSANDO o cl como argumento.
		 * 
		 * Conclusão: Eu estou percorrendo a minha lista de Client( que é a minha list),
		 * para cada elemento da lista, eu dei o elemento de cl, e entao eu pego esse
		 * elemento da lista e passo ele de argumento no constructor ClientDTO E AI VOU
		 * INSTACIAR UM DTO com esse client e adicionar esse dto na minha listDto.
		 * 
		 * No final das contas eu mando retornar o meu listDto, é uma forma de converter
		 * uma lista que era de Client para uma lista agora que é de ClientDTO
		 */

		/*
		 * List<ClientDTO> listDto = new ArrayList<>(); for(Client cl: list) {
		 * listDto.add(new ClientDTO(cl));
		 * 
		 * } return listDto;
		 * 
		 */

		// 2ªhipotese
		// função lambda
		return list.map(x -> new ClientDTO(x));

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);

	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity = repository.save(entity);

		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
			entity = repository.save(entity);
			return new ClientDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}

	}

	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	
}
