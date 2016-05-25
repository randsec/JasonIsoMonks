package mainPackage;
import conexion.ConnectionListener;
import jason.JasonException;
import jason.asSemantics.Agent;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.runtime.RuntimeServicesInfraTier;

public class Abadia extends Environment {
	
	private static Abadia instance;
	private Thread thread;
	public Abadia() {
		instance = this;
		this.thread = null;
	}
	
	public static Abadia getInstance() {
		return instance;
	}
	

	
	public void init(String[] args) {
		System.out.println("Jason BDI ready...");
		this.thread = new Thread(new ConnectionListener(), "connectionListener" );
	    this.thread.start();

		
		/*
		while(!AbadiaModel.getInstance().isEnvironmentLoaded()){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
		*/
	}
	
	/** Called before the end of MAS execution **/
	  @Override
	  public void stop() {
	    this.thread.interrupt();
	    super.stop();
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