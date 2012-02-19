(ns ^{:doc "Problem: http://www.4clojure.com/problem/150"}
    denlab-4clojure-exercices.problem-150
    (:use [midje.sweet]))

;; Palindromic Numbers
 
;; Difficulty:	Medium
;; Topics:	seqs math


;; A palindromic number is a number that is the same when written
;; forwards or backwards (e.g., 3, 99, 14341).

;; Write a function which takes an integer n, as its only argument,
;; and returns an increasing lazy sequence of all palindromic numbers
;; that are not less than n.

;; The most simple solution will exceed the time limit!

(defn g
  [n])

(fact
  (take 26 (g 0)) => 
  [0 1 2 3 4 5 6 7 8 9
   11 22 33 44 55 66 77 88 99
   101 111 121 131 141 151 161])
(fact
  (take 16 (g 162)) => 
  [171 181 191 202
   212 222 232 242
   252 262 272 282
   292 303 313 323])
(fact
  (take 6 (g 1234550000)) => 
  [1234554321 1234664321 1234774321
   1234884321 1234994321 1235005321])
(fact
  (first (g (* 111111111 111111111))) => 
  (* 111111111 111111111))
(fact (set (take 199 (g 0))) => 
  (set (map #(first (g %)) (range 0 10000))))
(fact
  (apply < (take 6666 (g 9999999))) => true)
(fact
  (nth (g 0) 10101) =>  
  9102019)

