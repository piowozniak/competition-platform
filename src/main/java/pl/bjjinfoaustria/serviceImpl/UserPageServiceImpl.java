package pl.bjjinfoaustria.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.bjjinfoaustria.bean.SecurityContext;
import pl.bjjinfoaustria.entity.Competitor;
import pl.bjjinfoaustria.entity.Event;
import pl.bjjinfoaustria.entity.User;
import pl.bjjinfoaustria.enums.StatusE;
import pl.bjjinfoaustria.repository.CompetitorRepository;
import pl.bjjinfoaustria.repository.EventRepository;
import pl.bjjinfoaustria.repository.UserRepository;
import pl.bjjinfoaustria.service.ModelService;
import pl.bjjinfoaustria.service.UserPageService;

@Service
public class UserPageServiceImpl implements UserPageService, ModelService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private CompetitorRepository competitorRepository;
	private User user;
	private List<Event> listOfEventsUserSignedUp = new ArrayList<>();
	private List<Event> listOfCreatedEvents = new ArrayList<>();
	private boolean displayedEventsList = false;
	private boolean displayedUserDetails = false;
	private boolean displayedCreatedEvents = false;
	
	@Override
	public String initUserPage(Model model) {		
		user = userRepository.findByUsername(SecurityContext.getUsername());
		listOfEventsUserSignedUp.clear();
		listOfEventsUserSignedUp = eventRepository.findEventsUserJoined(user.getId());
		listOfCreatedEvents.clear();
		listOfCreatedEvents = eventRepository.findByOrganizer(user.getUsername());
		addAttributesToModel(model);
		return "userpage";
	}
	
	@Override
	public String displayEvents(Model model) {
		displayedEventsList = checkIfDiplayEvents();
		addAttributesToModel(model);
		return "userpage";
	}

	@Override
	public String displayUserDetails(Model model) {
		displayedUserDetails = checkIfDisplayDetails();
		addAttributesToModel(model);
		return "userpage";
	}	
	
	@Override
	public String displayCreatedEvents(Model model) {
		displayedCreatedEvents = checkIfDiplayCreatedEvents();
		addAttributesToModel(model);
		return "userpage";
	}
	
	@Override
	public String acceptUserInEvent(Model model, long id) {
		Competitor competitor = competitorRepository.findOne(id);
		competitor.setStatus(StatusE.ACCEPTED.getValue());
		competitorRepository.saveAndFlush(competitor);
		return "redirect:/displayuserpage";
	}
	
	private boolean checkIfDisplayDetails() {
		return displayedUserDetails ? false : true;
	}
	
	private boolean checkIfDiplayEvents() {
		return displayedEventsList ? false : true;
	}
	
	private boolean checkIfDiplayCreatedEvents() {
		return displayedCreatedEvents ? false : true;
	}
	
	@Override
	public void addAttributesToModel(Model model) {
		model.addAttribute("user", user);
		model.addAttribute("listOfEventsUserSignedUp", listOfEventsUserSignedUp);
		model.addAttribute("listOfCreatedEvents", listOfCreatedEvents);
		model.addAttribute("displayedEventsList", displayedEventsList);
		model.addAttribute("displayedUserDetails", displayedUserDetails);
		model.addAttribute("displayedCreatedEvents", displayedCreatedEvents);
	}

	@Override
	public String editUser(Model model) {
		this.user = userRepository.findOne(user.getId());
		addAttributesToModel(model);
		return "edituser";
	}

	@Override
	public String editUserConfirmation(Model model, User user) {
		this.user.setEmail(user.getEmail());
		this.user.setUsername(user.getUsername());
		this.user.setFirstName(user.getFirstName());
		this.user.setLastName(user.getLastName());
		this.user.setPhoneNumber(user.getPhoneNumber());
		userRepository.saveAndFlush(this.user);	
		addAttributesToModel(model);
		return "userpage";
	}


}
