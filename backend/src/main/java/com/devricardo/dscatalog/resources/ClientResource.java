package com.devricardo.dscatalog.resources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devricardo.dscatalog.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@GetMapping
	public ResponseEntity<List<Client>>findAll(){
		
		//Instant data = Instant.now();
		List<Client> list= new ArrayList<>();
		list.add(new Client(1L, "Maria", "230971fd", 2100.32, Instant.now(), 2 ));
		list.add(new Client(2L, "Manuel", "230971fe", 2100.32, Instant.now(), 1 ));
		list.add(new Client(3L, "Joana", "230971re", 2100.32, Instant.now(), 3 ));
		return ResponseEntity.ok().body(list);
	}
}
