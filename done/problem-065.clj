(ns ^{:doc "Problem: http://www.4clojure.com/problem/65"}
    denlab-4clojure-exercices.problem-065
    (:use [midje.sweet]))

#(cond (= :y (:x (conj % [:x :y]))) :map
         (= (conj % 1) (conj % 1 1))  :set
         (= 2 (last (conj % 1 2)))    :vector
         :else                        :list)
