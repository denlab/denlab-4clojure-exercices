(ns ^{:doc "Problem: http://www.4clojure.com/problem/61"}
    denlab-4clojure-exercices.problem-061
    (:use [midje.sweet]))

#(apply hash-map (interleave % %2))
