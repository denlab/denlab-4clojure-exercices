(ns ^{:doc "Problem: http://www.4clojure.com/problem/22"}
    denlab-4clojure-exercices.problem-022
    (:use [midje.sweet]))
#(reduce + (map (fn [x] 1) %))
