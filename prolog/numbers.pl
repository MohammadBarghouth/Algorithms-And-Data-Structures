max(0, Y, Y).
max(X, 0, X).
max(s(X), s(Y), s(M)) :- max(X,Y,M).

min(0, _, 0).
min(_, 0, 0).
min(s(X), s(Y), s(M)) :- min(X,Y,M).

isGreaterThen(s(_), 0).
isGreaterThen(s(X), s(Y)) :- isGreaterThen(X, Y).

isGreaterOrEqualThen(_, 0).
isGreaterOrEqualThen(s(X), s(Y)) :- isGreaterOrEqualThen(X, Y).

add(0, Y, Y).
add(s(X), Y, s(Z)) :- add(X, Y, Z).

subtract(X,Y,Z) :- add(Y,Z,X).

% x * y = x * (y - 1) + x = x * (y - 2) + 2x = .... = x * (y - y) + y * x = y * x
multiply(X, s(0), X).
multiply(_, 0, 0).
multiply(X, s(Y), Z) :- multiply(X, Y, PZ), add(X, PZ, Z).

plus(0,X,X).
plus(s(X),Y,s(Z)) :- plus(X,Y,Z).
plus(p(X),Y,p(Z)) :- plus(X,Y,Z).

normalize(0,0).
normalize(s(X),s(s(Y))) :- normalize(X,s(Y)).
normalize(s(X),Y) :- normalize(X,p(Y)).
normalize(s(X),s(0)) :- normalize(X,0).
normalize(p(X),p(p(Y))) :- normalize(X,p(Y)).
normalize(p(X),Y) :- normalize(X,s(Y)).
normalize(p(X),p(0)) :- normalize(X,0).

normalize2(X,Y) :- norm(X,0,Y).
norm(0,X,X).
norm(p(X),0,Z) :- norm(X,p(0),Z).
norm(p(X),s(Y),Z) :- norm(X,Y,Z).
norm(p(X),p(Y),Z) :- norm(X,p(p(Y)),Z).
norm(s(X),0,Z) :- norm(X,s(0),Z).
norm(s(X),s(Y),Z) :- norm(X,s(s(Y)),Z).
norm(s(X),p(Y),Z) :- norm(X,Y,Z).
