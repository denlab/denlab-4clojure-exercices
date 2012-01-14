(ns ^{:doc "Problem: http://www.4clojure.com/problem/67"}
    denlab-4clojure-exercices.problem-067
    (:use [midje.sweet]))
#(loop [p [] c 2]
     (if (<= % (count p)) p
         (recur (concat p (if (some (comp zero? (partial rem c)) p) [] [c]))
                (inc c))))
