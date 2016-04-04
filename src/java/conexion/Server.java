package conexion;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

public class Server extends Thread{
	private void show(String direction, String data) {
		ArrayList<String> security_names = new ArrayList<String>();
		security_names.addAll(Arrays.asList("move", "turn"));
		JSONObject json = new JSONObject(data);
		String name = json.getString("name");
		if (security_names.contains(name)) {
			System.out.println(direction + " " + data);
		} else {
			System.out.println("WARNING NEW COMMAND " + direction + " " + data);
		}
	}
	
	private void waitToTalk() {
		try {
    		DataConection dc = new DataConection();
			DatagramSocket serverSocket = new DatagramSocket(dc.exitPort);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			boolean next = true;
			while(next) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket); //en espera hasta recibir datos
				String receivedsentence = new String(receivePacket.getData());
				receivedsentence = receivedsentence.substring(0, receivePacket.getLength());
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				show(">>", receivedsentence);
				String sentSentence = receivedsentence;
				//String sentSentence = "{\"name\":\"move\",\"parameters\":{\"entity\":9580,\"cell\":-57800}}";
				show("<<", sentSentence);
								
				byte[] sentSentence_bytes = sentSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, IPAddress, dc.enterPort);
				serverSocket.send(sendPacket);
			}
    	} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
	
	public void talk() {
		try {
			//System.in.read();
			DataConection dc = new DataConection();
			@SuppressWarnings("resource")
			DatagramSocket serverSocket = new DatagramSocket(dc.exitPort);
			
			String sentSentence = "{\"name\":\"move\",\"parameters\":{\"entity\":9842,\"cell\":10116}}";
			show("<<", sentSentence);
							
			byte[] sentSentence_bytes = sentSentence.getBytes();
			InetAddress addr = InetAddress.getByName(dc.Address);
			DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, addr, dc.enterPort);
			
			serverSocket.send(sendPacket);
		} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
	
    public void run() {
    	//this.waitToTalk();
    	this.talk(); 
    }
}