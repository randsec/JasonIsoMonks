/* Initial beliefs and rules */
/* Conocimientos que el frayAlejandro tiene del entorno */
ganasrezar(ninguna).

/* Initial goals */
//!invitarA.

/*Plans */
//+ir(location)
//<- yendo(location).


+quiero_ir_a_rezar : ganasrezar(ninguna)
	<- .print("No tengo ganas de ir a rezar").
//	   -ganasrezar(ninguna);
//	   +ganasrezar(muchas).	   
		
+quiero_ir_a_rezar : not ganasrezar(ninguna)
	<- 	.print("Me apetece ir a rezar");
		voy_a(fAlocation).
//		-ganasrezar(muchas);
//	    +ganasrezar(ninguna).


+rezar_o_no_rezar
 <- ?ganasrezar(P);
   !decidirrezar(P).   


+!decidirrezar(P)
    : not ganasrezar(ninguna)
    <- .print("Me voy a rezar");
     voy_a(fAlocation);
    -ganasrezar(muchas);
	+ganasrezar(ninguna).
	
+!decidirrezar(P)
  : ganasrezar(ninguna)
   <- .print("Sudo de rezar");  
   	-ganasrezar(ninguna);  
	+ganasrezar(muchas);
	!decidirrezar(P).

+cambiobilif
	<- -ganasrezar(ninguna);
	 +ganasrezar(muchas).


//+saludo[source(A)] 
//  <- .print("He sido saludado por ",A).
     
//+insultame[source(A)] 
//	<-	+caracter(A,antipatico);	// Agrega el belief de que A es antipatico
//  		.print("No me insultes, ",A, ", eres un antipático");
//  	 	.send(A,tell, quetal).     
  	 	
//+interactuar(PERSONA,ACCION)
//	<- 	.print("Interactuar con ", PERSONA, " con la accion ", ACCION);
//		.send(PERSONA,tell,ACCION).
