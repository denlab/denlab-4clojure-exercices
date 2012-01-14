(ns ^{:doc "Problem: http://www.4clojure.com/problem/55"}
    denlab-4clojure-exercices.problem-055
    (:use [midje.sweet]))
(fn [s] (reduce #(assoc % %2 (if-let [c (% %2)] (inc c) 1))
                 {}
                 s))
