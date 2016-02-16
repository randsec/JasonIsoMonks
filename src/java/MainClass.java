import java.io.*;
import jason.JasonException;
import jason.architecture.AgArch;
import jason.asSemantics.Agent;
import jason.asSemantics.Circumstance;
import jason.asSemantics.TransitionSystem;
import jason.mas2j.*;
import jason.mas2j.parser.ParseException;
import jason.runtime.Settings;

public class MainClass {
	
   //Clases utiles por estudiar
   //AgentParameters = contiene elementos de agentes pero no beliefs ni plans
   //Agent
   //Environment contiene las percepciones y belief base

	
	public static void main(String[] args) throws ParseException {
	
		System.out.println("EJECUTANDO A LA VEZ");
		Abadia abadia = new Abadia();
		abadia.init(null);
		
		System.out.println(abadia.getPercepts("frayAlejandro"));
		System.out.println(abadia.getPercepts("frayFernando"));
		
		
	}
	
	public void mostrarAgentes() throws FileNotFoundException, ParseException{
		 // parse that file
		  jason.mas2j.parser.mas2j parser = new jason.mas2j.parser.mas2j(new FileInputStream("agentes.mas2j"));
		  MAS2JProject project = parser.mas();	  
		 
		  // Muestra los nombres
		  for (AgentParameters ap : project.getAgents()) {
			  System.out.println(ap.name);
		  }	 
	}
}
