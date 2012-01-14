(ns ^{:doc "Problem: http://www.4clojure.com/problem/62"}
    denlab-4clojure-exercices.problem-062
    (:use [midje.sweet]))
(fn foo [f v] (lazy-seq (cons v (foo f (f v)))))
