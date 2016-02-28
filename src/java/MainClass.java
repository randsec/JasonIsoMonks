import java.util.List;
import jason.JasonException;
import jason.asSyntax.Literal;
import jason.asSyntax.Term;
import jason.mas2j.parser.ParseException;

public class MainClass {
	
	public static void main(String[] args) throws ParseException, JasonException {
		System.out.println("EJECUTANDO A LA VEZ");
		Abadia abadia = new Abadia();
		abadia.init(null);
		
		// frayGuillermo y Adso 
		
		System.out.println("Percepciones del Entorno: " + abadia.getPercepts(""));
		System.out.println("Percepciones de Fray Guillermo: " + abadia.getPercepts("frayGuillermo"));
		//System.out.println("Percepciones de Adso: " + abadia.getPercepts("adso"));
		// cuando se pone el "getPercepts" se quitan del entorno D: !!!!
		
		System.out.println(perceptionListToJSON(abadia.getPercepts("adso")));
		
		/**
		abadia.executeAction("frayGuillermo", new Structure(Literal.parseLiteral("llamar(comida)")));
		abadia.executeAction("frayGuillermo", new Structure(Literal.parseLiteral("llamar(misa)")));
		abadia.executeAction("adso", new Structure(Literal.parseLiteral("hablar(frayGuillermo,eres_mu_tonto)")));
		*/
		
	}
	
	public static String perceptionListToJSON(List<Literal> percepts){
		String JSON = "{";
		
		for (Literal literal : percepts) {
			JSON += '"' + literal.getFunctor() + '"' + ":";
			
			List<Term> terminos = literal.getTerms();
			if (terminos.size() == 0)
				JSON += '"' + '"';
			else if (terminos.size() == 1) 
				JSON += '"' + literal.getTerm(0).toString() + '"';
			else{
				JSON += '[';
				for (Term term : terminos) {
					JSON += '"' + term.toString() + '"' + ",";
				}
				JSON = JSON.substring(0, JSON.length()-1);
				JSON += ']';
			}
			JSON += ",";
		}		
		
		JSON = JSON.substring(0, JSON.length()-1);
		JSON += "}";
		
		return JSON;
	}
	
	public static List<Literal> JSONtoPerceptionList(String JSON){
		// {"name":"move","parameters":{"entity":9084,"cell":9200}}
		return null;
	}
	
}
