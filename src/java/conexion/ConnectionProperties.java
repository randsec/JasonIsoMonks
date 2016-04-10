package conexion;

public class ConnectionProperties {
	
	private String Address;
	private int exitPort;
	private int enterPort;
	
	public ConnectionProperties() {
		this.Address = "127.0.0.1";
		this.exitPort = 9876;
		this.enterPort = 9877;
	}

	public String getAddress() {
		return Address;
	}

	public int getExitPort() {
		return exitPort;
	}

	public int getEnterPort() {
		return enterPort;
	}	
}
