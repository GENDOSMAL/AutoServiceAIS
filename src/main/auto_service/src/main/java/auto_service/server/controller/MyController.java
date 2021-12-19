package auto_service.server.controller;
import auto_service.server.model.Client;
import auto_service.server.model.Order;
import auto_service.server.model.Service;
import auto_service.server.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("auto_service")
public class MyController {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ServiceRepository serviceRepository;

    public MyController( ClientRepository clientRepository,
                        OrderRepository orderRepository,
                        ServiceRepository serviceRepository ){
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.serviceRepository = serviceRepository;

    }
    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices(){
        return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(){
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/clients/{phoneNumber}")
    @ResponseBody
    public ResponseEntity<Client> getClientByPhone(@PathVariable(value = "phoneNumber") String phone){
        return new ResponseEntity<>(clientRepository.findClientByPhoneNumber(phone), HttpStatus.OK);
    }
    @GetMapping("/time/{date}/{duration}")
    @ResponseBody
    public ResponseEntity<ArrayList<Integer>> getClientByPhone(@PathVariable(value = "date") String date,
                                                               @PathVariable(value="duration") Integer duration){
        List<Order> orders = orderRepository.findAll();
        ArrayList<Integer> times = new ArrayList<>();
        for(int i=8;i<19;i++){
            times.add(i);
        }
        for(Order o:orders){
            if(o.getOrderTime().toLocalDate().toString().equals(LocalDate.parse(date).toString())){
                times.removeIf(i -> i == o.getOrderTime().getHour());
                int d = o.getService().getDuration()/60;
                if(d!=0 ){
                    for(int j=0;j<d;j++){
                        int finalJ = j;
                        times.removeIf(i -> i ==o.getOrderTime().getHour()+ finalJ);
                    }
                }



            }
        }
        int d = duration/60;
        for(int i=0;i<times.size();i++){
            for(int dd=0;dd<d;dd++){
                if(!times.contains(times.get(i)+dd)){
                    int finalI = i;
                    times.removeIf(z -> z.equals(times.get(finalI)));
                }
            }

        }
        for(int i=0;i<times.size();i++){
            if(24-d<times.get(i)){
                int finalI1 = times.get(i);
                times.removeIf(z -> z.equals(finalI1));
            }

        }


        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }
    @PostMapping("/serviceCreate")
    Service newService(@RequestBody Service service){
        return serviceRepository.save(service);
    }
    @PostMapping("/clients")
   Client newClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
    
    @PutMapping("/orders/{id}")
    @ResponseBody
    public ResponseEntity<Order> updateStoreProduct(@PathVariable(value = "id") Long id,
                                                           @RequestBody Order order){

        Order order1 =orderRepository.findStoreProductById(id);
        if(order1 != null){
                order1.setPrice(order.getPrice());
                order1.setStatus(order.getStatus());
                return  new ResponseEntity<>(orderRepository.save(order1),HttpStatus.OK);
        }
        return new ResponseEntity<>(orderRepository.save(order),HttpStatus.OK);

    }
}
