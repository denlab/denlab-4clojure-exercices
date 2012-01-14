(ns ^{:doc "Problem: http://www.4clojure.com/problem/56"}
    denlab-4clojure-exercices.problem-056
    (:use [midje.sweet]))
(fn [s] (reduce #(if ((set %) %2) %
                      (conj % %2))
                 []
                 s))
