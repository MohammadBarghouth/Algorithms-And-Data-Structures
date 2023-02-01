data Tree a = Node a [Tree a] | Nil deriving Show

tr :: Tree Int
tr = Node 1 [Node 2 [], Node 5 [Node 6 [Node 7 []]], Node 8 []]
tr2 :: Tree Int
tr2 =  Node 5 [Node 6 [Node 7 []]]

-- see the map 
mapTree :: (a -> b) -> Tree a -> Tree b
mapTree _ Nil = Nil
mapTree f (Node v ts) = Node (f v) (map (mapTree f) ts)

mapTreeWithPathH :: ([a] -> a -> b) -> [a] -> Tree a -> Tree b
mapTreeWithPathH _ _ Nil = Nil
mapTreeWithPathH f path (Node v ts) = Node (f path v) (map (mapTreeWithPathH f nPath) ts)
    where nPath = path ++ [v]

mapTreeWithPath :: ([a] -> a -> b) -> Tree a -> Tree b
mapTreeWithPath _ Nil = Nil
mapTreeWithPath f t = mapTreeWithPathH f [] t

filterTree :: (a -> Bool) -> Tree a -> Tree a
filterTree _ Nil = Nil
filterTree f (Node v ts) 
    | f v = Node v (filter notEmpty (map (filterTree f) ts))
    | otherwise = Nil
    where notEmpty Nil = False
          notEmpty _ = True

foldTree :: (a -> [b] -> b) -> b -> Tree a -> b
foldTree _ b Nil = b
foldTree f b (Node v ts) = f v (map (foldTree f b) ts)

sumTree :: Tree Int -> Int
sumTree t = foldTree (\a bs -> a + (foldl (+) 0 bs)) 0 t

-- see foldl + map
depth :: Tree a -> Int
depth Nil = 0
depth (Node _ ts) = 1 + (foldl max 0 (map depth ts)) 

toList :: Tree a -> [a]
toList Nil = []
toList (Node v ts) = v:(foldl (++) [] (map toList ts)) 

contains :: Int -> Tree Int -> Bool
contains _ Nil  = False
contains x (Node v ts) = x == v || any (==True) (map (contains x) ts)

-- see max v ...
maxInTree :: Tree Int -> Int
maxInTree Nil = 0
maxInTree (Node v ts) = max v (foldl max 0 (map maxInTree ts))

-- see the foldl + 2*map and Clustering:
allPaths :: Tree a -> [[a]]
allPaths Nil = [[]]
allPaths (Node v []) = [[v]] -- is Important, as a basis
allPaths (Node v ts) = map (v:) (foldl (++) [] (map allPaths ts))

-- see all and zip
identical :: Tree Int -> Tree Int -> Bool
identical Nil Nil = True
identical (Node _ _) Nil = False
identical Nil (Node _ _) = False
identical (Node v1 ts1) (Node v2 ts2) = 
    v1 == v2 && length ts1 == length ts2 
    && all (==True) (map (\(w1,w2)-> identical w1 w2) (zip ts1 ts2))

isSubTree :: Tree Int -> Tree Int -> Bool
isSubTree subtree Nil = identical subtree Nil
isSubTree subtree (Node v ts) = 
    identical subtree (Node v ts) 
    || any (==True) (map (isSubTree subtree) ts) 

        

{-

Important Hints:

    1. foldl :: (b' -> a -> b) -> b -> t a -> b
    2. zip :: [a] -> [b] -> (a,b)
    3. all :: (a -> Bool) -> [a] -> Bool
    4. any :: (a -> Bool) -> [a] -> Bool
    5. use predefined functions:
        - (++) like (\a b -> a ++ b)
        - (v:) like (\a -> v:a)
        - (==True) like (\a -> a == True)
    
-}