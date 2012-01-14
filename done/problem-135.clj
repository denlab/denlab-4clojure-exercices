(ns ^{:doc "Problem: http://www.4clojure.com/problem/135"}
    denlab-4clojure-exercices.problem-135
    (:use [midje.sweet]))

(fn [& args] (reduce (fn [r [op b]] (op r b))
                      (first args)
                      (partition-all 2 (rest args))))
