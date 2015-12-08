/*Meta del agente */
!invitarA.

/*plan */
+!invitarA : true
	<- 	.wait(5000); //5 segundos
		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
		.send(frayFernando,tell,comer).

+saludame[source(A)] 
  <- .print("He sido saludado por ",A);
     .send(A,tell,hello).
     
+insultame[source(A)] 
  <- .print("Eres un capullo, ",A);
  	 .send(A,tell, quetal).     