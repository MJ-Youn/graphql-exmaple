package kr.co.ymtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import kr.co.ymtech.dto.UserDTO;
import kr.co.ymtech.provider.CustomProvider;
import kr.co.ymtech.service.UserService;

@RestController
@CrossOrigin
public class Controller {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomProvider prov;
	
	@RequestMapping(path = "/users",  method = RequestMethod.GET)
	public ResponseEntity<Object> findAll() {
		List<UserDTO> userList = userService.findAll();
		
		return ResponseEntity.ok(userList);
	}
	
	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findById(@PathVariable("id") String id) {
		UserDTO user = userService.find(id);
		
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Object> execute(@RequestBody String query) {
		ExecutionResult result = prov.execute(query);
		
		return ResponseEntity.ok(result);
	}
	
}
