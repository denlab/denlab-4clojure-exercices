(ns ^{:doc "Problem: http://www.4clojure.com/problem/46"}
    denlab-4clojure-exercices.problem-046
    (:use [midje.sweet]))
(fn [f] (fn [a b] (f b a)))
