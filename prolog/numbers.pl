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


