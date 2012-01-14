(ns ^{:doc "Problem: http://www.4clojure.com/problem/58"}
    denlab-4clojure-exercices.problem-058
    (:use [midje.sweet]))

(fn [& funs] (fn [& argz] (reduce (fn [r f] (f r))
                                  (apply (first (reverse funs)) argz)
                                  (rest (reverse funs)))))
