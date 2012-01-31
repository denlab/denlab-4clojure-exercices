(ns ^{:doc "Problem: http://www.4clojure.com/problem/93"}
    denlab-4clojure-exercices.problem-093
    (:use [midje.sweet]))

;; Partially Flatten a Sequence
 
;; Difficulty:	Medium
;; Topics:	seqs

;; Write a function which flattens any nested combination of
;; sequential things (lists, vectors, etc.), but maintains the lowest
;; level sequential items. The result should be a sequence of
;; sequences with only one level of nesting.

(def g
  (fn [x] (if (sequential? (first x))
           (concat (g (first x)) (g (rest x)))
           [x])))

(def g
  (fn [x] (cond (sequential? (first x)) (concat (g (first x)) (g (rest x)))
               :else                   [x])))

(def g
  (fn [x] (cond (sequential? (first x)) (mapcat #(g %) x)
               :else                   [x])))

(def g
  (fn [[f & _ :as s]] (cond (sequential? f) (mapcat g s)
                           :else                   [s])))

(def g
  #(cond (sequential? (first %)) (mapcat g %)
         :else                   [%]))

(def g
  (fn f [s] (cond (sequential? (first s)) (mapcat f s)
                 :else                   [s])))

(def g
  (fn f [s] (if (sequential? (first s)) (mapcat f s)
                                [s])))

(fact
 (g [["Do"] ["Nothing"]]) => [["Do"] ["Nothing"]])

(fact
 (g [[[[:a :b]]] [[:c :d]] [:e :f]]) => [[:a :b] [:c :d] [:e :f]])

(fact
 (g '((1 2)((3 4)((((5 6))))))) => '((1 2)(3 4)(5 6)))
