
public class AbadiaModel {
	
   private static AbadiaModel instance = null;
   public static final int[] posLibro = {5,2};
	
   //########################################################################################
   //########################################################################################
   //########################################################################################
   protected AbadiaModel() {
	   // Exists only to defeat instantiation.
   }
   
   public static AbadiaModel getInstance() {
      if(instance == null) {
         instance = new AbadiaModel();
      }
      return instance;
   }
   
   //########################################################################################
   //########################################################################################
   //########################################################################################
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

}
