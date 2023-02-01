edge(a,b).
edge(a,c).
edge(a,d).
edge(d,f).
edge(f,e).
edge(s,e).

follower(X,Y) :- edge(X,Y).

areNeighbors(X,Y) :- edge(X,Y).
areNeighbors(X,Y) :- edge(Y,X).

pathExists(X,X).
pathExists(X,Y) :- follower(X, F), pathExists(F, Y).

% all other function are the

