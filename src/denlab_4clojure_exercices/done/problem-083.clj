(ns ^{:doc "Problem: http://www.4clojure.com/problem/83"}
    denlab-4clojure-exercices.problem-083
    (:use [midje.sweet]))

(fn [& args] (< 1 (count (set args))))
