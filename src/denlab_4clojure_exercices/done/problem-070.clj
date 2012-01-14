(ns ^{:doc "Problem: http://www.4clojure.com/problem/70"}
    denlab-4clojure-exercices.problem-070
    (:use [midje.sweet]))
(fn [s] (sort-by #(.toLowerCase %)
                  (.split (.replaceAll s "[^a-zA-Z ]" "")
                          " ")))
