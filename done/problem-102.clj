(ns ^{:doc "Problem: http://www.4clojure.com/problem/102"}
    denlab-4clojure-exercices.problem-102
    (:use [midje.sweet]))

(fn [s] (reduce #(.replaceAll % (str "-" (char %2))
                               (.toUpperCase (str (char %2))))
                 s
                 (range 97 123)))
