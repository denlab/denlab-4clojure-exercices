(ns ^{:doc "Problem: http://www.4clojure.com/problem/115"}
    denlab-4clojure-exercices.problem-115
    (:use [midje.sweet]))

;; The Balance of N
 
;; Difficulty:	Medium
;; Topics:	math

;; A balanced number is one whose component digits have the same sum
;; on the left and right halves of the number. Write a function which
;; accepts an integer n, and returns true iff n is balanced.

(def f
  (fn [n] (loop [c (str n) a #{} b #{}]
           (if (second c)
             (recur (rest (butlast c)) (conj a (first c)) (conj b (last c)))
             (= a b)))))

(fact
  (f 11) => true )
(fact
  (f 121) => true )
(fact
  (f 123) => false )
(fact
  (f 0) => true )
(fact
  (f 88099) => false )
(fact
  (f 89098) => true )
(fact
  (f 89089) => true )
(fact
  (take 20 (filter f (range))) =>
   [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101])
