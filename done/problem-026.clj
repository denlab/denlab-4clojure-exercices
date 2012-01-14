(ns ^{:doc "Problem: http://www.4clojure.com/problem/26"}
    denlab-4clojure-exercices.problem-026
    (:use [midje.sweet]))
#(take % (map (fn [[a b]] b)
              (iterate (fn [[a b]] [b (+ a b)])
                       [0 1])))
