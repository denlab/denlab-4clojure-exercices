(ns ^{:doc "Problem: http://www.4clojure.com/problem/132"}
    denlab-4clojure-exercices.problem-132
    (:use [midje.sweet]))

 
;; Difficulty:	Medium
;; Topics:	seqs core-functions


;; Write a function that takes a two-argument predicate, a value, and
;; a collection; and returns a new collection where the value is
;; inserted between every two items that satisfy the predicate.

(def g
  (fn [p v s] (if (seq s)
               (cons (first s)
                     (reduce (fn [r [o [b a]]] (if (p b a)
                                                (conj r v o)
                                                (conj r o)))
                             []
                             (map vector
                                  (rest s)
                                  (partition 2 1 s))))
               [])))

(fact
  (g < :less [1 6 7 4 3]) => '(1 :less 6 :less 7 4 3) )
(fact
 (g > :more [2]) => '(2) )
(fact
 (g #(and (pos? %) (< % %2)) :x (range 5)) => [0 1 :x 2 :x 3 :x 4]  )
(fact
 (g > :more ()) => [])

(future-fact
 (take 12 (->> [0 1]
               (iterate (fn [[a b]] [b (+ a b)]))
               (map first)              ; fibonacci numbers
               (g (fn [a b]              ; both even or both odd
                    (= (mod a 2) (mod b 2)))
                  :same)))
 => [0 1 :same 1 2 3 :same 5 8 13 :same 21])
