import conexion.Connection;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class Abadia extends Environment {
	
	// any class members needed...
	public static final Literal llamar_misa = Literal.parseLiteral("llamar(misa)");
	public static final Literal llamar_comida = Literal.parseLiteral("llamar(comida)");
	public static final Literal hablar = Literal.parseLiteral("hablar(agente,mensaje)");

	public static AbadiaModel model;
	
	@Override
	public void init(String[] args) {
		System.out.println("Start Jason Connection...");
		new Thread( Connection.getInstance(), "connection" ).start();
		
		model = new AbadiaModel();


		// MIERDA
		/*

		updatePercepts();
			addPercept(Literal.parseLiteral("dia(lunes)"));
			addPercept(Literal.parseLiteral("mes(febrero)"));
			addPercept(Literal.parseLiteral("anyo(1987)"));
			addPercept(Literal.parseLiteral("clima(soleado)"));

			System.out.println(consultPercepts("frayFernando"));
			System.out.println(consultPercepts("frayHector"));
			System.out.println(consultPercepts("frayAlejandro"));


		for (Literal l : consultPercepts("frayFernando")){
			if (l.getFunctor().equalsIgnoreCase("ropa"))
				System.out.println(l.getTerms());
		}
		
		removePercept("frayFernando", Literal.parseLiteral("ropa(tunica)"));
		addPercept("frayFernando", Literal.parseLiteral("ropa(tunica,sombrero,bufanda)"));

		System.out.println(consultPercepts("frayFernando"));
*/
// HASTA AQUI
		//String tomaco = "{\"name\":\"move\",\"parameters\":{\"entity\":9842,\"cell\":10116}}";
		//Connection.getInstance().enviarComandoJSON(tomaco);
	}
	

	public void updatePercepts(){
		clearPercepts("frayFernando");
		clearPercepts("frayHector");
		clearPercepts("frayAlejandro");
		
		addPercept("frayFernando", Literal.parseLiteral("ropa(tunica)"));
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