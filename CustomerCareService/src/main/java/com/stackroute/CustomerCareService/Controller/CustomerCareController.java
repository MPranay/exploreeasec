package com.stackroute.CustomerCareService.Controller;

import com.stackroute.CustomerCareService.Model.Customer;
import com.stackroute.CustomerCareService.Service.CustomerCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v8/customercare")
public class CustomerCareController {
    private CustomerCareService customerCareService;

@Autowired
CustomerCareController(CustomerCareService customerCareService){
    this.customerCareService = customerCareService;
}

@GetMapping("/getAllQuery")
public ResponseEntity<?> getAllQuery(){
    List<Customer> all = customerCareService.getAllQuery();
    return new ResponseEntity<>(all, HttpStatus.OK);
}



@GetMapping("/getAllQueryByUserId/{userID}")
public ResponseEntity<?> getAllQueryByUserId(@PathVariable int userID){
    List<Customer> all = customerCareService.getAllQueryByUserId(userID);
    return new ResponseEntity<>(all, HttpStatus.OK);
}

@PutMapping("update/{userID}/{customerCareId}")
public ResponseEntity<?> updateQuery(@RequestBody Customer customerCare, @PathVariable int userID, @PathVariable int customerCareId){
    Optional<Customer> r = Optional.ofNullable(customerCareService.updateQuery(customerCare, userID, customerCareId));
    if(r.isEmpty())
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(HttpStatus.OK);
}

@PostMapping("/add")
public ResponseEntity<?> addQuery(@RequestBody Customer customer) {
    List<Customer> s = customerCareService.getAllQuery();
    customer.setCustomerCareId(s.size()+1);
    Optional<Customer> r = Optional.ofNullable(customerCareService.addQuery(customer));
    if (r.isEmpty())
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    return new ResponseEntity<>(HttpStatus.OK);
}

}
