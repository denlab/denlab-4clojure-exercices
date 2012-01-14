(ns ^{:doc "Problem: http://www.4clojure.com/problem/23"}
    denlab-4clojure-exercices.problem-023
    (:use [midje.sweet]))

#(reduce (fn [r i] (cons i r))
           [] %)
