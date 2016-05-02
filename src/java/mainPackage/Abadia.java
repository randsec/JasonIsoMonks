package mainPackage;
import conexion.ConnectionListener;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class Abadia extends Environment {
	
	private static Abadia instance;
	
	public Abadia() {
		instance = this;
	}
	
	public static Abadia getInstance() {
		return instance;
	}
	
	public void init(String[] args) {
		System.out.println("Jason BDI ready...");
		new Thread(new ConnectionListener(), "connectionListener" ).start();
		
		while(!AbadiaModel.getInstance().isEnvironmentLoaded()){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean executeAction(String agente, Structure accion) {
		AbadiaModel.getInstance().setAgent(agente);
		boolean success = false;
		
		switch (accion.getFunctor()) {
			case "toco":
				String object = accion.getTerm(0).toString();
				success = AbadiaModel.getInstance().tocar(object);
				break;
			case "voy_a":
				String location = accion.getTerm(0).toString();
				success = AbadiaModel.getInstance().ir_a(location);
				break;
			default:
				System.out.println("Accion desconocida: " + accion.getFunctor());
				break;
		}
		
		return success;
	}

	public void addPercept(String ag, String percept) {
		addPercept(ag,Literal.parseLiteral(percept));
	}
}