package es.uniovi.hci.model;

public class UserData {
	
	Boolean browserOnline, javaEnabled, dataCookiesEnabled;

	String timeOpened, pageon, referrer, browserName, browserEngine, browserVersion1a, browserVersion1b,
			browserLanguage, browserPlatform, dataCookies1, dataCookies2, dataStorage, sessionId, locale,
			remoteAddress, remoteHost;

	Integer previousSites, sizeScreenW, sizeScreenH, sizeDocW, sizeDocH, sizeInW, sizeInH, sizeAvailW, sizeAvailH,
			scrColorDepth, scrPixelDepth, remotePort, timezone;
	
	Long timeStamp, idExperiment;
	
	public UserData(Boolean browserOnline, Boolean javaEnabled, Boolean dataCookiesEnabled, String timeOpened,
			String pageon, String referrer, String browserName, String browserEngine, String browserVersion1a,
			String browserVersion1b, String browserLanguage, String browserPlatform, String dataCookies1,
			String dataCookies2, String dataStorage, Integer previousSites, Integer sizeScreenW, Integer sizeScreenH,
			Integer sizeDocW, Integer sizeDocH, Integer sizeInW, Integer sizeInH, Integer sizeAvailW,
			Integer sizeAvailH, Integer scrColorDepth, Integer scrPixelDepth, String sessionId, Long timeStamp,
			String locale, String remoteAddress, String remoteHost, Integer remotePort, Integer timezone) {
		super();
		this.browserOnline = browserOnline;
		this.javaEnabled = javaEnabled;
		this.dataCookiesEnabled = dataCookiesEnabled;
		this.timeOpened = timeOpened;
		this.pageon = pageon;
		this.referrer = referrer;
		this.browserName = browserName;
		this.browserEngine = browserEngine;
		this.browserVersion1a = browserVersion1a;
		this.browserVersion1b = browserVersion1b;
		this.browserLanguage = browserLanguage;
		this.browserPlatform = browserPlatform;
		this.dataCookies1 = dataCookies1;
		this.dataCookies2 = dataCookies2;
		this.dataStorage = dataStorage;
		this.previousSites = previousSites;
		this.sizeScreenW = sizeScreenW;
		this.sizeScreenH = sizeScreenH;
		this.sizeDocW = sizeDocW;
		this.sizeDocH = sizeDocH;
		this.sizeInW = sizeInW;
		this.sizeInH = sizeInH;
		this.sizeAvailW = sizeAvailW;
		this.sizeAvailH = sizeAvailH;
		this.scrColorDepth = scrColorDepth;
		this.scrPixelDepth = scrPixelDepth;
		this.sessionId = sessionId;
		this.timeStamp = timeStamp;
		this.locale = locale;
		this.remoteAddress = remoteAddress;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.timezone = timezone;
	}

	public UserData(String idSession, Long idExperiment, long time, String locale, String remoteAddr, String remoteHost2, int remotePort2,
			Integer timezone) {
		this.sessionId = idSession;
		this.idExperiment = idExperiment;
		this.timeStamp = time;
		this.locale = locale;
		this.remoteAddress = remoteAddr;
		this.remoteHost = remoteHost2;
		this.remotePort = remotePort2;
		this.timezone = timezone;
	}

	public boolean isBrowserOnline() {
		return browserOnline;
	}

	public void setBrowserOnline(boolean browserOnline) {
		this.browserOnline = browserOnline;
	}

	public boolean isJavaEnabled() {
		return javaEnabled;
	}

	public void setJavaEnabled(boolean javaEnabled) {
		this.javaEnabled = javaEnabled;
	}

	public boolean isDataCookiesEnabled() {
		return dataCookiesEnabled;
	}

	public void setDataCookiesEnabled(boolean dataCookiesEnabled) {
		this.dataCookiesEnabled = dataCookiesEnabled;
	}

	public String getTimeOpened() {
		return timeOpened;
	}

	public void setTimeOpened(String timeOpened) {
		this.timeOpened = timeOpened;
	}

	public String getPageon() {
		return pageon;
	}

	public void setPageon(String pageon) {
		this.pageon = pageon;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserEngine() {
		return browserEngine;
	}

	public void setBrowserEngine(String browserEngine) {
		this.browserEngine = browserEngine;
	}

	public String getBrowserVersion1a() {
		return browserVersion1a;
	}

	public void setBrowserVersion1a(String browserVersion1a) {
		this.browserVersion1a = browserVersion1a;
	}

	public String getBrowserVersion1b() {
		return browserVersion1b;
	}

	public void setBrowserVersion1b(String browserVersion1b) {
		this.browserVersion1b = browserVersion1b;
	}

	public String getBrowserLanguage() {
		return browserLanguage;
	}

	public void setBrowserLanguage(String browserLanguage) {
		this.browserLanguage = browserLanguage;
	}

	public String getBrowserPlatform() {
		return browserPlatform;
	}

	public void setBrowserPlatform(String browserPlatform) {
		this.browserPlatform = browserPlatform;
	}

	public String getDataCookies1() {
		return dataCookies1;
	}

	public void setDataCookies1(String dataCookies1) {
		this.dataCookies1 = dataCookies1;
	}

	public String getDataCookies2() {
		return dataCookies2;
	}

	public void setDataCookies2(String dataCookies2) {
		this.dataCookies2 = dataCookies2;
	}

	public String getDataStorage() {
		return dataStorage;
	}

	public void setDataStorage(String dataStorage) {
		this.dataStorage = dataStorage;
	}

	public Integer getPreviousSites() {
		return previousSites;
	}

	public void setPreviousSites(Integer previousSites) {
		this.previousSites = previousSites;
	}

	public Integer getSizeScreenW() {
		return sizeScreenW;
	}

	public void setSizeScreenW(Integer sizeScreenW) {
		this.sizeScreenW = sizeScreenW;
	}

	public Integer getSizeScreenH() {
		return sizeScreenH;
	}

	public void setSizeScreenH(Integer sizeScreenH) {
		this.sizeScreenH = sizeScreenH;
	}

	public Integer getSizeDocW() {
		return sizeDocW;
	}

	public void setSizeDocW(Integer sizeDocW) {
		this.sizeDocW = sizeDocW;
	}

	public Integer getSizeDocH() {
		return sizeDocH;
	}

	public void setSizeDocH(Integer sizeDocH) {
		this.sizeDocH = sizeDocH;
	}

	public Integer getSizeInW() {
		return sizeInW;
	}

	public void setSizeInW(Integer sizeInW) {
		this.sizeInW = sizeInW;
	}

	public Integer getSizeInH() {
		return sizeInH;
	}

	public void setSizeInH(Integer sizeInH) {
		this.sizeInH = sizeInH;
	}

	public Integer getSizeAvailW() {
		return sizeAvailW;
	}

	public void setSizeAvailW(Integer sizeAvailW) {
		this.sizeAvailW = sizeAvailW;
	}

	public Integer getSizeAvailH() {
		return sizeAvailH;
	}

	public void setSizeAvailH(Integer sizeAvailH) {
		this.sizeAvailH = sizeAvailH;
	}

	public Integer getScrColorDepth() {
		return scrColorDepth;
	}

	public void setScrColorDepth(Integer scrColorDepth) {
		this.scrColorDepth = scrColorDepth;
	}

	public Integer getScrPixelDepth() {
		return scrPixelDepth;
	}

	public void setScrPixelDepth(Integer scrPixelDepth) {
		this.scrPixelDepth = scrPixelDepth;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public Integer getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public Long getIdExperiment() {
		return idExperiment;
	}

	public void setIdExperiment(Long idExperiment) {
		this.idExperiment = idExperiment;
	}

	@Override
	public String toString() {
		return "UserData [browserOnline=" + browserOnline + ", javaEnabled=" + javaEnabled + ", dataCookiesEnabled="
				+ dataCookiesEnabled + ", timeOpened=" + timeOpened + ", pageon=" + pageon + ", referrer=" + referrer
				+ ", browserName=" + browserName + ", browserEngine=" + browserEngine + ", browserVersion1a="
				+ browserVersion1a + ", browserVersion1b=" + browserVersion1b + ", browserLanguage=" + browserLanguage
				+ ", browserPlatform=" + browserPlatform + ", dataCookies1=" + dataCookies1 + ", dataCookies2="
				+ dataCookies2 + ", dataStorage=" + dataStorage + ", sessionId=" + sessionId + ", locale=" + locale
				+ ", remoteAddress=" + remoteAddress + ", remoteHost=" + remoteHost + ", previousSites=" + previousSites
				+ ", sizeScreenW=" + sizeScreenW + ", sizeScreenH=" + sizeScreenH + ", sizeDocW=" + sizeDocW
				+ ", sizeDocH=" + sizeDocH + ", sizeInW=" + sizeInW + ", sizeInH=" + sizeInH + ", sizeAvailW="
				+ sizeAvailW + ", sizeAvailH=" + sizeAvailH + ", scrColorDepth=" + scrColorDepth + ", scrPixelDepth="
				+ scrPixelDepth + ", remotePort=" + remotePort + ", timezone=" + timezone + ", timeStamp=" + timeStamp
				+ ", idExperiment=" + idExperiment + "]";
	}

	
}
