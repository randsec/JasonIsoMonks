/* Initial beliefs and rules */
/* Conocimientos que el monje2 tiene del entorno */
estado(self,durmiendo).

/*Initial goals */
!invitarA.

/*Plans */
+!invitarA
	<- 	.wait(15000); //15 segundos
		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
		.send(frayFernando,tell,comer).


+saludo[source(A)] 
	<-	+ caracter(A,simpatico);	// Agrega un belief de que A es simpatico
		.print("He sido saludado por ",A);
     	.send(A,tell,insultame).

+rezar(X)
	: caracter(X,simpatico)
	<- .print("Ire a rezar con ", X).

+interactuar(PERSONA,ACCION)
	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
		.send(PERSONA,tell,ACCION).