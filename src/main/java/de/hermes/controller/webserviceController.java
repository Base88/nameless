package de.hermes.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



import de.hermes.database.MySQL;
import de.hermes.model.ListeUser;
import de.hermes.model.MyResult;
import de.hermes.model.User;

@RestController
public class webserviceController {
	
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	@RequestMapping(value="/select", method= RequestMethod.POST)
	public ResponseEntity<User> selectUser(@RequestBody User username) throws ClassNotFoundException, SQLException{
		User user = MySQL.selectUser(username.getUsername());
	
		if (user==null){
			System.out.println("ITS NO CONTENT");
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT) ;
		}
		System.out.println("ICH BIN IM SELECT HIFLEEE!! "+ username);
	
		return new ResponseEntity<User>(user, HttpStatus.OK) ;
	}
	
	@RequestMapping(value="/selectAll", method= RequestMethod.GET)
	public ResponseEntity<ListeUser> getAllUser() throws ClassNotFoundException, SQLException{
		
		ListeUser listOfUsers = MySQL.selectAll();
		if(listOfUsers==null){
			return new ResponseEntity<ListeUser>(listOfUsers, HttpStatus.NO_CONTENT) ;
		}
		System.out.println("ES WERDEN ALLE DATEN GEHOLT!!!! /selectAll");
		return new ResponseEntity<ListeUser>(listOfUsers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert", method= RequestMethod.POST)
	public MyResult insertUser(@RequestBody User user) throws ClassNotFoundException, SQLException{
		System.out.println("Erstelle den user mit den namen: "+ user.getUsername());

		return MySQL.insertUser(user);
	}
	
	@RequestMapping("/delete")
	public MyResult deleteUser(String username) throws ClassNotFoundException, SQLException{
		return MySQL.deleteUser(username);
	}
	
	
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public MyResult updateUser(@RequestBody User user) throws ClassNotFoundException, SQLException{
		System.out.println("Hier wird geupdated "+ user.getUsername());
		return MySQL.updateUser(user);
	}
	
	
	

	
	 	

}
