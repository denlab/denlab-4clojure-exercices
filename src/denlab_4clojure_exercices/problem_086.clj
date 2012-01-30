(ns ^{:doc "Problem: http://www.4clojure.com/problem/86"}
    denlab-4clojure-exercices.problem-086
    (:use [midje.sweet]))

;; Happy numbers
 
;; Difficulty:	Medium
;; Topics:	math


;; Happy numbers are positive integers that follow a particular
;; formula: take each individual digit, square it, and then sum the
;; squares to get a new number. Repeat with the new number and
;; eventually, you might get to a number whose squared sum is 1. This
;; is a happy number. An unhappy number (or sad number) is one that
;; loops endlessly. Write a function that determines if a number is
;; happy or not.  

(defn f
  [n])

(fact
  (f 7) => true)
(fact
  (f 986543210) => true)
(fact
  (f 2) => false)
(fact
  (f 3) => false)
