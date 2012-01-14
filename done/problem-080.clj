(ns ^{:doc "Problem: http://www.4clojure.com/problem/80"}
    denlab-4clojure-exercices.problem-080
    (:use [midje.sweet]))

(fn [n] (= n (reduce + (filter #(zero? (rem n %)) (range 1 n)))))
