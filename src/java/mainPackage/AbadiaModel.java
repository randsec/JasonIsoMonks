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

    public abstract Thread getThreadByName(String id);
	public abstract boolean isValidJSON(String test); 
	public abstract boolean llamar_comida();
	public abstract boolean llamar_misa();
	public abstract boolean tocar_campana();
public abstract boolean hablar(String ag1, String ag2, String mensaje);
	public abstract void moveAgentToDecoration(String agName, String decName);
	
	public abstract String recieveDataFromConnection(String data);
	public abstract HashMap<String, HashMap<String,String>> getEntities();
	public abstract HashMap<String, HashMap<String,String>> getDecorations();

	public abstract boolean isEnvironmentLoaded();

}

