package FCI_Advanced_Software_Engineering.Fawry_App.API_Controllers;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import FCI_Advanced_Software_Engineering.Fawry_App.User_SubSystem.User;

@RestController
public class User_Model_Controller {
	
	private UserBsl myUserBsl = new UserBsl(); 
	 
	
	@PostMapping(value="/SignUp")
	public String SignUp(@RequestBody User user) {
		return myUserBsl.SignUp(user);
	}
	
	@PostMapping(value="/Login")
	public String Login(@RequestBody User user) {
		return myUserBsl.Login(user);
	}
	
	@GetMapping(value="/Login/HomePage")
	public String HomePage(@RequestBody(required = false) UserSelections selections) {
		return myUserBsl.HomePage(selections);
	}
	

	
}
