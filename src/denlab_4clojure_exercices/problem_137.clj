(ns ^{:doc "Problem: http://www.4clojure.com/problem/137"}
    denlab-4clojure-exercices.problem-137
    (:use [midje.sweet]))

;; Digits and bases
 
;; Difficulty:	Medium
;; Topics:	math


;; Write a function which returns a sequence of digits of a
;; non-negative number (first argument) in numerical system with an
;; arbitrary base (second argument). Digits should be represented with
;; their integer values, e.g. 15 would be [1 5] in base 10, [1 1 1 1]
;; in base 2 and [15] in base 16.


(defn g
  [n b] (if (zero? n)
          [0]
          (second
           (first
            (drop-while (fn [[x _]] (not= 0 x))
                        (iterate (fn [[x r]] [(quot x b) (conj r (rem x b))])
                                 [n '()])))))) 

(defn p
  [[_ x]] (prn "x=" x) (not= 0 x))

(defn p
  [x] (not (zero? (second x))))

(def p (comp not zero? second))

(fact
  (p [:a 1]) => truthy)

(fact
  (p [:a 0]) => falsey) 

(defn g
  [n b] (if (zero? n) [0]
            (ffirst
             (drop-while (fn [[_ x]] (not= 0 x))
                         (iterate (fn [[r x]] [(conj r (rem x b)) (quot x b)])
                                  ['() n]))))) 
(defn g
  [n b] (if (zero? n) [0]
            (ffirst
             (drop-while (comp not zero? second)
                         (iterate (fn [[r x]] [(conj r (rem x b)) (quot x b)])
                                  ['() n]))))) 
(fact
  (g 9 2) =>  [1 0 0 1] )
(fact
  (g 1234501 10) =>  [1 2 3 4 5 0 1] )
(fact
  (g 0 11) =>  [0] )
(fact
  (let [n (rand-int 100000)](g n n)) =>  [1 0] )
(fact
  (g Integer/MAX_VALUE 42) =>  [16 18 5 24 15 1] )


