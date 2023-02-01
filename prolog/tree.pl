% node(2,[node(3,[node(5,[]), node(6,[node(1,[])])]), node(4,[node(5,[])])])

max(X, Y, Y) :- Y >= X.
max(X, Y, X) :- X > Y.

depthChildren([],0).
depthChildren([C|CS], D) :- 
    depth(C, DC), 
    depthChildren(CS, DCS), 
    max(DC, DCS, D).

depth(node(_,[]), 1).
depth(node(_,CS), D) :- depthChildren(CS, DS), D is DS + 1.


multiplyBy2ForEach([],[]).
multiplyBy2ForEach([N|NS], [MN|MNS]) :- multiplyBy2(N, MN), multiplyBy2ForEach(NS, MNS).

multiplyBy2(node(V,CS), node(MV,MCS)) :- MV is V * 2, multiplyBy2ForEach(CS, MCS).


contains(node(V,_), V).
contains(node(V,[C|CS]), X) :- contains(C, X); contains(node(V,CS), X).


append([], YS, YS).
append([X|XS], YS, [X|ZS]) :- append(XS, YS, ZS).

valueofEach([],[]).
valueofEach([N|NS], VS) :- toList(N, NVS), valueofEach(NS, NSVS), append(NVS, NSVS, VS).

toList(node(V,[]), [V]).
toList(node(V,CS), [V|VS]) :- valueofEach(CS, VS).


allLeafsOfEach([],[]).
allLeafsOfEach([N|NS], LS) :- allLeafs(N, NLS), allLeafsOfEach(NS, NSLS), append(NLS, NSLS, LS).

allLeafs(node(V,[]), [V]).
allLeafs(node(_,CS), LS) :- allLeafsOfEach(CS, LS).


shiftEachList([], _, []).
shiftEachList([L|LS], X, [[X|L]|NLS]) :- shiftEachList(LS, X, NLS).

allPathsForEach([], []).
allPathsForEach([N|NS], PS) :- allPaths(N, PN), allPathsForEach(NS, PNS), append(PN, PNS, PS).

allPaths(node(V,[]),[[V]]).
allPaths(node(V,CS),PS) :- allPathsForEach(CS, PPS), shiftEachList(PPS, V, PS).



