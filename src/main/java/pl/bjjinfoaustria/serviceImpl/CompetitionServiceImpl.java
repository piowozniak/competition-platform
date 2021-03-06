package pl.bjjinfoaustria.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.bjjinfoaustria.bean.DivisionMB;
import pl.bjjinfoaustria.bean.Round;
import pl.bjjinfoaustria.entity.Competitor;
import pl.bjjinfoaustria.entity.Division;
import pl.bjjinfoaustria.entity.Event;
import pl.bjjinfoaustria.repository.CompetitorRepository;
import pl.bjjinfoaustria.repository.DivisionRepository;
import pl.bjjinfoaustria.repository.EventRepository;
import pl.bjjinfoaustria.service.CompetitionService;
@Service
public class CompetitionServiceImpl implements CompetitionService {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private DivisionRepository divisionRepository;
	@Autowired
	private CompetitorRepository competitorRepository;
	private Event event;
	private DivisionMB division;
	private List<Division> divisions = new ArrayList<>();
	private List<DivisionMB> listOfDivisions = new ArrayList<>();
	
	@Override
	public String displayBrackets(Model model, long id) {
		initEvent(model, id);
		return "displaycompetitionbrackets";
	}
	private void initEvent(Model model, long id) {
		divisions.clear();
		listOfDivisions.clear();
		event = eventRepository.findOne(id);
		divisions = event.getDivisions().stream().filter(Objects::nonNull).collect(Collectors.toList());
		divisions.forEach(d-> listOfDivisions.add(new DivisionMB(event, d, d.getCompetitors())));		
		initializeDivisions(model, id );
		division = listOfDivisions.get(0);
		addToModelAttribute(model);
	}
	@Override
	public String displayDivision(Model model, long id) {
		listOfDivisions.forEach(d-> {if (d.getDivision().getId() == id) {division = d;};});
		addToModelAttribute(model);
		return "displaycompetitionbrackets";
	}
	private void initializeDivisions(Model model, long id ) {
		listOfDivisions.forEach(d->d.initializeFights());
	}
	private void addToModelAttribute(Model model) {
		model.addAttribute("event", event);
		model.addAttribute("listOfDivisions", listOfDivisions);
		model.addAttribute("division", division);
	}
	@Override
	public String addWinnerToTheNextRound(Model model, long id, int fightIndex, int roundIndex) {
		Competitor competitor = division.getCompetitors().stream().filter(c-> c.getId()==id).findFirst().get();
		division.addWinnerToNextRound(competitor, fightIndex, division.getRounds().get(roundIndex));		
		addToModelAttribute(model);
		return "displaycompetitionbrackets";
	}

	@Override
	public String removeCompetitorFromWinnerArray(Model model, long id, int fightIndex, int roundIndex) {
		Round round = division.getRounds().get(roundIndex);
		division.removeWinnerFromArray(id, round, fightIndex);
		addToModelAttribute(model);
		return "displaycompetitionbrackets";
	}
	@Override
	public String submitCompetitorsToTheNextRound(Model model, int roundIndex) {
		Round round = division.getRounds().get(roundIndex);
		round.initializeBracketsForNextRound();
		division.saveCompetitorsToTheNextRound(round, competitorRepository);
		addToModelAttribute(model);
		return "displaycompetitionbrackets";
	}

	

}
