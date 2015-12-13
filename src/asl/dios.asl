// Agent dios in project JasonIsoMonks

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true
	<- 	.print("Dios controla a los monjes");
		.wait(3000);
		.send(frayHector,tell,interactuar(frayFernando,saludo));
		.wait(3000);
		.send(frayHector,tell,interactuar(frayFernando,comer)).
		