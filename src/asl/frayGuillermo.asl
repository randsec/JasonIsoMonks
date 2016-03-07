// Agent frayGuillermo in project JasonIsoMonks

/* Initial beliefs and rules */
humor(enfadado).
dolor(espalda).
caracter(adso,vivaz).
hambre(alta).
ubicacion(habitacion).

/* Initial goals */

!start.

/* Plans */

+!sonar(campana)
 : esHora(rezar)
 <- ir(misa).
 
 +!sonar(campana)
 : esHora(comer) | esHora(desayunar) | esHora(cenar)
 <- ir(comedor).