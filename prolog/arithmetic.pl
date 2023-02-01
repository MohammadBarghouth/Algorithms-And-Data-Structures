% Aufgabe 7 -------------------------------------------------
% a)
mod(X,Y,0) :- X == Y.
mod(X,Y,X) :- X < Y.
mod(X,Y,Z) :- X > 0, PX is X - Y, mod(PX,Y,Z).

primeH(_, 1).
primeH(N, I) :- mod(N, I, R), not(R == 0), PI is I - 1, primeH(N, PI).

prime(N) :- N > 1, I is N - 1, primeH(N,I).

% b)
onlyPrimes([]).
onlyPrimes([P|PS]) :- prime(P), onlyPrimes(PS).

% c)
pow(_,0,1).
pow(X,N,Y) :- PN is N - 1, pow(X, PN, PY), Y is X * PY.

powers(X, 1, [X]) :- X > 1.
powers(X, N, [Y|YS]) :- X > 1, N > 1, pow(X,N,Y), PN is N - 1, powers(X, PN, YS).


