package es.uniovi.hci.model;

public class EventItem {
	
	//private Integer id=0;
	private String sceneId;
	private Integer eventType;
	private String elementId;
	private Long timeStamp;
	private Integer x;
	private Integer y;
	private String sessionId;
	private String keyValueEvent;
	private Integer keyCodeEvent;
	
	public EventItem() {}
	
	public EventItem(String sessionId, String sceneId, Integer eventType, String elementId, Long timeStamp, Integer x,
			Integer y, String keyValueEvent, Integer keyCodeEvent) {
		super();
		this.sessionId = sessionId;
		this.sceneId = sceneId;
		this.eventType = eventType;
		this.elementId = elementId;
		this.timeStamp = timeStamp;
		this.x = x;
		this.y = y;
		this.keyValueEvent = keyValueEvent;
		this.keyCodeEvent = keyCodeEvent;
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
	
	public Integer getEventType() {
		return eventType;
	}
	
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
	
	public String getElementId() {
		return elementId;
	}
	
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	
	public Long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
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

	public String getKeyValueEvent() {
		return keyValueEvent;
	}

	public void setKeyValueEvent(String keyValueEvent) {
		this.keyValueEvent = keyValueEvent;
	}

	public Integer getKeyCodeEvent() {
		return keyCodeEvent;
	}

	public void setKeyCodeEvent(Integer keyCodeEvent) {
		this.keyCodeEvent = keyCodeEvent;
	}

	@Override
	public String toString() {
		return "EventItem [sceneId=" + sceneId + ", eventType=" + eventType + ", elementId=" + elementId
				+ ", timeStamp=" + timeStamp + ", x=" + x + ", y=" + y + ", sessionId=" + sessionId + ", keyValueEvent="
				+ keyValueEvent + ", keyCodeEvent=" + keyCodeEvent + "]";
	}

	
}
