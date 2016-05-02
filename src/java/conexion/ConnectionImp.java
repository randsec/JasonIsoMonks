package conexion;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import mainPackage.AbadiaModel;

public class ConnectionImp extends Connection {
	
	private ConnectionProperties cp;
	
	public ConnectionImp(){
    	this.cp = new ConnectionProperties();
	}
	
	/**
	@SuppressWarnings("resource")
	private void receiveAndSend() {
		try {
    		ConnectionProperties cp = new ConnectionProperties();
    		DatagramSocket serverSocket = new DatagramSocket(cp.getExitPort());	
			byte[] receiveData = new byte[1024];
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
								// en espera hasta recibir datos
				String receivedSentence = new String(receivePacket.getData());
				receivedSentence = receivedSentence.substring(0, receivePacket.getLength());
				InetAddress IPAddress = receivePacket.getAddress();
				
				//A partir de aqui, receivedSentence es un JSON útil
				this.show(">>", receivedSentence); // solo vale para mostrar la info
				String sentSentence = this.elaborateResult(receivedSentence);
				//this.show("<<", sentSentence); // solo vale para mostrar la info
								
				byte[] sentSentence_bytes = sentSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, IPAddress, cp.getEnterPort());
				serverSocket.send(sendPacket);
			}
    	} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
	 */
            	
	@SuppressWarnings("unused")
	private String elaborateResult(String data) {
		System.out.println("In");
			AbadiaModel.getInstance().recieveDataFromConnection(data);
		System.out.println("Out");
		return data;
	}	
	
	/**
	 * Muestra por consola el comando recibido
	 * Se lleva una lista de comandos conocidos por si "surge" alguno no controlado
	 * 
	 * @param direction
	 * @param data
	 */
	private void show(String direction, String data) {
		if (!data.isEmpty()) {
			//System.out.println(data);
			ArrayList<String> security_names = new ArrayList<String>();
			security_names.addAll(Arrays.asList("move", "turn", "environment"));
			JSONObject json = new JSONObject(data);
			String name = json.getString("name");
			
			if (security_names.contains(name)) {
				if (!name.equalsIgnoreCase("registerCell"))
					System.out.println(direction + " " + data);
			} else 
				System.out.println("WARNING NEW COMMAND " + direction + " " + data);
		}
	}
	
	
	public void send(String sentSentence) {
		//this.show("<<", sentSentence);
		try {							
			byte[] sentSentence_bytes = sentSentence.getBytes();
			InetAddress addr = InetAddress.getByName(this.cp.getAddress());
			DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, addr, this.cp.getEnterPort());
			this.cp.getServerSocket().send(sendPacket);
		} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
	
	public String receive() {
	    String result = "";
	    try {
	    	byte[] receiveData = new byte[1024];

	        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	        this.cp.getServerSocket().receive(receivePacket); //en espera hasta recibir datos
	        String receivedSentence = new String(receivePacket.getData());
	        receivedSentence = receivedSentence.substring(0, receivePacket.getLength());
	        
	        this.show(">>", receivedSentence); // solo vale para mostrar la info
	        result = receivedSentence;
	      } catch (Exception e) {
	        System.out.println("Error Server: " + e.getMessage());
	      }
	    
	    return result;
	  }

	public ConnectionProperties getCp() {
		return cp;
	}
}