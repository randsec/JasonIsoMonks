package conexion;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

public class Server extends Thread{
	private void show(String direction, String data) {
		ArrayList<String> security_names = new ArrayList<String>();
		security_names.addAll(Arrays.asList("move", ""));
		JSONObject json = new JSONObject(data);
		String name = json.getString("name");
		if (security_names.contains(name)) {
			System.out.println(direction + " " + data);
		} else {
			System.out.println("WARNING NEW COMMAND " + direction + " " + data);
		}
	}
	
    public void run() {
    	try {
    		DataConection dc = new DataConection();
			DatagramSocket serverSocket = new DatagramSocket(dc.exitPort);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			boolean next = true;
			while(next) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);				
				String receivedsentence = new String(receivePacket.getData());
				receivedsentence = receivedsentence.substring(0, receivePacket.getLength());
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				show(">>", receivedsentence);
				String sentSentence = receivedsentence;
				//show("<<", sentSentence);
								
				byte[] sentSentence_bytes = sentSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, IPAddress, dc.enterPort);
				serverSocket.send(sendPacket);
			}
    	} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
    }
}