(ns ^{:doc "Problem: http://www.4clojure.com/problem/116"}
    denlab-4clojure-exercices.problem-116
    (:use [midje.sweet]))

;; Prime Sandwich
 
;; Difficulty:	Medium
;; Topics:	math


;; A balanced prime is a prime number which is also the mean of the
;; primes directly before and after it in the sequence of valid
;; primes. Create a function which takes an integer n, and returns
;; true if it is a balanced prime.

(unfinished)

(defn primes "the nth first primes"
  [n] (loop [curr 2 acc []]
        (if (= n (count acc))
          acc
          (if (some #(zero? (rem curr %)) acc)
            (recur (inc curr) acc)
            (recur (inc curr) (conj acc curr))))))

(fact
 (primes 3) => [2 3 5])

;; TODO : here we could use a list instead of a vector for acc, it
;; will allow to conj at the head of the list, and allow more concise destructuring
(defn p-ival "Given an int, if n is not prime, return false, otherwise return [previous-prime next-prime]"
  [n] (loop [curr 2 acc []]
        (if (and (last acc) (< n (last acc))) 
          (if (and (= n (last (butlast acc))) (not= 2 n))
            [(last (butlast (butlast acc))) (last acc)]
            nil)
          (if (some #(zero? (rem curr %)) acc)
            (recur (inc curr) acc)
            (recur (inc curr) (conj acc curr))))))

(fact "p-ival: nominal"
      (p-ival 3) => [2 5]
      (p-ival 5) => [3 7])

(fact "p-ival: not a prime"
      (p-ival 4) => nil)

(fact "p-ival: edge case of 2"
 (p-ival 2) => nil) 

(defn g
  [n] (let [iv (p-ival n)]
        (if iv
          (let [[b a] iv]
            (= n (/ (+ b a) 2))))))

(fact "g: balanced prime"
      (g 3) => truthy
      (provided
       (p-ival 3) => [1 5]))

(fact "g: prime but not balanced"
      (g 3) => falsey
      (provided
       (p-ival 3) => [1 4]))

(fact "g: not a prime"
      (g :n) => falsey
      (provided
       (p-ival :n) => nil))

(fact
 (g 4) => falsey)

(future-fact
 (g 2) => falsey)

(fact
 (g 563) => truthy)

(future-fact
 (nth (filter g (range)) 15) => 1103)
