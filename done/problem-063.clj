(ns ^{:doc "Problem: http://www.4clojure.com/problem/63"}
    denlab-4clojure-exercices.problem-063
    (:use [midje.sweet]))
#(reduce (fn [r i] (let [fi (% i) v (r fi)]
                      (assoc r fi (if v (conj v i) [i]))))
           {}
           %2)
