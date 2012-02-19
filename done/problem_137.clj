(ns ^{:doc "Problem: http://www.4clojure.com/problem/137"}
    denlab-4clojure-exercices.problem-137
    (:use [midje.sweet]))

;; Digits and bases
 
;; Difficulty:	Medium
;; Topics:	math

;; Write a function which returns a sequence of digits of a
;; non-negative number (first argument) in numerical system with an
;; arbitrary base (second argument). Digits should be represented with
;; their integer values, e.g. 15 would be [1 5] in base 10, [1 1 1 1]
;; in base 2 and [15] in base 16.

(def g
  #(if (zero? %) [0]
       (ffirst
        (drop-while (comp not zero? second)
                    (iterate (fn [[r x]] [(conj r (rem x %2)) (quot x %2)])
                             ['() %]))))) 

(fact
  (g 9 2) =>  [1 0 0 1] )
(fact
  (g 1234501 10) =>  [1 2 3 4 5 0 1] )
(fact
  (g 0 11) =>  [0] )
(fact
  (let [n (rand-int 100000)](g n n)) =>  [1 0] )
(fact
  (g Integer/MAX_VALUE 42) =>  [16 18 5 24 15 1] )


