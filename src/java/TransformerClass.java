import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jason.asSemantics.Agent;
import jason.asSyntax.Literal;
import jason.mas2j.AgentParameters;
import jason.mas2j.MAS2JProject;
import jason.mas2j.parser.ParseException;

public class TransformerClass {
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		System.out.println("EJECUTANDO A LA VEZ");

	  // parse that file
	  jason.mas2j.parser.mas2j parser = new jason.mas2j.parser.mas2j(new FileInputStream("agentes.mas2j"));
	  MAS2JProject project = parser.mas();
			
	  // Muestra los nombres
	  for (AgentParameters ap : project.getAgents()) {
		  System.out.println(ap.name);
	  }
		   

	}
}
