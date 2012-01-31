(ns ^{:doc "Problem: http://www.4clojure.com/problem/98"}
    denlab-4clojure-exercices.problem-098
    (:use [midje.sweet])
        (:use [clojure.repl :only [doc]]))

;; Equivalence Classes
 
;; Difficulty:	Medium
;; Topics:	

;; A function f defined on a domain D induces an equivalence relationM
;; on D, as follows: a is equivalent to b with respect to f if and
;; only if (f a) is equal to (f b). Write a function with arguments f
;; and D that computes the equivalence classesN of D with respect to
;; f.

(def e
  (fn [f s] (set (map set (partition-by f s)))))

(fact
  (e #(* % %)          #{-2 -1 0 1 2})  => #{#{0} #{1 -1} #{2 -2}})
(fact
  (e #(rem % 3)        #{0 1 2 3 4 5 }) => #{#{0 3} #{1 4} #{2 5}})
(fact
  (e identity          #{0 1 2 3 4})    => #{#{0} #{1} #{2} #{3} #{4}})
(fact
  (e (constantly true) #{0 1 2 3 4})    => #{#{0 1 2 3 4}})
