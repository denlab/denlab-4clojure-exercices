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

(unfinished )

(defn div-by?
  [a b] (zero? (rem a b)))

(fact
  (div-by? 4 2) => truthy
  (div-by? 4 3) => falsey
  (div-by? 3/2 3/4) =>  truthy)

(defn nxt
  [x s] (map #(if (div-by? % x) (/ % x) %) s))

(fact
  (nxt 2 [4 7 12 21 42]) => [2 7 6 21 21]
  (nxt 2 [2 7 6  21 21]) => [1 7 3 21 21]
  (nxt 3 [1 7 3  21 21]) => [1 7 1 7  7 ]
  (nxt 7 [1 7 1  7  7 ]) => [1 1 1 1  1 ])

(defn end?
  [s] (apply = (map first s)))

(fact "end?"
      (end? [[1 2] [1 3] [1 4]]) => truthy
      (end? [[1 2] [1 3] [2 4]]) => falsey)

(def f
  (fn [& a] (take-while
            (fn [[_ _ i]]
              (do #_(prn "i=" i) (some
                                  (partial not= 1) i)))
            (iterate (fn [[b n s]] (if  (and (not= 1 n) (some #(div-by? % n) s))
                                    [true  n       (nxt n s)]
                                    [false (inc n)  s]))
                     [true 1 a]))))

(def f
  (fn [& a] (iterate (fn [[b n s]] (if (and (not= 1 n) (some #(div-by? % n) s))
                                   [true  n       (nxt n s)]
                                   [false (inc n)  s]))
                    [true 1 a])))

(def f
  (fn [& a] (take-while
            (fn [[_ _ i]] (some (partial not= 1) i))
            (iterate (fn [[b n s]] (if  (and (not= 1 n) (some #(div-by? % n) s))
                                    [true  n       (nxt n s)]
                                    [false (inc n)  s]))
                     [false 2 a]))))

(def f
  (fn [& a] (take 2 (partition-by (fn [[_ _ i]] (some (partial not= 1) i))
                                 (iterate (fn [[b n s]] (if (some #(div-by? % n) s)
                                                         [true  n       (nxt n s)]
                                                         [false (inc n)  s]))
                                          [false 2 a])))))
(def f
  (fn [& a] (iterate (fn [[b n s]] (if (some #(div-by? % n) s)
                                   [true  n       (nxt n s)]
                                   [false (inc n)  s]))
                    [false 2 a])))

(def f
  (fn [& a] 
    (reduce (fn [r [b n s]] (if (= :take b) (* r n) r))
            1
            (take-while #(not= :end (first %))
                        (iterate (fn [[_ n s]] (cond (every? #(= 1 %)       s) [:end  n       (nxt n s)]
                                                    (some   #(div-by? % n) s) [:take  n       (nxt n s)]
                                                    :else                     [:drop (inc n)  s]))
                                 [:drop 2 a])))))

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
  [s] (map first (take 2 (partition-by first s))))

(defn pick-2-diff
  [s] (let [[[a & m] [b & n] & r] (partition-by first s)]
        (apply concat [a b] m n r)))

[[1 2] [1 4] [2 3] [3 4]]


(fact
 (pick-2-diff [[1 2]
               [1 4]
               [2 3]
               [2 4]
               [3 4]
               [5 5]]) => [[1 2] [2 3]
                           [1 4] [2 4] [3 4] [5 5]])

(defn f
  [& a] (do (prn "a = ") (pprint a) 
            (cond (apply = (map first a)) (do (prn "->1") (first (first a)))
                  (= 2 (count a))         (do (prn "->2") (appareil-seq (first a) (second a)))
                  (= 1 (count a))         (do (prn "->3") a)
                  :else                   (do (prn "->4") (concat (f (take 2 a)) (f (drop 2 a)))))))

(defn extract-res
  [s] (first (first s)))

(fact
 (extract-res [[1 2] [1 3]]) => 1)

(defn g
  [s] (do #_(prn)
          #_(prn "s=" s)
          (if (end? s)
            (do #_(prn "->1 foudn !!!") (extract-res s))
            (do #_(prn "->2 ")
                (let [[a b] (split-at 2 (pick-2-diff s))]
                  #_(prn "a=" a "b=" b)
                  (g (concat (appareil-seq a) b)))))))

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
  [s] (map #(map (partial * %) 
                 (map inc (range)))
           s))

(fact
  (take 3 (first  (tomat [1 2]))) => [1 2 3]
  (take 3 (second (tomat [1 2]))) => [2 4 6] )

(defn f
[& a])

(future-fact
   (f 2 3) => 6)
(future-fact
   (f 5 3 7) => 105)
(future-fact
   (f 1/3 2/5) => 2)
(future-fact
   (f 3/4 1/6) => 3/2)
(future-fact
   (f 7 5/7 2 3/5) => 210)
