package pl.bjjinfoaustria.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.bjjinfoaustria.entity.Division;
import pl.bjjinfoaustria.entity.Event;
import pl.bjjinfoaustria.repository.DivisionRepository;
import pl.bjjinfoaustria.repository.EventRepository;
import pl.bjjinfoaustria.service.CompetitionService;
@Service
public class CompetitionServiceImpl implements CompetitionService {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private DivisionRepository divisionRepository;
	private Event event;
	private Division division;
	private List<Division> divisions = new ArrayList<>();
//	private List<BracketMB> alldivisions = new ArrayList<>();
	
	@Override
	public String createBrackets(Model model, long id) {
		initEvent(model, id);
		return "bracketcreator";
	}
	private void initEvent(Model model, long id) {
		event = eventRepository.findOne(id);
		divisions = event.getDivisions().stream().filter(Objects::nonNull).collect(Collectors.toList());
		division = divisions.get(0);
		model.addAttribute("event", event);
		model.addAttribute("divisions", divisions);
		model.addAttribute("division", division);
	}
	@Override
	public String displayDivision(Model model, long id) {
		for (Division d :divisions) {
			if (d.getId() == id) {
				division = d;
			}
		}
		model.addAttribute("division", division);
		model.addAttribute("event", event);
		model.addAttribute("divisions", divisions);
		return "bracketcreator";
	}
	

}
