package com.genderfrender.autoServiceApi.controllers;

import com.genderfrender.autoServiceApi.dto.BaseDto;
import com.genderfrender.autoServiceApi.dto.PartForUser;
import com.genderfrender.autoServiceApi.entities.Order;
import com.genderfrender.autoServiceApi.repository.IOrderRepository;
import com.genderfrender.autoServiceApi.repository.IPartRepository;
import com.genderfrender.autoServiceApi.utils.StringExtension;
import lombok.experimental.ExtensionMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order/")
@ExtensionMethod({String.class, StringExtension.class})
public class OrderController
{
	private final IOrderRepository _orderRepository;
	private final IPartRepository _partRepository;

	public OrderController(IOrderRepository clientRepository, IPartRepository partRepository)
	{
		_orderRepository = clientRepository;
		_partRepository = partRepository;
	}

	@GetMapping("all")
	public ResponseEntity<BaseDto<List<Order>>> getAll()
	{
		try
		{
			var res=_orderRepository.findAll();
			for (var data:res)
			{
				//if(!data.getParts().isEmpty())
				//{
					data.setPartsUser(new ArrayList<PartForUser>());
					for (var part:data.getParts())
					{
						data.getPartsUser().add(new PartForUser(part.getPart(),part.getCountOfUse()));
					}
				//}					
			}
			return new ResponseEntity<>(BaseDto.generateNormal(res), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("new")
	public ResponseEntity<BaseDto<Long>> saveNew(@RequestBody Order order)
	{
		try
		{
			_orderRepository.save(order);
			
			var newClientId = order.getId();
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
			/*var orderPare=new OrderPart();
			orderPare.setPart(_partRepository.getById(1L));
			orderPare.setCountOfUse(5);
			existObject.getParts().add(orderPare);*/
			_orderRepository.save(existObject);
			return new ResponseEntity<>(BaseDto.generateNormal(true), HttpStatus.OK);
		} catch (Exception ex)
		{
			return new ResponseEntity<>(BaseDto.generateWithStatus(HttpStatus.INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
