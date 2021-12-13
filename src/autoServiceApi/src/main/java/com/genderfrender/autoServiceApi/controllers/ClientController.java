package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.dto.BaseDto;
import com.genderfrender.autoServiceApi.entities.Client;
import com.genderfrender.autoServiceApi.repository.IClientRepository;
import com.genderfrender.autoServiceApi.utils.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/")
@ExtensionMethod({String.class, StringExtension.class})
public class ClientController
{
	private final IClientRepository _clientRepository;

	public ClientController(IClientRepository clientRepository)
	{
		_clientRepository = clientRepository;
	}

	@GetMapping("all")
	public ResponseEntity<BaseDto<List<Client>>> getAllClient()
	{
		try
		{
			return new ResponseEntity<BaseDto<List<Client>>>(BaseDto.generateNormal(_clientRepository.findAll()), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("new")
	public ResponseEntity<BaseDto<Long>> saveNewClient(@RequestBody Client client)
	{
		try
		{
			client.setId(0L);
			_clientRepository.save(client);
			var newClientId = client.getId();
			return new ResponseEntity<>(BaseDto.generateNormal(newClientId), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("edit/{id}")
	public ResponseEntity<BaseDto<Boolean>> update(@RequestBody Client newClientInfo, @PathVariable(value = "id") long id)
	{
		try
		{
			var existItem = _clientRepository.findById(id);
			if (existItem.isEmpty())
				return new ResponseEntity<BaseDto<Boolean>>(BaseDto.generateNormal(false), HttpStatus.BAD_REQUEST);

			var existObject = existItem.get();
			existObject.setName(newClientInfo.getName());
			existObject.setLastName(newClientInfo.getLastName());
			existObject.setPatronymics(newClientInfo.getPatronymics());
			existObject.setBirthDay(newClientInfo.getBirthDay());
			existObject.setEmail(newClientInfo.getEmail());
			existObject.setPhone(newClientInfo.getPhone());
			existObject.setPreferCommunicationType(newClientInfo.getPreferCommunicationType());
			existObject.setAutoType(newClientInfo.getAutoType());
			existObject.setAutoNumber(newClientInfo.getAutoNumber());
			_clientRepository.save(existObject);
			return new ResponseEntity<>(BaseDto.generateNormal(true), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<BaseDto<Boolean>>(BaseDto.generateWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
