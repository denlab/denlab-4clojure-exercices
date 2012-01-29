(ns ^{:doc "Problem: http://www.4clojure.com/problem/85"}
    denlab-4clojure-exercices.problem-085
    (:use [midje.sweet])
    (:use [clojure.pprint :only [pprint]]))

;; Power Set
 
;; Difficulty:	Medium
;; Topics:	set-theory

;; Write a function which generates the power setM of a given set. The
;; power set of a set x is the set of all subsets of x, including the
;; empty set and x itself.

(def p
  (comp set
        (fn p [[f & r]] (if f (concat (p r) [#{f}] (map #(conj % f) (p r)))
                           [#{}]))
        seq))

(fact
  (p #{:a :b :c}) => #{#{}
                       #{:a} #{:b} #{:c}
                       #{:a :b}  #{:a :c}  #{:b :c}
                       #{:a :b :c}})

(fact
  (p #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}})
(fact
  (p #{}) => #{#{}})
(fact
  (p #{1 2 3}) =>    #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(fact
  (count (p (into #{} (range 10)))) => 1024)
