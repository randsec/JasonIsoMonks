package conexion;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import mainPackage.AbadiaModel;

public class ConnectionListener extends Thread{
	
    public void run() {
    	this.receive();
    }

	/**
	 * Muestra por consola el comando recibido
	 * Se lleva una lista de comandos conocidos por si "surge" alguno no controlado
	 * 
	 * @param direction
	 * @param data
	 */
	@SuppressWarnings("unused")
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

    
	public void receive() {
	    try {
	    	while(true){		    	
		    	byte[] receiveData = new byte[1024];
	
		        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		        Connection.getInstance().getCp().getServerSocket().receive(receivePacket); //en espera hasta recibir datos
		        String receivedSentence = new String(receivePacket.getData());
		        receivedSentence = receivedSentence.substring(0, receivePacket.getLength());
		        
		        //this.show(">>", receivedSentence); // solo vale para mostrar la info
		        AbadiaModel.getInstance().recieveDataFromConnection(receivedSentence);
	    	}
	      } catch (Exception e) {
	        System.out.println("Error Server: " + e.getMessage());
	      }
	    
	  }


}
