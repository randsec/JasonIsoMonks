import java.util.ArrayList;
import java.util.HashMap;
import org.json.*;
import conexion.Connection;
import conexion.ConnectionImp;

public class AbadiaModel {   

	private static AbadiaModel instance;

	private HashMap<Integer,String> entities;
	private ArrayList<Integer> cells;

	private int hour;
	private int minutes;
	private String weather;

   public AbadiaModel() {
	   this.hour = 07;
	   this.minutes = 00;
	   this.weather = "shiny";
	   
	   this.entities = Connection.getInstance().getEntities();
	   this.cells = Connection.getInstance().getCells();
   }
      
   //////////////////////////////
   
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
