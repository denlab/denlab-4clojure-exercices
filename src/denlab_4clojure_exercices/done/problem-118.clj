(ns ^{:doc "Problem: http://www.4clojure.com/problem/118"}
    denlab-4clojure-exercices.problem-118
    (:use [midje.sweet]))

(fn foo [f s & frst] (if (seq frst)
                        (lazy-seq (cons (first frst) (if s (foo f (next s) (f (first s))))))
                        (foo f (next s) (f (first s)))))
