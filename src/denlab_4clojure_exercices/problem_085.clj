(ns ^{:doc "Problem: http://www.4clojure.com/problem/85"}
    denlab-4clojure-exercices.problem-085
    (:use [midje.sweet]))

;; Power Set
 
;; Difficulty:	Medium
;; Topics:	set-theory


;; Write a function which generates the power setM of a given set. The
;; power set of a set x is the set of all subsets of x, including the
;; empty set and x itself.

(defn children
  [[g v]] (map (fn [x] [(disj g x) (conj v x)])
           g))

(fact
  (children [#{:a :b :c} #{}]) => [[#{:b :c} #{:a}]
                                   [#{:a :b} #{:c}]
                                   [#{:a :c} #{:b}]])

(fact
  (children [#{:b :c} #{:a}]) => [[#{:b} #{:a :c}]
                                  [#{:c} #{:a :b}]]) 



(defn f
  ([g  ] (f g #{}))
  ([g v] (if (seq g)
           (map f
                (children [g v])))))

(fact
  (f #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}})
(future-fact
  (f #{}) => #{#{}})
(future-fact
  (f #{1 2 3}) =>    #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(future-fact
  (f (into #{} (range 10))) => 1024)



