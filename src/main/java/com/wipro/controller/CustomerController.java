package com.wipro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.Exception.NoDataException;
import com.wipro.Exception.NotFoundException;
import com.wipro.model.Account;
import com.wipro.model.Customer;
import com.wipro.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PutMapping("/update/{customerId}")
	public ResponseEntity<?> updateEmployee(@PathVariable("customerId") int customerId, @RequestBody Customer customer)
			throws NotFoundException {
		Customer c = service.updateCustomer(customerId, customer);
		if (c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		} else {
			throw new NotFoundException("Customer Not Found");
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCustomers() throws NoDataException {
		List<Customer> list = service.getAllCustomers().stream().distinct().collect(Collectors.toList());
		if (!(list.isEmpty())) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			throw new NoDataException("Empty data");
		}
	}
	
	@GetMapping("/addAllListCus")
	public ResponseEntity<?> addListCustomerAccountDetails(@RequestBody List<Customer> acc) throws NoDataException {
	//	List<Account> acountDetailsList=new  ArrayList<Account>();
		List<Customer> list = (List<Customer>) service.addListCustomerAccountDetails(acc);//.stream().distinct().collect(Collectors.toList());
		List<Customer> acountDetailsList=new  ArrayList<Customer>();
		for (Customer accountss:acountDetailsList) {
			Customer acountDetails=new  Customer();
			acountDetails.setAadharNo(accountss.getAadharNo());
			acountDetails.setContactNumber(accountss.getContactNumber());
			acountDetailsList.add(acountDetails);
			acountDetailsList=acountDetailsList.stream().distinct().collect(Collectors.toList());
		}
		return ResponseEntity.ok().body(acountDetailsList);
		}
	
	
	
	
	@GetMapping("/findCustomer/{customerId}")
	public ResponseEntity<?> findCustomerbyId(@PathVariable("customerId") int customerId) throws NotFoundException {
		Customer customer = service.findCustomerbyId(customerId);
		if (customer != null) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			throw new NotFoundException("Customer not found ");
		}
	}

}
