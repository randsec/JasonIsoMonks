/* Initial beliefs and rules */
/* Conocimientos que el frayAlejandro tiene del entorno */
ganas_rezar(ninguna).
ganas_ir(comedor, muchas).
ganas_ir(taller, muchas).

/* Initial goals */
//!invitarA.
//!go.

/*Plans */
+!invitarA
	<- 	mover_a(frayHector);
		.wait(5000); //5 segundos
		.print("Hola, frayHector, ¿Vienes a comer conmigo?").
		/**  .send(frayFernando,tell,comer).*/

+quiero_ir(capilla)  
	: ganas_rezar(ninguna)
		<- .print("Mmmmm, esta sonando la campana, pero no tengo ganas de ir a rezar...").
		
+quiero_ir(capilla)
	: not ganas_rezar(ninguna)
		<- 	.print("EH! la campana, me apetece ir a rezar");
			voy_a(fAlocation_capilla).
		
+quiero_ir(comedor)[source(A)] 
	: ganas_ir(comedor, ninguna) & not (A == self)
		<- .print("Creo haber visto a ", A, " entrar en el comedor, bah, serán imaginaciones mias...").
		
+quiero_ir(comedor)[source(A)]
	: not ganas_ir(comedor, ninguna) & not (A == self)
		<- 	.print("Creo haber visto a ", A, " entrar en el comedor, voy a mirar si hay comida");
			voy_a(fAlocation_comedor).
		
+quiero_ir(taller)[source(A)]
	: ganas_ir(taller, ninguna) & not (A == self)
		<- .print("Aunque ", A, " se ponga a hacer cosas en su escritorio, yo no pienso hacer nada").
		
+quiero_ir(taller)[source(A)]
	: not ganas_ir(taller, ninguna) & not (A == self)
		<- 	.print("Pues si ", A, " se pone en su escritorio, yo voy a trabajar en el taller");
			voy_a(fAlocation_taller).

//+!invitarA
//	<- 	.wait(5000); //5 segundos
//		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
//		.send(frayFernando,tell,comer).
     
//+insultame[source(A)] 
//	<-	+caracter(A,antipatico);	// Agrega el belief de que A es antipatico
//  		.print("No me insultes, ",A, ", eres un antipático");
//  	 	.send(A,tell, quetal).     
  	 	
//+interactuar(PERSONA,ACCION)
//	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
//		.send(PERSONA,tell,ACCION).
