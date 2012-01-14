(ns ^{:doc "Problem: http://www.4clojure.com/problem/66"}
    denlab-4clojure-exercices.problem-066
    (:use [midje.sweet]))
#(loop [curr %1] (if (= 0 (rem %1 curr) (rem %2 curr))
                     curr
                     (recur (dec curr))))
