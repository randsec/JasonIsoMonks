package mainPackage;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import conexion.Connection;

public class AbadiaModelImp extends AbadiaModel{   
	
	private String Agent;
	private HashMap<String, HashMap<String,String>> entities;
	private HashMap<String, HashMap<String,String>> decorations;
	private boolean loadedEnvironment;
	private int cellEvent;

	public AbadiaModelImp() {
		this.Agent = "";
		this.entities = new HashMap<String, HashMap<String,String>>();
		this.decorations = new HashMap<String, HashMap<String,String>>();			   
		this.loadedEnvironment = false;
		this.cellEvent = -1;
	}
	
	public void setAgent(String agente) {
		this.Agent = agente;
	}
	
   	public HashMap<String, HashMap<String,String>> getEntities() {
		return this.entities;
	}
	
	public HashMap<String, HashMap<String,String>> getDecorations() {
		return this.decorations;
	}
	
	public boolean isEnvironmentLoaded(){
		return this.loadedEnvironment;
	}
	
	public boolean tocar(String object){
		switch (object) {
		case "campana":
			this.sendActionToUnity("suena_campana");
			Abadia.getInstance().addPercept("frayAlejandro", "quiero_ir(capilla)");
			Abadia.getInstance().addPercept("frayHector", "quiero_ir(capilla)");
			break;
		default: 
			System.out.println("tocar ¿" + object + "?");
			break;
	}
		return true;
	}
   
	public boolean ir_a(String location) {
		this.moveAgentToDecoration(this.Agent, location);
		return true;
	}
   
	public String recieveDataFromConnection(String data){
		String result = data;
		JSONObject json = new JSONObject(data);
		String name = json.getString("name");
		JSONObject parameters = json.getJSONObject("parameters");
		switch(name) {
			case "environment":
				this.registerEnvironment(parameters);
				Abadia.getInstance().addPercept("frayHector", "quiero_ir(capilla)");
				break;
			case "event":
				this.cellEvent = parameters.getInt("cell");
				this.parseEvent(parameters);
				break;
			default: break;
		}
		return result;
	}
	
	private void parseEvent(JSONObject json){
		//{"name":"event","parameters":{"eventName":"campana_cerca","who":"frayFernando"}}
		String eventName = json.getString("eventName");
		String who = json.getString("who");
		switch (eventName){
			case "campana_cerca":
				Abadia.getInstance().addPercept("frayFernando", "quiero_tocar(campana)");
				break;
			case "entrar_comedor":
				Abadia.getInstance().addPercept("frayAlejandro", "quiero_ir(comedor)[source(" + who + ")]");
				Abadia.getInstance().addPercept("frayHector", "quiero_ir(comedor)[source(" + who + ")]");
				break;
			case "hora_trabajar":
				Abadia.getInstance().addPercept("frayAlejandro", "quiero_ir(taller)[source(" + who + ")]");
				Abadia.getInstance().addPercept("frayHector", "quiero_ir(cocina)[source(" + who + ")]");
				break;
			case "cerrar_puerta":
				Abadia.getInstance().addPercept("frayHector", "cerrada(cocina)");
				break;
			//default: System.out.println("Evento: ¿" + eventName + "?");
		}
	}
   
	private void registerEnvironment(JSONObject json){
		this.registerEntities(json.getJSONArray("entities"));
		this.registerDecorations(json.getJSONArray("decorations"));
		this.loadedEnvironment = true;
		System.out.println("Entorno...OK");
	}
	
	private void registerEntities(JSONArray ents){
		HashMap<String, HashMap<String,String>> hmTemp = new HashMap<String, HashMap<String,String>>();
		for (int i = 0; i < ents.length(); i++){
			JSONObject entidadActual = ents.getJSONObject(i);
			
			String name = entidadActual.getString("name");
			int id = entidadActual.getInt("id");
			int cell = entidadActual.getInt("cell");
			
			HashMap<String, String> atributos = new HashMap<String,String>();
			atributos.put("id", Integer.toString(id));
			atributos.put("cell", Integer.toString(cell));
	
			hmTemp.put(name, atributos);
		}
		this.entities = hmTemp;
	}
	
	private void registerDecorations(JSONArray decs){
		HashMap<String, HashMap<String,String>> hmTemp = new HashMap<String, HashMap<String,String>>();
		for (int i = 0; i < decs.length(); i++){
			JSONObject decorationActual = decs.getJSONObject(i);
			
			String name = decorationActual.getString("name");
			int id = decorationActual.getInt("id");
			int cell = decorationActual.getInt("cell");
			
			HashMap<String, String> atributos = new HashMap<String,String>();
				atributos.put("id", Integer.toString(id));
				atributos.put("cell", Integer.toString(cell));
			
			hmTemp.put(name, atributos);
		}		
		this.decorations = hmTemp;
	}
	
	private void sendActionToUnity(String actionName) {
		String sentSentence = "{\"name\":\"action\",\"parameters\":{\"cell\":" + this.cellEvent + ",\"actionName\":\""+actionName+"\"}}";
		Connection.getInstance().send(sentSentence);
	}
	
	private void moveAgentToDecoration(String agName, String decName) {
		int agentID = Integer.parseInt(AbadiaModel.getInstance().getEntities().get(agName).get("id"));
		int destino = Integer.parseInt(AbadiaModel.getInstance().getDecorations().get(decName).get("cell"));
		String sentSentence = "{\"name\":\"move\",\"parameters\":{\"entity\":"+agentID+",\"cell\":"+destino+"}}";
		Connection.getInstance().send(sentSentence);
		this.updateAgentPosition(agName, destino);
	}
	
	private void updateAgentPosition(String agName, int cell){
		HashMap<String, String> atrs = this.entities.get(agName);
		atrs.put("cell", Integer.toString(cell));
		this.entities.put(agName, atrs);
	}
	
}
