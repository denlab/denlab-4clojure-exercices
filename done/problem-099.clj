(ns ^{:doc "Problem: http://www.4clojure.com/problem/99"}
    denlab-4clojure-exercices.problem-099
    (:use [midje.sweet]))

(fn [a b] (map #(Integer/valueOf (str %)) (str (* a b))))
