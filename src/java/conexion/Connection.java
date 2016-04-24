package conexion;

public abstract class Connection{
	
	private static Connection instance;
		
    public static Connection getInstance() {
    	if (instance == null) {
    		instance = new ConnectionImp();
    	}
    	return instance; 
    }
		
	public abstract String receive() ;
	public abstract void send(String sentSentence);
 
	public abstract ConnectionProperties getCp();    
}