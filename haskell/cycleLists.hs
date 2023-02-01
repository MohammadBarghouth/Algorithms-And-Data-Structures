import Lists

data Cyclist a = Element a (Cyclist a) deriving Show

c1 :: Cyclist Int
c1 = Element 1 (Element 2 (Element 3 c1))

c2 :: Cyclist Int
c2 = let lastElement = Element 3 lastElement in Element 1 (Element 2 lastElement)

singelton :: a -> Cyclist a
singelton x = let c = Element x c in c

toCyclist :: [a] -> Cyclist a
toCyclist [x] = singelton x
toCyclist (x:xs) = Element x (toCyclist xs)

toListH :: Cyclist Int -> [Int] -> [Int]
toListH (Element x c) xs
    | elem x xs = [x]
    | otherwise = x:(toListH c (x:xs)) 

toList :: Cyclist Int -> [Int] 
toList c = toListH c []

normalizeH :: Cyclist Int -> [Int] -> Cyclist Int
normalizeH (Element x (Element y c)) xs
    | elem y xs = singelton x
    | otherwise = Element x (normalizeH (Element y c) (x:xs))

normalize :: Cyclist Int -> Cyclist Int
normalize cs = normalizeH cs []

append :: Cyclist Int -> [Int] -> Cyclist Int
append (Element x (Element y c)) (a:as)
    | x == y = Element x (append (singelton a) as)  -- <-- see the "Element x (append ..."
    | otherwise = Element x (append (Element y c) (a:as))
append (Element x (Element y c)) [] = (Element x (Element y c))

reverseCyclist :: Cyclist Int ->  Cyclist Int
reverseCyclist (Element x (Element y c)) 
    | x == y = singelton x
    | otherwise = append prev [x]  -- <-- see append
    where prev = reverseCyclist (Element y c)

