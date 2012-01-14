(ns ^{:doc "Problem: http://www.4clojure.com/problem/95"}
    denlab-4clojure-exercices.problem-095
    (:use [midje.sweet]))

(fn f [s] (or (and (sequential? s) (= 3 (count s)) (f (nth s 1)) (f (nth s 2)))
               (nil? s)))
