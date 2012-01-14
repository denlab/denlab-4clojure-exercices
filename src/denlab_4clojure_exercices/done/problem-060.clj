(ns ^{:doc "Problem: http://www.4clojure.com/problem/60"}
    denlab-4clojure-exercices.problem-060
    (:use [midje.sweet]))

;; Sequence Reductions
 
;; Difficulty:	Medium
;; Topics:	seqs core-functions


;; Write a function which behaves like reduce, but returns each intermediate value of the reduction. Your function must accept either two or three arguments, and the return sequence must be lazy.

(def red
  (fn redz
    ([f s]   (redz f (first s) (rest s)))
    ([f n s] (lazy-seq (cons n
                             (if (seq s) (redz f (f n (first s)) (rest s))))))))

(fact
  (red conj [1] [2 3 4]) => [[1] [1 2] [1 2 3] [1 2 3 4]])
(fact
  (take 5 (red + (range))) => [0 1 3 6 10])
(fact
  (last (red * 2 [3 4 5])) => (reduce * 2 [3 4 5])
  (last (red * 2 [3 4 5])) =>  120)
