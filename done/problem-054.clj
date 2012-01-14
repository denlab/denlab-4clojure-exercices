(ns ^{:doc "Problem: http://www.4clojure.com/problem/54"}
    denlab-4clojure-exercices.problem-054
    (:use [midje.sweet]))

(fn f [n s] (if (<= n (count s))
               (cons (take n s) (f n (drop n s)))))
