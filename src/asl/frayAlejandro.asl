/* Initial beliefs and rules */
/* Conocimientos que el frayAlejandro tiene del entorno */
//ganas_rezar(ninguna).

/* Initial goals */
//!invitarA.
!go.

/*Plans */
//+ir(location)
//<- yendo(location).

+!go : true
	<- .broadcast(tell,hello).
	
+hello_from(Agent) : true
	<- .println("Yay! Heard from: ", Agent).

+quiero_ir_a_rezar : ganas_rezar(ninguna)
	<- .print("No tengo ganas de ir a rezar").
		
+quiero_ir_a_rezar : not ganas_rezar(ninguna)
	<- 	.print("Me apetece ir a rezar");
		voy_a(fAlocation).

//+!invitarA
//	<- 	.wait(5000); //5 segundos
//		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
//		.send(frayFernando,tell,comer).

//+saludo[source(A)] 
//  <- .print("He sido saludado por ",A).
     
//+insultame[source(A)] 
//	<-	+caracter(A,antipatico);	// Agrega el belief de que A es antipatico
//  		.print("No me insultes, ",A, ", eres un antipático");
//  	 	.send(A,tell, quetal).     
  	 	
//+interactuar(PERSONA,ACCION)
//	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
//		.send(PERSONA,tell,ACCION).
