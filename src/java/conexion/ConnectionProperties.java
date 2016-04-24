package conexion;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectionProperties {
	
	private String Address;
	private int exitPort;
	private int enterPort;
	private DatagramSocket serverSocket;
	
	public ConnectionProperties() {
		this.Address = "127.0.0.1";
		this.exitPort = 9876;
		this.enterPort = 9877;
		
    	try {
			this.serverSocket = new DatagramSocket(this.exitPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public DatagramSocket getServerSocket() {
		return serverSocket;
	}	
	
	
}
