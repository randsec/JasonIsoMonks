import jason.JasonException;
import jason.mas2j.parser.ParseException;

public class MainClass {
	

	public static void main(String[] args) throws ParseException, JasonException {
		
		Abadia abadia = new Abadia();
		abadia.init(null);
		
		// frayGuillermo y Adso 
		System.out.println("Percepciones del Entorno: " + abadia.getPercepts(""));
		System.out.println("Percepciones de Fray Guillermo: " + abadia.getPercepts("frayGuillermo"));
		System.out.println("Percepciones de Adso: " + abadia.getPercepts("adso"));
		abadia.updatePerceptsFromModel();

		// Cada vez que se hace un getPercepts, éstos desaparecen del Environment
		// Pero siguen almacenado en el Model, por lo que habrá que llamar a establecerPerceptsDelModelo
		abadia.model.agregarPercepcion("adso", "dolor(cabeza)");

		System.out.println("Percepciones de Adso: " + abadia.getPercepts("adso"));


		// RECIBE UN COMANDO QUE SERA UN JSON DE VETE A SABER DONDE
		String JSONrecibido = "{\"name\":\"move\",\"parameters\":{\"entity\":9084,\"cell\":9200}}";
		String accion = JSON_Manager.actionSwitcher(JSONrecibido);
		System.out.println(accion);
		
		
		/**
		abadia.executeAction("frayGuillermo", new Structure(Literal.parseLiteral("llamar(comida)")));
		abadia.executeAction("frayGuillermo", new Structure(Literal.parseLiteral("llamar(misa)")));
		abadia.executeAction("adso", new Structure(Literal.parseLiteral("hablar(frayGuillermo,eres_mu_tonto)")));
		*/
		
	}
}
