package pl.bjjinfoaustria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.bjjinfoaustria.service.UserService;

@Controller
@ComponentScan(basePackages="pl.bjjinfoaustria")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/displayuserpage")
	public String displayUserPage(Model model ) {		
		return userService.initUserPage(model);
	}
	
	@RequestMapping(path="/displayuserdetails")
	public String displayUserDetails(Model model) {
		return userService.displayUserDetails(model);
	}
	
	@RequestMapping(path="/displayusersevents")
	public String displayUserEvents(Model model) {
		return userService.displayEvents(model);
	}
	
	@RequestMapping(path="/displaycreatedevents")
	public String displayCreatedEvents(Model model) {
		return userService.displayCreatedEvents(model);
	}
	
	@RequestMapping(path="/edituserdetails?id= {id}")
	public String editUserDetails(Model model, @RequestParam("id")String id) {
		return "";
	}

}