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
	
	public static void main(String[] args) throws ParseException, FileNotFoundException, JasonException {
		System.out.println("EJECUTANDO A LA VEZ");

	  // parse that file
	  jason.mas2j.parser.mas2j parser = new jason.mas2j.parser.mas2j(new FileInputStream("agentes.mas2j"));
	  MAS2JProject project = parser.mas();	  
	 
	  // Muestra los nombres
	  for (AgentParameters ap : project.getAgents()) {
		  System.out.println(ap.name);
	  }	 
	  
	  /*
	   * Clases utiles por estudiar
	   * AgentParameters = contiene elementos de agentes pero no beliefs ni plans
	   * Agent
	   * Environment contiene las percepciones y belief base
	   */
	  //Agent ag = new Agent();
	  //ag.initAg("src/asl/frayFernando.asl");
	  
      Agent ag = new Agent();
      new TransitionSystem(ag, new Circumstance(), new Settings(), new AgArch());
      ag.initAg("src/asl/frayFernando.asl"); // demo.asl is the file containing the code of the agent
 
	 	  
	}
}
