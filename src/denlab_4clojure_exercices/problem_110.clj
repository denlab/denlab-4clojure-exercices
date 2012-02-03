(ns ^{:doc "Problem: http://www.4clojure.com/problem/110"}
    denlab-4clojure-exercices.problem-110
    (:use [midje.sweet]))

;; Sequence of pronunciations
 
;; Difficulty:	Medium
;; Topics:	seqs


;; Write a function that returns a lazy sequence of "pronunciations"
;; of a sequence of numbers. A pronunciation of each element in the
;; sequence consists of the number of repeating identical numbers and
;; the number itself. For example, [1 1] is pronounced as [2 1] ("two
;; ones"), which in turn is pronounced as [1 2 1 1] ("one two, one
;; one").

;; Your function should accept an initial sequence of numbers, and
;; return an infinite lazy sequence of pronunciations, each element
;; being a pronunciation of the previous element.

(unfinished )

(defn mv
  [s] (reduce (fn [r i] (do (prn "r=" r "i=" i)
                         (if (= i (second (last r)))
                          (update-in r [(dec (count r)) 0] inc)
                          (conj r [1 i]))))
       []
       s))

(defn mv
  [s] (reduce (fn [r i] (if (= i (last r))
                         (update-in r [(- (count r) 2)] inc)
                         (conj r 1 i)))
              [] 
              s))

(fact "mv"
      (mv [1 1] ) => [2 1])

(fact "mv"
      (mv [2 1]) => [1 2 1 1])

(def g
  (fn [x] (rest (iterate mv x))))

(def g
  (fn [x] (rest (iterate (fn [s] (reduce (fn [r i] (if (= i (last r))
                                                  (update-in r [(- (count r) 2)] inc)
                                                  (conj r 1 i)))
                                       [] 
                                       s))
                        x))))

(def g
  (fn [x] (rest (iterate (fn [s] (reduce #(if (= %2 (last %))
                                          (update-in % [(- (count %) 2)] inc)
                                          (conj % 1 %2))
                                       [] 
                                       s))
                        x))))

(future-fact
 (take 3 (g :a)) => [:b :c :d]
 (provided
  (mv :a) => :b
  (mv :b) => :c
  (mv :c) => :d))
 
(fact
 (take 3 (g [1]))           => [[1 1] [2 1] [1 2 1 1]])
(fact
 (first (g [1 1 1 4 4]))    => [3 1 2 4])
(fact
 (nth (g [1]) 6)            => [1 1 1 3 2 1 3 2 1 1])
(fact
 (count (nth (g [3 2]) 15)) => 338)

