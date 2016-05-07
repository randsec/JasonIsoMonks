/* Initial beliefs and rules */
/* Conocimientos que el frayFernando tiene del entorno */
//caracter(frayHector,simpatico).
//caracter(frayAlejandro,idiota).
ganas_tocar(campana, muchas).

/* Initial goals */
//!comer.
//!start.

/* Plans */
//+!start : true 
//	<- .print("Mmmmm... qué hambre.").

//+hello[source(A)] : .my_name(Me)
//        <- .send(A, tell, hello_from(Me)).

+quiero_tocar(campana) : ganas_tocar(campana, ninguna)
	<- .print("Eh, una campana!, pero no tengo ganas de tocarla...").
		
+quiero_tocar(campana) : not ganas_tocar_campana(campana, ninguna)
	<- 	.print("Eh, una campana!, voy a tocarla...");
		toco(campana).

//+recibircosa(X) 
//  <- .print("He recibido ",X).

//+comer[source(A)]
//	: caracter(A,Y)
//	<-	.wait(2000); 
//		.print("(El monje ", A, " me ha invitado a comer... es ", Y, ")");
//		.send(frayFernando,tell,decidirComer(A,Y)).
		
//+decidirComer(A,Y)
//	: Y == simpatico
//	<- 	.print("Vale").		

//+decidirComer(A,Y)
//	: Y == idiota
//	<- .print("No, gracias, iré más tarde.").
	
//+interactuar(PERSONA,ACCION)
//	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
//		.send(PERSONA,tell,ACCION).
		