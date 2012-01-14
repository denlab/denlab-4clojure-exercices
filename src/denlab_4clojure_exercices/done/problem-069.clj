(ns ^{:doc "Problem: http://www.4clojure.com/problem/69"}
    denlab-4clojure-exercices.problem-069
    (:use [midje.sweet]))

(fn [f & maps] (reduce #(reduce (fn [r [k v]]
                                   (if-let [v2 (r k)]
                                     (assoc r k (f v v2))
                                     (assoc r k v)))
                                 %2
                                 %)
                        maps))
