len([], 0).
len([_|XS],L) :- len(XS,PL), L is PL + 1.

firstIsLonger([_],[]).
firstIsLonger([_|AS],[_|BS]) :- firstIsLonger(AS,BS).

contains([X|_], X).
contains([_|XS], Y) :- contains(XS, Y).

nth([X|_], 0, X).
nth([_|XS], N, Y) :- SN is N - 1, nth(XS, SN, Y).

indexOf([X|_], X, 0).
indexOf([_|XS], Y, I) :- I is PI + 1, indexOf(XS, Y, PI).

/*
is wrong:
indexOf([X|_], X, I).
indexOf([X|XS], Y, I) :- SI is I - 1, indexOf(XS, Y, SI).
*/

remove([], _, []).
remove([X|XS], X, ZS) :- remove(XS, X, ZS).
remove([X|XS], Y, [X|ZS]) :- remove(XS, Y, ZS).

removeNth([_|XS], 0, XS).
removeNth([X|XS], N, [X|YS]) :- SN is N - 1, removeNth(XS, SN, YS).

removeDiplucates([], []).
removeDiplucates([X|XS], ZS) :- contains(XS, X), removeDiplucates(XS, ZS).
removeDiplucates([X|XS], [X|ZS]) :- removeDiplucates(XS, ZS).

getDiplucates([], []).
getDiplucates([X|XS], [X|DS]):- contains(XS, X), getDiplucates(XS, DS), not(contains(DS, X)).
getDiplucates([_|XS], DS):- getDiplucates(XS, DS).

insert([], Y, 0, [Y]).
insert([X|XS], Y, 0, [Y, X| XS]).
insert([X|XS], Y, N, [X|ZS]) :- SN is N - 1, insert(XS, Y, SN, ZS).

append([], YS, YS).
append([X|XS], YS, [X|ZS]) :- append(XS, YS, ZS).

multBy2([], []).
multBy2([X|XS], [Y|YS]) :- Y is X * 2, multBy2(XS,YS).

greaterThen10([], []).
greaterThen10([X|XS], [X|YS]) :- X > 10, greaterThen10(XS, YS).
greaterThen10([_|XS], YS) :- greaterThen10(XS, YS).

max(X, Y, Y) :- Y >= X.
max(X, Y, X) :- X > Y.

maxInList([X], X). % <-- !
maxInList([X|XS], M) :- maxInList(XS, Y), max(X, Y, M).

sortList([], []). % <-- !
sortList(XS, [M|ZS]) :- maxInList(XS, M), remove(XS, M, YS), sortList(YS, ZS).

userDefinedList(nil).
userDefinedList(cons(_,L)) :- userDefinedList(L).

asPrologList([], nil).
asPrologList([A|PL], cons(A, UL)) :- asPrologList(PL, UL).