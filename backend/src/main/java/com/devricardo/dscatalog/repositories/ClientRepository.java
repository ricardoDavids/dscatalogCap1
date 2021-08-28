package com.devricardo.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devricardo.dscatalog.entities.Client;

@Repository // aqui foi para registar como um componente injectavel pelo mecanismo de injecção de dependencias do spring
             // Agora os objectos do tipo ClientRepository passam a ser geridos pelo o Spring
public interface ClientRepository extends JpaRepository<Client,Long>{
	
	
	
}
