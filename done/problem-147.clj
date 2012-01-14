(ns ^{:doc "Problem: http://www.4clojure.com/problem/147"}
    denlab-4clojure-exercices.problem-147
    (:use [midje.sweet]))

(partial iterate #(vec `(~(% 0)
                           ~@(map (partial reduce +) (partition 2 1 %))
                           ~(last %))))
