(ns ^{:doc "Problem: http://www.4clojure.com/problem/112"}
    denlab-4clojure-exercices.problem-112
    (:use [midje.sweet])
    (:use [clojure.repl :only [doc dir dir-fn]])
    (:use [clojure.pprint :only [pprint print-table]]))

;; Sequs Horribilis
 
;; Difficulty:	Medium
;; Topics:	seqs

;; Create a function which takes an integer and a nested collection of
;; integers as arguments. Analyze the elements of the input collection
;; and return a sequence which maintains the nested structure, and
;; which includes all elements starting from the head whose sum is
;; less than or equal to the input integer.


(defn sum-g
  [g] (if (sequential? g)
        (+ (sum-g (first g))
           (sum-g (next  g)))
        (if g g 0)))

(fact
  (sum-g [1 [2 [3]]]) => 6)

(defn inc-g
  [g] (do (prn (str "(inc-g " g ")"))
          (if (sequential? g)
            (if (= 1 (count g))
              (inc-g (first g))
              (conj (inc-g (first g)) (inc-g (next g))))
            [(inc g)])))

(unfinished)

(defn char-digit?
  [c] (re-matches #"[\d\-]" (str c)))

(fact
 (char-digit? \0) => truthy
 (char-digit? \9) => truthy
 (char-digit? \-) => truthy
 (char-digit? \+) => falsey)

(defn g-str-fn
  [n [r c p] i] (do #_(prn "r=" r "c=" c "p=" p)
                    (if  (char-digit? i)
                      [r c (str p i)]
                      (if p
                        (let [j (read-string p)]
                          (do #_(prn "n=" n "c=" c)
                              #_(prn (str "(< " c " " n ")")) 
                              (if (<= (+ j c) n) 
                                (do #_(prn "->1") [(str r p i) (+ c j) nil])
                                (do #_(prn "->2") [(str r i  ) c       nil])))) 
                        [  (str r i)   c        p]))))

;; 
;; 
;;  Find max number (can be done in advance)
;;  Hint: (partition-by number s)
;;  take-while (n < max number)
;;
;;  use the previsou method, except that you count the number of open
;; parenth
;;  at the end, add the number of missing of ']'
;; 
;; 
;; Note: you may have to convert any '(' into ']'
;; 
;; 


(fact "(g-str-fn 6 [1 [2 [3 [4 [5 [6]]]]]])"
      (g-str-fn 6 [""                     0 nil] \[     ) => ["["                    0 nil]
      (g-str-fn 6 ["["                    0 nil] \1     ) => ["["                    0 "1"]
      (g-str-fn 6 ["["                    0 "1"] \space ) => ["[1 "                  1 nil]
      (g-str-fn 6 ["[1 "                  1 nil] \[     ) => ["[1 ["                 1 nil]
      (g-str-fn 6 ["[1 "                  1 nil] \[     ) => ["[1 ["                 1 nil]
      (g-str-fn 6 ["[1 ["                 1 nil] \2     ) => ["[1 ["                 1 "2"]
      (g-str-fn 6 ["[1 ["                 1 "2"] \space ) => ["[1 [2 "               3 nil]
      (g-str-fn 6 ["[1 [2 "               3 nil] \[     ) => ["[1 [2 ["              3 nil]
      (g-str-fn 6 ["[1 [2 ["              3 nil] \3     ) => ["[1 [2 ["              3 "3"]
      (g-str-fn 6 ["[1 [2 ["              3 "3"] \space ) => ["[1 [2 [3 "            6 nil]
      (g-str-fn 6 ["[1 [2 [3 "            6 nil] \[     ) => ["[1 [2 [3 ["           6 nil]
      (g-str-fn 6 ["[1 [2 [3 ["           6 nil] \4     ) => ["[1 [2 [3 ["           6 "4"]
      (g-str-fn 6 ["[1 [2 [3 ["           6 "4"] \space ) => ["[1 [2 [3 [ "          6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ "          6 nil] \[     ) => ["[1 [2 [3 [ ["         6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ ["         6 nil] \5     ) => ["[1 [2 [3 [ ["         6 "5"]
      (g-str-fn 6 ["[1 [2 [3 [ ["         6 "5"] \space ) => ["[1 [2 [3 [ [ "        6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ "        6 nil] \[     ) => ["[1 [2 [3 [ [ ["       6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ ["       6 nil] \6     ) => ["[1 [2 [3 [ [ ["       6 "6"]
      (g-str-fn 6 ["[1 [2 [3 [ [ ["       6 "6"] \]     ) => ["[1 [2 [3 [ [ []"      6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ []"      6 nil] \]     ) => ["[1 [2 [3 [ [ []]"     6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ []]"     6 nil] \]     ) => ["[1 [2 [3 [ [ []]]"    6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ []]]"    6 nil] \]     ) => ["[1 [2 [3 [ [ []]]]"   6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ []]]]"   6 nil] \]     ) => ["[1 [2 [3 [ [ []]]]]"  6 nil]
      (g-str-fn 6 ["[1 [2 [3 [ [ []]]]]"  6 nil] \]     ) => ["[1 [2 [3 [ [ []]]]]]" 6 nil])

(fact
  (g-str-fn 30 ["" 28 "8"] \space) => [" " 28 nil])

(defn sum-first "Takes a seq of numbers, returns a seq of (cons (+ first second) (drop 2 seq))"
  [s] (cons (+ (first s) (second s)) (drop 2 s)))

(fact
 (sum-first [1 2 3])                  => [3 3]
 (take 2 (sum-first (iterate inc 1))) => [3 3])

(defn max-included
  [n s] (second (first
                 (drop-while (fn [s] (< (first s) n)) 
                             (iterate sum-first (flatten s))))))

(fact
 (max-include)

(future-fact 
  (max-includ)                 =>  4
  (max-includ 8]] 9]] 10] 11]) => 7
  (max-includ                  => 1
  (max-includ                  => 0
  (max-includ                  => 3
  (max-includ)                 => 0
  (max-includ6 7 [8]]]]]])     => 4)

(defn g-str
  [n s] (firs%2)
             
             

(fact
 (g-str 0 "[1
 (g-str 6 "[1
 (g-str 6 "[-

(fact 
  (g-str 6 "[[1 [2 [3 [ []]]]]" )
           

(fact
 (g-str 5 "[-1 1 [2 [3 []]]]")

(defn g
  [n s] (read))

(fact
 (g :n :seq2)
 (provided
  (g-str :n ")


(fact
 (g 10 [1 2 [2 (3 (4))))

(fact
 (g 30 [1 2 [ 11]) =>    '(1 2 (3 (4 (5 (6 (7)))))))
(fact
 (g 1 [[[[[1]
(fact
 (g 0 [0 0 [0))))
(future-fact 
  (g 9 (range)) =>    '(0 1 2 3))
 (future-fact
  (g 0 [1 2 [3 [4 5] 6] 7]) =>    '())
 (future-fact
  (g 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]]) =>    '(-10 (1 (2 3 (4))))))
