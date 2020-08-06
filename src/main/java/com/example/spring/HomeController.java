package com.example.spring;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/distribute")
public class HomeController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody DistributeBody request) {
	double amount = Double.parseDouble(request.getDistributionAmount());
	List <Account> accounts = request.getAccounts();
	if(amount > Distribute.totalAmountDue(accounts)) {
	 return new ResponseEntity(new Error(400, "Distributed Amount cannot be greater than Total Amount Due"), HttpStatus.BAD_REQUEST);
	}
	
	if(amount < 0) {
		 return new ResponseEntity(new Error(400, "Distributed Amount should "), HttpStatus.BAD_REQUEST);
		}
	List <Account> result = Distribute.splitAmountByType(request.getDistributionType(), accounts, amount);
	return new ResponseEntity(result, HttpStatus.OK);
	}

}
