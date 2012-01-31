(ns ^{:doc "Problem: http://www.4clojure.com/problem/115"}
    denlab-4clojure-exercices.problem-115
    (:use [midje.sweet]))

;; The Balance of N
 
;; Difficulty:	Medium
;; Topics:	math

;; A balanced number is one whose component digits have the same sum
;; on the left and right halves of the number. Write a function which
;; accepts an integer n, and returns true iff n is balanced.

(def g
  (fn [n] (loop [[f & r] (str n) a #{} b #{}]
           (if (seq r)
             (recur (butlast r) (conj a f) (conj b (last r)))
             (= a b)))))

(fact
  (g 11) => true )
(fact
  (g 121) => true )
(fact
  (g 123) => false )
(fact
  (g 0) => true )
(fact
  (g 88099) => false )
(fact
  (g 89098) => true )
(fact
  (g 89089) => true )
(fact
  (take 20 (filter g (range))) =>
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])
