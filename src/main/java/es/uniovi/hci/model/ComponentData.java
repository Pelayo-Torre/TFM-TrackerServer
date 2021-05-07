package es.uniovi.hci.model;

public class ComponentData {
	
	private Integer x,y,xF,yF, timezone, typeId;
	private Long timeStamp;
	private String sceneId, componentId, sessionId, componentAssociated;
	private Long idExperiment;
	
	public ComponentData(Integer x, Integer y, Integer xF, Integer yF, String id, String sessionId, String sceneId,
			Integer timezone, Long timeStamp, Integer typeId, String componentAssociated) {
		super();
		this.x = x;
		this.y = y;
		this.xF = xF;
		this.yF = yF;
		this.componentId = id;
		this.sessionId = sessionId;
		this.sceneId = sceneId;
		this.timezone = timezone;
		this.timeStamp = timeStamp;
		this.typeId = typeId;
		this.componentAssociated = componentAssociated;
	}
	
	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getTimezone() {
		return timezone;
	}
	

	public void setTimezone(Integer timeZone) {
		this.timezone = timeZone;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getxF() {
		return xF;
	}

	public void setxF(Integer xF) {
		this.xF = xF;
	}

	public Integer getyF() {
		return yF;
	}

	public void setyF(Integer yF) {
		this.yF = yF;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String id) {
		this.componentId = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	
	public Long getIdExperiment() {
		return idExperiment;
	}

	public void setIdExperiment(Long idExperiment) {
		this.idExperiment = idExperiment;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getComponentAssociated() {
		return componentAssociated;
	}

	public void setComponentAssociated(String componentAssociated) {
		this.componentAssociated = componentAssociated;
	}

	@Override
	public String toString() {
		return "ComponentData [x=" + x + ", y=" + y + ", xF=" + xF + ", yF=" + yF + ", timezone=" + timezone
				+ ", typeId=" + typeId + ", timeStamp=" + timeStamp + ", sceneId=" + sceneId + ", componentId="
				+ componentId + ", sessionId=" + sessionId + ", componentAssociated=" + componentAssociated
				+ ", idExperiment=" + idExperiment + "]";
	}

}
