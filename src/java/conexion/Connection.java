package conexion;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Connection extends Thread{
	
	private static Connection instance;
		
    public static Connection getInstance() {
    	if (instance == null) {
    		instance = new ConnectionImp();
    	}
    	return instance; 
    }
		
    public abstract void run();
    
    public abstract HashMap<String, Integer> getEntities();
    public abstract HashMap<String, Integer> getDecorations();
    public abstract HashMap<Integer, String> getCells();
    
    public abstract boolean sendCommand(String command);

    
}