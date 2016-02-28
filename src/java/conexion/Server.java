package conexion;

import java.net.*;

public class Server extends Thread{
    public void run() {
    	try {
    		DataConection dc = new DataConection();
			DatagramSocket serverSocket = new DatagramSocket(dc.PortReceived);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			boolean next = true;
			while(next) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);				
				String sentence = new String(receivePacket.getData());
				sentence = sentence.substring(0, receivePacket.getLength());
				System.out.println("Server Received: " + sentence);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				String capitalizedSentence = sentence.toUpperCase();
				if (sentence.equalsIgnoreCase("hola")) {
					capitalizedSentence = "adios";
				}
				
				sendData = capitalizedSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				if (sentence.equals("exit")) {
					serverSocket.close(); 
					next = false;
				}
			}
    	} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
    }
}