(ns ^{:doc "Problem: http://www.4clojure.com/problem/53"}
    denlab-4clojure-exercices.problem-053
    (:use [midje.sweet]))

;; Longest Increasing Sub-Seq
 
;; Difficulty:	Hard
;; Topics:	seqs


;; Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers. If two sub-sequences have the same length, use the one that occurs first. An increasing sub-sequence must have a length of 2 or greater to qualify.


(defn sseq
  [s] (if (next s)
        (conj (sseq (next s)) s)
        []))

(defn sseq
  [s] (let [c (count s)]
        (for [b (range 0 (dec c))
              e (range c (+ b 1) -1)] (subvec s b e))))

(fact "sseq"
      (sseq [1 2 3]) => [[1 2 3]
                         [1 2]
                         [2 3]])

(defn g
  [s] (let [sf (filter #(apply < %) (let [c (count s)]
                                      (for [b (range 0 (dec c))
                                            e (range c (+ b 1) -1)] (subvec s b e))))]
        (if (seq sf)
          (first (filter #(= (apply max (map count sf)) (count %)) sf))
          [])))

(def g
  (fn [s] (let [sf (filter #(apply < %) (let [c (count s)]
                                         (for [b (range 0 (dec c))
                                               e (range c (+ b 1) -1)] (subvec s b e))))]
           (if (seq sf)
             (first (filter #(= (apply max (map count sf)) (count %)) sf))
             []))))

;; 4clj ;;

(fact (g [1 0 1 2 3 0 4 5]) => [0 1 2 3])


 (fact (g [5 6 1 3 2 7]) => [5 6])
 
 (fact (g [2 3 3 4 5]) => [3 4 5])
 
 (fact (g [7 6 5 4]) => [])

(future-fact 
 )
