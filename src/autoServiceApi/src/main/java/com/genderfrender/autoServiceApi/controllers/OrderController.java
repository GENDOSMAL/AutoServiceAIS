package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.dto.BaseDto;
import com.genderfrender.autoServiceApi.entities.Order;
import com.genderfrender.autoServiceApi.repository.IOrderRepository;
import com.genderfrender.autoServiceApi.utils.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order/")
@ExtensionMethod({String.class, StringExtension.class})
public class OrderController
{
	private final IOrderRepository _orderRepository;

	public OrderController(IOrderRepository clientRepository)
	{
		_orderRepository = clientRepository;
	}

	@GetMapping("all")
	public ResponseEntity<BaseDto<List<Order>>> getAllClient()
	{
		try
		{
			return new ResponseEntity<>(BaseDto.generateNormal(_orderRepository.findAll()), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("new")
	public ResponseEntity<BaseDto<Long>> saveNewClient(@RequestBody Order client)
	{
		try
		{
			client.setId(0L);
			_orderRepository.save(client);
			var newClientId = client.getId();
			return new ResponseEntity<>(BaseDto.generateNormal(newClientId), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("edit/{id}")
	public ResponseEntity<BaseDto<Boolean>> update(@RequestBody Order newInfo, @PathVariable(value = "id") long id)
	{
		try
		{
			var existItem = _orderRepository.findById(id);
			if (existItem.isEmpty())
				return new ResponseEntity<>(BaseDto.generateNormal(false), HttpStatus.BAD_REQUEST);

			var existObject = existItem.get();
			existObject.setClient(newInfo.getClient());
			existObject.setCodeName(newInfo.getCodeName());
			existObject.setRegistrationDate(newInfo.getRegistrationDate());
			existObject.setPreferEndDate(newInfo.getPreferEndDate());
			existObject.setEndPrice(newInfo.getEndPrice());
			existObject.setDateOfCreate(newInfo.getDateOfCreate());
			existObject.setLastEditDate(LocalDateTime.now());
			existObject.setCodeName(newInfo.getCodeName());
			existObject.setClient(newInfo.getClient());
			existObject.setEmployer(newInfo.getEmployer());
			existObject.setParts(newInfo.getParts());
			existObject.setServices(newInfo.getServices());
			_orderRepository.save(existObject);
			return new ResponseEntity<>(BaseDto.generateNormal(true), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
