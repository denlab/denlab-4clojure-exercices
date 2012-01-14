(ns ^{:doc "Problem: http://www.4clojure.com/problem/120"}
    denlab-4clojure-exercices.problem-120
    (:use [midje.sweet]))

(fn [s] (count
          (filter (fn [[n nn]] (< n nn))
                  (map (fn [x] [x
                               (reduce #(+ % (let [i (Integer/valueOf (str %2))] (* i i)))
                                       0 (str x))])
                       s))))
