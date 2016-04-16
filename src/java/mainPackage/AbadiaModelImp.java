package mainPackage;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import conexion.Connection;

public class AbadiaModelImp extends AbadiaModel{   

	private HashMap<String, HashMap<String,String>> entities;
	private HashMap<String, HashMap<String,String>> decorations;

	private boolean loadedEnvironment;
		
	private int hour;
	private int minutes;
	private String weather;

   public AbadiaModelImp() {
	   this.entities = new HashMap<String, HashMap<String,String>>();
	   this.decorations = new HashMap<String, HashMap<String,String>>();
	   
	   this.hour = 07;
	   this.minutes = 00;
	   this.weather = "shiny";
	   
	   this.loadedEnvironment = false;
   }
      
   
   public Thread getThreadByName(String id){
	   for (Thread t : Thread.getAllStackTraces().keySet()){ 
		   if (t.getName() == id){
		   		return t;
		   }
	   }
	   return null;
   }
   
   public boolean isValidJSON(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   public boolean llamar_comida(){
	   System.out.println("LLAMADA A TODOS LOS MONJES A COMER");
	   return true;
   }

   public boolean llamar_misa(){
	   System.out.println("LLAMADA A TODOS LOS MONJES A MISA");
	   return true;
   }
	
   public boolean hablar(String ag1, String ag2, String mensaje){
	   System.out.println(ag1 + " a " + ag2 +": " + mensaje);
	   return true;
   }
   
   	public HashMap<String, HashMap<String,String>> getEntities() {
		return this.entities;
	}
	
	public HashMap<String, HashMap<String,String>> getDecorations() {
		return this.decorations;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String recieveDataFromConnection(String data){
		String result = data;
		JSONObject json = new JSONObject(data);
		String name = json.getString("name");
		switch(name) {
			case "environment":
				this.registerEnvironment(json.getJSONObject("parameters"));
			break;
			default: break;
		}
		return result;
	}
	private void registerEnvironment(JSONObject json){
		this.registerEntities(json.getJSONArray("entities"));
		this.registerDecorations(json.getJSONArray("decorations"));
		
		System.out.println(this.entities.toString());
		System.out.println(this.decorations.toString());

		this.loadedEnvironment = true;
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
	
	public boolean isEnvironmentLoaded(){
		return this.loadedEnvironment;
	}
	
	private void updateAgentPosition(String agName, int cell){
		HashMap<String, String> atrs = this.entities.get(agName);
		atrs.put("cell", Integer.toString(cell));
		this.entities.put(agName, atrs);
	}
	
	@Override
	public void moveAgentToDecoration(String agName, String decName) {
		int agentID = Integer.parseInt(AbadiaModel.getInstance().getEntities().get(agName).get("id"));
		int destino = Integer.parseInt(AbadiaModel.getInstance().getDecorations().get(decName).get("cell"));
		
		String sentSentence = "{\"name\":\"move\",\"parameters\":{\"entity\":"+agentID+",\"cell\":"+destino+"}}";
		
		Connection.getInstance().send(sentSentence);
		
		this.updateAgentPosition(agName, destino);
	}

}
