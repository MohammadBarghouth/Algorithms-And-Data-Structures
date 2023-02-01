data Tree a = Node a (Tree a) (Tree a) | Nil deriving Show

tr :: Tree Int
tr = Node 1 (Node 2 (Node 3 Nil Nil) (Node 4 Nil Nil)) (Node 2 (Node 4 Nil Nil) (Node 3 Nil Nil))

tr1 :: Tree Int
tr1 = Node 1 (Node 2 (Node 3 Nil Nil) (Node 4 Nil Nil)) (Node 5 (Node 6 Nil Nil) (Node 7 (Node 5 Nil Nil) Nil))


areIdentical :: Tree Int -> Tree Int -> Bool
areIdentical Nil Nil = True
areIdentical (Node _ _ _) Nil = False
areIdentical Nil (Node _ _ _) = False
areIdentical (Node v1 l1 r1) (Node v2 l2 r2) =
    v1 == v2 && areIdentical l1 l2 && areIdentical r1 r2

isSymmetric :: Tree Int -> Bool
isSymmetric Nil = True
isSymmetric (Node _ Nil Nil) = True
isSymmetric (Node _ (Node _ _ _) Nil) = False
isSymmetric (Node _ Nil (Node _ _ _)) = False
isSymmetric (Node _ (Node v1 l1 r1) (Node v2 l2 r2)) = 
    v1 == v2 && areIdentical l1 r2 && areIdentical r1 l2

mirror :: Tree a -> Tree a
mirror Nil = Nil
mirror (Node v Nil (Node v2 l2 r2)) = Node v (Node v2 l2 r2) Nil
mirror (Node v (Node v1 l1 r1) Nil) = Node v Nil (Node v1 l1 r1)
mirror (Node v l r) = Node v (mirror r) (mirror l)