(ns ^{:doc "Problem: http://www.4clojure.com/problem/92"}
    denlab-4clojure-exercices.problem-092
    (:use [midje.sweet]))

;; Read Roman numerals
 
;; Difficulty:	Hard
;; Topics:	strings math


;; Roman numerals are easy to recognize, but not everyone knows all
;; the rules necessary to work with them. Write a function to parse a
;; Roman-numeral string and return the number it represents.

;; You can assume that the input will be well-formed, in upper-case,
;; and follow the subtractive principleM. You don't need to handle any
;; numbers greater than MMMCMXCIX (3999), the largest number
;; representable with ordinary letters.

(defn g
  [s])

(fact
 (g "XIV") => 14 )
(fact
 (g "DCCCXXVII") => 827 )
(fact
 (g "MMMCMXCIX") => 3999 )
(fact
 (g "XLVIII") => 48 )
