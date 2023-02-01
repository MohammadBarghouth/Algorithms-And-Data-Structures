% Aufgabe 7 -------------------------------------------------

% a)
myMod(N,M,0) :- N == M.
myMod(N,M,N) :- N < M.
myMod(N,M,K) :- N > 0, PN is N - M, myMod(PN,M,K).

primeH(X, 1).
primeH(X, G) :- V is G - 1, myMod(X, G, A), not(A == 0), primeH(X, V).

prime(X) :- H is X - 1, X > 1, primeH(X,H).

% b)
onlyPrimes([]).
onlyPrimes([X|XS]) :- prime(X), onlyPrimes(XS).

% c)
hoch(K,0,1).
hoch(K,L,O) :- J is L - 1, hoch(K, J, B), O is K * B.

powers(H, 1, [H]) :- H > 1.
powers(B, C, [V|VL]) :- C > 1, B > 1, Q is C - 1, hoch(B,C,V), powers(B, Q, VL).