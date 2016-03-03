package conexion;

public class MainConnection {
	
	static public void lanzarConexion(String args[] ) {
		System.out.println("=== START ===");
		        
        Thread Server = new Thread( new Server(), "server" );
        Thread Client = new Thread( new Client(), "client" );
        
        Server.start();
        Client.start();        
    }
    
}
