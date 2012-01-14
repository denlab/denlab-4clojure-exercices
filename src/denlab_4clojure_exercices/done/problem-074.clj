(ns ^{:doc "Problem: http://www.4clojure.com/problem/74"}
    denlab-4clojure-exercices.problem-074
    (:use [midje.sweet]))
#(reduce str
  (interpose \,
             (filter (fn [n] (= (Math/sqrt n) (int (Math/sqrt n))))
                     (read-string
                      (str "[" (.replace % \, \space) "]")))))
