/* Initial beliefs and rules */
/* Conocimientos que el monje2 tiene del entorno */

/* Initial goals */
!invitarA.

/*Plans */
+!invitarA : true
	<- 	.wait(5000); //5 segundos
		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
		.send(frayFernando,tell,comer).

+saludo[source(A)] 
  <- .print("He sido saludado por ",A).
     
+insultame[source(A)] 
	<-	+caracter(A,antipatico);	// Agrega el belief de que A es antipatico
  		.print("No me insultes, ",A, ", eres un antipático");
  	 	.send(A,tell, quetal).     