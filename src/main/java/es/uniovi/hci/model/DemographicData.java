package es.uniovi.hci.model;

import java.util.Date;

public class DemographicData {
	
	private Long id;
	private String sessionId;
	private Integer timezone;
	private Long idExperiment;
	private String type;
	private String name;
	
	private Double numberValue;
	private String stringValue;
	private Date dateValue;
	
	public DemographicData(Long id, String name, String type, Long idExperiment) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.idExperiment = idExperiment;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Double getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(Double numberValue) {
		this.numberValue = numberValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	
	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}
	
	public Long getIdExperiment() {
		return idExperiment;
	}

	public void setIdExperiment(Long idExperiment) {
		this.idExperiment = idExperiment;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DemographicData [id=" + id + ", sessionId=" + sessionId + ", timezone=" + timezone + ", idExperiment="
				+ idExperiment + ", type=" + type + ", name=" + name + ", numberValue=" + numberValue + ", stringValue="
				+ stringValue + ", dateValue=" + dateValue + "]";
	}
	
}
