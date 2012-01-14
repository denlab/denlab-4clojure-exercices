(ns ^{:doc "Problem: http://www.4clojure.com/problem/38"}
    denlab-4clojure-exercices.problem-038
    (:use [midje.sweet]))

(fn [ & lst]
  (reduce (fn [r i] (if (> i r) i r))
          -1
          lst))
