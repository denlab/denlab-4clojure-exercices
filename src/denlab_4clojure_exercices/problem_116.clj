(ns ^{:doc "Problem: http://www.4clojure.com/problem/116"}
    denlab-4clojure-exercices.problem-116
    (:use [midje.sweet]))

;; Prime Sandwich
 
;; Difficulty:	Medium
;; Topics:	math


;; A balanced prime is a prime number which is also the mean of the
;; primes directly before and after it in the sequence of valid
;; primes. Create a function which takes an integer n, and returns
;; true iff it is a balanced prime.

(defn g
  [n])

(fact
 (g 4) => false)

(fact
 (g 563) => true)

(fact
 (nth (filter g (range)) 15) => 1103)


