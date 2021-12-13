package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.dto.BaseDto;
import com.genderfrender.autoServiceApi.entities.Employee;
import com.genderfrender.autoServiceApi.repository.IEmployeeRepository;
import com.genderfrender.autoServiceApi.utils.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee/")
@ExtensionMethod({String.class, StringExtension.class})
public class EmployeeController
{
	private final IEmployeeRepository _employeeRepo;

	public EmployeeController(IEmployeeRepository clientRepository)
	{
		_employeeRepo = clientRepository;
	}

	@GetMapping("all")
	public ResponseEntity<BaseDto<List<Employee>>> getAllClient()
	{
		try
		{
			return new ResponseEntity<>(BaseDto.generateNormal(_employeeRepo.findAll()), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("new")
	public ResponseEntity<BaseDto<Long>> saveNewClient(@RequestBody Employee client)
	{
		try
		{
			client.setId(0L);
			_employeeRepo.save(client);
			var newClientId = client.getId();
			return new ResponseEntity<>(BaseDto.generateNormal(newClientId), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("edit/{id}")
	public ResponseEntity<BaseDto<Boolean>> update(@RequestBody Employee newClientInfo, @PathVariable(value = "id") long id)
	{
		try
		{
			var existItem = _employeeRepo.findById(id);
			if (existItem.isEmpty())
				return new ResponseEntity<>(BaseDto.generateNormal(false), HttpStatus.BAD_REQUEST);

			var existObject = existItem.get();
			existObject.setName(newClientInfo.getName());
			existObject.setLastName(newClientInfo.getLastName());
			existObject.setPatronymics(newClientInfo.getPatronymics());
			existObject.setSpecialization(newClientInfo.getSpecialization());
			existObject.setWorkNow(newClientInfo.getIsWorkNow());
			existObject.setSalaryPerPoint(newClientInfo.getSalaryPerPoint());
			_employeeRepo.save(existObject);
			return new ResponseEntity<>(BaseDto.generateNormal(true), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
