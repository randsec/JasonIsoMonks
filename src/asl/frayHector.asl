/* Initial beliefs and rules */
/* Conocimientos que el frayHector tiene del entorno */
//estado(self,durmiendo).
ganas_rezar(muchas).

/*Initial goals */
//!invitarA.

/*Plans */
//+ir(location)
//<- yendo(location).

+quiero_ir_a_rezar : ganas_rezar(ninguna)
	<- .print("No tengo ganas de ir a rezar").
		
+quiero_ir_a_rezar : not ganas_rezar(ninguna)
	<- 	.print("Me apetece ir a rezar");
		voy_a(fHlocation).

//+!invitarA
//	<- 	.wait(15000); //15 segundos
//		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
//		.send(frayFernando,tell,comer).


//+saludo[source(A)] 
//	<-	+ caracter(A,simpatico);	// Agrega un belief de que A es simpatico
//		.print("He sido saludado por ",A);
//     	.send(A,tell,insultame).

//+rezar(X)
//	: caracter(X,simpatico)
//	<- .print("Ire a rezar con ", X).

//+interactuar(PERSONA,ACCION)
//	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
//		.send(PERSONA,tell,ACCION).
