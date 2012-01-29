(ns ^{:doc "Problem: http://www.4clojure.com/problem/100"}
    denlab-4clojure-exercices.problem-100
    (:use [midje.sweet]))

 
;; Difficulty:	Easy
;; Topics:	math


;; Write a function which calculates the least common multiple. Your
;; function should accept a variable number of positive integers or
;; ratios.

(def f
  (fn [& a] ))

(fact
   (f 2 3) => 6)
(fact
   (f 5 3 7) => 105)
(fact
   (f 1/3 2/5) => 2)
(fact
   (f 3/4 1/6) => 3/2)
(fact
   (f 7 5/7 2 3/5) => 210)
