(ns ^{:doc "Problem: http://www.4clojure.com/problem/108"}
    denlab-4clojure-exercices.problem-108
    (:use [midje.sweet]))
 
;; Difficulty:	Medium
;; Topics:	seqs sorting


;; Given any number of sequences, each sorted from smallest to
;; largest, find the smallest number which appears in each
;; sequence. The sequences may be infinite, so be careful to search
;; lazily.

 
(def g
  (fn [& a] (letfn
               [(align-seq  [[a b]] [(drop-while #(< % (first b)) a)
                                     b])
                (sync-seq   [[a b]] (if (= (first a) (first b)) [a b]
                                        (sync-seq (align-seq (sort-by first [a b])))))
                (diff-first [s    ] (let [[[a & m] [b & n] & r] (partition-by first s)]
                                      (apply concat [a b] m n r)))
                (sync-seqs  [s    ] (if (apply = (map first s))
                                      (first (first s))
                                      (let [[a b] (split-at 2 (diff-first s))]
                                        (sync-seqs (concat (sync-seq a) b)))))]
             (sync-seqs a))))

(fact
 (g [3 4 5]) => 3 )
 
(fact
 (g [1 2 3 4 5 6 7] [0.5 3/2 4 19]) => 4 )

(fact
 (g (range) (range 0 100 7/6) [2 3 5 7 11 13]) => 7 )

(fact
 (g (map #(* % % %) (range)) ;; perfect cubes
          (filter #(zero? (bit-and % (dec %))) (range)) ;; powers of 2
          (iterate inc 20)) => 64 ) ;; at least as large as 20

