package entities;

public class ProxyData {

	private String address;
	private String port;
	private String type;
	private String anonymity;
	private String country;
	private String hostname;
	private String latency;
	private String uptime;
	private String checkDate;

	public ProxyData(String address, String port, String type, String anonymity, String country, String hostname,
			String latency, String uptime, String checkDate) {
		super();
		this.address = address;
		this.port = port;
		this.type = type;
		this.anonymity = anonymity;
		this.country = country;
		this.hostname = hostname;
		this.latency = latency;
		this.uptime = uptime;
		this.checkDate = checkDate;
	}

	public ProxyData() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnonymity() {
		return anonymity;
	}

	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getLatency() {
		return latency;
	}

	public void setLatency(String latency) {
		this.latency = latency;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	
	
	
}
