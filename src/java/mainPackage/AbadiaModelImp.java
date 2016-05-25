package mainPackage;

import org.json.JSONArray;
import org.json.JSONObject;
import conexion.Connection;
import jason.JasonException;
import jason.architecture.AgArch;
import jason.asSemantics.Agent;
import jason.asSemantics.Intention;
import jason.asSyntax.Literal;
import jason.bb.BeliefBase;
import jason.runtime.RuntimeServicesInfraTier;
import jason.stdlib.foreach;
import java.util.*;
import jason.*;

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
			String accion = "quiero_ir_a_rezar";
			
			/*--------------------------------------------*/
			
			Agent agentetest = new Agent();		
			
			
			try {
				
				agentetest.initAg("src/asl/frayAlejandro.asl");				
				//agentetest.addBel(Literal.parseLiteral("ganasrezar(muchas)"));
				
				
				
				
			/*	RuntimeServicesInfraTier rsit = getEnvironmentInfraTier().getRuntimeServices();
				System.out.println(rsit.toString());
				
				
				AgArch agar = new AgArch();
				
				agentetest.initAg("src/asl/frayAlejandro.asl");											
				//agar.getTS().getAg().addBel(Literal.parseLiteral("ganasrezar(muchas)"));							
				
				System.out.println("BeliefBase de Alejandro: " + agentetest.getBB().toString());
				
				
				agar.setTS(agentetest.getTS());		
				
								
				agar.getTS().getAg().addBel(Literal.parseLiteral("ganasrezar(muchas)"));
				
				System.out.println(agar.getAgName());
				
				//Literal lit = (Literal) Literal.parseLiteral("ganas_rezar(muchas)");
				
				//agentetest.getTS().getAg().addBel(Literal.parseLiteral("ganas_rezar(muchas)"));
				
				//agentetest.delBel(Literal.parseLiteral("ganas_rezar(ninguna)"));
				
				
				
				//agentetest.delBel(Literal.parseLiteral("ganasrezar(ninguna)"));
				
				//agentetest.addBel(Literal.parseLiteral("ganasrezar(muchas)"));	
				
				
				System.out.println(">>>>>> SE HA ANYADIDO EL BELIEF");
				
				System.out.println("BeliefBase de Alejandro modificada: " + agentetest.getBB().toString());
				
				Agent agentetest2 = new Agent();
				
				agentetest2.initAg("src/asl/frayAlejandro.asl");
				System.out.println("BeliefBase de Alejandro reiniciada: " + agentetest.getBB().toString());
				
				
				Intention intention = new Intention();															
				
				*/
				
				//agentetest2.brf(Literal.parseLiteral("ganas_rezar(muchas)"), Literal.parseLiteral("ganas_rezar(ninguna)"), intention);
				
				//System.out.println("-->brf ejecutado");				
				
				//agentetest.getBB().clear();
				
				
				
				//System.out.println("initial bels: " + agentetest.getInitialBels().size());
				
				//agentetest.addBel(Literal.parseLiteral("ganas_rezar(muchas)")); HACE LO MISMO QUE LA LINEA DE ABAJO
				//agentetest.getBB().add(Literal.parseLiteral("ganas_de_comer(muchas)"));			
				//agentetest.getBB().add(Literal.parseLiteral("ganas_rezar(muchas)"));
				
				//agentetest.addInitialBel(Literal.parseLiteral("ganas_rezar(muchas)"));
				//agentetest.getBB().add(Literal.parseLiteral("ganas_rezar(muchas)"));
				
				//agentetest.addInitialBelsInBB();
				
				
				
				//setBB(BeliefBase bb)
				//System.out.println("--" + agentetest.getBB());
				
				
				/*for (Literal l  : agentetest.getInitialBels()) {
					System.out.println("initial belief: " + l);
					
				}*/
				//System.out.println("BB: " + agentetest.getBB().toString());
				
				//System.out.println("initial bels: " + agentetest.getInitialBels().size());
						
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			/*--------------------------------------------*/
						
			
			Abadia.getInstance().addPercept("frayAlejandro", "rezar_o_no_rezar");			
			
			
			
			//Abadia.getInstance().addPercept("frayAlejandro", accion);
			Abadia.getInstance().addPercept("frayHector", accion);
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
				break;
			case "event":
				//{"name":"event","parameters":{"eventName":"campana_cerca"}}
				this.cellEvent = parameters.getInt("cell");
				this.parseEvent(parameters);
				break;
			default: break;
		}
		return result;
	}
	
	private void parseEvent(JSONObject json){
		String eventName = json.getString("eventName");
		switch (eventName){
			case "campana_cerca":
				Abadia.getInstance().addPercept("frayFernando", "quiero_tocar(campana)");
				break;
		}
	}
   
	private void registerEnvironment(JSONObject json){
		this.registerEntities(json.getJSONArray("entities"));
		this.registerDecorations(json.getJSONArray("decorations"));
		
		this.loadedEnvironment = true;
		System.out.println("Entorno cargado");
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
