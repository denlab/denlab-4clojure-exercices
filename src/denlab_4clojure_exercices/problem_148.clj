(ns ^{:doc "Problem: http://www.4clojure.com/problem/148"}
    denlab-4clojure-exercices.problem-148
    (:use [midje.sweet]))

;; The Big Divide
 
;; Difficulty:	Medium
;; Topics:	math


;; Write a function which calculates the sum of all natural numbers
;; under n (first argument) which are evenly divisible by at least one
;; of a and b (second and third argument). Numbers a and b are
;; guaranteed to be coprimesM.

;; Note: Some test cases have a very large n, so the most obvious
;; solution will exceed the time limit.

(defn g
  [n a b] (reduce + (concat (take-while #(< % n) (iterate #(+ a %) a))
                            (take-while #(< % n) (iterate #(+ b %) b)))))

(fact
  (g 10 3 5)                               =>  23 )
(fact
  (g 3 17 11)                              =>  0 )
(fact
  (g 1000 3 5)                             =>  233168 )
(future-fact
  (str (g 100000000 3 5))                  =>  "2333333316666668" )
(future-fact
  (str (g (* 10000 10000 10000) 7 11))     =>  "110389610389889610389610")
(future-fact
  (str (g (* 10000 10000 10000) 757 809))  =>  "1277732511922987429116")
(future-fact
  (str (g (* 10000 10000 1000) 1597 3571)) =>  "4530161696788274281")
