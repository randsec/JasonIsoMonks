package mainPackage;
import java.util.List;

import org.json.JSONObject;

import jason.asSyntax.Literal;
import jason.asSyntax.Term;

public class JSON_Manager {

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
		
	/**
	 * Mega switch de la muerte que en funcion del JSON que le llege, cogerá unos parámetros
	 * u otros para crear el comando o lo que sea, y luego se pueda cambiar en el modelo 
	 * y actuar en consecuensia
	 * @param JSON
	 * @return
	 */
	public static String actionSwitcher(String JSON){
		// {"name":"move","parameters":{"entity":9084,"cell":9200}}
		// {\"name\":\"move\",\"parameters\":{\"entity\":9084,\"cell\":9200}}
		JSONObject obj = new JSONObject(JSON);
		String action = obj.getString("name");
		
		switch (action) {
		case "move":
			int agentID = obj.getJSONObject("parameters").getInt("entity");
			int cellID = obj.getJSONObject("parameters").getInt("cell");
			break;

		default:
			break;
		}
		return action;

	}
}
