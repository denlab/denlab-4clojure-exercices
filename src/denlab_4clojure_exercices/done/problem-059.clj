(ns ^{:doc "Problem: http://www.4clojure.com/problem/59"}
    denlab-4clojure-exercices.problem-059
    (:use [midje.sweet]))

 
;; Difficulty:	Medium
;; Topics:	higher-order-functions core-functions


;; Take a set of functions and return a new function that takes a variable number of arguments and returns a sequence containing the result of applying each function left-to-right to the argument list.


(def f
  (fn [& fns] (fn [& args]
               (map #(apply % args) fns))))

(fact
  ((f + max min) 2 3 5 1 6 4) => [21 6 1])

(fact
  ((f #(.toUpperCase %) count) "hello") => ["HELLO" 5])

(fact
  ((f :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}) => [2 6 4])
