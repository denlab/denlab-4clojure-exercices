(ns ^{:doc "Problem: http://www.4clojure.com/problem/110"}
    denlab-4clojure-exercices.problem-110
    (:use [midje.sweet]))

;; Sequence of pronunciations
 
;; Difficulty:	Medium
;; Topics:	seqs


;; Write a function that returns a lazy sequence of "pronunciations"
;; of a sequence of numbers. A pronunciation of each element in the
;; sequence consists of the number of repeating identical numbers and
;; the number itself. For example, [1 1] is pronounced as [2 1] ("two
;; ones"), which in turn is pronounced as [1 2 1 1] ("one two, one
;; one").

;; Your function should accept an initial sequence of numbers, and
;; return an infinite lazy sequence of pronunciations, each element
;; being a pronunciation of the previous element.

(def g
  (fn [x]))

(fact
 (take 3 (g [1]))           => [[1 1] [2 1] [1 2 1 1]])
(fact
 (first (g [1 1 1 4 4]))    => [3 1 2 4])
(fact
 (nth (g [1]) 6)            => [1 1 1 3 2 1 3 2 1 1])
(fact
 (count (nth (g [3 2]) 15)) => 338)

