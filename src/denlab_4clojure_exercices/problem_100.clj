(ns ^{:doc "Problem: http://www.4clojure.com/problem/100"}
  denlab-4clojure-exercices.problem-100
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]])
  (:use [clojure.repl  :only [doc]]))

 
;; Difficulty:	Easy
;; Topics:	math


;; Write a function which calculates the least common multiple. Your
;; function should accept a variable number of positive integers or
;; ratios.

(defn end?
  [s] (apply = (map first s)))

(fact "end?"
      (end? [[1 2] [1 3] [1 4]]) => truthy
      (end? [[1 2] [1 3] [2 4]]) => falsey)

(defn sort-by-first
  [s] (sort-by first s))

(fact
  (sort-by-first [[2 3] [1 2]]) => [[1 2] [2 3]])

(defn align-seq
  [[a b]] [(drop-while #(< % (first b)) a)
         b])

(fact
  (align-seq  [[7 9 11 13] [10 20]]) => [[11 13] [10 20]]
  (align-seq  [[8 10 12  ] [10 20]]) => [[10 12] [10 20]]
  (align-seq  [[10 20]     [11 13]]) => [[20   ] [11 13]])

(defn appareil-seq
  [[a b]] (if (= (first a) (first b))
          [a b]
          (appareil-seq (align-seq (sort-by-first [a b])))))

(fact
  (appareil-seq [[4 8 12 16 20]
                 [3 6 9  12 15]]) =>
  (in-any-order [[12 16 20]
                 [12 15   ]]))

(defn pick-2-diff
  [s] (let [[[a & m] [b & n] & r] (partition-by first s)]
        (apply concat [a b] m n r)))

(fact
 (pick-2-diff [[1 2]
               [1 4]
               [2 3]
               [2 4]
               [3 4]
               [5 5]]) => [[1 2] [2 3]
                           [1 4] [2 4] [3 4] [5 5]])

(defn extract-res
  [s] (first (first s)))

(fact
 (extract-res [[1 2] [1 3]]) => 1)

(defn g
  [s] (if (end? s)
        (extract-res s)
        (let [[a b] (split-at 2 (pick-2-diff s))]
          (g (concat (appareil-seq a) b)))))

(fact 
 (g [:s1 :s2 :s3]) => :res
 (provided
  (end? [:s1 :s2 :s3])        => false
  (pick-2-diff [:s1 :s2 :s3]) => [:s1 :s3 :s2]
  (appareil-seq [:s1 :s3])    => [:s1 :s4]
  (end? [:s1 :s4 :s2])        => true
  (extract-res [:s1 :s4 :s2]) => :res))

(fact
 (g [[1 2] [1 3]]) => 1
 (provided
  (end? [[1 2] [1 3]]) => true))

(fact
 (g [(map #(* 2 (inc %)) (range 6))
     (map #(* 3 (inc %)) (range 5))
     (map #(* 4 (inc %)) (range 5))]) => 12)

(fact
 (g [(map #(* 5 (inc %)) (range))
     (map #(* 3 (inc %)) (range))
     (map #(* 7 (inc %)) (range))]) => 105)

(defn tomat
  [a] (map #(map (partial * %) 
                 (map inc (range)))
           a))

(fact
  (take 3 (first  (tomat [1 2]))) => [1 2 3]
  (take 3 (second (tomat [1 2]))) => [2 4 6] )

(defn f
  [& a] (g (tomat a)))

(defn f
  [& a] (g (map #(map (partial * %) 
                      (map inc (range)))
                a)))

(fact
   (f 2 3) => 6)
(fact
   (f 5 3 7) => 105)
(fact
   (f 1/3 2/5) => 2)
(fact
   (f 3/4 1/6) => 3/2)
(fact
   (f 7 5/7 2 3/5) => 210)
