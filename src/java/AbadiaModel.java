import java.util.ArrayList;
import java.util.HashMap;
import org.json.*;
import conexion.Connection;
import conexion.ConnectionImp;

public class AbadiaModel {   

	private HashMap<String,Integer> entities;
	private HashMap<String,Integer> decorations;
	private HashMap<Integer, String> cells;

	private int hour;
	private int minutes;
	private String weather;

   public AbadiaModel() {
	   this.hour = 07;
	   this.minutes = 00;
	   this.weather = "shiny";
	   
	   actualizarElementos();
   }
      
   public void actualizarElementos(){
	   this.entities = Connection.getInstance().getEntities();
	   this.cells = Connection.getInstance().getCells();
	   this.decorations = Connection.getInstance().getDecorations();
   }
   //////////////////////////////
   public boolean moverAgenteADecoracion(String agentName, String decorationName){
	   boolean status = false;
	   actualizarElementos();
	   
	   if (entities.containsKey(agentName) && decorations.containsKey(decorationName)){
		   int agentId = entities.get(agentName);
		   int decorationCell = entities.get(decorationName);
		   
		   moverEntidadACelda(agentId, decorationCell);
	   }
	   
	   return status;
   }
   
   public boolean moverEntidadACelda(int entity, int cell){
	   String json_command = "{\"name\":\"move\",\"parameters\":{\"entity\":" + entity + ",\"cell\":" + cell + "}}";
	   
	   return Connection.getInstance().sendCommand(json_command);
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

   ////////////////////////////////////
   
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
}
