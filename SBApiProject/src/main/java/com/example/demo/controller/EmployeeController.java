package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.employee;

@RestController
@RequestMapping("/home")
public class EmployeeController {

	//an object of type EmployeeRepository which is our database table
	@Autowired
	private EmployeeRepository er;
	
	@GetMapping("/employees")
	public List<employee> getallemployees(){
		return this.er.findAll();
	}
	
	@PostMapping("/employees")
	public employee createemployee(@RequestBody  employee temp) {
		return this.er.save(temp);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<employee> updateemployee(@Validated @RequestBody employee temp, 
			@PathVariable Long id) throws ResourceNotFoundException{
		
		employee t = er.findById(id) 
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		
		t.setDesg(temp.getDesg());
		t.setName(temp.getName());
		
		return ResponseEntity.ok(this.er.save(t)); 
	}

	@DeleteMapping("employees/{id}")
	public Map<String, Boolean> deleteemployee(@PathVariable Long id) throws ResourceNotFoundException{
		employee t = er.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		this.er.delete(t);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
}
