(ns ^{:doc "Problem: http://www.4clojure.com/problem/34"}
    denlab-4clojure-exercices.problem-034
    (:use [midje.sweet]))
(fn [s e] (take-while #(< % e) (iterate inc s)))
