package es.uniovi.hci.model;

import java.util.ArrayList;
import java.util.List;

public class EventsTransferObject {
	
	List<EventItem> list = new ArrayList<EventItem>();
	Integer timezone = null;
	String sessionId;
	Long idExperiment;
	
	public EventsTransferObject() {
		super();
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public List<EventItem> getList() {
		return list;
	}

	public void setList(List<EventItem> list) {
		this.list = list;
	}

	public EventsTransferObject(List<EventItem> list) {
		super();
		this.list = list;
	}
	
	public void add ( EventItem item )
	{
		list.add(item);
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Long getIdExperiment() {
		return idExperiment;
	}

	public void setIdExperiment(Long idExperiment) {
		this.idExperiment = idExperiment;
	}

	@Override
	public String toString() {
		return "EventsTransferObject [list=" + list + ", timezone=" + timezone + ", sessionId=" + sessionId + "]";
	}
	
}
