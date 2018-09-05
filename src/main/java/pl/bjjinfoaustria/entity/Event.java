package pl.bjjinfoaustria.entity;


import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import pl.bjjinfoaustria.enums.StatusE;

@Entity
@Table(name="events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nameOfEvent;
	
	@ElementCollection
	private List<Participant> participants;
	private String typeOfEvent;
	private String host;
	private String organisator;
	private String startDate;
	private String endDate;
	private String startHour;
	private String deadline;
	private String locationCity;
	private String locationAddress;
	private String fee;
	private StatusE status;
	
	
	public List<Participant> getParticipants() {
		return participants;
	}
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	public long getId() {
		return id;
	}
	public String getTypeOfEvent() {
		return typeOfEvent;
	}
	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNameOfEvent() {
		return nameOfEvent;
	}
	public void setNameOfEvent(String nameOfEvent) {
		this.nameOfEvent = nameOfEvent;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getOrganisator() {
		return organisator;
	}
	public void setOrganisator(String organisator) {
		this.organisator = organisator;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getLocationCity() {
		return locationCity;
	}
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public StatusE getStatus() {
		return status;
	}
	public void setStatus(StatusE status) {
		this.status = status;
	}

	
	

}
