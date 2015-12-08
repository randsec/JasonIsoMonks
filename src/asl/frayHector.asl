/*Meta del agente */
!invitarA.

/*plan */
+!invitarA : true
	<- 	.wait(15000); //15 segundos
		.print("Hola, frayFernando, ¿Vienes a comer conmigo?");
		.send(frayFernando,tell,comer).

/*Believes*/
+hello[source(A)] 
	<-	.print("He sido saludado por ",A);
     	.send(A,tell,insultame).

+quetal[source(A)] 
	<-	.print("soy un mamonazo",A);
     	.send(A,tell,saludame).   
     