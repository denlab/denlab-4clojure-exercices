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


;; from wikipedia:

;; I 	1
;; V 	5
;; X 	10
;; L 	50
;; C 	100
;; D 	500
;; M 	1000

(unfinished)

(def transco
  {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000})

(def transco
  (zipmap "IVXLCDM" [1 5 10 50 100 500 1000]))

(def g
  #(loop [[a b & r] (map transco %) m 0]
     (cond
      (nil? a) m
      (nil? b) (+ m a)
      (< a b ) (recur r (+ m (- b a)))
      :else    (recur (cons b r) (+ m a)))))

(fact
 (g "XIV") => 14 )

(fact
 (g "DCCCXXVII") => 827 )

(fact
 (g "MMMCMXCIX") => 3999 )

(fact
 (g "XLVIII") => 48 )
