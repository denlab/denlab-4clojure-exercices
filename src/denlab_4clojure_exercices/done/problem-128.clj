(ns ^{:doc "Problem: http://www.4clojure.com/problem/128"}
    denlab-4clojure-exercices.problem-128
    (:use [midje.sweet]))

(fn [[s r]] {:suit ({\S :spade, \H :heart, \D :diamond, \C :club} s)
              :rank ((zipmap (concat (map char (range 50 58)) [\T \J \Q \K \A])
                             (range 13)) r)})
