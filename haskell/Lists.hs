module Lists where 

contains :: [Int] -> Int -> Bool
contains [] _ = False
contains (x:xs) y
    | x == y = True
    | otherwise = contains xs y

removeDiplucates :: [Int] -> [Int]
removeDiplucates [] = []
removeDiplucates (x:xs) 
    | contains xs x = removeDiplucates xs
    | otherwise = x:(removeDiplucates xs)

flatten :: [(a,a)] -> [a]
flatten [] = []
flatten ((x,y):xs) = x:y:(flatten xs)

intersection :: [Int] -> [Int] -> [Int]
intersection [] _ = []
intersection (a:as) bs 
    | contains bs a = a:(intersection as bs)
    | otherwise = intersection as bs

reverseList :: [a] -> [a]
reverseList [] = []
reverseList (x:xs) = (reverseList xs) ++ [x]