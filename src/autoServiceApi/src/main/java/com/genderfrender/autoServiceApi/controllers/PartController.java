package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.dto.BaseDto;
import com.genderfrender.autoServiceApi.entities.Part;
import com.genderfrender.autoServiceApi.repository.IPartRepository;
import com.genderfrender.autoServiceApi.utils.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/part/")
@ExtensionMethod({String.class, StringExtension.class})
public class PartController
{
	private final IPartRepository _partRepository;

	public PartController(IPartRepository clientRepository)
	{
		_partRepository = clientRepository;
	}

	@GetMapping("all")
	public ResponseEntity<BaseDto<List<Part>>> getAll()
	{
		try
		{
			return new ResponseEntity<>(BaseDto.generateNormal(_partRepository.findAll()), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("new")
	public ResponseEntity<BaseDto<Long>> saveNew(@RequestBody Part part)
	{
		try
		{
			part.setId(0L);
			_partRepository.save(part);
			var newClientId = part.getId();
			return new ResponseEntity<>(BaseDto.generateNormal(newClientId), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("edit/{id}")
	public ResponseEntity<BaseDto<Boolean>> update(@RequestBody Part updateInfo, @PathVariable(value = "id") long id)
	{
		try
		{
			var existItem = _partRepository.findById(id);
			if (existItem.isEmpty())
				return new ResponseEntity<>(BaseDto.generateNormal(false), HttpStatus.BAD_REQUEST);

			var existObject = existItem.get();
			existObject.setName(updateInfo.getName());
			existObject.setCount(updateInfo.getCount());
			existObject.setPrice(updateInfo.getPrice());
			existObject.setUserPrice(updateInfo.getUserPrice());
			existObject.setDateOfAdd(updateInfo.getDateOfAdd());
			existObject.setLastEditDate(LocalDateTime.now());
			_partRepository.save(existObject);
			return new ResponseEntity<>(BaseDto.generateNormal(true), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
