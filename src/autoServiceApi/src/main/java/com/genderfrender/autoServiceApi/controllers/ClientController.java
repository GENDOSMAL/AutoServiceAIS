package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.entities.Client;
import com.genderfrender.autoServiceApi.repository.IClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/client/")
public class ClientController
{
	private final IClientRepository _clientRepository;

	public ClientController(IClientRepository clientRepository)
	{
		_clientRepository = clientRepository;
	}

	@GetMapping("all")
	public ResponseEntity<List<Client>> getAllClient(){
		return new ResponseEntity<>(_clientRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("new")
	public ResponseEntity<Long> saveNewClient(@RequestBody Client order){
		var newObject=new Client();
		newObject.setAutoNumber("3333");
		newObject.setName("343434");			
		_clientRepository.save(newObject);		
		var d=newObject.getId();
		return new ResponseEntity<Long>(d,HttpStatus.OK);
	}
}
