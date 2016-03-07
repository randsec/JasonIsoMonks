import java.util.ArrayList;
import java.util.HashMap;

import jason.asSyntax.Literal;

public class AbadiaModel {   
   private int[] posLibro = {5,6};
   

   public AbadiaModel() {	  
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
}
