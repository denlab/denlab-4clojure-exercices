(ns ^{:doc "Problem: http://www.4clojure.com/problem/105"}
    denlab-4clojure-exercices.problem-105
    (:use [midje.sweet]))

(fn [v] (first
          (reduce (fn [[r curr] i] (if (keyword? i)
                                    [(assoc r i []) i]
                                    [(update-in r [curr] #(conj % i)) curr]))
                  [{}] v)))
