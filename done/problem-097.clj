(ns ^{:doc "Problem: http://www.4clojure.com/problem/97"}
    denlab-4clojure-exercices.problem-097
    (:use [midje.sweet]))
(fn [n] (letfn [(nxt [p]
                   (vec (concat [1] (map #(+ (p %) (p (inc %)))
                                         (range (dec (count p))))
                                [1])))]
           (nth (iterate nxt [1]) (dec n))))
