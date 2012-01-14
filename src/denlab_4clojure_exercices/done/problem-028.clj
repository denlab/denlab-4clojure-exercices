(ns ^{:doc "Problem: http://www.4clojure.com/problem/28"}
    denlab-4clojure-exercices.problem-028
    (:use [midje.sweet]))
(fn f [s] (if (sequential? s)
             (mapcat f s)
             [s]))
