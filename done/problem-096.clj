(ns ^{:doc "Problem: http://www.4clojure.com/problem/96"}
    denlab-4clojure-exercices.problem-096
    (:use [midje.sweet]))
(fn [s] (letfn [(rev-tree [t]
                   (if (sequential? t)
                     (cons (first t) (reduce #(conj % (rev-tree %2))
                                             []
                                             (reverse (rest t))))
                     t))]
           (= s (rev-tree s))))
