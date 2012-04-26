(ns ^{:doc "Problem: http://www.4clojure.com/problem/112"}
    denlab-4clojure-exercices.problem-112
    (:use [midje.sweet])
    (:use [clojure.repl :only [doc dir dir-fn]])
    (:use [clojure.pprint :only [pprint print-table]]))

;; Sequs Horribilis
 
;; Difficulty:	Medium
;; Topics:	seqs

;; Create a function which takes an integer and a nested collection of
;; integers as arguments. Analyze the elements of the input collection
;; and return a sequence which maintains the nested structure, and
;; which includes all elements starting from the head whose sum is
;; less than or equal to the input integer.

(defn g
  [n s] (let [f (first s)]
          (if (pos? n)
            (if (number? f)
              (if (neg? (- n f))
                []
                (cons f (g (- n f) (rest s))))
              [(g n (first s))])
            [])))

(defn g
  [n s] (let [f (first s)]
          (cond (nil? f )    []
                (number? f) (let [m (- n f)]
                               (if (>= m 0)
                                 (cons f (g m (rest s)))
                                 []))
                :else [(g n (first s))])))

(defn g
  [n [f & _ :as s]] (cond (nil? f )    []
                          (number? f) (let [m (- n f)]
                                        (if (>= m 0)
                                          (cons f (g m (rest s)))
                                          []))
                          :else [(g n (first s))]))

(def g
  (fn [n [f & _ :as s]] (cond (nil? f )    []
                             (number? f) (let [m (- n f)]
                                           (if (>= m 0)
                                             (cons f (g m (rest s)))
                                             []))
                             :else [(g n (first s))])))

(fact
  (g 10 [1 2 3 4 5 6 7]) => [1 2 3 4])

(fact  (g 10 [1 2 [3 [4 5] 6] 7]) => 
   '(1 2 (3 (4))))

(fact  (g 1 [[[[[1]]]]]) => 
       '(((((1))))))


(fact  (g 0 [1 2 [3 [4 5] 6] 7]) => 
   '())

(fact  (g 9 (range)) => 
   '(0 1 2 3))

 (fact  (g 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11]) => 
   '(1 2 (3 (4 (5 (6 (7)))))))

(fact  (g 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]]) => 
   '(-10 (1 (2 3 (4)))))

(fact  (g 0 [0 0 [0 [0]]]) => 
   '(0 0 (0 (0))))


;; 4clj tests
(future-fact







)


