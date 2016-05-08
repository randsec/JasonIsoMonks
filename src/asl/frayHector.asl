/* Initial beliefs and rules */
/* Conocimientos que el frayHector tiene del entorno */
//estado(self,durmiendo).
ganas_rezar(muchas).
ganas_ir(comedor, ninguna).
ganas_ir(cocina, muchas).

/*Initial goals */
//!invitarA.

/*Plans */
//+ir(location)
//<- yendo(location).

+quiero_ir_a_rezar : ganas_rezar(ninguna)
	<- .print("mmmm, esta sonando la campana, pero no tengo ganas de ir a rezar...").
		
+quiero_ir_a_rezar : not ganas_rezar(ninguna)
	<- 	.print("EH! la campana, me apetece ir a rezar");
		voy_a(fHlocation_capilla).
		
+quiero_ir(comedor) : ganas_ir(comedor, ninguna)
	<- .print("Creo haber visto a alguien entrar en el comedor, bah, serán imaginaciones mias...").
		
+quiero_ir(comedor) : not ganas_ir(comedor, ninguna)
	<- 	.print("Creo haber visto a alguien entrar en el comedor, voy a mirar si hay comida");
		voy_a(fHlocation_comedor).
		
+quiero_ir(cocina) : ganas_ir(cocina, ninguna)
	<- .print("Aunque frayFernando se ponga a hacer cosas en su escritorio, yo no pienso hacer nada").
		
+quiero_ir(cocina) : not ganas_ir(cocina, ninguna)
	<- 	.print("Pues si frayFernando se pone en su escritorio, yo voy a trabajar en la cocina");
		voy_a(fHlocation_cocina).

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
