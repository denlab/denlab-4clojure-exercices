(ns ^{:doc "Problem: http://www.4clojure.com/problem/44"}
    denlab-4clojure-exercices.problem-044
    (:use [midje.sweet]))

;; Rotate Sequence
 
;; Difficulty:	Medium
;; Topics:	seqs


;; Write a function which can rotate a sequence in either direction.

(defn f
  [n s] (let [nn (rem n (count s))]
          (if (pos? nn)
            (concat (drop nn s) (take nn s))
            (let [nnn (+ (count s) nn)]
              (concat (drop nnn s) (take nnn s))))))


(defn norm
  [n s] (let [n (rem n s) nn (if (< -1 n) n (+ s n))] nn))




(comment (fact "nominal"
   (norm 2 4) => 2
   (norm 1 4) => 1)
 (fact "overflow"
   (norm 4 4) => 0)
 (fact "negative"
   (norm -1 4) => 3))

;; if (>= n 0) => nn is n
;; else        => nn is (+ n (count s)

(def f
  #(let [c (count %2) d (if (pos? %) % (+ c (rem % c)))]
     (take c (drop d (cycle %2)))))

(fact
  (f 2 [1 2 3 4 5]) => '(3 4 5 1 2))
(fact
  (f 6 [1 2 3 4 5]) => '(2 3 4 5 1))
(fact
  (f 1 '(:a :b :c)) => '(:b :c :a))
(fact
  (f -2 [1 2 3 4 5]) => '(4 5 1 2 3))
(fact
  (f -4 '(:a :b :c)) => '(:c :a :b))
