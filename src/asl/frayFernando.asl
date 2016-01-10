/* Initial beliefs and rules */
/* Conocimientos que el frayFernando tiene del entorno */
caracter(frayHector,simpatico).
caracter(frayAlejandro,idiota).

/* Initial goals */
!comer.
!start.

/* Plans */

+!start : true 
	<- .print("Mmmmm... qué hambre.").

+saludo[source(A)] 
  <- .print("He sido saludado por ",A).

+comer[source(A)]
	: caracter(A,Y)
	<-	.wait(2000); 
		.print("(El monje ", A, " me ha invitado a comer... es ", Y, ")");
		.send(frayFernando,tell,decidirComer(A,Y)).
		
+decidirComer(A,Y)
	: Y == simpatico
	<- 	.print("Vale").		

+decidirComer(A,Y)
	: Y == idiota
	<- .print("No, gracias, iré más tarde.").
	
+interactuar(PERSONA,ACCION)
	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
		.send(PERSONA,tell,ACCION).