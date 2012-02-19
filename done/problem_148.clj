(ns ^{:doc "Problem: http://www.4clojure.com/problem/148"}
    denlab-4clojure-exercices.problem-148
    (:use [clojure.pprint :only [pprint]] [midje.sweet]))

;; The Big Divide
 
;; Difficulty:	Medium
;; Topics:	math


;; Write a function which calculates the sum of all natural numbers
;; under n (first argument) which are evenly divisible by at least one
;; of a and b (second and third argument). Numbers a and b are
;; guaranteed to be coprimesM.

;; Note: Some test cases have a very large n, so the most obvious
;; solution will exceed the time limit.

(defn ss
  [n a] (let [m (quot (dec n) a)]
          (/ (*' m (inc m)  a) 2)))

(fact
  (ss 10 3) =>  18 )

(fact
  (ss 10 2) => 20)

(comment (defn g
   [n a b] (+ (s n a) (s n b) (- (s n (* a b))))))

(def g
  (let [s #(let [m (quot (dec %) %2)]
             (/ (*' m (inc m) %2) 2))]
    #(+ (s % %2) (s % %3) (- (s % (* %2 %3))))))

(fact
  (g 10 3 5)                               =>  23 )
(fact
  (g 16 3 5)                               =>  60)
(fact
  (g 3 17 11)                              =>  0 )
(fact
  (g 1000 3 5)                             =>  233168 )
(fact
  (str (g 100000000 3 5))                  =>  "2333333316666668" )
(fact
  (str (g (* 10000 10000 10000) 7 11))     =>  "110389610389889610389610")
(fact
  (str (g (* 10000 10000 10000) 757 809))  =>  "1277732511922987429116")
(fact
  (str (g (* 10000 10000 1000) 1597 3571)) =>  "4530161696788274281")

(prn)
