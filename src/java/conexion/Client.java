package conexion;

import java.io.*;
import java.net.*;

public class Client extends Thread {
	public void run() {
		try {
			BufferedReader inFromUser =
			new BufferedReader(new InputStreamReader(System.in));

			DatagramSocket clientSocket = new DatagramSocket();
			
			DataConection dc = new DataConection();
			InetAddress IPAddress = InetAddress.getByName(dc.Address);

			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			String sentence;
			do {
				System.out.print("Client send > ");
				sentence = inFromUser.readLine();
				sendData = sentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, dc.PortSend);
				clientSocket.send(sendPacket);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());
				modifiedSentence = modifiedSentence.substring(0, receivePacket.getLength());
				System.out.println("Client Received: " + modifiedSentence);
			} while(!sentence.equals("exit"));
			if (sentence.equals("exit")) { System.out.println("=== FINISH ==="); }
			clientSocket.close();
		} catch (Exception e) {
			System.out.println("Error Client: " + e.getMessage());
		}
	}
}