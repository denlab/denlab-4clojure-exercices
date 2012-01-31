(ns ^{:doc "Problem: http://www.4clojure.com/problem/104"}
    denlab-4clojure-exercices.problem-104
    (:use [midje.sweet]))

;; Write Roman Numerals
 
;; Difficulty:	Medium
;; Topics:	strings math


;; This is the inverse of Problem 92M, but much easier. Given an
;; integer smaller than 4000, return the corresponding roman numeral
;; in uppercase, adhering to the subtractive principleN.

(defn g
  [n])

(future-fact
 (g 1) => "I" )

(future-fact
 (g 30) => "XXX" )

(future-fact
 (g 4) => "IV" )

(future-fact
 (g 140) => "CXL" )

(future-fact
 (g 827) => "DCCCXXVII" )

(future-fact
 (g 3999) => "MMMCMXCIX" )

(future-fact
 (g 48) => "XLVIII" )

