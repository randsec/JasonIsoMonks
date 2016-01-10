/* Initial beliefs and rules */
/* Conocimientos que el frayFede tiene del entorno */
posicion(x,9).
posicion(y,0).

destino(x,0).
destino(y,0).

obstaculo1(x,7).
obstaculo1(y,0).

/*
 * Introduciendo el movimiento con objetivo del monje mediante el algoritmo A* (A Estrella)
 * El monje situado en la X (9,0) debe dirigirse al objetivo O (0,1)
 *
 *	           0 1 2 3 4 5 6 7 8 9
 *		       _ _ _ _ _ _ _ _ _ _
 *		 	  |O|_|_|_|_|_|_|M|_|X| 0
 * 		 	__|_|_|_|_|_|_|_|_|_|_| 1
 * Salida	  |_|_|_|_|_|_|_|_|_|_| 2
 * Salida	__|_|_|_|_|_|_|_|_|_|_| 3
 *		  	  |_|_|_|_|_|_|_|_|_|_| 4
 * 		  	  |_|_|_|_|_|_|_|_|_|_| 5 
 * 
 */

/*Initial goals */
!alcanzarObjetivo.

/*Plans */
+!alcanzarObjetivo
	: posicion(x,PX) & posicion(y,PY)
	<-	-posicion(x,PX); -posicion(y,PY);
		.send(self,tell,comprobarPosicion(PX,PY)).
	
		
+comprobarPosicion(PX,PY)
	: destino(x,DX) & destino(y,DY)
	<-  if(PX == DX & PY == DY){
			.print("HE LLEGADO A MI DESTINO");
			.print("Estoy en la posicion (",PX, ",", PY,")");
			+posicion(x,DX); +posicion(y,DY);
		}
		else{
			.print("NO HE LLEGADO A MI DESTINO");
			.print("Estoy en la posicion (",PX, ",", PY,")");
			.wait(2000); 
			.send(self,tell,comprobarPosicion(PX-1,PY))
		}.	
		