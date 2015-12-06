// Agent r1 in project JasonIsoMonks
/* Initial beliefs and rules */
pos(r2,2,2).
checking(slots).

/* Initial goals */

/* Plans */
+pos(r1,X1,Y1) : checking(slots) & not garbage(r1)
      <- next(slot).

+garbage(r1) : checking(slots)
      <- !stop(check);
      !take(garb,r2);
      !continue(check).

+!stop(check) : true
      <- ?pos(r1,X1,Y1);
      +pos(back,X1,Y1);
      -checking(slots).

+!take(S,L) : true
      <- !ensure_pick(S);
      !go(L);
      drop(S).

+!ensure_pick(S) : garbage(r1)
      <- pick(garb);
      !ensure_pick(S).

+!ensure_pick(S) : true <- true.

+!continue(check) : true
      <- !go(back);
      -pos(back,X1,Y1);
+checking(slots);
      next(slot).

+!go(L) : pos(L,Xl,Yl) & pos(r1,Xl,Yl)
      <- true.

+!go(L) : true
      <- ?pos(L,Xl,Yl);
      moveTowards(Xl,Yl);
      !go(L).