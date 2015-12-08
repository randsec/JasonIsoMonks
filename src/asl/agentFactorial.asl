// Agent agentFactorial in project JasonIsoMonks

/* Initial beliefs and rules */
fact(0,1).
/* Initial goals */

!start.

/* Plans */

/*
	+!start : true 
		<-	.print("Agente factorial operativo").
* 
*/

+fact(X,Y)
 : X < 5
 <- +fact(X+1, (X+1)*Y).
 
+fact(X,Y)
 : X == 5
 <- .print("fact 5 == ", Y).