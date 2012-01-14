(ns ^{:doc "Problem: http://www.4clojure.com/problem/43"}
    denlab-4clojure-exercices.problem-043
    (:use [midje.sweet])
        (:use [clojure.repl :only [doc]]))

 
;; Difficulty:	Medium
;; Topics:	seqs


;; Write a function which reverses the interleave process into x number of subsequences.

(def f
  (fn [s n] (map #(take-nth n (drop % s))
                (range n))))

(fact
  (f [1 2 3 4 5 6] 2) => '((1 3 5) (2 4 6)))
(fact
  (f (range 9) 3) => '((0 3 6) (1 4 7) (2 5 8)))
(fact
  (f (range 10) 5) => '((0 5) (1 6) (2 7) (3 8) (4 9)))
