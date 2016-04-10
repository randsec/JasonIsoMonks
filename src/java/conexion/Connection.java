package conexion;

public abstract class Connection extends Thread{
	
	private static Connection instance;
		
    public static Connection getInstance() {
    	if (instance == null) {
    		instance = new ConnectionImp();
    	}
    	return instance; 
    }
		
    public abstract void run();
}
