package pl.bjjinfoaustria.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.bjjinfoaustria.entity.Event;
import pl.bjjinfoaustria.entity.Participant;
import pl.bjjinfoaustria.entity.User;
import pl.bjjinfoaustria.enums.StatusE;
import pl.bjjinfoaustria.repository.EventRepository;
import pl.bjjinfoaustria.repository.ParticipantRepository;
import pl.bjjinfoaustria.repository.UserRepository;
import pl.bjjinfoaustria.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addEvent(Event event) {
		event.setStatus("SUBMITTED");
		eventRepository.saveAndFlush(event);
	}

	@Override
	public List<Event> allEvents() {
		return eventRepository.findAll();
	}

	@Override
	public void addParticipant(long eventId, long userId) {
			Event event = eventRepository.findOne(eventId);
			User user = userRepository.findOne(userId);
			event.getParticipants().add(user);
			eventRepository.saveAndFlush(event);
	}

	@Override
	public Event findEventById(long id) {
		return eventRepository.findOne(id);
	}

	@Override
	public void deleteEvent(Event event) {
		eventRepository.delete(event);
	}
	


}
