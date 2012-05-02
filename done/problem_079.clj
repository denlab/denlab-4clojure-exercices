(ns ^{:doc "Problem: http://www.4clojure.com/problem/79"}
    denlab-4clojure-exercices.problem-079
    (:use [midje.sweet])
    (:use [clojure.repl :only [doc dir]])
            (:use [clojure.pprint :only [pprint]]))

;; Triangle Minimal Path
 
;; Difficulty:	Hard
;; Topics:	graph-theory


;; Write a function which calculates the sum of the minimal path
;; through a triangle. The triangle is represented as a collection of
;; vectors. The path should start at the top of the triangle and move
;; to an adjacent number on the next row until the bottom of the
;; triangle is reached.

(defn children2
  [[t y x tot depth]] (if (<= (dec (count t)) y)
                        []
                        (let [v (vec t), y+ (inc y), x+ (inc x)]
                          [[v y+ x  (+ tot (get-in v [y+ x ])) (inc depth)]
                           [v y+ x+ (+ tot (get-in v [y+ x+])) (inc depth)]])))

(defn children2
  [[t y x tot depth]] (if (<= (dec (count t)) y) []
                          (map #(vector (vec t) (inc y) % (+ tot (get-in (vec t) [(inc y) %])) (inc depth))
                               [x (inc x)])))

(fact
  (let [t [[   1  ]   ;; 0
           [  2 3 ]   ;; 1
           [ 4 5 6]   ;; 2
           [7 8 9 0]]] ;; 3
      
    (children2 [t 2 0 4 0]) => [[t 3 0 11 1] [t 3 1 12 1]]
    (children2 [t 2 1 5 0]) => [[t 3 1 13 1] [t 3 2 14 1]]
    (children2 [t 2 2 6 0]) => [[t 3 2 15 1] [t 3 3 6  1]]
    (children2 [t 3 1 8 0]) => []))

(defn end2?
  [[t y x tot depth :as v]] 
  (= (v 4) (count (v 0))))

(fact "end2?"
  (let [t [[   1  ]    ;; 0
          [  2 3 ]    ;; 1
          [ 4 5 6]    ;; 2
          [7 8 9 0]]] ;; 3
      
   (end2? [t 2 2 6 0]) => falsey
   (end2? [t 3 1 8 4]) => truthy))

(defn value2
  [s] (nth s 3))

(defn g
  [t] (loop [todo [[t 0 0 (get-in (vec t) [0 0]) 1]] done []]
        (if (seq todo)
          (let [f (first todo)]
            (if (end2? f)
              (recur (rest todo) (conj done f))
              (recur (concat (rest todo) (children2 f)) done)))
          (apply min (map value2 done)))))

(defn g
  [t] (loop [todo [[t 0 0 (get-in (vec t) [0 0]) 1]] done []]
        (let [f (first todo)]
          (if (seq todo)
            (if (end2? f)
              (recur (rest todo) (conj done f))
              (recur (concat (rest todo) (children2 f)) done))
            (apply min (map value2 done))))))

(defn g
  [t] (loop [todo [[t 0 0 (get-in (vec t) [0 0]) 1]] done []]
        (let [f (first todo)]
          (cond (empty? todo) (apply min (map value2 done))
                (end2? f)     (recur (rest todo) (conj done f))
                :else         (recur (concat (rest todo) (children2 f)) done)))))

(defn g
  [t] (loop [todo [[t 0 0 (get-in (vec t) [0 0]) 1]] done []]
        (let [f (first todo)]
          (cond (not f)                 (apply min (map #(% 3) done))
                (= (f 4) (count (f 0))) (recur (rest todo) (conj done f))
                :else                   (recur (concat (rest todo) (children2 f)) done)))))

(defn g
  [t] (loop [T [[t 0 0 (ffirst t) 1]] D []]
        (let [[t y x tot d :as f] (first T)
              r                   (rest T)]
          (cond (not f)                (apply min (map #(% 3) D))
                (= d (count t))        (recur r (conj D f))
                (<= (dec (count t)) y) (recur r D)
                :else                  (recur (concat r
                                                      (map #(vector (vec t) (inc y) % (+ tot (get-in (vec t) [(inc y) %])) (inc d))
                                                           [x (inc x)]))
                                              D)))))

(def g
  (fn [t] (loop [T [[t 0 0 (ffirst t) 1]] D []]
           (let [[t y x tot d :as f] (first T)
                 r                   (rest T)]
             (cond (not f)                (apply min (map #(% 3) D))
                   (= d (count t))        (recur r (conj D f))
                   (<= (dec (count t)) y) (recur r D)
                   :else                  (recur (concat r    
                                                         (map (fn [k] [(vec t) (inc y) k (+ tot (get-in (vec t) [(inc y) k])) (inc d)])
                                                              [x (inc x)]))
                                                 D))))))

(def g
  (fn [t] (loop [T [[0 0 (ffirst t) 1]] D []]
           (let [[y x s d :as f] (first T)
                 r (rest T)
                 t (vec t)
                 c (count t)]
             (cond (not f) (apply min (map #(% 2) D))
                   (= d c) (recur r (conj D f))
                   (< c y) (recur r D)
                   :else   (recur (concat r    
                                          (map (fn [k] [(inc y) k (+ s (get-in t [(inc y) k])) (inc d)])
                                               [x (inc x)]))
                                  D))))))


;; clj ;;
(fact (g '( 
           [1]
           [2 4]
           [5 1 4]
           [2 3 4 5])) => 7)            ; 1->2->1->3

(fact (g '([3]
             [2 4]
             [1 9 3]
             [9 9 2 4]
             [4 6 6 7 8]
             [5 7 3 5 1 4])) => 20)
   ; 3->4->3->2->7->1
      