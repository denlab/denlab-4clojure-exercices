(ns ^{:doc "Problem: http://www.4clojure.com/problem/40"}
    denlab-4clojure-exercices.problem-040
    (:use [midje.sweet]))
#(rest (mapcat (fn [x] [% x]) %2))
