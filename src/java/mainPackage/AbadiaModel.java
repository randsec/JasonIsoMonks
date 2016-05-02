package mainPackage;
import java.util.HashMap;

public abstract class AbadiaModel {
	private static AbadiaModel instance;
	
    public static AbadiaModel getInstance() {
    	if (instance == null) {
    		instance = new AbadiaModelImp();
    	}
    	return instance; 
    }
    
    //Getters & Setters
    public abstract void setAgent(String agente);
    public abstract HashMap<String, HashMap<String,String>> getEntities();
	public abstract HashMap<String, HashMap<String,String>> getDecorations();
	public abstract boolean isEnvironmentLoaded();
       
	//Acciones de los agentes o del entorno
	public abstract boolean tocar(String object);
	public abstract boolean ir_a(String location);
	
	//Receive Data
	public abstract String recieveDataFromConnection(String data);
}

