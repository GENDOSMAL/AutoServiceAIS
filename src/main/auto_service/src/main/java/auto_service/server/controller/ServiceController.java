package auto_service.server.controller;

import auto_service.server.model.Service;
import auto_service.server.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auto_service")
public class ServiceController
{
	private final ServiceRepository serviceRepository;

	public ServiceController(ServiceRepository serviceRepository)
	{
		this.serviceRepository = serviceRepository;
	}
	
	@GetMapping("/services")
	public ResponseEntity<List<Service>> getAllServices(){
		return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
	}


	@PostMapping("/serviceCreate")
	Service newService(@RequestBody Service service)
	{
		return serviceRepository.save(service);
	}
}
