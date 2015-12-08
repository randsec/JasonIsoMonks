/* Initial beliefs and rules */
/* Conocimientos que el monje2 tiene del entorno */
caracter(frayHector,simpatico).
caracter(frayAlejandro,idiota).

/* Initial goals */
!comer.

/* Plans */

+!start : true 
	<- .print("Mmmmm... qué hambre.").

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
	