(ns ^{:doc "Problem: http://www.4clojure.com/problem/107"}
    denlab-4clojure-exercices.problem-107
    (:use [midje.sweet]))

(fn [n] (fn [x] (Math/pow x n)))
