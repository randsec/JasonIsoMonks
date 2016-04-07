package conexion;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;

public class ConnectionImp extends Connection {
	
	private ArrayList<Integer> Entities;
	
	public ConnectionImp() {
		this.Entities = new ArrayList<Integer>();
	}
	
    public void run() {
    	int valor = 0;
    	switch(valor) {
    		case 0: this.receiveAndSend(); break;
    		case 1: this.send(); break;
    		default: break;
    	}
    }
	
	private boolean registerParameters(JSONObject json) {
		boolean add = false;
		int entity = json.getInt("entity");
		if (!this.Entities.contains(entity)) {
			add = this.Entities.add(entity);
			if (add) {
				System.out.println("	NUEVA ENTIDAD REGISTRADA: " + entity);
			} else {
				System.out.println("	La entidad " + entity + " no ha podido ser registrada");
			}
		} else { 
			System.out.println("	La entidad " + entity + " ya ha sido registrada");
		}
		return add;
	}
	
	private String elaborateResult(String data) {
		String result = data;
		JSONObject json = new JSONObject(data);
		String name = json.getString("name");
		switch(name) {
			case "info": 
				if (this.registerParameters(json.getJSONObject("parameters"))) {
					int entity = this.Entities.get(0);
					int celda = 11084; //CUIDADO ES DADO A MANO Y PUEDE EXPLOTAR
					result = "{\"name\":\"move\",\"parameters\":{\"entity\":" + entity + ",\"cell\":" + celda + "}}";
				} else { result = ""; }
				break;
			default: break;
		}
		return result;
	}
	
	private void show(String direction, String data) {
		if (!data.isEmpty()) {
			ArrayList<String> security_names = new ArrayList<String>();
			security_names.addAll(Arrays.asList("move", "turn", "info"));
			JSONObject json = new JSONObject(data);
			String name = json.getString("name");
			if (security_names.contains(name)) {
				System.out.println(direction + " " + data);
			} else {
				System.out.println("WARNING NEW COMMAND " + direction + " " + data);
			}
		}
	}
	
	@SuppressWarnings("resource")
	private void receiveAndSend() {
		try {
    		ConnectionProperties cp = new ConnectionProperties();
			DatagramSocket serverSocket = new DatagramSocket(cp.getExitPort());
			byte[] receiveData = new byte[1024];
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket); //en espera hasta recibir datos
				String receivedsentence = new String(receivePacket.getData());
				receivedsentence = receivedsentence.substring(0, receivePacket.getLength());
				InetAddress IPAddress = receivePacket.getAddress();
				
				this.show(">>", receivedsentence); // solo vale para mostrar la info
				String sentSentence = this.elaborateResult(receivedsentence);
				this.show("<<", sentSentence); // solo vale para mostrar la info
								
				byte[] sentSentence_bytes = sentSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, IPAddress, cp.getEnterPort());
				serverSocket.send(sendPacket);
			}
    	} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
	
	private void send() {
		try {
			ConnectionProperties cp = new ConnectionProperties();
			@SuppressWarnings("resource")
			DatagramSocket serverSocket = new DatagramSocket(cp.getExitPort());
			
			String sentSentence = "{\"name\":\"move\",\"parameters\":{\"entity\":9842,\"cell\":10116}}";
			show("<<", sentSentence);
							
			byte[] sentSentence_bytes = sentSentence.getBytes();
			InetAddress addr = InetAddress.getByName(cp.getAddress());
			DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, addr, cp.getEnterPort());
			
			serverSocket.send(sendPacket);
		} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
}