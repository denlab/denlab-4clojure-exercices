(ns ^{:doc "Problem: http://www.4clojure.com/problem/103"}
    denlab-4clojure-exercices.problem-103
    (:use [midje.sweet]))

;; Generating k-combinations
 
;; Difficulty:	Medium
;; Topics:	seqs combinatorics


;; Given a sequence S consisting of n elements generate all
;; k-combinationsM of S, i. e. generate all possible sets consisting
;; of k distinct elements taken from S. The number of k-combinations
;; for a sequence is equal to the binomial coefficientN.

(defn combin
  [n s] (set (map #(conj % n) s)))

(fact
 (combin :a #{#{:b} #{:c}}) => #{#{:a :b} #{:a :c}})

(def g
  (fn f [n s] (set
              (cond (= 1 n) (map hash-set s)
                    :else   (into (combin (first s)
                                          (f (dec n) (rest s)))
                                  (f n (rest s)))))))

(def g
  (fn f [n s] (set
              (cond
               (= 1 n)         (map hash-set s)
               (< (count s) n) #{}
               :else           (into (set (map #(conj % (first s)) (f (dec n) (rest s))))
                                     (f n (rest s)))))))

(fact
 (g 2 #{:a :b :c}) => #{#{:a :b} #{:a :c} #{:c :b}}) 

(fact
 (g 1 #{:b :c}) => #{#{:b} #{:c}})

(fact
 (g 2 #{:a :b}) => #{#{:a :b}}) 

(fact
 (g 1 #{:a}) => #{#{:a}}) 

(fact
 (g 1 #{4 5 6}) => #{#{4} #{5} #{6}})

(fact
 (g 10 #{4 5 6}) => #{})

(fact
 (g 2 #{0 1 2}) => #{#{0 1} #{0 2} #{1 2}})

(fact
 (g 3 #{0 1 2 3 4}) => #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                         #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})

(fact
 (g 4 #{[1 2 3] :a "abc" "efg"}) => #{#{[1 2 3] :a "abc" "efg"}})

(fact
 (g 2 #{[1 2 3] :a "abc" "efg"}) => #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                       #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})
