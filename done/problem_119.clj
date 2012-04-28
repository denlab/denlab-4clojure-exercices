(ns ^{:doc "Problem: http://www.4clojure.com/problem/119"}
    denlab-4clojure-exercices.problem-119
    (:use [midje.sweet])
        (:use [clojure.pprint :only [pprint]]))

 
;; Difficulty:	Hard
;; Topics:	game


;; As in Problem 73, a tic-tac-toe board is represented by a two
;; dimensional vector. X is represented by :x, O is represented by :o,
;; and empty is represented by :e. Create a function that accepts a
;; game piece and board as arguments, and returns a set (possibly
;; empty) of all valid board placements of the game piece which would
;; result in an immediate win.

;; Board coordinates should be as in calls to get-in. For example, [0
;; 1] is the topmost row, center position.

(defn as-lines
  [[[a b c]
    [d e f]
    [g h i]]] [[a b c] [d e f] [g h i] [a d g] [b e h] [c f i] [a e i] [c e g]])

(fact "as-lines"
  (sort (as-lines [[:a :b :c]
                   [:d :e :f]
                   [:g :h :i]])) => (sort
                                     [[:a :b :c] [:d :e :f] [:g :h :i]
                                      [:a :d :g] [:b :e :h] [:c :f :i]
                                      [:a :e :i] [:c :e :g]]))

(defn winner?
  [p b] (seq (filter #(and (= (first %) p) (apply = %)) (as-lines b))))

(fact
  (winner? :x  [[:o :e :e]
                [:o :x :o]
                [:x :x :x]]) => truthy
  (winner? :o  [[:o :e :e]
                [:o :x :o]
                [:x :x :x]]) => falsey)

(defn g [p b]
  (reduce (fn [r i] (if (and (= (get-in b i) :e)
                            (winner? p (assoc-in b i p)))
                     (conj r i)
                     r))
          #{}
          (for [y [0 1 2] x [0 1 2]] [y x])))

(def g
  (letfn [(as-lines [[[a b c]
                      [d e f]
                      [g h i]]] [[a b c] [d e f] [g h i] [a d g] [b e h] [c f i] [a e i] [c e g]])
          (winner?
            [p b] (seq (filter #(and (= (first %) p) (apply = %)) (as-lines b))))]
    (fn [p b]
      (reduce (fn [r i] (if (and (= (get-in b i) :e)
                                (winner? p (assoc-in b i p)))
                         (conj r i)
                         r))
              #{}
              (for [y [0 1 2] x [0 1 2]] [y x])))))

;; 4clj ;;
 (fact (g :x [[:o :e :e]
              [:o :x :o]
              [:x :x :e]])
   =>  #{[2 2] [0 1] [0 2]})


 (fact (g :x [[:x :o :o]
              [:x :x :e]
              [:e :o :e]])
   =>  #{[2 2] [1 2] [2 0]})
 
 (fact (g :x [[:x :e :x]
              [:o :x :o]
              [:e :o :e]])
   =>  #{[2 2] [0 1] [2 0]})
 
 (fact (g :x [[:x :x :o]
              [:e :e :e]
              [:e :e :e]])
   =>  #{})
 
 (fact (g :o [[:x :x :o]
              [:o :e :o]
              [:x :e :e]])
   =>  #{[2 2] [1 1]})
(comment 
 )
