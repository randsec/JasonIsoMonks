/* Initial beliefs and rules */
/* Conocimientos que el frayHector tiene del entorno */
ganas_rezar(muchas).
ganas_ir(comedor, ninguna).
ganas_ir(cocina, muchas).

/*Initial goals */
//!invitarA.

/*Plans */
//+ir(location)
//<- yendo(location).

+quiero_ir(capilla)
	: ganas_rezar(ninguna)
		<- .print("mmmm, esta sonando la campana, pero no tengo ganas de ir a rezar...").
		
+quiero_ir(capilla)
	: not ganas_rezar(ninguna)
		<- 	.print("EH! la campana, me apetece ir a rezar");
			voy_a(fHlocation_capilla);
			.wait(10000); //Tiempo rezando
			+quiero_ir(cocina)[source(self)].
		
+quiero_ir(comedor)[source(A)] 
	: ganas_ir(comedor, ninguna)
		<- .print("Creo haber visto a ", A, " entrar en el comedor, bah, serán imaginaciones mias...").
		
+quiero_ir(comedor)[source(A)] 
	: not ganas_ir(comedor, ninguna)
		<- 	.print("Creo haber visto a ", A, " entrar en el comedor, voy a mirar si hay comida");
			voy_a(fHlocation_comedor).
		
+quiero_ir(cocina)[source(A)]
	: ganas_ir(cocina, ninguna) & (A == self)
		<-	.print("Ahora que he terminado de rezar no me apetece hacer nada, me quedare aqui durmiendo").

+quiero_ir(cocina)[source(A)]
	: not ganas_ir(cocina, ninguna) & (A == self)
		<-	.print("Y despues de un rezo rapido, voy a ir a la cocina a trabajar un poco");
			voy_a(fHlocation_cocina).
		
+quiero_ir(cocina)[source(A)]
	: ganas_ir(cocina, ninguna) & not (A == self)
		<- .print("Aunque ", A, " se ponga a hacer cosas en su escritorio, yo no pienso hacer nada").
		
+quiero_ir(cocina)[source(A)] 
	: not ganas_ir(cocina, ninguna) & not (A == self)
		<- 	.print("Pues si ", A, " se pone en su escritorio, yo voy a trabajar en la cocina");
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
