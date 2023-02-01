import Lists

data Graph = Edges [(Int,Int)] deriving Show

g1:: Graph
g1 = Edges [(1,2),(1,4),(1,5),(2,3),(2,4),(3,4),(4,5),(4,6)]
g2:: Graph
g2 = Edges [(1,2),(1,4),(1,5),(2,3),(3,7)]

nodesOf :: Graph -> [Int]
nodesOf (Edges ls) = removeDiplucates (flatten ls)

-- see the implementation of 'contains' using foldl
graphContains :: Int -> Graph -> Bool
graphContains x g = foldl (\b y -> b || y == x) False (nodesOf g)

nighborsOf :: Int -> Graph -> [Int]
nighborsOf x (Edges es) = removeDiplucates (foldl checkNighbors [] es)
    where checkNighbors ns (n1,n2) = 
            if n1 == x then n2:ns 
            else if n2 == x then n1:ns 
            else ns

pathExists :: Int -> Int -> Graph -> Bool
pathExists a b (Edges []) = a == b
pathExists a b (Edges ((x1,x2):xs)) = 
    pathExists a b (Edges xs) || (pathExists a x1 (Edges xs) && pathExists x2 b (Edges xs))

notEmpty :: [a] -> Bool
notEmpty [] = False
notEmpty (x:xs) = True

path :: Int -> Int -> Graph -> [Int]
path a b (Edges []) 
    | a == b = [a]
    | otherwise = []

path a b (Edges ((u,v):xs))  
    | notEmpty ab = ab
    | notEmpty au && notEmpty vb = au ++ vb
    | otherwise = []
    where ab = path a b (Edges xs)
          au = path a u (Edges xs)
          vb = path v b (Edges xs)



{-isCycledH :: Graph -> [Int] -> Int -> Bool
isCycledH g [] l  = any (True==) (map (isCycledH g [l]) ns)
    where ns = nighborsOf l g

isCycledH g (v:vs) l
    | length (intersection ns vs) > 0 = True
    | length ns <= 1 = False
    | otherwise = any (True==) (map (isCycledH g (l:v:vs)) ns)
    where ns = nighborsOf l g

isCycled :: Graph -> Bool
isCycled g = isCycledH g [] ((nodesOf g) !! 0)-}

-- spanTree :: Graph -> Graph
-- spanTree (Edges ls)