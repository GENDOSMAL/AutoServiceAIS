package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.dto.BaseDto;
import com.genderfrender.autoServiceApi.entities.Service;
import com.genderfrender.autoServiceApi.repository.IServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ServiceController
{
	private final IServiceRepository _serviceRepository;

	public ServiceController(IServiceRepository clientRepository)
	{
		_serviceRepository = clientRepository;
	}

	@GetMapping("all")
	public ResponseEntity<BaseDto<List<Service>>> getAllClient()
	{
		try
		{
			return new ResponseEntity<>(BaseDto.generateNormal(_serviceRepository.findAll()), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("new")
	public ResponseEntity<BaseDto<Long>> saveNewClient(@RequestBody Service client)
	{
		try
		{
			client.setId(0L);
			_serviceRepository.save(client);
			var newClientId = client.getId();
			return new ResponseEntity<>(BaseDto.generateNormal(newClientId), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("edit/{id}")
	public ResponseEntity<BaseDto<Boolean>> update(@RequestBody Service newClientInfo, @PathVariable(value = "id") long id)
	{
		try
		{
			var existItem = _serviceRepository.findById(id);
			if (existItem.isEmpty())
				return new ResponseEntity<>(BaseDto.generateNormal(false), HttpStatus.BAD_REQUEST);

			var existObject = existItem.get();
			existObject.setName(newClientInfo.getName());
			existObject.setPrice(newClientInfo.getPrice());
			_serviceRepository.save(existObject);
			return new ResponseEntity<>(BaseDto.generateNormal(true), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
