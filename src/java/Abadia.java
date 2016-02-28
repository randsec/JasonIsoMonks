import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class Abadia extends Environment {
	
	// any class members needed...
	public static final Literal llamar_misa = Literal.parseLiteral("llamar(misa)");
	public static final Literal llamar_comida = Literal.parseLiteral("llamar(comida)");
	public static final Literal hablar = Literal.parseLiteral("hablar(agente,mensaje)");

	public AbadiaModel model;
	
	@Override
	public void init(String[] args) {
		//De momento vamos a crear dos agentes al inicializar
		//Agent agente_frayFernando = new Agent();
		//Agent agente_frayAlejandro = new Agent();
		
		model = new AbadiaModel();
		
		// Se establecen las percepciones globales que tendrán todos los agentes en el entorno
		addPercept(Literal.parseLiteral("dia(lunes)"));
		addPercept(Literal.parseLiteral("mes(febrero)"));
		addPercept(Literal.parseLiteral("anyo(1987)"));
		addPercept(Literal.parseLiteral("clima(soleado)"));
		
		// Se establecen las percepciones individuales para cada agente
		addPercept("frayGuillermo", Literal.parseLiteral("humor(enfadado)"));
		addPercept("frayGuillermo", Literal.parseLiteral("dolor(espalda)"));
		addPercept("frayGuillermo", Literal.parseLiteral("caracter(adso,vivaz)"));
		addPercept("frayGuillermo", Literal.parseLiteral("hambre(alta)"));
		
		addPercept("adso", Literal.parseLiteral("humor(normal)"));
		addPercept("adso", Literal.parseLiteral("caracter(frayGuillermo,arisco)"));
		addPercept("adso", Literal.parseLiteral("hambre(media)"));
	}
	
	@Override
	public void stop() {
		
		// anything else to be done by the environment when
		
		// the system is stopped...
		
	}
	
	@Override
	public boolean executeAction(String agente, Structure accion) {
		
		System.out.println("Agente [" + agente + "] ejecutando accion: " + accion);

		boolean success = false;
		
		if(accion.equals(llamar_comida)){
			success = model.llamar_comida();
		}
		else if(accion.equals(llamar_misa)){
			success = model.llamar_misa();
		}
		else if(accion.getFunctor().equals("hablar")){
			String interlocutor = accion.getTerm(0).toString();
			String mensaje = accion.getTerm(1).toString();
			
			success = model.hablar(agente, interlocutor, mensaje);
		}
		return success;
		
	}
	
}