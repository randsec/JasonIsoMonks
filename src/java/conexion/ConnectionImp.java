package conexion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;

import jason.util.Pair;

public class ConnectionImp extends Connection {
	
	private HashMap<String,Integer> entities;
	private HashMap<String,Integer> decorations;
	
	private HashMap<Integer,String> cells;
	
	public ConnectionImp() {
		this.entities = new HashMap<String,Integer>();
		this.decorations = new HashMap<String,Integer>();
		
		this.cells = new HashMap<Integer,String>();
	}
	
    public void run() {
    	int valor = 0;
    	switch(valor) {
    		case 0: this.receiveAndSend(); break;
    		case 1: this.send(""); break;
    		default: break;
    	}
    }
        
    /**
     * Obtiene el identificador de una celda registrada del mapa
     * @return
     */
	private int getRandomCellPosition(){	
		List<Integer> keys      = new ArrayList<Integer>(this.cells.keySet());
		Integer       randomKey = keys.get(new Random().nextInt(keys.size()));

		return randomKey.intValue();
	}    
    
    /**
     * Registra la celda en la estructura correspondiente
     * El JSON de entrada es SOLO los parameters, no el JSON completo
     * @param json
     * @return
     */
	private boolean registerCell(JSONObject json){
		boolean status = false;
		int cell = json.getInt("cell");
				
		if (!this.cells.containsKey(cell)){
			if(json.has("cellDescription")){
				String desc = json.getString("cellDescription");
				cells.put(cell, desc);
				System.out.println("La celda " + cell + "tiene una descripcion " + desc);
			}
			else
				cells.put(cell, "");
			status = true;
		}
		
		return status;
	}
	
	/**
	 * Registra la entidad en la estructura correspondiente
     * El JSON de entrada es SOLO los parameters, no el JSON completo
	 * @param json
	 * @return
	 */
	private boolean registerEntity(JSONObject json) {
		boolean status = false;
		int entity = json.getInt("entity");
		String entityName = json.getString("entityName");
		
		
		if (!this.entities.containsKey(entity)) {
			this.entities.put(entityName,entity);			
			System.out.println("	NUEVA ENTIDAD REGISTRADA: " + entityName + "(entidad " + entity + ")");
			status = true;
		} else 
			System.out.println("	La entidad " + entity + " ya ha sido registrada con nombre " + this.entities.get(entity));
		
		
		return status;
	}
	
	private boolean registerDecoration(JSONObject json){
		boolean status = false;
		int decoration = json.getInt("decoration");
		int cell = json.getInt("cell");
		String decorationName = json.getString("decorationName");
		
		
		if (!this.decorations.containsKey(decoration)) {
			this.decorations.put(decorationName, cell);			
			System.out.println("	NUEVA DECORACIÓN REGISTRADA: " + decorationName + "(" + decoration + ") en la celda " + cell);
			status = true;
		} else 
			System.out.println("	La decoracion " + decoration + " ya ha sido registrada con nombre " + this.decorations.get(decoration));
		
		
		return status;

	}
	
	/**
	 * Recibe un String en formato JSON que será la acción a ejecutar en Unity
	 * Se switchea segun el nombre de accion y se actua en consecuencia
	 * 
	 * FORMATO:
	 *  {"name":"####","parameters":{...}}
	 * @param data
	 * @return
	 */
	private String elaborateResult(String data) {
		String result = data;
		JSONObject json = new JSONObject(data);
		String name = json.getString("name");
		switch(name) {
			case "registerEntity": 
				if (this.registerEntity(json.getJSONObject("parameters"))) {					
					JSONObject parametersJSON = json.getJSONObject("parameters");
					int entity = parametersJSON.getInt("entity");
					
					int celda = 9526; // Esta es la celda del tomate (se debe sacar cuando se registre)
					//int celda = this.getRandomCellPosition();
					
					result = "{\"name\":\"move\",\"parameters\":{\"entity\":" + entity + ",\"cell\":" + celda + "}}";
				} else { result = ""; }
				break;
			case "registerCell":
				this.registerCell(json.getJSONObject("parameters"));
			break;
			case "registerDecoration":
				this.registerDecoration(json.getJSONObject("parameters"));
			break;
			default: break;
		}
		return result;
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
			security_names.addAll(Arrays.asList("move", "turn", "registerEntity", "registerCell"));
			JSONObject json = new JSONObject(data);
			String name = json.getString("name");
			
			if (security_names.contains(name)) {
				if (!name.equalsIgnoreCase("registerCell"))
					System.out.println(direction + " " + data);
			} else 
				System.out.println("WARNING NEW COMMAND " + direction + " " + data);
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
	
	@SuppressWarnings({ "resource" })
	private void send(String sentSentence) {
		try {
			ConnectionProperties cp = new ConnectionProperties();
			DatagramSocket serverSocket = new DatagramSocket(cp.getExitPort());
			
			//String sentSentence = "{\"name\":\"move\",\"parameters\":{\"entity\":9842,\"cell\":10116}}";
			show("<<", sentSentence);
							
			byte[] sentSentence_bytes = sentSentence.getBytes();
			InetAddress addr = InetAddress.getByName(cp.getAddress());
			DatagramPacket sendPacket = new DatagramPacket(sentSentence_bytes, sentSentence_bytes.length, addr, cp.getEnterPort());
			
			serverSocket.send(sendPacket);
		} catch (Exception e) {
    		System.out.println("Error Server: " + e.getMessage());
    	}
	}
	
	@SuppressWarnings({ "resource", "unused" })
	  private void received() {
	    try {
	        ConnectionProperties cp = new ConnectionProperties();
	      DatagramSocket serverSocket = new DatagramSocket(cp.getExitPort());
	      byte[] receiveData = new byte[1024];
	      while(true) {
	        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	        serverSocket.receive(receivePacket); //en espera hasta recibir datos
	        String receivedsentence = new String(receivePacket.getData());
	        receivedsentence = receivedsentence.substring(0, receivePacket.getLength());
	        
	        this.show(">>", receivedsentence); // solo vale para mostrar la info
	        this.elaborateResult(receivedsentence);    
	      }
	      } catch (Exception e) {
	        System.out.println("Error Server: " + e.getMessage());
	      }
	  }

	@Override
	public HashMap<String, Integer> getEntities() {
		return this.entities;
	}

	@Override
	public HashMap<Integer, String> getCells() {
		return this.cells;
	}
	
	@Override
	public HashMap<String, Integer> getDecorations() {
		return this.decorations;
	}
	
	public boolean sendCommand(String json){
		boolean status = false;
		
		try{
			send(json);
			status = true;
		}
		catch (Exception e) {
				System.out.println("Error Sending Command: " + e.getMessage());
		}

		return status;
	}
	
}