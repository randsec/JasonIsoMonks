/* Initial beliefs and rules */
/* Conocimientos que el frayAlejandro tiene del entorno */

/* Initial goals */
!invitarA.

/*Plans */
+!invitarA
	<- 	mover_a(frayHector);
		.wait(5000); //5 segundos
		.print("Hola, frayHector, ¿Vienes a comer conmigo?").
		/**  .send(frayFernando,tell,comer).*/

+saludo[source(A)] 
  <- .print("He sido saludado por ",A).
     
+insultame[source(A)] 
	<-	+caracter(A,antipatico);	// Agrega el belief de que A es antipatico
  		.print("No me insultes, ",A, ", eres un antipático");
  	 	.send(A,tell, quetal).     
  	 	
+interactuar(PERSONA,ACCION)
	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
		.send(PERSONA,tell,ACCION).