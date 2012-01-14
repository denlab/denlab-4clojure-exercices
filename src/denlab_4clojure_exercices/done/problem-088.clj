(ns ^{:doc "Problem: http://www.4clojure.com/problem/88"}
    denlab-4clojure-exercices.problem-088
    (:use [midje.sweet]))

#(apply (partial disj (clojure.set/union % %2)) 
          (clojure.set/intersection % %2))
