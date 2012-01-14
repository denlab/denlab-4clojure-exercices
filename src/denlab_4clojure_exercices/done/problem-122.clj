(ns ^{:doc "Problem: http://www.4clojure.com/problem/122"}
    denlab-4clojure-exercices.problem-122
    (:use [midje.sweet]))

(fn [x] (read-string (str "2r" x)))
