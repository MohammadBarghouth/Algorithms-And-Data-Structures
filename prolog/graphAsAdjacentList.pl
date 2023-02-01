node(a,[b,c]).
node(b,[c,d]).
node(c,[]).
node(d,[]).
node(e,[f]).
node(f,[]).

followerH(node(_, [N|_]), N).
followerH(node(X, [_|NXS]), Y) :- followerH(node(X, NXS), Y).

%retrieve and Translate with node(X, NXS):
follower(X, Y) :- node(X, NXS), followerH(node(X, NXS),Y).

pathExists(X, X).
pathExists(X, Y) :- follower(X, F), pathExists(F, Y).

path(X, X, [X]).
path(X, Y, [X|P]) :- follower(X, F), path(F, Y, P).

allNeighbors(_, []).
allNeighbors(X, [Y|YS]) :- follower(X, Y), allNeighbors(X, YS).
allNeighbors(X, [Y|YS]) :- follower(Y, X), allNeighbors(X, YS).

isComplete([]).
isComplete([X|XS]) :- allNeighbors(X, XS), isComplete(XS).

allAccessibleNodes(_, []).
allAccessibleNodes(X, [Y|YS]) :- pathExists(X,Y), allAccessibleNodes(X, YS).
allAccessibleNodes(X, [Y|YS]) :- pathExists(Y,X), allAccessibleNodes(X, YS).

isRelatedGraph([]).
isRelatedGraph([X|XS]) :- allAccessibleNodes(X, XS), isRelatedGraph(XS).

