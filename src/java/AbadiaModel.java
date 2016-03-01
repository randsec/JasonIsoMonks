import java.util.ArrayList;
import java.util.HashMap;

import jason.asSyntax.Literal;

public class AbadiaModel {   
   private int[] posLibro = {5,6};
   private ArrayList<String> agentes;
   private HashMap<String, ArrayList<String>> percepciones;
   

   public AbadiaModel() {
	   this.agentes = new ArrayList<String>();
	   agentes.add("entorno");
	   agentes.add("frayGuillermo");
	   agentes.add("adso");
	   
	   percepciones = new HashMap<String, ArrayList<String>>();
	   ArrayList<String> p0 = new ArrayList<String>();
		   p0.add("dia(lunes)");
		   p0.add("dia(lunes)");
		   p0.add("mes(febrero)");
		   p0.add("anyo(1987)");
		   p0.add("clima(soleado)");
		   percepciones.put("entorno", p0);

	   ArrayList<String> p1 = new ArrayList<String>();
		   p1.add("humor(enfadado)");
		   p1.add("dolor(espalda)");
		   p1.add("caracter(adso,vivaz)");
		   p1.add("hambre(alta)");
		   percepciones.put("frayGuillermo", p1);

	   ArrayList<String> p2 = new ArrayList<String>();
			p2.add("humor(normal)");
			p2.add("caracter(frayGuillermo,arisco)");
			p2.add("hambre(media)");
			percepciones.put("adso", p2);
	  
	}

   //////////////////////////////
   
   /**
    * Agrega la percepcion al agente elegido.
    * Si el agente no existe, se crea con esa percepcion
    * Si la percepcion ya existe, no hace nada
    * @param ag
    * @param pc
    */
   public void agregarPercepcion(String ag, String pc){
	   if (!agentes.contains(ag)){
		   agentes.add(ag);
		   ArrayList<String> percepts = new ArrayList<String>();
		   percepts.add(pc);
		   percepciones.put(ag, percepts);
	   }
	   else{
		   ArrayList<String> percepts = percepciones.get(ag);
		   if(! percepts.contains(pc)){
			   percepts.add(pc);
			   percepciones.put(ag, percepts);
		   }
	   }
   }
   
   /**
    * Elimina la percepcion del agente elegido
    * Si el agente o la percepcion no existen, no hace nada
    * @param ag
    * @param pc
    */
   public void eliminarPercepcion(String ag, String pc){
	   if (agentes.contains(ag)){
		   ArrayList<String> percepts = percepciones.get(ag);
		   if (percepts.contains(pc)){
			   percepts.remove(pc);
			   percepciones.put(ag, percepts);
		   }
	   }
   }
   
   /**
    * Elimina el agente de las variables, y sus percepciones
    * El agente "entorno" no se puede eliminar
    * Si el agente no existe o es el entorno no hace nada
    * @param ag
    */
   public void eliminarAgente(String ag){
	   if (agentes.contains(ag) && ag != "entorno"){
		   agentes.remove(ag);
		   percepciones.remove(ag);
	   }
   }

   //////////////////////////////
   public boolean llamar_comida(){
	   System.out.println("LLAMADA A TODOS LOS MONJES A COMER");
	   return true;
   }

   public boolean llamar_misa(){
	   System.out.println("LLAMADA A TODOS LOS MONJES A MISA");
	   return true;
   }
	
   public boolean hablar(String ag1, String ag2, String mensaje){
	   System.out.println(ag1 + " a " + ag2 +": " + mensaje);
	   return true;
   }
   //////////////////////////////
	
	public int[] getPosLibro() {
		return posLibro;
	}
	
	public void setPosLibro(int[] posLibro) {
		this.posLibro = posLibro;
	}
	
	public ArrayList<String> getNombres() {
		return agentes;
	}
		
	public HashMap<String, ArrayList<String>> getPercepciones() {
		return percepciones;
	}

}
