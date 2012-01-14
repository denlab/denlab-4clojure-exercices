(ns ^{:doc "Problem: http://www.4clojure.com/problem/41"}
    denlab-4clojure-exercices.problem-041
    (:use [midje.sweet]))
#(mapcat (fn [x i] (if-not (zero? (rem (inc i) %2)) [x]))
           % (range))
