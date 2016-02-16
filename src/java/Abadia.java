import jason.JasonException;
import jason.asSemantics.Agent;
import jason.asSyntax.*;

import jason.environment.*;

public class Abadia extends Environment {
	
	// any class members needed...
	private int[] posLibro = {5,2};
	
	
	@Override
	public void init(String[] args) {
		//De momento vamos a crear dos agentes al inicializar
		//Agent agente_frayFernando = new Agent();
		//Agent agente_frayAlejandro = new Agent();
		

		// Se establecen las percepciones globales que tendrán todos los agentes en el entorno
		addPercept(Literal.parseLiteral("dia(lunes)"));
		addPercept(Literal.parseLiteral("mes(febrero)"));
		addPercept(Literal.parseLiteral("anyo(1987)"));
		addPercept(Literal.parseLiteral("clima(soleado)"));
		
		// Se establecen las percepciones individuales para cada agente
		addPercept("frayAlejandro", Literal.parseLiteral("humor(enfadado)"));
		addPercept("frayAlejandro", Literal.parseLiteral("dolor(espalda)"));
		addPercept("frayAlejandro", Literal.parseLiteral("caracter(frayFernando,antipatico)"));
		addPercept("frayAlejandro", Literal.parseLiteral("hambre(alta)"));
		
		addPercept("frayFernando", Literal.parseLiteral("humor(normal)"));
		addPercept("frayFernando", Literal.parseLiteral("caracter(frayAlejandro,arisco)"));
		addPercept("frayFernando", Literal.parseLiteral("hambre(media)"));
	}
	
	@Override
	public void stop() {
		
		// anything else to be done by the environment when
		
		// the system is stopped...
		
	}
	
	@Override
	
	/**
	 * En esencia este método es un parser que llamará a unos métodos o variables
	 * declarados en un Modelo que se haya establecido
	 * 
	 * Ver #113 del libro
	 */
	public boolean executeAction(String agente, Structure accion) {
		
		// this is the most important method, where the
		
		// effects of agent actions on perceptible properties
		
		// of the environment is defined
		boolean success = false;
		
		if(accion.equals("invitarComer")){	// Creo que no son strings, sino metodos
			
		}
		else if(accion.equals("hablar")){
		
		}
		return success;
		
	}
	
}