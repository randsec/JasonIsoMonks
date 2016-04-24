package mainPackage;
import conexion.ConnectionListener;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class Abadia extends Environment {
	
	// any class members needed...
	public static final Literal tocar_campana = Literal.parseLiteral("tocar(campana)");
	public static final Literal llamar_misa = Literal.parseLiteral("llamar(misa)");
	public static final Literal llamar_comida = Literal.parseLiteral("llamar(comida)");
	public static final Literal hablar = Literal.parseLiteral("hablar(agente,mensaje)");
	
	public static Abadia instance;
	
	
	
	@Override
	public void init(String[] args) {
		System.out.println("Start Jason Connection...");
		instance = this;
		new Thread(new ConnectionListener(), "connectionListener" ).start();
		
		while(!AbadiaModel.getInstance().isEnvironmentLoaded()){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//String firstCommand = Connection.getInstance().receive();
		//AbadiaModel.getInstance().recieveDataFromConnection(firstCommand);
		
		//AbadiaModel.getInstance().moveAgentToDecoration("frayFernando","antorchaNorte");		
		//AbadiaModel.getInstance().moveAgentToDecoration("frayHector","antorchaOeste");
		//AbadiaModel.getInstance().moveAgentToDecoration("frayAlejandro","antorchaSur");
		
		this.updatePercepts();
	}

	public void updatePercepts(){
		clearPercepts("frayFernando");
		clearPercepts("frayHector");
		clearPercepts("frayAlejandro");
				
		addPercept("frayFernando",Literal.parseLiteral("recibircosa(cuchara)"));
		addPercept("frayFernando",Literal.parseLiteral("recibircosa(mazorca)"));
		addPercept("frayFernando",Literal.parseLiteral("recibircosa(extintor)"));
	}
	
	@Override
	public void stop() {
		// anything else to be done by the environment when
		// the system is stopped...
		AbadiaModel.getInstance().getThreadByName("connectionListener").interrupt();
	}
	
	@Override
	public boolean executeAction(String agente, Structure accion) {
		
		System.out.println("Agente [" + agente + "] ejecutando accion: " + accion.toString());

		boolean success = false;
		
		if(accion.equals(llamar_comida)){
			success = AbadiaModel.getInstance().llamar_comida();
		}
		else if(accion.equals(llamar_misa)){
			success = AbadiaModel.getInstance().llamar_misa();
		}
		else if(accion.equals(tocar_campana)){
			success = AbadiaModel.getInstance().tocar_campana();
		}
		else if(accion.getFunctor().equals("hablar")){
			String interlocutor = accion.getTerm(0).toString();
			String mensaje = accion.getTerm(1).toString();
			
			success = AbadiaModel.getInstance().hablar(agente, interlocutor, mensaje);
		}
		return success;
	}

	public void addPercept(String ag, String percept) {
		addPercept(ag,Literal.parseLiteral(percept));
	}
}