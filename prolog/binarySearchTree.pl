% node(leaf(s(0)),s(s(0)),node(leaf(s(0)),0,leaf(s(s(s(0)))))).
% node(leaf(2),3,node(leaf(5),6,leaf(7)))
% node(node(nil,2,nil),3,node(node(nil,5,nil),6,node(nil,7,nil)))

append([], YS, YS).
append([X|XS], YS, [X|ZS]) :- append(XS, YS, ZS).

preorder(leaf(X), [X]).
preorder(node(L, V, R), [V|NS]) :- preorder(L, LS), preorder(R, RS), append(LS,RS,NS).

postorder(leaf(X), [X]).
postorder(node(L, V, R), NS) :- postorder(L, LS), postorder(R, RS), append(LS,RS,Res), append(Res,[V], NS).

inorder(leaf(X), [X]).
inorder(node(L, V, R), NS) :- inorder(L, LS), inorder(R, RS), append(LS,[V|RS],NS).

maxValue(leaf(X), X).
maxValue(node(_,_,R),X) :- maxValue(R, X).

minValue(leaf(X), X).
minValue(node(L,_,_),X) :- minValue(L, X).

isBinarySearchTree(leaf(_)).
isBinarySearchTree(node(L, V, R)) :- 
    isBinarySearchTree(L), isBinarySearchTree(R), 
    maxValue(L,ML), ML < V,
    maxValue(R,MR), MR > V.

search(leaf(X), X).
search(node(_, X, _), X).
search(node(L, V, _), X) :- V > X, search(L, X).
search(node(_, V, R), X) :- V < X, search(R, X).

add(node(L,X,R),X,node(L,X,R)).
add(node(nil,X,nil),Y,node(nil,X,node(nil,Y,nil))):- Y > X.
add(node(nil,X,nil),Y,node(node(nil,Y,nil),X,nil)):- Y < X.
add(node(L,X,R),Y, node(L,X,N)):- Y > X, add(R, Y, N).
add(node(L,X,R),Y, node(N,X,R)):- Y < X, add(L, Y, N).