import java.util.List;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.stdlib.foreach;

public class Abadia extends Environment {
	
	// any class members needed...
	public static final Literal llamar_misa = Literal.parseLiteral("llamar(misa)");
	public static final Literal llamar_comida = Literal.parseLiteral("llamar(comida)");
	public static final Literal hablar = Literal.parseLiteral("hablar(agente,mensaje)");

	public static AbadiaModel model = null;
	
	@Override
	public void init(String[] args) {
		
		if (model == null)
			model = new AbadiaModel();
		
		updatePerceptsFromModel();
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
		
	/**
	 * Recoge las percepciones que haya almacenadas en el modelo y las transporta al Entorno
	 */
	public void updatePerceptsFromModel(){
		for(String nombre : model.getNombres()) {
			if (nombre == "entorno")
				for(String percepcionDeEntorno : model.getPercepciones().get(nombre))
					addPercept(Literal.parseLiteral(percepcionDeEntorno));
			else
				for(String percepcionDePersonaje : model.getPercepciones().get(nombre))
					addPercept(nombre, Literal.parseLiteral(percepcionDePersonaje));
		}
	}
	
}