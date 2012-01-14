(ns ^{:doc "Problem: http://www.4clojure.com/problem/75"}
    denlab-4clojure-exercices.problem-075
    (:use [midje.sweet]))
(fn [x] (letfn [(coprime? [a b] (every? #(not= 0 (rem a %) (rem b %))
                                         (range a 1 -1)))]
           (count (filter #(coprime? x %)
                          (range x)))))
