(ns ^{:doc "Problem: http://www.4clojure.com/problem/114"}
  denlab-4clojure-exercices.problem-114
  (:use [midje.sweet]
        [clojure
         [repl   :only [doc]]
         [pprint :only [pprint]]]))

;; Global take-while
 
;; Difficulty:	Medium
;; Topics:	seqs higher-order-functions


;; take-while is great for filtering sequences, but it limited: you
;; can only examine a single item of the sequence at a time. What if
;; you need to keep track of some state as you go over the sequence?

;; Write a function which accepts an integer n, a predicate p, and a
;; sequence. It should return a lazy sequence of items in the list up
;; to, but not including, the nth item that satisfies the predicate.

(defn adv
  [p [[f & r] _ sm]] [r f (if (p f) (inc sm) sm)])

(defn g
  [n p s] (map second
               (take-while #(< (nth % 2) n)
                           (next
                            (iterate #(adv p %) [s nil 0]))))) 

(defn g
  [n p s] (map second
               (take-while #(< (nth % 2) n)
                           (next
                            (iterate (fn [[[f & r] _ w]] [r f (if (p f) (inc w) w)])
                                     [s nil 0]))))) 

(def g
  (fn [n p s] (map second
                  (take-while #(< (nth % 2) n)
                              (next
                               (iterate (fn [[[f & r] _ w]] [r f (if (p f) (inc w) w)])
                                        [s nil 0])))))) 

(fact 
   (g 4 #(= 2 (mod % 3))
         [2 3 5 7 11 13 17 19 23]) => [2 3 5 7 11 13])
(fact 
   (g 3 #(some #{\i} %)
         ["this" "is" "a" "sentence" "i" "wrote"]) => ["this" "is" "a" "sentence"])
(fact 
   (g 1 #{"a"}
         ["this" "is" "a" "sentence" "i" "wrote"]) => ["this" "is"])
