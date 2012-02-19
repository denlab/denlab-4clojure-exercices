(ns ^{:doc "Problem: http://www.4clojure.com/problem/144"}
    denlab-4clojure-exercices.problem-144
    (:use [midje.sweet]))

;; Oscilrate
 
;; Difficulty:	Medium
;; Topics:	sequences

;; Write an oscillating iterate: a function that takes an initial
;; value and a variable number of functions. It should return a lazy
;; sequence of the functions applied to the value in order, restarting
;; from the first function after it hits the end.

(def g
  (fn [v & f] (map first 
                  (iterate (fn [[w h]] [((first h) w) (rest h)])
                           [v (cycle f)]))))

(fact
  (take 3 (g 3.14 int double))        =>  [3.14 3 3.0])
(fact
  (take 5 (g 3 #(- % 3) #(+ 5 %)))    =>  [3 0 5 2 7])
(fact
  (take 12 (g 0 inc dec inc dec inc)) =>  [0 1 0 1 0 1 2 1 2 1 2 3])
