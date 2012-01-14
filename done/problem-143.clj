(ns ^{:doc "Problem: http://www.4clojure.com/problem/143"}
    denlab-4clojure-exercices.problem-143
    (:use [midje.sweet]))

(fn [s1 s2] (reduce + (map #(* % %2)
                            s1 s2)))
