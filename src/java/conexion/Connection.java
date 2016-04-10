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
    
    public abstract HashMap<Integer, String> getEntities();
    public abstract ArrayList<Integer> getCells();
    
}
