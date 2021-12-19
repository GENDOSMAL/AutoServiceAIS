package auto_service.server.controller;

import auto_service.server.model.Client;
import auto_service.server.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auto_service")
public class ClientController
{
	private final ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository)
	{
		this.clientRepository = clientRepository;
	}

	@PostMapping("/clients")
	Client newClient(@RequestBody Client client)
	{
		return clientRepository.save(client);
	}
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients(){
		return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/clients/{phoneNumber}")
	@ResponseBody
	public ResponseEntity<Client> getClientByPhone(@PathVariable(value = "phoneNumber") String phone)
	{
		return new ResponseEntity<>(clientRepository.findClientByPhoneNumber(phone), HttpStatus.OK);
	}
}
